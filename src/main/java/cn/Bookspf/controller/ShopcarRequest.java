package cn.Bookspf.controller;

import cn.Bookspf.mapper.*;
import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.DO.DBShopcar;
import cn.Bookspf.model.DO.DBStock;
import cn.Bookspf.model.DTO.Book;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.model.RO.ShopcarResponse;
import cn.Bookspf.utils.Generator;
import cn.Bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class ShopcarRequest {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;
    BookMapper bookMapper;
    ShopcarMapper shopcarMapper;
    OrderMapper orderMapper;
    SaleMapper saleMapper;
    StockMapper stockMapper;

    @Autowired
    public ShopcarRequest(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper,
                          ShopcarMapper shopcarMapper, OrderMapper orderMapper, SaleMapper saleMapper,
                          StockMapper stockMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.shopcarMapper=shopcarMapper;
        this.orderMapper=orderMapper;
        this.saleMapper=saleMapper;
        this.stockMapper=stockMapper;
    }

    @GetMapping("/getShopcarList")
    public Response getShopcarList(){
        if(!validator.isLogin()) return new Response(false,"请登录再操作");
        if(validator.isIdentity(userMapper)!=2) return new Response(false,"请登录普通用户帐号");
        Integer uid = (Integer) httpSession.getAttribute("userToken");
        return new ShopcarResponse(shopcarMapper.getShopcarOfUid(uid));
    }


    //结算购物车
    @Transactional
    @PostMapping("/settlement")
    public Response settlement(){
        if(!validator.isLogin()) return new Response(false,"请登录再操作");
        if(validator.isIdentity(userMapper)!=2) return new Response(false,"请登录普通用户帐号");
        Integer uid = (Integer) httpSession.getAttribute("userToken");

        ArrayList<DBShopcar> shopcars = shopcarMapper.getShopcarOfUid(uid);
        if(shopcars==null) return new Response(false,"购物车暂时还空无一物");

        Double total=0.0;
        for(int i=0;i<shopcars.size();i++){
           total+=bookMapper.getBookprice(shopcars.get(i).getBid())*shopcars.get(i).getBooknumber();
        }
        Double balance = userMapper.getBalance(uid);
        if(balance<total) return new Response(false,"余额不足,请充值");
        userMapper.updateBalance(uid, balance-total);

        //生成订单
        Long orderid = Generator.generateId();
        if(orderMapper.getOrderOfOrderid(orderid).size()!=0) orderid+=123;

        //生成销售记录
        Long saleid = Generator.generateId();
        if(saleMapper.getSaleOfSaleid(saleid).size()!=0) saleid+=123;

        for(int i=0;i<shopcars.size();i++){
            Integer bid = shopcars.get(i).getBid();
            Integer booknumber = shopcars.get(i).getBooknumber();
            DBBook book = bookMapper.getBook(bid);
            Double bookprice=book.getBookprice();
            if(booknumber>book.getNumber()) return new Response(false,"该书库存不足"+booknumber+"本,如需购买请联系客服");

            bookMapper.updateBookNumber(bid,book.getNumber()-booknumber);

            String time= Generator.generateTime();
            for(int j=0;j<booknumber;j++){
                DBStock stock = stockMapper.getComeStock(bid);
                stockMapper.updateOutStock(stock.getStockid(),time);
                String isbn=stock.getIsbn();

                orderMapper.insertOrder(orderid,uid,bid,isbn,bookprice,time);

                saleMapper.insertSale(saleid,bid,isbn,time);
            }
        }

        shopcarMapper.deleteAllShopcar(uid);

        System.out.println(total);
        return new Response(true,"购买成功,书本将会加急送到您手中");
    }


    @PostMapping("/deleteShopcarOfBid")
    public Response deleteShopcarOfBid(@RequestBody Book book){
        if(!validator.isLogin()) return new Response(false,"请登录再操作");
        if(validator.isIdentity(userMapper)!=2) return new Response(false,"请登录普通用户帐号");
        Integer uid = (Integer) httpSession.getAttribute("userToken");
        shopcarMapper.deleteShopcarOfBid(uid,book.getBid());
        return new Response(true,"删除成功");
    }
}
