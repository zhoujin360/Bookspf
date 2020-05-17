package cn.Bookspf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;

@Mapper
@Repository
public interface UserMapper {
	
	//获取Uid Of Username
	@Select("select uid from user where username=#{username}")
	public Integer getUid(String username);
	
	//获取Uid Of Uid
	@Select("select uid from user where uid=#{uid}")
	public Integer findUid(Integer uid);
	
	//获取密码 Of Username
	@Select("select password from user where username=#{username}")
	public String getPassword(String username);
	
	//获取User Of Username
	@Select("select * from user where username=#{username}")
	public DBUser getUserOfUsername(String username);
	
	//获取User Of Email
	@Select("select * from user where email=#{email}")
	public DBUser getUserOfEmail(String email);
	
	//插入用户
	@Insert("insert into user(uid,username,password,email) values(#{uid},#{username},#{password},#{email})")
	public void insertUser(User user);
}
