package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.utils.Validator;

@Controller
public class PagesRequest {
	HttpSession httpSession;
	UserMapper userMapper;
	
	
	@Autowired
	public PagesRequest(HttpSession httpSession,UserMapper userMapper) {
		this.httpSession=httpSession;
		this.userMapper=userMapper;
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
		if(!new Validator(httpSession).isLogin()) return "login";
		int admin = userMapper.getAdmin((int) httpSession.getAttribute("userToken"));
		if(admin==2) return "index";
		if(admin==1) return "manager";
		return "superManager";
	}
	
	//账户页面
	@RequestMapping("/account/{id}")
	public String account (@PathVariable("id") Integer id,Model model) {
		if(!new Validator(httpSession).isLogin()) return "login";
		DBUser user=userMapper.getUserOfUid(id);
		model.addAttribute("uid",user.getUid());
		model.addAttribute("username",user.getUsername());
		model.addAttribute("balance",user.getBalance());
		model.addAttribute("email",user.getEmail());
		model.addAttribute("phone",user.getPhone());
		model.addAttribute("realname",user.getRealname());
		model.addAttribute("address",user.getAddress());
		return "account";
	}
	
	//订单页
	@RequestMapping("/orders")
	public String order () {	
		return new Validator(httpSession).isLogin()?"orders":"login";
	}
}
