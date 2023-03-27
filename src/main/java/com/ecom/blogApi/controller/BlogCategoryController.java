package com.ecom.blogApi.controller;

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
import org.springframework.web.client.ResponseErrorHandler;

import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.service.BlogCategoryService;

@RestController
@RequestMapping("/blog")
public class BlogCategoryController {
   
	@Autowired
	BlogCategoryService blogCategoryService;
	
	@PostMapping(value="/blogcategoryvalue" , consumes = "application/json" , produces = "application/json")
	ResponseEntity<Object> postBlogCategory (@RequestBody BlogCategory blogCategory){
		try {
		
			BlogCategory blgCategory = blogCategoryService.createBlogCategory(blogCategory);
			
			return  ResponseHandler.generateResponseBlogCategory("Sucessfully added data",HttpStatus.CREATED,blgCategory);
			
		}catch(Exception a) {
			return  ResponseHandler.generateResponseBlogCategory(a.getMessage(),HttpStatus.MULTI_STATUS,null);
		}
		
		
	}
	
	@GetMapping(value="/blogcategory")
	ResponseEntity<Object> getAllBlogCategory (){
		try {
			List<BlogCategory> abc = blogCategoryService.getallBlogCategory();
			return new ResponseEntity<>(abc ,HttpStatus.OK);
			
		}catch(Exception d) {
			return ResponseHandler.generateResponseBlogCategory(d.getMessage(), HttpStatus.NOT_FOUND, null);
		}
		
		
	}
	

	@GetMapping(value="/blogcategorybyid/{blogcategoryId}")
	ResponseEntity<Object> getBlogCategorybyId(@PathVariable int blogcategoryId){
		try {
			
			BlogCategory blogCat = blogCategoryService.getSingleBlogCategory(blogcategoryId);
			
			return new ResponseEntity<>(blogCat ,HttpStatus.OK);
		}catch(Exception e) {
			return ResponseHandler.generateResponseBlogCategory(e.getMessage(),HttpStatus.NOT_FOUND, null);
		}
		
	}
	
	@PutMapping(value = "/updateblogcategory/{blogcategoryid}" , consumes = "application/json" , produces = "application/json" )
	ResponseEntity<Object> putBlogCategory(@PathVariable("blogcategoryid") int id,@RequestBody BlogCategory blogCategory){
		BlogCategory aa = null;
		try {
			
			aa = blogCategoryService.updateBlogCategory(id, blogCategory);
			return ResponseHandler.generateResponseBlogCategory("Data is Updated ", HttpStatus.OK, aa);
		}
		catch(Exception ex) {
			return ResponseHandler.generateResponseBlogCategory(ex.getMessage(),HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@DeleteMapping(value="/deleteBlogCategory/{blogcategoryid}")
	ResponseEntity<Object> deleteBlogCategory(@PathVariable("blogcategoryid") int id){
		
		BlogCategory db;
		try {
			
			db = blogCategoryService.deleteBlogCategory(id);
			
			return ResponseHandler.generateResponseBlogCategory("Successfully deleted data!", HttpStatus.OK, db);
			
		}catch(Exception e){
			
			return ResponseHandler.generateResponseBlogCategory(e.getMessage(), HttpStatus.MULTI_STATUS, null);
			
		}
		
		
		
		
	}
	
	
}
