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

import cn.Bookspf.mapper.BookMapper;
import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.mapper.SortMapper;
import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.DTO.Sort;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.RO.BookResponse;
import cn.Bookspf.model.RO.OrderResponse;
import cn.Bookspf.model.RO.Response;
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
	
	@Autowired
	public AxiosRequest(HttpSession httpSession,UserMapper userMapper,BookMapper bookMapper,SortMapper sortMapper,OrderMapper orderMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.userMapper=userMapper;
		this.bookMapper=bookMapper;
		this.sortMapper=sortMapper;
		this.orderMapper=orderMapper;
	}
	
	@PostMapping("/register")
	public Response register (@RequestBody User request) {
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
	
	@PostMapping("/logout")
	public void logout () {
		httpSession.removeAttribute("userToken");
		httpSession.invalidate();
	}
	
	@PostMapping("/superManager")
	public Response superManager () {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		DBUser user=userMapper.getUserOfUid((int) httpSession.getAttribute("userToken"));
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
	
	@GetMapping("/getBookList")
	public Response getBookList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new BookResponse(bookMapper.getBooks());
	}
	
	@PostMapping("/addBook")
	public Response addBook(@RequestBody Book request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		request.setHot(0);
		request.setNumber(0);
		if(request.getDescription()!=null)bookMapper.addBook(request);
		else bookMapper.addBookNoDescription(request);
		return new Response(true,"添加成功");
	}
	
	@GetMapping("/getSortList")
	public Response getSortList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new SortResponse(sortMapper.getSorts());
	}
	
	@PostMapping("/addSort")
	public Response addSort(@RequestBody Sort request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		sortMapper.addSort(request);
		return new Response(true,"添加成功");
	}
	
	@PostMapping("/deleteSort")
	public Response deleteSort(@RequestBody Sort request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		sortMapper.deleteSort(request.getSortid());
		return new Response(true,"删除成功");
	}
	
	
	@PostMapping("/orders")
	public Response orders() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		int uid = (int) httpSession.getAttribute("userToken");
		return new OrderResponse(true,"获取成功",orderMapper.getOrders(uid));
	}
}
