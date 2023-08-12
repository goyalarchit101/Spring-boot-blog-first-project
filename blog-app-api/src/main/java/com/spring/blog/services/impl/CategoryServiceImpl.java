package com.spring.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.Category;
import com.spring.blog.entities.User;
import com.spring.blog.exceptions.ResourseNotFoundException;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.repositories.CategoryRepo;
import com.spring.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category =  this.categoryRepo.save(CategoryDtotoCategory(categoryDto));
		return this.CategorytoCategoryDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryID) {
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourseNotFoundException("Catergory", "categoryID", categoryID));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepo.save(category);
		return this.CategorytoCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getallCategoryList() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catdtDtos = categories.stream().map(cat -> this.CategorytoCategoryDto(cat)).collect(Collectors.toList());
		return catdtDtos;
	}

	@Override
	public CategoryDto getSinleCategory(Integer Id) {
		Category category = this.categoryRepo.findById(Id)
				.orElseThrow(() -> new ResourseNotFoundException("Catergory", "categoryID", Id));
		return CategorytoCategoryDto(category);
	}

	@Override
	public void deleteCategory(Integer Id) {
		Category category = this.categoryRepo.findById(Id)
				.orElseThrow(() -> new ResourseNotFoundException("Catergory", "categoryID", Id));
		this.categoryRepo.delete(category);
	}
	
	private Category CategoryDtotoCategory(CategoryDto categoryDto) {
		Category category = this.modelmapper.map(categoryDto, Category.class);
		return category;
		
	}
	
	private CategoryDto CategorytoCategoryDto(Category category) {
		CategoryDto categoryDto = this.modelmapper.map(category, CategoryDto.class);
		return categoryDto;
	}


}
