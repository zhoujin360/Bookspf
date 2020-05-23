package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBUser;
import cn.Bookspf.model.DTO.UserNoPassword;

public class UserResponse extends Response{
	private ArrayList<UserNoPassword> users;
	
	public UserResponse() {}
	public UserResponse(ArrayList<UserNoPassword> users) {
		this.users=users;
	}

	public ArrayList<UserNoPassword> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserNoPassword> users) {
		this.users = users;
	}
	
	
}
