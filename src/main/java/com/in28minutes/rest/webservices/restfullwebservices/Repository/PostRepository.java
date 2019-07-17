package com.in28minutes.rest.webservices.restfullwebservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfullwebservices.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
