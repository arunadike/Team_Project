package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.User;
import com.Project3.Project3.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/userGet")
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
	public void post(@RequestBody User user)
	{
		userService.saveData(user);
	}
	

}
