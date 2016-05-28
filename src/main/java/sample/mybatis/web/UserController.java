package sample.mybatis.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.mybatis.domain.User;
import sample.mybatis.jpa.repository.UserRepository;
import sample.mybatis.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> readUserInfo(){
		List<User> list = new ArrayList<User>();
		list.add(userService.readUserByUsername("lxy"));
		list.addAll(userRepository.findAll());
		System.out.println(userService.readUserCount());
		return list;
	}
}
