package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.Bookspf.utils.Validator;

@Controller
public class PagesRequest {
	HttpSession httpSession;
	
	
	@Autowired
	public PagesRequest(HttpSession httpSession) {
		this.httpSession=httpSession;
	}
	
	
	//主页
	@RequestMapping({"/","/index"})
	public String index () {
		return "index";
	}
	
	//注册页
	@RequestMapping("/register")
	public String register () {
		return new Validator(httpSession).isLogin()?"index":"register";
	}
	
	//登录页
	@RequestMapping("/login")
	public String login () {
		return new Validator(httpSession).isLogin()?"index":"login";
	}
}
