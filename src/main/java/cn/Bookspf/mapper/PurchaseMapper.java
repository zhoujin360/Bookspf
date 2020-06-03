package cn.Bookspf.mapper;

import cn.Bookspf.model.DO.DBPurchase;
import cn.Bookspf.model.DTO.Purchase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface PurchaseMapper {

    //插入记录
    @Insert("insert into purchase(purchaseid,bid,isbn,purchaseprice,purchasetime,operator) "+
            "values(#{purchaseid},#{bid},#{isbn},#{purchaseprice},#{purchasetime},#{operator})")
    public void insertPurchase(Purchase purchase);

    //删除记录
    @Delete("delete from purchase where isbn=#{isbn}")
    public void deletePurchase(String isbn);

    //查询进货统计
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase group by purchaseid,purchasetime,operator")
    public ArrayList<DBPurchase> getPurchases();

    //查询某个进货统计
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase where purchaseid=#{purchaseid} group by purchaseid,purchasetime,operator")
    public ArrayList<DBPurchase> getPurchaseOfPurchaseid(long purchaseid);

    //查询某个进货统计
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase where purchasetime=#{purchasetime} group by purchaseid,purchasetime,operator")
    public ArrayList<DBPurchase> getPurchaseOfPurchasetime(String purchasetime);

    //查询某个进货信息
    @Select("select purchaseid,bid,isbn,purchaseprice from purchase where purchaseid=#{purchaseid}")
    public ArrayList<DBPurchase> getPurchasesinfo(long purchaseid);

    //获取Purchaseid Of Purchaseid
    @Select("select purchaseid from purchase where purchaseid=#{purchaseid}")
    public ArrayList<Long> findPurchaseid (long purchaseid);

    //获取isbn Of isbn
    @Select("select isbn from purchase where isbn=#{isbn}")
    public String findIsbn (String isbn);


}
