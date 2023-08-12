package com.spring.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.payloads.ApiResponse;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.payloads.CommentsDto;
import com.spring.blog.repositories.CommentRepo;
import com.spring.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class commentController {
	
	@Autowired
	private CommentService commentService;
	
	// create comment
	@PostMapping("/")
	public ResponseEntity<CommentsDto> createComment(@Valid @RequestBody CommentsDto categoryDto) {
		CommentsDto commentDto = this.commentService.createComment(categoryDto, 3, 1);
		return new ResponseEntity<CommentsDto>(commentDto, HttpStatus.CREATED);
	}
	
	// delete comment
	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> deleteComment(@Valid @RequestBody CommentsDto categoryDto) {
		this.commentService.deleteComment(9);
		return new ResponseEntity<ApiResponse>(new ApiResponse("succes", true), HttpStatus.OK);
	}

}
