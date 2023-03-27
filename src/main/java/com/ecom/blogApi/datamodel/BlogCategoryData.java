package com.ecom.blogApi.datamodel;
//
//import com.ecom.blogApi.api.model.BlogCategoryImages;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="blog_category")
public class BlogCategoryData {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blog_category_id")
	 private int blogCategoryId;
	
	@Column(name="category_name")
	 private String categoryName;
	
	@Column(name="seo_title")
	 private String seoTitle;
	
	@Column(name="seo_meta_desc")
	 private String seoMetaDesc;
	
	@Column(name="status")
	 private String status;
	
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY , mappedBy = "blogCategoryData")
//	private BlogCategoryImages blogCategoryImages;
	 
	 
//	  public BlogCategoryImages getBlogCategoryImages() {
//	  	   return blogCategoryImages;
//	     }
//	     public void setBlogCategoryImages(BlogCategoryImages blogCategoryImages) {
//	  	   this.blogCategoryImages=blogCategoryImages;
//	     }
	
	public int getBlogCategoryId() {
		return blogCategoryId;
	}
	public void setBlogCategoryId(int blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSeoTitle() {
		return seoTitle;
	}
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	public String getSeoMetaDesc() {
		return seoMetaDesc;
	}
	public void setSeoMetaDesc(String seoMetaDesc) {
		this.seoMetaDesc = seoMetaDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	 
	 
	 
	 
}
