package com.gouxiang.common.entity;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013
 * Date:			2014-1-10
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		操作系统信息
 * </pre>
 **/

public class OS {
	private String osName;// 系统名称
	private String osVersion;// 系统版本
	private String osArch;// 系统架构
	private String jdkVersion;// jdk环境
	private String projectPath;// 用户的当前工作目录
	private String userHome;// 用户的主目录
	private String javaVendor;// Java 运行时环境供应商
	private String javaVendorUrl;// Java 供应商的 URL
	private String javaHome;// Java 安装目录
	private String loadLibPath;// 加载库时搜索的路径列表
	private String tmpFile;// 默认的临时文件路径

	public OS() {
		super();
	}

	public OS(String osName, String osVersion, String osArch,
			String jdkVersion, String projectPath, String userHome,
			String javaVendor, String javaVendorUrl, String javaHome,
			String loadLibPath, String tmpFile) {
		super();
		this.osName = osName;
		this.osVersion = osVersion;
		this.osArch = osArch;
		this.jdkVersion = jdkVersion;
		this.projectPath = projectPath;
		this.userHome = userHome;
		this.javaVendor = javaVendor;
		this.javaVendorUrl = javaVendorUrl;
		this.javaHome = javaHome;
		this.loadLibPath = loadLibPath;
		this.tmpFile = tmpFile;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getJdkVersion() {
		return jdkVersion;
	}

	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getUserHome() {
		return userHome;
	}

	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}

	public String getJavaVendor() {
		return javaVendor;
	}

	public void setJavaVendor(String javaVendor) {
		this.javaVendor = javaVendor;
	}

	public String getJavaVendorUrl() {
		return javaVendorUrl;
	}

	public void setJavaVendorUrl(String javaVendorUrl) {
		this.javaVendorUrl = javaVendorUrl;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public String getLoadLibPath() {
		return loadLibPath;
	}

	public void setLoadLibPath(String loadLibPath) {
		this.loadLibPath = loadLibPath;
	}

	public String getTmpFile() {
		return tmpFile;
	}

	public void setTmpFile(String tmpFile) {
		this.tmpFile = tmpFile;
	}

}
