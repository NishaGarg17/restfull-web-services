package com.in28minutes.rest.webservices.restfullwebservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.rest.webservices.restfullwebservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
