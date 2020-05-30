package cn.Bookspf.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Generator {
	
	//生成用户ID
	public static int generateUid() {
		String uid =String.valueOf(System.currentTimeMillis()).substring(1, 8);
		return Integer.parseInt(uid);
	}

	//生成订单ID
	public static Long generateId(){
		String uid =String.valueOf(System.currentTimeMillis());
		return  Long.parseLong(uid);
	}

	//生成当前时间String
	public static String generateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date().getTime());
	}
}
