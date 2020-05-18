package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.utils.Generator;

@RestController
public class AxiosRequest {
	HttpSession httpsession;
	UserMapper userMapper;
	
	
	@Autowired
	public AxiosRequest(HttpSession httpSession,UserMapper userMapper) {
		this.httpsession=httpSession;
		this.userMapper=userMapper;
	}
	
	@PostMapping("/register")
	public Response register (@RequestBody User request) {
		String username=request.getUsername();
		String email = request.getEmail();
		if(userMapper.getUserOfUsername(username) != null) return new Response(false,"用户名已存在");
		if(userMapper.getUserOfEmail(email)!=null) return new Response(false,"邮箱已注册");
		Integer uid = Generator.generateUid();
		if(userMapper.findUid(uid)!=null) uid+=1;
		request.setUid(uid);
		userMapper.insertUser(request);
		return new Response(true,"注册成功");
	}
	
	@PostMapping("/login")
	public Response login (@RequestBody User request) {
		String username = request.getUsername();
		String password = request.getPassword();
		DBUser user=userMapper.getUserOfUsername(username);
		if(user==null) return new Response(false,"用户名错误");
		if(!password.equals(user.getPassword())) return new Response(false,"密码错误");
		httpsession.setAttribute("userToken", user.getUid());
		httpsession.setAttribute("username", user.getUsername());
		httpsession.setMaxInactiveInterval(60*60);
		return new Response(true,"登陆成功");
	}
	
	@PostMapping("/login123")
	public Response login123 (@RequestBody User request) {
		
		
		
		return new Response(true,"登陆成功");
	}
}
