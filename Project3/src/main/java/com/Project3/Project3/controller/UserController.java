package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.User;
import com.Project3.Project3.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/userGet")
	@ResponseBody
	public List<User> get()
	{
		return  userService.returnData();
	}
//	@PostMapping("/userPost")
//	public void  post(@RequestBody User user)
//	{
//		userService.saveData(user);
//		//return "hi";
//	}
//	@PostMapping("/users")
//	@ResponseBody
//	public String display()
//	{
//		return "hi";
//	}
	@PostMapping("/user")
	@ResponseBody
	public void post(@RequestBody User user)
	{
		userService.saveData(user);
	}
	@GetMapping("/agent")
	public String arun()
	{
		return "agent";
	}
	
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	
	

}
