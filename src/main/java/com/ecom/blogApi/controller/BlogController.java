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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.Blog;
import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.service.BlogService;

@RestController
@RequestMapping("/api/v1")
public class BlogController {

	@Autowired
	BlogService blogService;

	@GetMapping("/getallblogs")
	ResponseEntity<Object> getAllBlogCategory() {
		try {
			List<Blog> blog = blogService.getAllBlogs();
			return new ResponseEntity<>(blog, HttpStatus.OK);

		} catch (Exception ex) {
			return ResponseHandler.generateResponseForBlog(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		}

	}

	@PostMapping(value = "/addblog")
	ResponseEntity<Object> postBlogs(@RequestParam("categoryId") int categoryId,
			@RequestParam("authorName") String authorName, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("description") String description, @RequestParam("seoTitle") String seoTitle,
			@RequestParam("seoMetaDescription") String seoMetaDescription, @RequestParam("status") String status,
			@RequestParam("imgData") MultipartFile imgData, @RequestParam("bannerData") MultipartFile bannerData) {

		try {
			Blog blog = blogService.createBlog(categoryId, authorName, blogTitle, description, seoTitle,
					seoMetaDescription, status, imgData, bannerData);
			return ResponseHandler.generateResponseForBlog("Sucessfully added data", HttpStatus.CREATED, blog);
		} catch (Exception ex) {
			return ResponseHandler.generateResponseBlogCategory(ex.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@GetMapping(value = "/getsingleblog/{blogId}")
	ResponseEntity<Object> getBlogById(@PathVariable int blogId) {

		try {
			Blog singleBlog = blogService.getSingleBlog(blogId);

			return new ResponseEntity<>(singleBlog, HttpStatus.OK);
		} catch (Exception e) {

			return ResponseHandler.generateResponseForBlog(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}

	@PutMapping(value = "/updateblog/{blogId}")
	ResponseEntity<Object> updateBlogAndImage(@PathVariable("blogId") int blogId,
			@RequestParam("categoryId") int categoryId, @RequestParam("authorName") String authorName,
			@RequestParam("blogTitle") String blogTitle, @RequestParam("description") String description,
			@RequestParam("seoTitle") String seoTitle, @RequestParam("seoMetaDesc") String seoMetaDescription,
			@RequestParam("status") String status, @RequestParam("blogImage") MultipartFile imageData,
			@RequestParam("blogBanner") MultipartFile bannerData) {

		try {
			Blog blogResponse = blogService.updateBlog(blogId, categoryId, authorName, blogTitle, description, seoTitle,
					seoMetaDescription, status, imageData, bannerData);

			return ResponseHandler.generateResponseForBlog("Data is Updated...!", HttpStatus.OK, blogResponse);
		} catch (Exception ex) {

			return ResponseHandler.generateResponseForBlog(ex.getMessage(), HttpStatus.MULTI_STATUS, null);
		}

	}

	@DeleteMapping(value = "/deleteblog/{blogid}")
	ResponseEntity<Object> deleteBlog(@PathVariable("blogid") int id) {
		try {
			Blog blogResponse = blogService.deleteBlog(id);
			return ResponseHandler.generateResponseForBlog("Successfully deleted data!", HttpStatus.OK, blogResponse);
		} catch (Exception ex) {

			return ResponseHandler.generateResponseForBlog(ex.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

}
