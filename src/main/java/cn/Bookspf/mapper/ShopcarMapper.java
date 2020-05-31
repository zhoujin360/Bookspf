package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBShopcar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopcarMapper {

    @Insert("insert into shopcar(carid,uid,bid,booknumber) values(#{carid},#{uid},#{bid},#{booknumber})")
    public void insertShopcar(Long carid,Integer uid,Integer bid,Integer booknumber);

    //根据购物车id查询所有
    @Select("select * from shopcar where carid=#{carid}")
    public ArrayList<DBShopcar> getShopcarOfCarid(Long carid);

    //查询某用户的购物车
    @Select("select * from shopcar where uid=#{uid}")
    public ArrayList<DBShopcar> getShopcarOfUid(Integer uid);

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
