package com.spring.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.entities.Comment;
import com.spring.blog.entities.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	List<Comment> findBypost(Post post);

}
