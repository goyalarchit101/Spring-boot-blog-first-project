package com.spring.blog.services;

import java.util.List;

import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.payloads.UserDto;


public interface CategoryService {
	
	 CategoryDto createCategory(CategoryDto categoryDto);
	 
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryID);
	 
	 List<CategoryDto> getallCategoryList();
	 
	 CategoryDto getSinleCategory(Integer Id);
	 
	 void deleteCategory(Integer ID);
	 
}
