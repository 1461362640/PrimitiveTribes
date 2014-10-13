package com.easyui.core.exception;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, www.hzxilei.com
 * Filename:		com.xilei.edu.core.exception.CustomException.java
 * Class:			CustomException
 * Date:			2013-12-13
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		自定义异�?
 * </pre>
 **/
@SuppressWarnings("serial")
public class CustomException extends Exception {

	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(Exception exception) {
		super();
		System.out.println("引发异常信息 {原因:" + exception.getCause() + ",信息:"
				+ exception.getMessage() + ",堆栈信息:" + exception.getStackTrace()
				+ "}");
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("引发异常信息 {原因:" + cause + ",信息:" + message + "}");
	}

	public CustomException(String message) {
		super(message);
		System.out.println("引发异常信息 {信息:" + message + "}");
	}

	public CustomException(Throwable cause) {
		super(cause);
		System.out.println("引发异常信息 {原因:" + cause + "}");
	}

}
