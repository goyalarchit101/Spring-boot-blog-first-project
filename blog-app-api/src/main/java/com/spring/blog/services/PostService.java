package com.spring.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.blog.entities.Category;
import com.spring.blog.entities.Post;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.payloads.PostDto;
import com.spring.blog.payloads.PostResponse;

public interface PostService {
	
//	 create post details
	 PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	 
//	 update post
	 PostDto updatePost (PostDto postDto, Integer postID);
	 
//	 get all post
	 PostResponse getallPostList(Integer pageSize, Integer pageNum, String SortBy, String SortDir);
	 
//	 get single Post
	 PostDto getSinglePost(Integer PostId);
	 
//	 delete single post
	 void deletePost(Integer PostID);
	 
	 
//	 get all post by Category
	 List<PostDto> getallPostByCategory(Integer CategoryId);
	 
//	 get all post done by user
	 List<PostDto> getallPostByUser(Integer UserId);

//	 search Post
	 List<PostDto> searchPosts(String keyword);
	 
}
