package cn.Bookspf.model.DO;

import org.springframework.beans.factory.annotation.Autowired;

public class DBUser {
	private Integer uid;
	private String username;
	private String password;
	private Integer admin;
	private String email;
	private Double balance;
	private String realname;
	private String phone;
	private String address;
	
	@Autowired
	public DBUser(Integer uid,String username,String password,Integer admin,String email,Double balance,String realname,String phone,String address) {
		this.uid=uid;
		this.username=username;
		this.password=password;
		this.admin=admin;
		this.email=email;
		this.balance=balance;
		this.realname=realname;
		this.phone=phone;
		this.address=address;
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

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DBUser [uid=" + uid + ", username=" + username + ", password=" + password + ", admin=" + admin
				+ ", email=" + email + ", balance=" + balance + ", realname=" + realname + ", phone=" + phone
				+ ", address=" + address + "]";
	}	
	
}
