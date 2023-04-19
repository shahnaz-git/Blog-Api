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
import jakarta.persistence.Table;

@Entity
@Table(name ="blog_images")
public class BlogImageData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_image_id")
	private Integer blogImageId;

	@Column(name = "blog_image_name")
	private String blogImageName;

	@Column(name = "download_url")
	private String imageDownloadUrl;

	@Column(name = "image_type")
	private String imgType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private BlogData blogData;

	public BlogData getBlogData() {
		return blogData;
	}

	public void setBlogData(BlogData blogData) {
		this.blogData = blogData;
	}

	public Integer getBlogImageId() {
		return blogImageId;
	}

	public void setBlogImageId(Integer blogImageId) {
		this.blogImageId = blogImageId;
	}

	public String getBlogImageName() {
		return blogImageName;
	}

	public void setBlogImageName(String blogImageName) {
		this.blogImageName = blogImageName;
	}

	public String getImageDownloadUrl() {
		return imageDownloadUrl;
	}

	public void setImageDownloadUrl(String imageDownloadUrl) {
		this.imageDownloadUrl = imageDownloadUrl;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	
}
