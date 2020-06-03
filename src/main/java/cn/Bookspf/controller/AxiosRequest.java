package cn.Bookspf.controller;




import org.springframework.util.DigestUtils;
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

import cn.Bookspf.mapper.BookMapper;
import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.mapper.SaleMapper;
import cn.Bookspf.mapper.SortMapper;
import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.DTO.Sort;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.RO.BookResponse;
import cn.Bookspf.model.RO.OrderResponse;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.model.RO.SaleResponse;
import cn.Bookspf.model.RO.SortResponse;
import cn.Bookspf.model.RO.UserResponse;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;

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
		String username = request.getUsername();
		String password = request.getPassword();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		DBUser user=userMapper.getUserOfUsername(username);
		String userPassword = user.getPassword();
		if(user==null) return new Response(false,"用户名错误");
//		if(!password.equals(user.getPassword())) return new Response(false,"密码错误");
		if(!password.equals(userPassword)) return new Response(false,"密码错误");
		httpSession.setAttribute("userToken", user.getUid());
		httpSession.setAttribute("username", user.getUsername());
		httpSession.setMaxInactiveInterval(60*60);
		return new Response(true,"登陆成功");
	}
	
	@PostMapping("/logout")
	public void logout () {
		httpSession.removeAttribute("userToken");
		httpSession.invalidate();
	}
	
	
}
