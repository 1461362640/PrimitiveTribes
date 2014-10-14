package com.gouxiang.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gouxiang.core.exception.CustomException;

public class UploaderServlet extends HttpServlet {
	protected final transient Log log = LogFactory
			.getLog(UploaderServlet.class);
	private static final long serialVersionUID = 1L;
	String repositoryPath;
	String uploadPath;
	String videoType = Propertie.getVal("videoType");

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Integer schunk = null;// 分割块数
		Integer schunks = null;// 总分割数
		String name = null;// 文件名
		// 生成新文件名
		String newFileName = null;
		BufferedOutputStream outputStream = null;
		//抓图类型
		//默认为空  需要时为max
		String picType=request.getParameter("picType");
		
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				factory.setRepository(new File(repositoryPath));// 设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(50000 * 1024 * 1024);// 设置附近大小
				List<FileItem> items = upload.parseRequest(request);
				// String fileType=null;
				for (FileItem item : items) {
					if (!item.isFormField()) {// 如果是文件类型
						name = item.getName();// 获得文件名
						newFileName = UUID
								.randomUUID()
								.toString()
								.replace("-", "")
								.concat(".")
								.concat("".equals(FilenameUtils
										.getExtension(name)) ? videoType
										: FilenameUtils.getExtension(name));
						log.debug(newFileName);
						if (name != null) {
							String nFname = newFileName;
							if (schunk != null) {
								nFname = schunk + "_" + name;
							}
							File savedFile = new File(uploadPath, nFname);
							item.write(savedFile);
						}
					} else {
						// 判断是否带分割信息
						if (item.getFieldName().equals("chunk")) {
							schunk = Integer.parseInt(item.getString());
						}
						if (item.getFieldName().equals("chunks")) {
							schunks = Integer.parseInt(item.getString());
						}
					}
				}

				if (schunk != null && schunk + 1 == schunks) {
					outputStream = new BufferedOutputStream(
							new FileOutputStream(new File(uploadPath,
									newFileName)));
					// 遍历文件合并
					for (int i = 0; i < schunks; i++) {
						File tempFile = new File(uploadPath, i + "_" + name);
						byte[] bytes = FileUtils.readFileToByteArray(tempFile);
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();
				}
				//
				log.debug("----------启动视频图片抓取进程---------");
				// 实现自动截图
				executeCodecs(
						Propertie.getVal("ffmpegPath"),
						uploadPath + "\\" + newFileName,
						Propertie.getVal("formatVideoPath"),
						uploadPath.replace(
								Propertie.getVal("videoFileName"),
								Propertie.getVal("picFileName"))+"\\"
								+ newFileName.replace("mp4", "jpg"),picType);
				// response.getWriter().write("{\"status\":true,\"newName\":\""+newFileName+",\"path\":"+uploadPath+"\"}");
				response.getWriter().write(
						"{'fileName':'"
								+ newFileName
								+ "','relPath':'."
								+ uploadPath.replaceAll("\\\\", "//").split(
										Propertie.getVal("projectName"))[1]
								+ "','picPath':'"
								+ Propertie.getVal("picPath")
								+ newFileName.replace("mp4", "jpg")
								+ "','status':'true'}");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"status\":false}");
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (Exception e) {
					new CustomException(e);
				}
			}
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		repositoryPath = FileUtils.getTempDirectoryPath();
		uploadPath = config.getServletContext().getRealPath(
				config.getInitParameter("uploadPath"));
		File up = new File(uploadPath);
		if (!up.exists()) {
			up.mkdir();
		}
	}

	/**
	 * 视频转码
	 * 
	 * @param ffmpegPath
	 *            转码工具的存放路径
	 * @param upFilePath
	 *            用于指定要转换格式的文件,要截图的视频源文件
	 * @param codcFilePath
	 *            格式转换后的的文件保存路径
	 * @param mediaPicPath
	 *            截图保存路径
	 * @return
	 * @throws Exception
	 */
	public boolean executeCodecs(String ffmpegPath, String upFilePath,
			String codcFilePath, String mediaPicPath,String picType) throws Exception {
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		List<String> convert = new ArrayList<String>();
		convert.add(ffmpegPath); // 添加转换工具路径
		convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
		convert.add(upFilePath); // 添加要转换格式的视频文件的路径
		convert.add("-qscale"); // 指定转换的质量
		convert.add("6");
		convert.add("-ab"); // 设置音频码率
		convert.add("64");
		convert.add("-ac"); // 设置声道数
		convert.add("2");
		convert.add("-ar"); // 设置声音的采样频率
		convert.add("22050");
		convert.add("-r"); // 设置帧频
		convert.add("24");
		convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
		convert.add(codcFilePath);

		// 创建一个List集合来保存从视频中截取图片的命令
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add(Propertie.getVal("startTime")); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		if("max".equalsIgnoreCase(picType)){
			//抓取大图
			cutpic.add(Propertie.getVal("maxPicSize")); // 添加截取的图片大小为750*1320
		}else if(picType==null||"".equalsIgnoreCase(picType)){
			//默认抓取
			cutpic.add(Propertie.getVal("picSize")); // 添加截取的图片大小为350*240
		}

		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

		boolean mark = true;
		ProcessBuilder builder = new ProcessBuilder();
		try {
			builder.command(convert);
			builder.redirectErrorStream(true);
			builder.start();

			builder.command(cutpic);
			builder.redirectErrorStream(true);
			// 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
			// 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
			builder.start();
		} catch (Exception e) {
			mark = false;
			System.out.println(e);
			new CustomException(e);
		}
		return mark;
	}
}
