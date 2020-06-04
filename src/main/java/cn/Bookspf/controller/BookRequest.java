package cn.Bookspf.controller;

import cn.Bookspf.mapper.*;
import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.DO.DBStock;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class BookRequest {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;
    BookMapper bookMapper;
    SortMapper sortMapper;
    OrderMapper orderMapper;
    SaleMapper saleMapper;
    StockMapper stockMapper;
    ShopcarMapper shopcarMapper;

    @Autowired
    public BookRequest(HttpSession httpSession,UserMapper userMapper,BookMapper bookMapper,
                       SortMapper sortMapper,OrderMapper orderMapper,SaleMapper saleMapper,
                       StockMapper stockMapper,ShopcarMapper shopcarMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.sortMapper=sortMapper;
        this.orderMapper=orderMapper;
        this.saleMapper=saleMapper;
        this.stockMapper=stockMapper;
        this.shopcarMapper=shopcarMapper;
    }

    @Transactional
    @PostMapping("/buyBook")
    public Response buyBook (@RequestBody Book request){
        if(!validator.isLogin()) return new Response(false,"请登录再操作");
        if(validator.isIdentity(userMapper)!=2) return new Response(false,"请登录普通用户帐号");

        Integer bid=request.getBid();
        if(bookMapper.getBookNumber(bid)==0) return new Response(false,"抱歉,该图书已售罄");
        Integer uid=(Integer) httpSession.getAttribute("userToken");
        DBBook book=bookMapper.getBook(bid);
        Double balance=userMapper.getBalance(uid);
        Double bookprice=book.getBookprice();
        if(bookprice>balance) return new Response(false,"余额不足,请充值");

        //扣钱
        userMapper.updateBalance(uid, balance-bookprice);

        //减少书籍数量
        bookMapper.updateBookNumber(bid,book.getNumber()-1);

        //出库
        String time=Generator.generateTime();
        DBStock stock = stockMapper.getComeStock(bid);
        stockMapper.updateOutStock(stock.getStockid(),time);
        String isbn=stock.getIsbn();

        //生成订单
        Long orderid = Generator.generateId();
        if(orderMapper.getOrderOfOrderid(orderid).size()!=0) orderid+=123;
        orderMapper.insertOrder(orderid,uid,bid,isbn,bookprice,time);

        //生成销售记录
        Long saleid = Generator.generateId();
        if(saleMapper.getSaleOfSaleid(saleid).size()!=0) saleid+=123;
        System.out.println(bid);
        saleMapper.insertSale(saleid,bid,isbn,time);


        return new Response(true,"购买成功,书本将会加急送到您手中");
    }



    @PostMapping("/addShopcar")
    public Response addShocar(@RequestBody Book request){
        if(!validator.isLogin()) return new Response(false,"请登录再操作");
        if(validator.isIdentity(userMapper)!=2) return new Response(false,"请登录普通用户帐号");

        Integer bid=request.getBid();
        if(bookMapper.getBookNumber(bid)==0) return new Response(false,"抱歉,该图书已售罄");
        Integer uid=(Integer) httpSession.getAttribute("userToken");
        //生成购物车记录
        Long carid = Generator.generateId();
        if(shopcarMapper.getShopcarOfCarid(carid).size()!=0) carid+=123;
        shopcarMapper.insertShopcar(carid,uid,bid,1);


        return new Response(true,"添加成功");
    }
}
