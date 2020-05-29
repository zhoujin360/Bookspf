package cn.Bookspf.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.model.DO.DBOrder;
import cn.Bookspf.model.DTO.OrderStatistics;
import cn.Bookspf.model.DTO.PurchaseStatistics;
import cn.Bookspf.model.RO.Response;
import org.springframework.web.multipart.MultipartFile;

public class Operator {
	
	public Operator() {}
	
	//查询订单的统计信息
	public ArrayList<OrderStatistics> getOrders(ArrayList<DBOrder> order,ArrayList<Integer> price){
		ArrayList<OrderStatistics> orders= new ArrayList<OrderStatistics>();
		for(int i=0;i<order.size();i++) {
			OrderStatistics temp=new OrderStatistics();
			temp.setOrderid(order.get(i).getOrderid());
			temp.setUid(order.get(i).getUid());
			temp.setCreatetime(order.get(i).getCreatetime());
			temp.setPaytime(order.get(i).getPaytime());
			temp.setTotal(price.get(i));
			orders.add(temp);
		}
		return orders;
	}


	//查询进货的统计信息
	public ArrayList<PurchaseStatistics> getPurchases(){
		ArrayList<PurchaseStatistics> purchases=new ArrayList<PurchaseStatistics>();

		return  purchases;
	}


	//上传图书图片
	public boolean uploadBookimg(MultipartFile file,Integer bid) throws IOException {
		String oldFileName=file.getOriginalFilename();
		String fileType=oldFileName.substring(oldFileName.indexOf("."));
		if(!".png".equals(fileType)) return false;
		String newFilename=bid+fileType;
		new File("F:/Web/Bookspf-图书售书平台/bookimg/").mkdirs();
		file.transferTo(new File("F:/Web/Bookspf-图书售书平台/bookimg/"+newFilename));
		return true;
	}

	//删除图书图片
	public void deleteBookimg(Integer bid) throws IOException {
		String filename=bid+".png";
		new File("F:/Web/Bookspf-图书售书平台/bookimg/"+filename).delete();
	}
}
