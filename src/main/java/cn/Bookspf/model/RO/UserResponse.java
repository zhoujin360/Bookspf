package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBUser;

public class UserResponse extends Response{
	private ArrayList<DBUser> users;
	
	public UserResponse() {}
	public UserResponse(ArrayList<DBUser> users) {
		this.users=users;
	}

	public ArrayList<DBUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<DBUser> users) {
		this.users = users;
	}
	
	
}
