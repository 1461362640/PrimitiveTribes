package com.gouxiang.common.pc.server.json;

import java.math.BigInteger;
import java.util.List;

import com.gouxiang.common.entity.Video;

public class JSONUtil {
	private final static String START = "[";
	private final static String END = "]";

	public static String objectToStr(List<Video> videos, BigInteger total) {
		StringBuffer sBuffer = new StringBuffer();
		int index = 0;
		sBuffer.append(START);
		for (Video video : videos) {
			if (index == videos.size()) {
				sBuffer.append("{'id':'").append(video.getId())
						.append("',").append("'name':'")
						.append(video.getName()).append("',")
						.append("'relName':'").append(video.getRelName())
						.append("',").append("'imageUrl':'")
						.append(video.getImageUrl()).append("',")
						.append("'videoUrl':'").append(video.getVideoUrl())
						.append("',").append("'type':")
						.append(video.getType()).append(",")
						.append("'describes':'")
						.append(video.getDescribes()).append("',")
						.append("'uploadDate':")
						.append(video.getUploadDate()).append(",")
						.append("'editDate':").append(video.getEditDate())
						.append(",").append("'status':")
						.append(video.getStatus()).append("}");
			} else {
				sBuffer.append("{'id':'").append(video.getId())
						.append("',").append("'name':'")
						.append(video.getName()).append("',")
						.append("'relName':'").append(video.getRelName())
						.append("',").append("'imageUrl':'")
						.append(video.getImageUrl()).append("',")
						.append("'videoUrl':'").append(video.getVideoUrl())
						.append("',").append("'type':")
						.append(video.getType()).append(",")
						.append("'describes':'")
						.append(video.getDescribes()).append("',")
						.append("'uploadDate':")
						.append(video.getUploadDate()).append(",")
						.append("'editDate':").append(video.getEditDate())
						.append(",").append("'status':")
						.append(video.getStatus()).append("}#");
			}
			index++;
		}
		sBuffer.append(END);
		return "{'total':" + total + ",'rows':" + sBuffer.toString();
	}

}
