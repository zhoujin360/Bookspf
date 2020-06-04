package cn.Bookspf.controller;




import javax.servlet.http.HttpSession;
import javax.validation.Validation;

import cn.Bookspf.model.RO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.Bookspf.mapper.BookMapper;
import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.mapper.SaleMapper;
import cn.Bookspf.mapper.SortMapper;
import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.DTO.Sort;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;

import java.io.IOException;

@RestController
public class AxiosRequest {
	HttpSession httpSession;
	Validator validator;
	UserMapper userMapper;
	BookMapper bookMapper;
	SortMapper sortMapper;
	OrderMapper orderMapper;
	SaleMapper saleMapper;
	
	@Autowired
	public AxiosRequest(HttpSession httpSession,UserMapper userMapper,BookMapper bookMapper,SortMapper sortMapper,OrderMapper orderMapper,SaleMapper saleMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.userMapper=userMapper;
		this.bookMapper=bookMapper;
		this.sortMapper=sortMapper;
		this.orderMapper=orderMapper;
		this.saleMapper=saleMapper;
	}
	
	@PostMapping("/register")
	public Response register (@RequestBody User request) {
		String captcha= (String) httpSession.getAttribute("captcha");
		if(!captcha.equals(request.getCaptcha())) return new Response(false,"验证码错误");
		String status =validator.isSame(userMapper, request);
		if(!"成功".equals(status)) return new Response(false,status);
		Integer uid = Generator.generateUid();
		if(userMapper.findUid(uid)!=null) uid+=1;
		request.setUid(uid);
		request.setAdmin(2);
		userMapper.insertUser(request);
		return new Response(true,"注册成功");
	} 
	
	@PostMapping("/login")
	public Response login (@RequestBody User request) {
		String captcha= (String) httpSession.getAttribute("captcha");
		if (!captcha.equalsIgnoreCase(request.getCaptcha())) return new Response(false,"验证码错误");
		String username = request.getUsername();
		String password = request.getPassword();
		DBUser user=userMapper.getUserOfUsername(username);
		if(user==null) return new Response(false,"用户名错误");
		if(!password.equals(user.getPassword())) return new Response(false,"密码错误");
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
