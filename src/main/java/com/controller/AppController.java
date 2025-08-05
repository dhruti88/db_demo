package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/db")
@CrossOrigin(origins="*")
public class AppController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/welcome")
	public String sayWelcome() {
		return "Welcome to db Spring Boot!";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		boolean isFound = service.findUser(user.getUserName(), user.getPassword());
		if(isFound)
			return user.getUserName() + " User logged in!";
		else return "User not found! Please Register";
		
	}
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		service.addUser(user);
		return user+" User Registered Successfully! ";
	}
	
	@GetMapping("/loadusers")
	public List<User> loadusers() {
		return service.loadUsers();
	}
	
	@GetMapping("/finduser/{uname}")
	public String finduser(@PathVariable String uname) {
		if(service.searchUser(uname)) {
			return uname + " found!";
		}else return "User not found!";
	}
	
	@DeleteMapping("/deleteuser/{uname}")
	public String deleteuser(@PathVariable String uname) {
		if(service.deleteUser(uname)) {
			return uname + " found and deleted!";
		}else return "User not found!";
	}
	
	@PutMapping("/updateuser/{uname}")
	public String updateUser(@PathVariable String uname,@RequestBody User user) {
		if (service.updateUser(uname,user)) {
			return uname + " " + "found and updated";
		}
		return "User not found...!";
	}
	
}
