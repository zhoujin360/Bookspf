package cn.Bookspf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Bookspf.mapper.LoginMapper;

@RestController
public class AxiosRequest {
	LoginMapper loginMapper;
	
	@Autowired
	public AxiosRequest(LoginMapper loginMapper) {
		this.loginMapper=loginMapper;
	}
	@PostMapping("/findUid")
	public Integer findUid (@RequestBody String username) {
		username="CShisan";
		Integer uid=loginMapper.findUid(username);
		return uid;
	}
}
