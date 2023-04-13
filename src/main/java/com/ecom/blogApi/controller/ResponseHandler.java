package com.ecom.blogApi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.api.model.BlogCategoryImage;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponseBlogCategory(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (responseObj != null && (responseObj instanceof BlogCategory)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogcategoryId", ((BlogCategory) responseObj).getBlogcategoryId());
			return new ResponseEntity<Object>(map, status);
		}else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogcategoryId", null);
			return new ResponseEntity<Object>(map, status);
		}
		
	}
	
	public static ResponseEntity<Object> generateResponseBlogCategoryImage(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (responseObj != null && (responseObj instanceof BlogCategoryImage)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogCategoryImageId", ((BlogCategoryImage) responseObj).getBlogCategoryImageId());
			return new ResponseEntity<Object>(map, status);
		}else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogCategoryImageId", null);
			return new ResponseEntity<Object>(map, status);
		}
		
	}


}
