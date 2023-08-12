package com.spring.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
		
	}

}
