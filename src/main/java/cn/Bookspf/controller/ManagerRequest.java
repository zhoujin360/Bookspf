package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import cn.Bookspf.mapper.*;
import cn.Bookspf.model.DO.DBOrder;
import cn.Bookspf.model.DO.DBPurchase;
import cn.Bookspf.model.DTO.*;
import cn.Bookspf.model.RO.*;
import cn.Bookspf.utils.Generator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import cn.Bookspf.utils.Operator;
import cn.Bookspf.utils.Validator;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	StockMapper stockMapper;
	PurchaseMapper purchaseMapper;
	
	@Autowired
	public ManagerRequest(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper,
						  SortMapper sortMapper, OrderMapper orderMapper, SaleMapper saleMapper,
						  StockMapper stockMapper,PurchaseMapper purchaseMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.operator=new Operator();
		this.userMapper=userMapper;
		this.bookMapper=bookMapper;
		this.sortMapper=sortMapper;
		this.orderMapper=orderMapper;
		this.saleMapper=saleMapper;
		this.stockMapper=stockMapper;
		this.purchaseMapper=purchaseMapper;
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
	public Response addBook(@RequestParam("file") MultipartFile file,
							@RequestParam("bid") Integer bid,
							@RequestParam("bookname") String bookname,
							@RequestParam("sortid") Integer sortid,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("bookprice") Double bookprice,
							@RequestParam("added") Integer added) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		if(file==null) return new Response(false,"上传失败");
		if (bookMapper.findBid(bid)!=null)return new Response(false,"图书编号已存在");
		try{
			if(!operator.uploadBookimg(file,bid))return new Response(false,"上传图片失败");
		}catch (IOException e){}
		Book request =new Book(bid,bookname,0,sortid,author,description,bookprice, added,0);
		bookMapper.addBook(request);
		return new Response(true,"添加成功");
	}

	//添加图书
	@PostMapping("/alterBook")
	public Response alterBook(@RequestParam("file") MultipartFile file,
							@RequestParam("bid") Integer bid,
							@RequestParam("bookname") String bookname,
							@RequestParam("sortid") Integer sortid,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("bookprice") Double bookprice,
							@RequestParam("added") Integer added) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		if(file==null) return new Response(false,"上传图片失败");
		try{
			if(!operator.uploadBookimg(file,bid))return new Response(false,"上传图片失败");
		}catch (IOException e){ return new Response(false,"上传图片失败");}
		Book request =new Book(bid,bookname,0,sortid,author,description,bookprice, added,0);
		bookMapper.alterBook(request);
		return new Response(true,"修改成功");
	}

	//删除图书
	@PostMapping("/deleteBook")
	public Response deleteBook(@RequestBody Book request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		Integer bid=request.getBid();
		try { operator.deleteBookimg(bid); } catch (IOException e){ return new Response(true,"删除失败"); }
		bookMapper.deleteBook(bid);
		return new Response(true,"删除成功");
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
	@PostMapping("/getOrderListOfAdmin")
	public Response getOrderListOfAdmin(@RequestBody String request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		OrderResponse orderResponse=new OrderResponse();
		JSONObject Obj=JSONObject.parseObject(request);
		int index = Obj.getInteger("index");
		if(index==0){
			ArrayList<DBOrder> order = orderMapper.getOrders();
			ArrayList<Integer> price = orderMapper.getOrderPrice();
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==1){
			long orderid=Obj.getLong("str");
			ArrayList<DBOrder> order = orderMapper.getOrderOfOrderid(orderid);
			ArrayList<Integer> price = orderMapper.getOrderPriceOfOrderid(orderid);
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==2){
			Integer uid=Obj.getInteger("str");
			ArrayList<DBOrder> order = orderMapper.getOrderOfUid(uid);
			ArrayList<Integer> price = orderMapper.getOrderPriceOfUid(uid);
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==3){
			String createtime=Obj.getString("str");
			createtime=createtime.replace("T"," ");
			ArrayList<DBOrder> order = orderMapper.getOrderOfCreatetime(createtime);
			ArrayList<Integer> price = orderMapper.getOrderPriceOfCreatetime(createtime);
			orderResponse.setOrders(operator.getOrders(order,price));
		}
		return orderResponse;
	}
	
	//获取订单详情列表
	@PostMapping("/checkOrderOfAdmin")
	public Response checkOrderOfAdmin(@RequestBody Order request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setOrdersinfo(orderMapper.getOrderinfoOfOrderid(request.getOrderid()));
		return orderResponse;
	}



	
	//获取销售信息列表
	@PostMapping("/getSaleList")
	public Response getSaleList(@RequestBody String request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		SaleResponse saleResponse=new SaleResponse();
		JSONObject Obj=JSONObject.parseObject(request);
		int index = Obj.getInteger("index");
		if(index==0){
			saleResponse.setSales(saleMapper.getSales());
		}else if(index==1){
			long saleid=Obj.getLong("str");
			saleResponse.setSales(saleMapper.getSaleOfSaleid(saleid));
		}else if(index==2){
			String isbn=Obj.getString("str");
			saleResponse.setSales(saleMapper.getSaleOfISBN(isbn));
		}else if(index==3){
			String saletime=Obj.getString("str");
			saletime=saletime.replace("T"," ");
			saleResponse.setSales(saleMapper.getSaleOfSaletime(saletime));
		}
		return saleResponse;
	}

	//获取进货信息列表
	@PostMapping("/getPurchaseList")
	public Response getPurchaseList(@RequestBody String request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		PurchaseResponse purchaseResponse=new PurchaseResponse();
		JSONObject Obj=JSONObject.parseObject(request);
		int index = Obj.getInteger("index");
		if(index==0){
			purchaseResponse.setPurchases(purchaseMapper.getPurchases());
		}else if(index==1){
			long stockid=Obj.getLong("str");
			purchaseResponse.setPurchases(purchaseMapper.getPurchaseOfPurchaseid(stockid));
		}else if(index==2){
			String purchasetime=Obj.getString("str");
			purchasetime=purchasetime.replace("T"," ");
			purchaseResponse.setPurchases(purchaseMapper.getPurchaseOfPurchasetime(purchasetime));
		}
		return purchaseResponse;
	}

	//获取进货详情列表
	@PostMapping("/checkPurchase")
	public Response checkPurchase(@RequestBody Purchase request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		PurchaseResponse purchaseResponse=new PurchaseResponse();
		purchaseResponse.setPurchasesinfo(purchaseMapper.getPurchasesinfo(request.getPurchaseid()));
		return purchaseResponse;
	}

	//添加进货记录
	@PostMapping("/addPurchase")
	@Transactional
	public Response addPurchase(@RequestBody Purchase request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		request.setOperator((int)httpSession.getAttribute("userToken"));
		request.setPurchasetime(request.getPurchasetime().replace("T"," "));
		if(purchaseMapper.findPurchaseid(request.getPurchaseid()).size()!=0) return new Response(false,"进货ID重复");
		if(purchaseMapper.findIsbn(request.getIsbn())!=null) return new Response(false,"ISBN重复");
		Integer number=bookMapper.getBookNumber(request.getBid());
		if(number==null)return new Response(false,"请先添加对应的图书,再进行操作");

		//修改书本数量
		bookMapper.updateBookNumber(request.getBid(),number+1);

		//插入进货记录
		purchaseMapper.insertPurchase(request);

		//插入库存记录
		Long stockid = Generator.generateId();
		String time = Generator.generateTime();
		if(stockMapper.getStockinfoOfStockid(stockid).size()!=0) stockid+=123;
		stockMapper.insertComeStock(stockid,request.getBid(),request.getIsbn(),time);


		return new Response(true,"添加成功");
	}



	//获取库存信息列表
	@PostMapping("/getStockList")
	public Response getStockList(@RequestBody String request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		StockResponse stockResponse=new StockResponse();
		JSONObject Obj=JSONObject.parseObject(request);
		int index = Obj.getInteger("index");
		if(index==0){
			stockResponse.setStocks(stockMapper.getStocks());
		}else if(index==1){
			long stockid=Obj.getLong("str");
			stockResponse.setStocks(stockMapper.getStockOfStockid(stockid));
		}else if(index==2){
			String stocktime=Obj.getString("str");
			stocktime=stocktime.replace("T"," ");
			stockResponse.setStocks(stockMapper.getStockOfStocktime(stocktime));
		}
		return stockResponse;
	}

	//获取库存详情列表
	@PostMapping("/checkStock")
	public Response checkStock(@RequestBody Stock request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		StockResponse stockResponse=new StockResponse();
		stockResponse.setStocks(stockMapper.getStockinfoOfStockid(request.getStockid()));
		return stockResponse;
	}
}
