
package com.spring.blog.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.Comment;
import com.spring.blog.entities.Post;
import com.spring.blog.entities.User;
import com.spring.blog.exceptions.ResourseNotFoundException;
import com.spring.blog.payloads.CommentsDto;
import com.spring.blog.repositories.CommentRepo;
import com.spring.blog.repositories.PostRepo;
import com.spring.blog.repositories.UserRepo;
import com.spring.blog.services.CommentService;

@Service
public class CommentImpl implements CommentService {
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer UserId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postId));
		User user = this.userRepo.findById(UserId).orElseThrow(()-> new ResourseNotFoundException("user", "user id", UserId));
		
		Comment comment = this.modelMapper.map(commentsDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		this.commentRepo.save(comment);
		CommentsDto commentsDtoNew= modelMapper.map(comment, commentsDto.getClass());
		return commentsDtoNew;
	}

	@Override
	public void deleteComment(Integer CommentId) {
		Comment comment= this.commentRepo.findById(CommentId)
				.orElseThrow(() -> new ResourseNotFoundException("Comment", "CommentId", CommentId));
		this.commentRepo.delete(comment);

	}

}
