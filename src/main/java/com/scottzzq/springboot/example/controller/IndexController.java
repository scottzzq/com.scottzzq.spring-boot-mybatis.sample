package com.scottzzq.springboot.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scottzzq.springboot.example.entity.UserEntity;
import com.scottzzq.springboot.example.service.LoginService;
import com.scottzzq.springboot.example.util.CurrentUserUtils;

@Controller
public class IndexController {
	@Autowired
	LoginService loginService;

	@RequestMapping(value = { "login", "/" }, method = RequestMethod.GET)
	public String login() {
		return "login.jsp";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(UserEntity user, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			user = loginService.login(user);
		} catch (ServiceException e) {
			redirect.addFlashAttribute("err_code", e.getMessage());
			redirect.addFlashAttribute("user", user);
			return "redirect:/login";
		}
		Cookie cookie = new Cookie("username", user.getName());
		cookie.setMaxAge(60 * 60);
		response.addCookie(cookie);
		CurrentUserUtils.getInstance().setUser(user);
		return "redirect:user/home";
	}
}
