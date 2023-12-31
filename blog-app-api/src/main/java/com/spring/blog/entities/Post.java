package com.spring.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.spring.blog.payloads.CategoryDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table (name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name =  "post_title",length = 100, nullable = false)
	private String title;
	
	@Column(length = 10000)
	private String content;
	
	private String imagename;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;	
	
	@OneToMany(mappedBy = "post",cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();

	
}
