package com.gouxiang.core.util;

public enum TipEnum {
	SUCCESS, FAILE, SAVE, SAVE_FAILE, DELETE, DELETE_FAILE, EDIT, EDIT_FAILE, UPDATE, UPDATE_FAILE, UPLOAD, UPLOAD_FAILE, DOWNLOAD, DOWNLOAD_FAILE, LOGIN, LOGIN_FAILE, CustomTip,ACCEPT,NO_POWER,EXIT;

	public String indexTip() {
		switch (this) {
		case SUCCESS:
			return "{\"success\":\"true\",\"tip\":\"操作成功!\"}";
		case FAILE:
			return "{\"success\":\"false\",\"tip\":\"操作失败!\"}";
		case SAVE:
			return "{\"success\":\"true\",\"tip\":\"保存成功!\"}";
		case SAVE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"保存失败!\"}";
		case DELETE:
			return "{\"success\":\"true\",\"tip\":\"删除成功!\"}";
		case DELETE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"删除失败!\"}";
		case EDIT:
			return "{\"success\":\"true\",\"tip\":\"编辑成功!\"}";
		case EDIT_FAILE:
			return "{\"success\":\"false\",\"tip\":\"编辑失败!\"}";
		case UPDATE:
			return "{\"success\":\"true\",\"tip\":\"更新成功!\"}";
		case UPDATE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"更新失败!\"}";
		case UPLOAD:
			return "{\"success\":\"true\",\"tip\":\"上传成功!\"}";
		case UPLOAD_FAILE:
			return "{\"success\":\"false\",\"tip\":\"上传失败!\"}";
		case DOWNLOAD:
			return "{\"success\":\"true\",\"tip\":\"下载成功!\"}";
		case DOWNLOAD_FAILE:
			return "{\"success\":\"false\",\"tip\":\"下载失败!\"}";
		case LOGIN:
			return "{\"success\":\"true\",\"tip\":\"登录成功!\",\"mIndex\":\"\"}";
		case LOGIN_FAILE:
			return "{\"success\":\"false\",\"tip\":\"登录失败!\"}";
		case ACCEPT:
			return "{\"success\":\"true\",\"tip\":\"通过授权!\"}";
		case NO_POWER:
			return "{\"success\":\"false\",\"tip\":\"没有权限!\"}";
		case EXIT:
			return "{\"success\":\"true\",\"tip\":\"退出系统!\"}";
		default:
			return "{\"success\":\"false\",\"tip\":\"未知操作!\"}";
		}
	}
	
	public String indexTip(Object args) {
		switch (this) {
		case SUCCESS:
			return "{\"success\":\"true\",\"tip\":\"操作成功!\"}";
		case FAILE:
			return "{\"success\":\"false\",\"tip\":\"操作失败!\"}";
		case SAVE:
			return "{\"success\":\"true\",\"tip\":\"保存成功!\"}";
		case SAVE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"保存失败!\"}";
		case DELETE:
			return "{\"success\":\"true\",\"tip\":\"删除成功!\"}";
		case DELETE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"删除失败!\"}";
		case EDIT:
			return "{\"success\":\"true\",\"tip\":\"编辑成功!\"}";
		case EDIT_FAILE:
			return "{\"success\":\"false\",\"tip\":\"编辑失败!\"}";
		case UPDATE:
			return "{\"success\":\"true\",\"tip\":\"更新成功!\"}";
		case UPDATE_FAILE:
			return "{\"success\":\"false\",\"tip\":\"更新失败!\"}";
		case UPLOAD:
			return "{\"success\":\"true\",\"tip\":\"上传成功!\"}";
		case UPLOAD_FAILE:
			return "{\"success\":\"false\",\"tip\":\"上传失败!\"}";
		case DOWNLOAD:
			return "{\"success\":\"true\",\"tip\":\"下载成功!\"}";
		case DOWNLOAD_FAILE:
			return "{\"success\":\"false\",\"tip\":\"下载失败!\"}";
		case LOGIN:
			return "{\"success\":\"true\",\"tip\":\"登录成功!\",\"mIndex\":\""+args+"\"}";
		case LOGIN_FAILE:
			return "{\"success\":\"false\",\"tip\":\"登录失败!\"}";
		case ACCEPT:
			return "{\"success\":\"true\",\"tip\":\"通过授权!\"}";
		case NO_POWER:
			return "{\"success\":\"false\",\"tip\":\"没有权限!\"}";
		default:
			return "{\"success\":\"false\",\"tip\":\"未知操作!\"}";
		}
	}

	public String customTip(String str) {
		return "{\"success\":\"false\",\"tip\":\"" + str + "!\"}";
	}
}
