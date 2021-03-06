package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBStock;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface StockMapper {

    //插入入库记录
    @Insert("insert into stock(stockid,bid,isbn,cometime,comeout) values(#{stockid},#{bid},#{isbn},#{cometime},0)")
    public void insertComeStock(Long stockid,Integer bid,String isbn,String cometime);

    //获取Bids
    @Select("select bid from stock where stockid=#{stockid}")
    public ArrayList<Integer> getBidsOfStockid(Long stockid);

    //获取所有库存记录
    @Select("select distinct stockid,comeout,cometime,outtime from stock")
    public ArrayList<DBStock> getStocks();

    //获取某个库存记录
    @Select("select distinct stockid,comeout,cometime,outtime from stock where stockid=#{stockid}")
    public ArrayList<DBStock> getStockOfStockid(long stockid);

    //获取某个库存记录
    @Select("select distinct stockid,comeout,cometime,outtime from stock where stocktime=#{stocktime}")
    public ArrayList<DBStock> getStockOfStocktime(String stocktime);

    //获取某个库存记录
    @Select("select stockid,bid,isbn from stock where stockid=#{stockid}")
    public ArrayList<DBStock> getStockinfoOfStockid(long stockid);

    //获取
    @Select("select * from stock where comeout=0 and bid=#{bid} limit 0,1")
    public DBStock getComeStock(Integer bid);

    //修改出库状态
    @Update("update stock set outtime=#{outtime},comeout=1 where stockid=#{stockid}")
    public void updateOutStock(Long stockid,String outtime);

    //删除记录
    @Delete("delete from stock where isbn=#{isbn}")
    public void deleteStock(String isbn);
}
