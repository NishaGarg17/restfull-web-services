package com.in28minutes.rest.webservices.restfullwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfullwebservices.entity.HelloWorldBean;

@RestController
//@RequestMapping("/")
public class HelloWorldController {
	// GET
	// URI - /hello-world
	//method - "Hello World"
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello Word");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVarialbe(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
}
