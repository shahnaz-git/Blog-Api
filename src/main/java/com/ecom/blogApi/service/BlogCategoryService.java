package com.ecom.blogApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.blogApi.api.model.BlogCategory;
//import com.ecom.blogApi.api.model.BlogCategoryImages;
import com.ecom.blogApi.datamodel.BlogCategoryData;
//import com.ecom.blogApi.datamodel.BlogCategoryImageData;
//import com.ecom.blogApi.repository.BlogCategoryImageRepository;
import com.ecom.blogApi.repository.BlogCategoryRepository;

@Service
public class BlogCategoryService {
	
	BlogCategoryData blogCategoryData;
	
	List<BlogCategoryData> blogCategoryDataList;
	
	
	Optional<BlogCategoryData>  blogCategoryDataOptional;
	
	@Autowired
	BlogCategoryRepository blogCategoryRepository;
	
//	@Autowired
//	BlogCategoryImageRepository blogCategoryImageRepository;
	
	public BlogCategory createBlogCategory(BlogCategory blgCategory) throws Exception {
		
		blogCategoryData = new BlogCategoryData();
			try {

				blogCategoryData.setCategoryName(blgCategory.getBlogCategoryName());
				blogCategoryData.setSeoTitle(blgCategory.getSeoTitle());
				blogCategoryData.setSeoMetaDesc(blgCategory.getSeoMetaDescription());
				blogCategoryData.setStatus("Active");
				blogCategoryData = blogCategoryRepository.save(blogCategoryData);
				
				BlogCategory ab = new BlogCategory();
				ab.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
				
				return ab;				
				
			
			}catch (Exception e) {
				
				throw new Exception("unable to save data" + e.getMessage());
			}
	}
	
	public List<BlogCategory> getallBlogCategory() throws Exception{
	try {
		List<BlogCategory> blogCategory= new ArrayList<BlogCategory>();
		List<BlogCategoryData> blogCategoryData = blogCategoryRepository.findByStatus("Active");
		
		if(blogCategoryData.size()>0) {
			for(BlogCategoryData blogCateData : blogCategoryData) {
				BlogCategory bcategory= new BlogCategory();
				
				bcategory.setBlogCategoryName(blogCateData.getCategoryName());
				bcategory.setSeoMetaDescription(blogCateData.getSeoMetaDesc());
				bcategory.setSeoTitle(blogCateData.getSeoTitle());
				bcategory.setBlogcategoryId(blogCateData.getBlogCategoryId());
				bcategory.setStatus("Active");
				blogCategory.add(bcategory);

				
			}
		}
		
		return blogCategory;
		}catch(Exception v) {
			throw new Exception("Data not found");
		}
	}
	
	public BlogCategory getSingleBlogCategory(int blogCategoryId) throws Exception {
		
		blogCategoryDataOptional =  blogCategoryRepository.findById(blogCategoryId);
		 
		if(blogCategoryDataOptional.isPresent()) {
			blogCategoryData = blogCategoryDataOptional.get();
			BlogCategory blogCate = new BlogCategory();
			blogCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
			blogCate.setBlogCategoryName(blogCategoryData.getCategoryName());
			blogCate.setSeoMetaDescription(blogCategoryData.getSeoMetaDesc());
			blogCate.setSeoTitle(blogCategoryData.getSeoTitle());
			blogCate.setStatus(blogCategoryData.getStatus());
			return blogCate;
		}else {
			throw new Exception("Data not found!");
		}					
	}

	public BlogCategory updateBlogCategory (int id,BlogCategory blogCate ) {
		
		 BlogCategory bCate;
		 
		 blogCategoryDataOptional = blogCategoryRepository.findById(id);
		 if(blogCategoryDataOptional.isPresent()) {
			 
			 blogCategoryData = blogCategoryDataOptional.get();
			 
			 blogCategoryData.setCategoryName(blogCate.getBlogCategoryName());
			 blogCategoryData.setSeoMetaDesc(blogCate.getSeoMetaDescription());
			 blogCategoryData.setSeoTitle(blogCate.getSeoTitle());
			 blogCategoryData.setStatus(blogCate.getStatus());
		 }
		 
		 blogCategoryRepository.save(blogCategoryData);
		 
		 bCate= new BlogCategory();
		 bCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
		 
		
		return bCate;
		
	}
	
	public BlogCategory deleteBlogCategory(int blogcategoryId) {
		
		BlogCategory bb;
		blogCategoryDataOptional = blogCategoryRepository.findById(blogcategoryId);
		
		if(blogCategoryDataOptional.isPresent()) {
			
			blogCategoryData = blogCategoryDataOptional.get();
			
			blogCategoryData.setStatus("Inactive");
			
		}
		
		blogCategoryRepository.save(blogCategoryData);
		
		bb = new BlogCategory();
		bb.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
		
		return bb;
		
	}
	
}
