package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.model.RO.UserResponse;
import cn.Bookspf.utils.Validator;

@RestController
public class SuperManagerRequest {
	HttpSession httpSession;
	Validator validator;
	UserMapper userMapper;
	
	@Autowired
	public SuperManagerRequest(HttpSession httpSession,UserMapper userMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.userMapper=userMapper;
	}
	
	@PostMapping("/superManager")
	public Response superManager () {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		return new Response(true,"success");
	}
	
	@GetMapping("/getManagerList")
	public Response getManagerList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		return new UserResponse(userMapper.getUserNoPasswordOfAdmin(1));
	}
	
	@PostMapping("/deleteAdmin")
	public Response deleteAdmin(@RequestBody User request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		userMapper.deleteAdmin(request.getUid());
		return new Response(true,"删除成功");
	}
	
	@PostMapping("/addAdmin")
	public Response addAdmin(@RequestBody User request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		String status =validator.isSame(userMapper, request);
		if(!"成功".equals(status)) return new Response(false,status);
		request.setAdmin(1);
		userMapper.insertManager(request);
		return new Response(true,"添加成功");
	}
}
