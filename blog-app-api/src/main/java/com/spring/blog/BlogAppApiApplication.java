package com.spring.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder pEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
		System.out.println("version: " + SpringVersion.getVersion());
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.pEncoder.encode("xyz"));
		
	}
	

}
 