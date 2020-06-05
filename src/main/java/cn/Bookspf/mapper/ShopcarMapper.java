package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBShopcar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopcarMapper {

    //插入记录
    @Insert("insert into shopcar(carid,uid,bid,booknumber) values(#{carid},#{uid},#{bid},#{booknumber})")
    public void insertShopcar(Long carid,Integer uid,Integer bid,Integer booknumber);

    //获得记录的某书数量
    @Select("select booknumber from shopcar where carid=#{carid} and bid=#{bid}")
    public Integer getBooknumber(Long carid,Integer bid);

    //更新购物车
    @Update("update shopcar set booknumber=#{booknumber} where carid=#{carid} and bid=#{bid}")
    public void updateShopcar(Long carid,Integer bid,Integer booknumber);

    //根据购物车id查询所有
    @Select("select * from shopcar where carid=#{carid}")
    public ArrayList<DBShopcar> getShopcarOfCarid(Long carid);

    //查询某用户的购物车
    @Select("select * from shopcar where uid=#{uid}")
    public ArrayList<DBShopcar> getShopcarOfUid(Integer uid);

    //查询某用户购物车id
    @Select("select distinct carid from shopcar where uid=#{uid}")
    public Long getShopcaridOfUid(Integer uid);

    //查询用户购物车是否存在某书
    @Select("select bid from shopcar where carid=#{carid} and bid=#{bid}")
    public Integer findBid(Long carid,Integer bid);

    //删除某用户整个购物车
    @Delete("delete from shopcar where uid=#{uid}")
    public void deleteAllShopcar(Integer uid);

    //删除某用户购物车的某本书
    @Delete("delete from shopcar where uid=#{uid} and bid=#{bid}")
    public void deleteShopcarOfBid(Integer uid,Integer bid);

    //修改购物车书籍数量
    @Update("update shopcar set booknumber=#{booknumber} where uid=#{uid} and bid=#{bid}")
    public void updateBooknumber(Integer uid,Integer bid,Integer booknumber);
}
