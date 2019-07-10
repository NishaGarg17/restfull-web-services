package com.in28minutes.rest.webservices.restfullwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfullwebservices.dao.UserDaoService;
import com.in28minutes.rest.webservices.restfullwebservices.entity.User;

@RestController
public class UserResourceController {
	// Get /users
	// retrieve all users
	UserDaoService userDaoService;
	
	@Autowired
	public UserResourceController(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userDaoService.findAll();
	}
	
	// Get /user/{id}
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable int id) {
		return userDaoService.findOne(id);
	}
}
