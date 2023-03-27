package com.ecom.blogApi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BlogCategory
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T15:29:15.714023765+05:30[Asia/Kolkata]")

public class BlogCategory   {
  @JsonProperty("blogcategoryId")
  private Integer blogcategoryId;

  @JsonProperty("BlogCategoryName")
  private String blogCategoryName;

  @JsonProperty("SeoTitle")
  private String seoTitle;

  @JsonProperty("SeoMetaDescription")
  private String seoMetaDescription;

  @JsonProperty("Status")
  private String status;

  public BlogCategory blogcategoryId(Integer blogcategoryId) {
    this.blogcategoryId = blogcategoryId;
    return this;
  }

  /**
   * Get blogcategoryId
   * @return blogcategoryId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getBlogcategoryId() {
    return blogcategoryId;
  }

  public void setBlogcategoryId(Integer blogcategoryId) {
    this.blogcategoryId = blogcategoryId;
  }

  public BlogCategory blogCategoryName(String blogCategoryName) {
    this.blogCategoryName = blogCategoryName;
    return this;
  }

  /**
   * Get blogCategoryName
   * @return blogCategoryName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=120) 
  public String getBlogCategoryName() {
    return blogCategoryName;
  }

  public void setBlogCategoryName(String blogCategoryName) {
    this.blogCategoryName = blogCategoryName;
  }

  public BlogCategory seoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
    return this;
  }

  /**
   * Get seoTitle
   * @return seoTitle
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=200) 
  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }

  public BlogCategory seoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
    return this;
  }

  /**
   * Get seoMetaDescription
   * @return seoMetaDescription
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=200) 
  public String getSeoMetaDescription() {
    return seoMetaDescription;
  }

  public void setSeoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
  }

  public BlogCategory status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")

@Size(max=6) 
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlogCategory blogCategory = (BlogCategory) o;
    return Objects.equals(this.blogcategoryId, blogCategory.blogcategoryId) &&
        Objects.equals(this.blogCategoryName, blogCategory.blogCategoryName) &&
        Objects.equals(this.seoTitle, blogCategory.seoTitle) &&
        Objects.equals(this.seoMetaDescription, blogCategory.seoMetaDescription) &&
        Objects.equals(this.status, blogCategory.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blogcategoryId, blogCategoryName, seoTitle, seoMetaDescription, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlogCategory {\n");
    
    sb.append("    blogcategoryId: ").append(toIndentedString(blogcategoryId)).append("\n");
    sb.append("    blogCategoryName: ").append(toIndentedString(blogCategoryName)).append("\n");
    sb.append("    seoTitle: ").append(toIndentedString(seoTitle)).append("\n");
    sb.append("    seoMetaDescription: ").append(toIndentedString(seoMetaDescription)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

