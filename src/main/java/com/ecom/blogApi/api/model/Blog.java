package com.ecom.blogApi.api.model;

import java.util.Objects;
import com.ecom.blogApi.api.model.BlogImages;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Blog
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-19T17:54:41.173460500+05:30[Asia/Calcutta]")

public class Blog   {
  @JsonProperty("blogId")
  private Integer blogId;

  @JsonProperty("authorName")
  private String authorName;

  @JsonProperty("blogTitle")
  private String blogTitle;

  @JsonProperty("description")
  private String description;

  @JsonProperty("seoTitle")
  private String seoTitle;

  @JsonProperty("seoMetaDescription")
  private String seoMetaDescription;

  @JsonProperty("status")
  private String status;

  @JsonProperty("blogSubImage")
  private BlogImages blogSubImage;

  @JsonProperty("blogBannerImage")
  private BlogImages blogBannerImage;

  public Blog blogId(Integer blogId) {
    this.blogId = blogId;
    return this;
  }

  /**
   * Get blogId
   * @return blogId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getBlogId() {
    return blogId;
  }

  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }

  public Blog authorName(String authorName) {
    this.authorName = authorName;
    return this;
  }

  /**
   * Get authorName
   * @return authorName
  */
  @ApiModelProperty(value = "")

@Size(max=120) 
  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public Blog blogTitle(String blogTitle) {
    this.blogTitle = blogTitle;
    return this;
  }

  /**
   * Get blogTitle
   * @return blogTitle
  */
  @ApiModelProperty(value = "")

@Size(max=200) 
  public String getBlogTitle() {
    return blogTitle;
  }

  public void setBlogTitle(String blogTitle) {
    this.blogTitle = blogTitle;
  }

  public Blog description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(value = "")

@Size(max=400) 
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Blog seoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
    return this;
  }

  /**
   * Get seoTitle
   * @return seoTitle
  */
  @ApiModelProperty(value = "")

@Size(max=80) 
  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }

  public Blog seoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
    return this;
  }

  /**
   * Get seoMetaDescription
   * @return seoMetaDescription
  */
  @ApiModelProperty(value = "")

@Size(max=200) 
  public String getSeoMetaDescription() {
    return seoMetaDescription;
  }

  public void setSeoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
  }

  public Blog status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Blog blogSubImage(BlogImages blogSubImage) {
    this.blogSubImage = blogSubImage;
    return this;
  }

  /**
   * Get blogSubImage
   * @return blogSubImage
  */
  @ApiModelProperty(value = "")

  @Valid

  public BlogImages getBlogSubImage() {
    return blogSubImage;
  }

  public void setBlogSubImage(BlogImages blogSubImage) {
    this.blogSubImage = blogSubImage;
  }

  public Blog blogBannerImage(BlogImages blogBannerImage) {
    this.blogBannerImage = blogBannerImage;
    return this;
  }

  /**
   * Get blogBannerImage
   * @return blogBannerImage
  */
  @ApiModelProperty(value = "")

  @Valid

  public BlogImages getBlogBannerImage() {
    return blogBannerImage;
  }

  public void setBlogBannerImage(BlogImages blogBannerImage) {
    this.blogBannerImage = blogBannerImage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Blog blog = (Blog) o;
    return Objects.equals(this.blogId, blog.blogId) &&
        Objects.equals(this.authorName, blog.authorName) &&
        Objects.equals(this.blogTitle, blog.blogTitle) &&
        Objects.equals(this.description, blog.description) &&
        Objects.equals(this.seoTitle, blog.seoTitle) &&
        Objects.equals(this.seoMetaDescription, blog.seoMetaDescription) &&
        Objects.equals(this.status, blog.status) &&
        Objects.equals(this.blogSubImage, blog.blogSubImage) &&
        Objects.equals(this.blogBannerImage, blog.blogBannerImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blogId, authorName, blogTitle, description, seoTitle, seoMetaDescription, status, blogSubImage, blogBannerImage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Blog {\n");
    
    sb.append("    blogId: ").append(toIndentedString(blogId)).append("\n");
    sb.append("    authorName: ").append(toIndentedString(authorName)).append("\n");
    sb.append("    blogTitle: ").append(toIndentedString(blogTitle)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    seoTitle: ").append(toIndentedString(seoTitle)).append("\n");
    sb.append("    seoMetaDescription: ").append(toIndentedString(seoMetaDescription)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    blogSubImage: ").append(toIndentedString(blogSubImage)).append("\n");
    sb.append("    blogBannerImage: ").append(toIndentedString(blogBannerImage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

