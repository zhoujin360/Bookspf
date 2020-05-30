package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBShopcar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopcarMapper {

    @Insert("insert into shopcar values(carid=carid,uid=uid,bid=bid,booknumber)")
    public void insertShopcar(Long carid,Integer uid,Integer bid,Integer booknumber);

    @Select("select * from shopcar where carid=#{carid}")
    public ArrayList<DBShopcar> getShopcarOfcarid(Long carid);

}
