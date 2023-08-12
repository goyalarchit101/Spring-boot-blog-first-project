package com.spring.blog.services;

import org.springframework.stereotype.Service;

import com.spring.blog.payloads.CommentsDto;

public interface CommentService {

	CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer UserId);
	void deleteComment(Integer CommentId);
	
}
