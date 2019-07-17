package com.in28minutes.rest.webservices.restfullwebservices.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfullwebservices.Exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfullwebservices.Repository.PostRepository;
import com.in28minutes.rest.webservices.restfullwebservices.Repository.UserRepository;
import com.in28minutes.rest.webservices.restfullwebservices.dao.UserDaoService;
import com.in28minutes.rest.webservices.restfullwebservices.entity.Post;
import com.in28minutes.rest.webservices.restfullwebservices.entity.User;

@RestController
public class UserJPAResourceController {
	// Get /users
	// retrieve all users
	private UserDaoService userDaoService;
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	@Autowired
	public UserJPAResourceController(UserDaoService userDaoService, UserRepository userRepository, PostRepository postRepository) {
		this.userDaoService = userDaoService;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	// Get /user/{id}
	@GetMapping("/jpa/users/{id}")
	public Resource<User> findOne(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Not Able to find user of id-" + id);
		}

		// Add HATEOAS 
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}


	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return "user with id- " + id + " deleted successfully";
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	
	public List<Post> retrieveSpecificUserPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Not Able to find user of id-" + id);
		}
		return user.get().getPosts();		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public String createPosts(@PathVariable int id, @RequestBody List<Post> postList) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("Not Able to find user of id-" + id);
		}
		
		User user = optionalUser.get();
		for(Post post : postList) {
			post.setUser(user);
		}
		postRepository.saveAll(postList);
		return "Posts for the user id-" + id + " has been created successfully";
	}
}
