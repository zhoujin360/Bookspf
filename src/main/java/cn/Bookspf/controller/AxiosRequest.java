package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.RO.OrderResponse;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;

@RestController
public class AxiosRequest {
	HttpSession httpSession;
	UserMapper userMapper;
	OrderMapper orderMapper;
	
	
	@Autowired
	public AxiosRequest(HttpSession httpSession,UserMapper userMapper,OrderMapper orderMapper) {
		this.httpSession=httpSession;
		this.userMapper=userMapper;
		this.orderMapper=orderMapper;
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
		httpSession.setAttribute("userToken", user.getUid());
		httpSession.setAttribute("username", user.getUsername());
		httpSession.setMaxInactiveInterval(60*60);
		return new Response(true,"登陆成功");
	}
	
	@PostMapping("/orders")
	public Response orders() {
		if(!new Validator(httpSession).isLogin()) return new Response(false,"请登录再操作");
		int uid = (int) httpSession.getAttribute("userToken");
		return new OrderResponse(true,"获取成功",orderMapper.getOrders(uid));
	}
}
