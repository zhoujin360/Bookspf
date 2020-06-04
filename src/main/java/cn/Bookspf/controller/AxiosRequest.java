package cn.Bookspf.controller;

import org.springframework.util.DigestUtils;
import javax.servlet.http.HttpSession;

import cn.Bookspf.model.RO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;

import java.io.IOException;

@RestController
public class AxiosRequest {
	HttpSession httpSession;
	Validator validator;
	UserMapper userMapper;

	
	@Autowired
	public AxiosRequest(HttpSession httpSession,UserMapper userMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.userMapper=userMapper;

	}
	
	@PostMapping("/register")
	public Response register (@RequestBody User request) {
		String captcha= (String) httpSession.getAttribute("captcha");
		if (!captcha.equalsIgnoreCase(request.getCaptcha())) return new Response(false,"验证码错误");
		String status =validator.isSame(userMapper, request);
		if(!"成功".equals(status)) return new Response(false,status);
		Integer uid = Generator.generateUid();
		if(userMapper.findUid(uid)!=null) uid+=1;	
		String password = request.getPassword();
		request.setUid(uid);
		request.setAdmin(2);
		request.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		userMapper.insertUser(request);
		return new Response(true,"注册成功");
	} 
	
	@PostMapping("/login")
	public Response login (@RequestBody User request) {
		String captcha= (String) httpSession.getAttribute("captcha");
		if (!captcha.equalsIgnoreCase(request.getCaptcha())) return new Response(false,"验证码错误");
		String username = request.getUsername();
		String password = request.getPassword();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		DBUser user=userMapper.getUserOfUsername(username);
		if(user==null) return new Response(false,"用户名错误");
		String userPassword = user.getPassword();
		if(!password.equals(userPassword)) return new Response(false,"密码错误");
		httpSession.setAttribute("userToken", user.getUid());
		httpSession.setAttribute("username", user.getUsername());
		httpSession.setMaxInactiveInterval(60*60);
		return new Response(true,"登陆成功");
	}

	@PostMapping("/changeCode")
	public String changeCode(){
		try {
			CaptchaResponse captcha =  captcha = Generator.generateCaptchaImg();
			httpSession.setAttribute("captcha",captcha.getCaptcha());
			return captcha.getImgJSON();
		} catch (IOException e) {}
		return null;
	}

	@PostMapping("/logout")
	public void logout () {
		httpSession.removeAttribute("userToken");
		httpSession.invalidate();
	}
	
	
}
