package com.spring.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> postContent;
	private Integer pageNum;
	private Integer pageSize;
	private long totalRecords;
	private Integer totalPages;
	private boolean lastPage;
	
}
