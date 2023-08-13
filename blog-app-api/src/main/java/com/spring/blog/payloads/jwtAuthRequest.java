package com.spring.blog.payloads;

import lombok.Data;

@Data
public class jwtAuthRequest {
	
	private String userName;
	
	private String password;
	
}
