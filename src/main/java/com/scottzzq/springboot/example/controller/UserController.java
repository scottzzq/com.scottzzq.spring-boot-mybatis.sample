package com.scottzzq.springboot.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scottzzq.springboot.example.entity.UserEntity;
import com.scottzzq.springboot.example.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("list")
	public List<UserEntity> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("post")
	public String post(UserEntity user) {
		userService.save(user);
		return "redirect:/user/home/adduser";
	}
	
	@RequestMapping("home")
	public String home(RedirectAttributes redirect) {
		return "user/home.jsp";
	}
}
