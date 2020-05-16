package cn.Bookspf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesRequest {
	
	//主页
	@RequestMapping({"/","/index"})
	public String index (Model model) {
		
		return "index";
	}
	
	//注册页
	@RequestMapping("/register")
	public String register (Model model) {
		
		return "register";
	}
	
	//登录页
	@RequestMapping("/login")
	public String login (Model model) {
			
		return "login";
	}
}
