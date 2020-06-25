package cn.Bookspf.controller;

import javax.servlet.http.HttpSession;

import cn.Bookspf.mapper.*;
import cn.Bookspf.model.DO.*;
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

	//修改图书
	@PostMapping("/alterBook")
	public Response alterBook(@RequestParam("isUpImg") Boolean isUpImg,
							@RequestParam("file") MultipartFile file,
							@RequestParam("bid") Integer bid,
							@RequestParam("bookname") String bookname,
							@RequestParam("sortid") Integer sortid,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("bookprice") Double bookprice,
							@RequestParam("added") Integer added) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		if(isUpImg){
			if(file==null) return new Response(false,"上传图片失败");
			try{
				if(!operator.uploadBookimg(file,bid))return new Response(false,"上传图片失败");
			}catch (IOException e){ return new Response(false,"上传图片失败");}
		}
		DBBook book = bookMapper.getBook(bid);
		Book request =new Book(bid,bookname,book.getHot(),sortid,author,description,bookprice, added,book.getNumber());
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
		if(sortMapper.findSortid(request.getSortid())!=null) return new Response(false,"分类ID重复");
		if(sortMapper.findSortname(request.getSortname())!=null) return new Response(false,"分类名字重复");
		sortMapper.addSort(request);
		return new Response(true,"添加成功");
	}

	//修改分类
	@PostMapping("/alterSort")
	public Response alterSort(@RequestBody Sort request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		if(sortMapper.findSortname(request.getSortname())!=null) return new Response(false,"分类名字重复");
		sortMapper.alterSort(request);
		return new Response(true,"修改成功");
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
			ArrayList<Double> price = orderMapper.getOrderPrice();
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==1){
			long orderid=Obj.getLong("str");
			ArrayList<DBOrder> order = orderMapper.getOrderOfOrderid(orderid);
			ArrayList<Double> price = orderMapper.getOrderPriceOfOrderid(orderid);
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==2){
			Integer uid=Obj.getInteger("str");
			ArrayList<DBOrder> order = orderMapper.getOrderOfUid(uid);
			ArrayList<Double> price = orderMapper.getOrderPriceOfUid(uid);
			orderResponse.setOrders(operator.getOrders(order,price));
		}else if(index==3){
			String createtime=Obj.getString("str");
			createtime=createtime.replace("T"," ");
			ArrayList<DBOrder> order = orderMapper.getOrderOfCreatetime(createtime);
			ArrayList<Double> price = orderMapper.getOrderPriceOfCreatetime(createtime);
			orderResponse.setOrders(operator.getOrders(order,price));
		}
		return orderResponse;
	}
	
	//获取订单详情列表
	@PostMapping("/checkOrderOfAdmin")
	public Response checkOrderOfAdmin(@RequestBody Order request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		Long orderid=request.getOrderid();
		ArrayList<DBOrder> orders = orderMapper.getOrderinfoOfOrderid(orderid);
		ArrayList<Integer> bids =  orderMapper.getBidsOfOrderid(orderid);
		for(int i=0;i<orders.size();i++){
			String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
			orders.get(i).setBookname(bookname);
		}
		return new OrderResponse(orders);
	}

	
	//获取销售信息列表
	@PostMapping("/getSaleList")
	public Response getSaleList(@RequestBody String request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		JSONObject Obj=JSONObject.parseObject(request);
		int index = Obj.getInteger("index");
		ArrayList<DBSale> sales=null;
		if(index==0){
			sales=saleMapper.getSalesinfo();
		}else if(index==1){
			long saleid=Obj.getLong("str");
			sales=saleMapper.getSalesinfoOfSaleid(saleid);
		}else if(index==2){
			String isbn=Obj.getString("str");
			sales=saleMapper.getSalesinfoOfISBN(isbn);
		}else if(index==3){
			String saletime=Obj.getString("str");
			saletime=saletime.replace("T"," ");
			sales=saleMapper.getSalesinfoOfSaletime(saletime);
		}
		for (int i=0;i<sales.size();i++){
			String bookname = bookMapper.getBooknameOfAdmin(sales.get(i).getBid());
			sales.get(i).setBookname(bookname);
		}
		return new SaleResponse(sales);
	}

	//获取销售详情列表
	@PostMapping("/checkSale")
	public Response checkSale(@RequestBody Sale request) {
		if(!validator.isLogin()) return new Response(false,"请登录再操作");
		if(validator.isIdentity(userMapper)!=1) return new Response(false,"请登录图书管理员帐号");
		Long saleid = request.getSaleid();
		ArrayList<DBSale> sales = saleMapper.getSaleOfSaleid(saleid);
		for (int i=0;i<sales.size();i++){
			String bookname = bookMapper.getBooknameOfAdmin(sales.get(i).getBid());
			sales.get(i).setBookname(bookname);
		}
		return new SaleResponse(sales);
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
		Long purchaseid = request.getPurchaseid();
		ArrayList<DBPurchase> purchases = purchaseMapper.getPurchasesinfo(purchaseid);
		ArrayList<Integer> bids = purchaseMapper.getBidsOfPurchase(purchaseid);
		for(int i=0;i<purchases.size();i++){
			String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
			purchases.get(i).setBookname(bookname);
		}
		return new PurchaseResponse(purchases);
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
		Integer number=bookMapper.getBookNumberOfAdmin(request.getBid());
		if(number==null)return new Response(false,"请先添加对应的图书,再进行操作");

		//修改书本数量
		bookMapper.updateBookNumberOfAdmin(request.getBid(),number+1);

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
		Long stockid = request.getStockid();
		ArrayList<DBStock> stocks = stockMapper.getStockinfoOfStockid(stockid);
		ArrayList<Integer> bids = stockMapper.getBidsOfStockid(stockid);
		for(int i=0;i<stocks.size();i++){
			String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
			stocks.get(i).setBookname(bookname);
		}
		return new StockResponse(stocks);
	}
}
