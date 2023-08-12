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
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.payloads.ApiResponse;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	

	// create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto catDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.CREATED);
	}
	
	

//	update single category
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("catid") Integer catId) {
		System.err.println("update cat is called");
		CategoryDto updatecategoryDto = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatecategoryDto, HttpStatus.OK);
	}
	
	

//	delete  single category
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<>(new ApiResponse("succes", true), HttpStatus.OK);
	}
	
	

//	get all Category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllcategory() {
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getallCategoryList(), HttpStatus.OK);
	}
	
	
	

//	get single Category
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("id") Integer catId) {
		return new ResponseEntity<CategoryDto>(this.categoryService.getSinleCategory(catId), HttpStatus.OK);
	}
	
	
}
