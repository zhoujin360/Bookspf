package cn.Bookspf.model.DO;



public class DBLogin {
	private Integer uid;
	private String username;
	private String password;
	
	public DBLogin(Integer uid,String username,String password) {
		this.uid=uid;
		this.username=username;
		this.password=password;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "DBLogin [uid=" + uid + ", username=" + username + ", password=" + password + "]";
	}
	
}
