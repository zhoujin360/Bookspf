package cn.Bookspf.model.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderStatistics {
	private long orderid;
	private Integer uid;
	private Integer total;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createtime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date paytime;
	
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
}
