package com.spring.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.payloads.jwtAuthRequest;
import com.spring.blog.payloads.jwtAuthResponse;
import com.spring.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllers {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<jwtAuthResponse> createToken(
			@RequestBody jwtAuthRequest jwtAuthRequest
			) throws Exception{
		
		this.authenticate(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassword());
		UserDetails loadUserByUsername = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
		String generateToken = this.jwtTokenHelper.generateToken(loadUserByUsername);
		jwtAuthResponse response = new jwtAuthResponse();
		response.setToken(generateToken);
		return new ResponseEntity<jwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("invalid creds!!!");
			throw new Exception("invalid username and passeword");
		
		}
		
	}

}
