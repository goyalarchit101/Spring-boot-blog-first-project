package com.spring.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.blog.payloads.UserDto;

public interface UserService {
	UserDto createuser(UserDto user);
	
	UserDto updateuser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userID);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
