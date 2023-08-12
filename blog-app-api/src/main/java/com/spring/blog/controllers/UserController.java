package com.spring.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.payloads.ApiResponse;
import com.spring.blog.payloads.UserDto;
import com.spring.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createuseDto= this.userService.createuser(userDto);
		return new ResponseEntity<UserDto>(createuseDto, HttpStatus.CREATED);
	}
	
//	update single user
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer uid){
		UserDto updateuserDto= this.userService.updateuser(userDto, uid);
		return new ResponseEntity<>(updateuserDto, HttpStatus.OK);
	}
	
//	delete  single user
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<>(new ApiResponse("succes", true), HttpStatus.OK);
	}
	
//	get all users
	@GetMapping("/")
	public ResponseEntity <List<UserDto>> getAlluser(){
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}
	
//	get single user
	@GetMapping("/{id}")
	public ResponseEntity <UserDto> getSingleuser(@PathVariable("id") Integer uid){
		return new ResponseEntity<UserDto>(this.userService.getUserById(uid), HttpStatus.OK);
	}
}
