package cn.Bookspf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.UserMapper;
import cn.Bookspf.model.DTO.User;

@RestController
public class AxiosRequest {
	UserMapper userMapper;
	
	@Autowired
	public AxiosRequest(UserMapper userMapper) {
		this.userMapper=userMapper;
	}
	@PostMapping("/findUser")
	public User findUser (@RequestBody String username) {
		username="CShisan";
		User user=userMapper.findUser(username);
		return user;
	}
	
	@PostMapping("/register")
	public boolean register (@RequestBody User request) {
		System.out.println(request.getUsername());
		System.out.println(request.getPassword());
		System.out.println(request.getEmail());
		return true;
	}
}
