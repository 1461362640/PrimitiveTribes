package com.easyui.core.util;

public enum Type {
	T1,T2;
	
	public short value(){
		switch(this){
		case T1:return 1;
		case T2:return 2;
		default : return 0;
		}
	}

}
