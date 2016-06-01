package spring.boot.sample.jpa.usermanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.boot.sample.jpa.usermanage.exception.UserNotFound;
import spring.boot.sample.jpa.usermanage.model.User;
import spring.boot.sample.jpa.usermanage.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addUserPage() {
		ModelAndView modelAndView = new ModelAndView("add-user-form.jsp");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute User user) {
		
		ModelAndView modelAndView = new ModelAndView("home.jsp");
		userService.addUser(user);
		
		String message = "用户添加成功";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfUsers() {
		ModelAndView modelAndView = new ModelAndView("list-of-users.jsp");
		List<User> users = userService.getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-user-form.jsp");
		User user = userService.getUser(id);
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingUser(@ModelAttribute User user, @PathVariable Integer id) throws UserNotFound {
		ModelAndView modelAndView = new ModelAndView("home.jsp");
		userService.updateUser(user);
		String message = "用户修改成功";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) throws UserNotFound {
		ModelAndView modelAndView = new ModelAndView("home.jsp");
		userService.deleteUser(id);
		String message = "用户删除成功";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
