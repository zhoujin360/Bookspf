package cn.Bookspf.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.UserMapper;
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
		userMapper.deleteUser(request.getUid());
		return new Response(true,"删除成功");
	}
	
	@PostMapping("/addAdmin")
	public Response addAdmin(@RequestBody User request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		String status =validator.isSame(userMapper, request);
		String password = request.getPassword();
		if(!"成功".equals(status)) return new Response(false,status);
		request.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		request.setAdmin(1);
		userMapper.insertManager(request);
		return new Response(true,"添加成功");
	}


	@PostMapping("/alterAdmin")
	@Transactional
	public Response alterAdmin(@RequestBody User request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=0) return new Response(false,"请登录超级管理员帐号");
		Integer uid = request.getUid();
		String username = request.getUsername();
		String email = request.getEmail();
		String password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
		if(userMapper.findUsername(username)!=null) return new Response(false,"用户名已存在");
		if(userMapper.findEmail(email)!=null) return new Response(false,"邮箱已存在");

		userMapper.updateUsername(uid,username);
		userMapper.updatePassword(uid,password);
		userMapper.updateEmail(uid,email);
		userMapper.updateRealname(uid,request.getRealname());

		return new Response(true,"添加成功");
	}
}
