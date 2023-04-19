package com.ecom.blogApi.datamodel;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "blogs")
public class BlogData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_id")
	private Integer blogId;
	
	@Column(name = "blog_category_id")
	private Integer blogCategoryId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "blog_title")
	private String blogTitle;

	@Column(name = "descriptions")
	private String description;
	
	@Column(name="seo_title")
	private String seoTitle;

	@Column(name = "seo_meta_desc")
	private String seoMetaDescription;

	@Column(name = "status")
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blogData", cascade = CascadeType.ALL)
	private List<BlogImageData> blogImageData;
	
	public List<BlogImageData> getBlogImageData() {
		return blogImageData;
	}

	public void setBlogImageData(List<BlogImageData> blogImageData) {
		this.blogImageData = blogImageData;
	}

	public Integer getBlogCategoryId() {
		return blogCategoryId;
	}

	public void setBlogCategoryId(Integer blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeoMetaDescription() {
		return seoMetaDescription;
	}

	public void setSeoMetaDescription(String seoMetaDescription) {
		this.seoMetaDescription = seoMetaDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	
}
