package com.in28minutes.rest.webservices.restfullwebservices.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ApiModelProperty(notes="Name cant' be null and should be a non empty value")
	@NotNull( message = "Name cant' be null")
	//@Size(min = 1, message = "Must provide a non-empty value for a Name")
	@NotBlank(message = "Must provide a non-empty value for a Name")
	private String name;
	
	@ApiModelProperty(notes="birth date must be in past")	
	@Past
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> postList) {
		this.posts = postList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public User(int id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public User() {
		
	}


	
	
}
