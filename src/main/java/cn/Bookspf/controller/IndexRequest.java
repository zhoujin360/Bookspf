package cn.Bookspf.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import cn.Bookspf.model.DTO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.BookMapper;

import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.RO.BookResponse;
import cn.Bookspf.model.RO.Response;
import cn.Bookspf.utils.Validator;


@RestController
public class IndexRequest {
	HttpSession httpSession;
	BookMapper bookMapper;
	Validator validator;
	
	@Autowired
	public IndexRequest(HttpSession httpSession,BookMapper bookMapper) {
		this.httpSession=httpSession;
		this.validator=new Validator(httpSession);
		this.bookMapper=bookMapper;
	}



	@GetMapping("/getRankList")
	public Response getRankList () {
		ArrayList<DBBook> rankList = bookMapper.getRankList();
		return new BookResponse(rankList);
	}
	
	@GetMapping("/getPublishBook")
	public Response getBook () {
		ArrayList<DBBook> bookList = bookMapper.getPublishBook();
		return new BookResponse(bookList);
	}

	@PostMapping("/search")
	public Response search(@RequestBody Book request){
		Integer bid = bookMapper.getBid(request.getBookname());
		if(bid==null) return new Response(false,"对不起,没有找到您需要的图书");
		return new Response(true,"/book/"+bid);
	}
	
}
