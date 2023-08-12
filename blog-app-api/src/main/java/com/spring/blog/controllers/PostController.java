package com.spring.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.config.AppConstants;
import com.spring.blog.payloads.ApiResponse;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.payloads.PostDto;
import com.spring.blog.payloads.PostResponse;
import com.spring.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	// create Post
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto newPostDto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPostDto, HttpStatus.OK);
	}
	
	

//	get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) Integer pageNum,
			@RequestParam(value = "SortBy", required = false, defaultValue = AppConstants.Sort_BY) String SortBy,
			@RequestParam(value = "SortDir", required = false, defaultValue = AppConstants.Sort_DIR) String sortDir) {
		PostResponse newPostDto1 = this.postService.getallPostList(pageSize, pageNum, SortBy, sortDir);
		return new ResponseEntity<PostResponse>(newPostDto1, HttpStatus.OK);
	}
	

//	get single post
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") Integer postId) {
		PostDto postDto = this.postService.getSinglePost(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	

//	 get posts by category
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> PostDto = this.postService.getallPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(PostDto, HttpStatus.OK);
	}
	
	

	// get all posts by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> getallPostByUser = this.postService.getallPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(getallPostByUser, HttpStatus.OK);
	}
	
	// update the post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
//	delete  single Post
	@DeleteMapping("/post/{PostId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("PostId") Integer PostId){
		this.postService.deletePost(PostId);
		return new ResponseEntity<>(new ApiResponse("succes", true), HttpStatus.OK);
	}
	
//	get single post
	@GetMapping("/posts/keyword/{keyword}")
	public ResponseEntity<List<PostDto>> SearchPost(@PathVariable("keyword") String keyword) {
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		return new ResponseEntity <List<PostDto>>(searchPosts, HttpStatus.OK);
	}
	
	
}
