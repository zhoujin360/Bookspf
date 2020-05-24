package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.BookMapper;
import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.mapper.SaleMapper;
import cn.Bookspf.mapper.SortMapper;
import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.DTO.Order;
import cn.Bookspf.model.DTO.Sort;
import cn.Bookspf.model.RO.BookResponse;
import cn.Bookspf.model.RO.OrderResponse;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.model.RO.SaleResponse;
import cn.Bookspf.model.RO.SortResponse;
import cn.Bookspf.utils.Operator;
import cn.Bookspf.utils.Validator;

@RestController
public class ManagerRequest {
	HttpSession httpSession;
	Validator validator;
	Operator operator;
	UserMapper userMapper;
	BookMapper bookMapper;
	SortMapper sortMapper;
	OrderMapper orderMapper;
	SaleMapper saleMapper;
	
	@Autowired
	public ManagerRequest(HttpSession httpSession,UserMapper userMapper,BookMapper bookMapper,SortMapper sortMapper,OrderMapper orderMapper,SaleMapper saleMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.operator=new Operator();
		this.userMapper=userMapper;
		this.bookMapper=bookMapper;
		this.sortMapper=sortMapper;
		this.orderMapper=orderMapper;
		this.saleMapper=saleMapper;
	}
	
	@PostMapping("/manager")
	public Response superManager () {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new Response(true,"success");
	}
	
	
	//获取图书信息列表
	@GetMapping("/getBookList")
	public Response getBookList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new BookResponse(bookMapper.getBooks());
	}
	
	//添加图书
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
	
	//获取分类信息列表
	@GetMapping("/getSortList")
	public Response getSortList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new SortResponse(sortMapper.getSorts());
	}
	
	//添加分类
	@PostMapping("/addSort")
	public Response addSort(@RequestBody Sort request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		sortMapper.addSort(request);
		return new Response(true,"添加成功");
	}
	
	//删除分类
	@PostMapping("/deleteSort")
	public Response deleteSort(@RequestBody Sort request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		sortMapper.deleteSort(request.getSortid());
		return new Response(true,"删除成功");
	}
	
	//获取订单信息列表
	@GetMapping("/getOrderList")
	public Response getOrderList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setOrders(operator.getOrders(orderMapper));
		return orderResponse;
	}
	
	//获取订单详情列表
	@PostMapping("/checkOrder")
	public Response checkOrder(@RequestBody Order request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setOrdersinfo(orderMapper.getOrderOfOrderid(request.getOrderid()));
		return orderResponse;
	}
	
	//获取销售信息列表
	@GetMapping("/getSaleList")
	public Response getSaleList() {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		return new SaleResponse(saleMapper.getSales());
	}
}
