package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.RO.UserResponse;
import cn.Bookspf.utils.Validator;

@Controller
public class PagesRequest {
	HttpSession httpSession;
	UserMapper userMapper;
	Validator validator;
	
	
	@Autowired
	public PagesRequest(HttpSession httpSession,UserMapper userMapper) {
		this.httpSession=httpSession;
		this.userMapper=userMapper;
		this.validator=new Validator(httpSession);
	}
	
	
	public void setModelUser(Model model) {
		int uid = (int) httpSession.getAttribute("userToken");
		DBUser user=userMapper.getUserOfUid(uid);
		model.addAttribute("user", user);
	}
	
	//主页
	@RequestMapping({"/","/index"})
	public String index (Model model) {
		if(!validator.isLogin())return "index";
		setModelUser(model);
		return validator.isIdentity(userMapper, "index");
	}
	
	//注册页
	@RequestMapping("/register")
	public String register (Model model) {
		if(!validator.isLogin())return "register";
		setModelUser(model);
		return validator.isIdentity(userMapper, "index");
	}
	
	//登录页
	@RequestMapping("/login")
	public String login (Model model) {
		if(!validator.isLogin())return "login";
		setModelUser(model);
		return validator.isIdentity(userMapper, "index");
	}
	
	//超级管理员
	@RequestMapping("/superManager")
	public String superManager(Model model) {
		if(!validator.isLogin())return "login";
		setModelUser(model);
		return validator.isIdentity(userMapper, "superManager");
	}
	
	//图书管理员
	@RequestMapping("/manager")
	public String manager(Model model) {
		if(!validator.isLogin())return "login";
		setModelUser(model);
		return validator.isIdentity(userMapper, "manager");
	}
	
	//账户页面
	@RequestMapping("/account/{id}")
	public String account (@PathVariable("id") Integer id,Model model) {
		if(!validator.isLogin())return "login";
		if(!validator.isAccount(id)) return "404";
		setModelUser(model);
		return validator.isIdentity(userMapper, "account");
	}
	
	//订单页
	@RequestMapping("/orders/{id}")
	public String orders (@PathVariable("id") Integer id,Model model) {
		if(!validator.isLogin())return "login";
		if(!validator.isAccount(id)) return "404";
		setModelUser(model);
		return validator.isIdentity(userMapper, "orders");
	}

	//购物车页
	@RequestMapping("/shopcar/{id}")
	public String shopcar (@PathVariable("id") Integer id,Model model) {
		if(!validator.isLogin())return "login";
		if(!validator.isAccount(id)) return "404";
		setModelUser(model);
		return validator.isIdentity(userMapper, "shopcar");
	}
}
