package com.spring.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.entities.Category;
import com.spring.blog.entities.Post;
import com.spring.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByuser(User user);
	
	List<Post> findBycategory(Category category);
	
	List<Post> findByContentContaining(String content);
	
}
