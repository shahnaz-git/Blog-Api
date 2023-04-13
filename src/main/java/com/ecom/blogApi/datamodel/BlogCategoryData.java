package com.ecom.blogApi.datamodel;

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
@Table(name = "blog_categoryab")
public class BlogCategoryData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_category_id")
	private int blogCategoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "seo_title")
	private String seoTitle;

	@Column(name = "seo_meta_desc")
	private String seoMetaDesc;

	@Column(name = "status")
	private String status;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "blogCategoryData", cascade = CascadeType.ALL)
	private BlogCategoryImageData blogCategoryImageData;

	public BlogCategoryImageData getBlogCategoryImageData() {
		return blogCategoryImageData;
	}

	public void setBlogCategoryImageData(BlogCategoryImageData blogCategoryImageData) {
		this.blogCategoryImageData = blogCategoryImageData;
	}

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
