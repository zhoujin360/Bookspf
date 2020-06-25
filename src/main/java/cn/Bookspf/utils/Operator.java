package cn.Bookspf.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.Bookspf.model.DO.DBOrder;
import org.springframework.web.multipart.MultipartFile;

public class Operator {
	
	public Operator() {}


	//上传图书图片
	public boolean uploadBookimg(MultipartFile file,Integer bid) throws IOException {
		String oldFileName=file.getOriginalFilename();
		String fileType=oldFileName.substring(oldFileName.indexOf("."));
		if(!".png".equals(fileType)) return false;
		String newFilename=bid+fileType;
		//new File("F:/Web/Bookspf-图书售书平台/bookimg/").mkdirs();
		//file.transferTo(new File("F:/Web/Bookspf-图书售书平台/bookimg/"+newFilename));
		new File("/bookspf/bookimg/").mkdirs();
		file.transferTo(new File("/bookspf/bookimg/"+newFilename));
		return true;
	}

	//删除图书图片
	public void deleteBookimg(Integer bid) throws IOException {
		String filename=bid+".png";
		//new File("F:/Web/Bookspf-图书售书平台/bookimg/"+filename).delete();
		new File("/bookspf/bookimg/"+filename).delete();
	}


	//查询订单的统计信息
	public ArrayList<DBOrder> getOrders(ArrayList<DBOrder> order,ArrayList<Double> price){
		for(int i=0;i<order.size();i++) {
			order.get(i).setTotal(price.get(i));
		}
		return order;
	}


}
