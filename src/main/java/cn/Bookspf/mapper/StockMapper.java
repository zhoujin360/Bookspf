package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface StockMapper {

    //获取所有库存记录
    @Select("select distinct stockid,comeout,stocktime from stock")
    public ArrayList<DBStock> getStocks();

    //获取某个库存记录
    @Select("select distinct stockid,comeout,stocktime from stock where stockid=#{stockid}")
    public ArrayList<DBStock> getStockOfStockid(long stockid);

    //获取某个库存记录
    @Select("select distinct stockid,comeout,stocktime from stock where stocktime=#{stocktime}")
    public ArrayList<DBStock> getStockOfStocktime(String stocktime);

    //获取某个库存记录
    @Select("select stockid,bid,isbn from stock where stockid=#{stockid}")
    public ArrayList<DBStock> getStockinfoOfStockid(long stockid);
}
