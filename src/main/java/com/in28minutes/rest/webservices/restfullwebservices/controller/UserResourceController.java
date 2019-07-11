package com.in28minutes.rest.webservices.restfullwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfullwebservices.Exception.UserNotFoundException;
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
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Not Able to find user of id-" + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		
		if(user == null)
			throw new UserNotFoundException("Not Able to find user of id-" + id);
	}
}
	