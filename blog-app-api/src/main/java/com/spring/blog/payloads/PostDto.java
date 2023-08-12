package com.spring.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.spring.blog.entities.Category;
import com.spring.blog.entities.Comment;
import com.spring.blog.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imagename;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;	
	
	private Set<CommentsDto> comments = new HashSet<>();
}
