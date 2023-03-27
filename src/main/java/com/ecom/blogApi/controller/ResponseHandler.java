package com.ecom.blogApi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecom.blogApi.api.model.BlogCategory;

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


}
