package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.User;
import cn.Bookspf.model.DTO.UserNoPassword;

@Mapper
@Repository
public interface UserMapper {
	
	//获取User Of Uid
	@Select("select * from user where uid=#{uid}")
	public DBUser getUserOfUid(Integer uid);

	//获取User Of Username
	@Select("select * from user where username=#{username}")
	public DBUser getUserOfUsername(String username);
	
	//获取User Of Email
	@Select("select * from user where email=#{email}")
	public DBUser getUserOfEmail(String email);
	
	//获取UserNoPass Of Uid
	@Select("select * from user where admin=#{admin}")
	public ArrayList<UserNoPassword> getUserNoPasswordOfAdmin(Integer admin);
	
	//插入用户
	@Insert("insert into user(uid,username,password,email,admin) values(#{uid},#{username},#{password},#{email},#{admin})")
	public void insertUser(User user);
	
	//插入用户
	@Insert("insert into user(uid,username,password,email,admin,realname) values(#{uid},#{username},#{password},#{email},#{admin},#{realname})")
	public void insertManager(User user);
	
	//获取Uid Of Username
	@Select("select uid from user where username=#{username}")
	public int getUid(String username);
	
	//获取Uid Of Uid
	@Select("select uid from user where uid=#{uid}")
	public Integer findUid(Integer uid);
	
	//获取Username Of Username
	@Select("select username from user where username=#{username}")
	public String findUsername(String username);
	
	//获取Email Of Email
	@Select("select email from user where email=#{email}")
	public String findEmail(String email);
	
	//获取Password Of Username
	@Select("select password from user where username=#{username}")
	public String getPasswordOfUsername(String username);
	
	//获取Admin Of Uid
	@Select("select admin from user where uid=#{uid}")
	public int getAdmin(int uid);

	//删除用户
	@Delete("delete from user where uid=#{uid}")
	public int deleteUser(int uid);

	//查询用户余额
	@Select("select balance from user where uid=#{uid}")
	public Double getBalance(Integer uid);

	//查询用户密码
	@Select("select password from user where uid=#{uid}")
	public String getPasswordOfUid(Integer uid);

	//修改用户余额
	@Update("update user set balance=#{balance} where uid=#{uid}")
	public void updateBalance(Integer uid,Double balance);

	//修改用户密码
	@Update("update user set password=#{password} where uid=#{uid}")
	public void updatePassword(Integer uid,String password);

	//修改用户手机
	@Update("update user set phone=#{phone} where uid=#{uid}")
	public void updatePhone(Integer uid,String phone);

	//修改用户真实名字
	@Update("update user set realname=#{realname} where uid=#{uid}")
	public void updateRealname(Integer uid,String realname);

	//修改用户地址
	@Update("update user set address=#{address} where uid=#{uid}")
	public void updateAddress(Integer uid,String address);
}
