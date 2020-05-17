package cn.Bookspf.utils;

import javax.servlet.http.HttpSession;

public class Validator {
	HttpSession httpSession;
	
	public Validator(HttpSession httpSession) {
		this.httpSession=httpSession;
	}
	
	//验证登录状态
	public boolean isLogin() {
		return httpSession.getAttribute("userToken")!=null?true:false;
	}
}
