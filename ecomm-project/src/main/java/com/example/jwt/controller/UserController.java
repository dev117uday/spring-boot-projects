package com.example.jwt.controller;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Users;
import com.example.jwt.service.UserServiceToRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserServiceToRepo userServiceToRepo;

	@GetMapping()
	public ResponseEntity<Users> getUser() throws ExceptionBroker {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userID = userDetails.getUsername();

		Users user = userServiceToRepo.getUserByID(userID);

		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@PostMapping
	public ResponseEntity<Users> updateUser(@RequestBody Users user) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userID = userDetails.getUsername();
		user.setSub(userID);

		Users updatedUser = userServiceToRepo.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

}
