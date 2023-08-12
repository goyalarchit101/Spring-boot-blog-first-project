package com.spring.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.Category;
import com.spring.blog.entities.Comment;
import com.spring.blog.entities.Post;
import com.spring.blog.entities.User;
import com.spring.blog.exceptions.ResourseNotFoundException;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.payloads.PostDto;
import com.spring.blog.payloads.PostResponse;
import com.spring.blog.payloads.UserDto;
import com.spring.blog.repositories.CategoryRepo;
import com.spring.blog.repositories.CommentRepo;
import com.spring.blog.repositories.PostRepo;
import com.spring.blog.repositories.UserRepo;
import com.spring.blog.services.PostService;

@Service
public class postServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "user id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category", "categoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		
		post.setImagename("default.png");
		post.setAddedDate(new Date());		
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost =  this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class); 
	}

	@Override	
	public PostDto updatePost(PostDto postDto, Integer postID) {
		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postID));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImagename(postDto.getImagename());
		post.setAddedDate(postDto.getAddedDate());
		post.setCategory(this.modelMapper.map(postDto.getCategory(), Category.class));
		post.setUser(this.modelMapper.map(postDto.getUser(), User.class));
		this.postRepo.save(post);
		return this.modelMapper.map(post, PostDto.class);
	}

	
	@Override
	public PostResponse getallPostList(Integer pageSize, Integer pageNum, String SortBy, String SortDir) {
		
		Sort sort = null;
		if(SortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(SortBy).ascending();
		}
		else {
			sort = Sort.by(SortBy).descending();
		}
		
		PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<Post> page = this.postRepo.findAll(pageable);
		List<Post> allPosts = page.getContent();
		List<PostDto> postDtos = allPosts.stream().map(post ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPostContent(postDtos);
		postResponse.setPageNum(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalRecords(page.getTotalElements());
		postResponse.setTotalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());
		return postResponse;
	}
	
	

//	get single post
	@Override
	public PostDto getSinglePost(Integer PostId) {
		Post post = this.postRepo.findById(PostId)
				.orElseThrow(() -> new ResourseNotFoundException("post", "postId", PostId));
		List<Comment> comments = this.commentRepo.findBypost(post);
		Set<Comment> commentdto = comments.stream().map(comment ->this.modelMapper.map(comment, Comment.class)).collect(Collectors.toSet());
		post.setComments(commentdto);
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public void deletePost(Integer PostID) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(PostID)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "PostID", PostID));
		this.postRepo.delete(post);

	}

	@Override
	public List<PostDto> getallPostByCategory(Integer CategoryId) {
		Category category = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Catergory", "categoryID", CategoryId));
		List<Post> posts = this.postRepo.findBycategory(category);
		List<PostDto> postDto = posts.stream().map(post ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
//		return null;
	}

	@Override
	public List<PostDto> getallPostByUser(Integer UserId) {
		  User user = this.userRepo.findById(UserId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "User", UserId));
		List<Post> userPosts = this.postRepo.findByuser(user);
		List<PostDto> userPostDto = userPosts.stream().map(post ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return userPostDto;
//		return null;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> findPost = this.postRepo.findByContentContaining(keyword);
		List<PostDto> findPostDto = findPost.stream().map(post ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return findPostDto;
	}


}
