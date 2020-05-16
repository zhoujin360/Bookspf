package cn.Bookspf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DTO.User;

@Mapper
@Repository
public interface UserMapper {
	
	@Select("select uid from login where username=#{username}")
	public Integer findUid(String username);
	
	@Select("select password from login where username=#{username}")
	public String findPassword(String username);
	
	@Select("select * from user where username=#{username}")
	public User findUser(String username);
}
