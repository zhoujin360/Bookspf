package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBSort;
import cn.Bookspf.model.DTO.Sort;

@Mapper
@Repository
public interface SortMapper {
	
	//查询所有分类信息
	@Select("select * from sort")
	public ArrayList<DBSort> getSorts();

	@Select("select sortname from sort where sortid=#{sortid}")
	public String getSortname(Integer sortid);
	
	@Insert("insert into sort values(#{sortid},#{sortname})")
	public void addSort(Sort sort);
	
	@Delete("delete from sort where sortid=#{sortid}")
	public void deleteSort(Integer sortid);
}
