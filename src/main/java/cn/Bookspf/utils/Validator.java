package cn.Bookspf.utils;

import javax.servlet.http.HttpSession;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DTO.User;

public class Validator {
	HttpSession httpSession;
	
	public Validator(HttpSession httpSession) {
		this.httpSession=httpSession;
	}
	
	//验证登录状态
	public boolean isLogin() {
		return httpSession.getAttribute("userToken")!=null?true:false;
	}
	
	//验证身份(返回身份标识)
	public Integer isIdentity(UserMapper userMapper) {
		Integer admin = userMapper.getAdmin((int) httpSession.getAttribute("userToken"));
		return admin==null?null:admin;
	}
	
	//验证身份(返回页面)
	public String isIdentity(UserMapper userMapper,String page) {
		Integer admin = userMapper.getAdmin((Integer)httpSession.getAttribute("userToken"));
		if(admin==2)return page;
		else if(admin==1)return "manager";
		else return "superManager";
	}

	//验证用户
	public boolean isAccount(int id){
		if(((int) httpSession.getAttribute("userToken"))!=id) return false;
		return true;
	}
	
	//验证是否有相同用户
	public String isSame(UserMapper userMapper,User request) {
		if(request.getUid()!=null) {			
			if(userMapper.findUid(request.getUid())!=null) return "UID已存在";
		}
		if(userMapper.findUsername(request.getUsername())!=null) return "用户名已存在";
		if(userMapper.findEmail(request.getEmail())!=null) return "邮箱已存在";
		return "成功";
	}
}
