//package com.ecom.blogApi.datamodel;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="blog_category_images")
//public class BlogCategoryImageData {
//	   
//	  @Id
//	  @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  @Column(name="blog_category_images_id")
//       private int blogImagesId;
//	  
//	  @Column(name="category_image")
//       private byte[] categoryImage;
//	  
//	  @Column(name="category_image_url")
//	  private String categoryImageUrl;
//	  
//     @Column(name="status")
//       private int status;
//     
//     @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//      private BlogCategoryImageData blogCategoryImageData ;
//       
//     public BlogCategoryImageData getBlogCategoryImageData() {
//  	   return blogCategoryImageData;
//     }
//     public void setBlogCategoryImageData(BlogCategoryImageData blogCategoryImageData) {
//  	   this.blogCategoryImageData=blogCategoryImageData;
//     }
//     
//       public int getBlogImagesId() {
//    	   return blogImagesId; 
//       }
//       
//       public void setBlogImagesId(int blogImagesId) {
//    	   this.blogImagesId=blogImagesId;
//       }
//
//	public byte[] getCategoryImage() {
//		return categoryImage;
//	}
//
//	public void setCategoryImage(byte[] categoryImage) {
//		this.categoryImage = categoryImage;
//	}
//	
//	public String getCategoryImageUrl() {
//		return categoryImageUrl;
//	}
//
//	public void setCategoryImageUrl(String categoryImageUrl) {
//		this.categoryImageUrl = categoryImageUrl;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//       
//       
//}
