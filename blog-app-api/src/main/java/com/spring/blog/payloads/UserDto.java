package com.spring.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class UserDto {
	private int id;
	@NotEmpty @NotNull
	@Size(min = 4, message = "usename must be min of 4 characters")
	private String name;
	
	@Email(message = "Email is not valid", regexp = ".+@.+\\..+")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 10, message = "password must be minimum of 4 and maximum of 10")
	
	private String password;
	
	@NotEmpty
	private String about;
}
