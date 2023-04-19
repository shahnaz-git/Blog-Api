package com.ecom.blogApi.datamodel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog_category_images")
public class BlogCategoryImageData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_category_images_id")
	private int blogImagesId;

	@Column(name = "category_image_name")
	private String categoryImageName;

	@Column(name = "category_image_url")
	private String categoryImageUrl;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_category_id")
	private BlogCategoryData blogCategoryData;

	public BlogCategoryData getBlogCategoryData() {
		return blogCategoryData;
	}

	public void setBlogCategoryData(BlogCategoryData blogCategoryData) {
		this.blogCategoryData = blogCategoryData;
	}

	public int getBlogImagesId() {
		return blogImagesId;
	}

	public void setBlogImagesId(int blogImagesId) {
		this.blogImagesId = blogImagesId;
	}

	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}

	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}

	public String getCategoryImageName() {
		return categoryImageName;
	}

	public void setCategoryImageName(String categoryImageName) {
		this.categoryImageName = categoryImageName;
	}

}
