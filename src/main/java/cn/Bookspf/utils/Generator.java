package cn.Bookspf.utils;

import java.util.UUID;

public class Generator {
	
	//生成用户ID
	public static int generateUid() {
		String uid =String.valueOf(System.currentTimeMillis()).substring(1, 8);
		return Integer.parseInt(uid);
	}
}
