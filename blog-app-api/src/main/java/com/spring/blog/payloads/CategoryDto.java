package com.spring.blog.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
	private Integer categoryId;
	@NotNull
	private String categoryDescription;
	@NotNull
	private String categoryTitle;

}

