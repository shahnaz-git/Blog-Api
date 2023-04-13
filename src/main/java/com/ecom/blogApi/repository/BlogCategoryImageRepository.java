package com.ecom.blogApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.blogApi.datamodel.BlogCategoryImageData;

public interface BlogCategoryImageRepository extends JpaRepository<BlogCategoryImageData, Integer>{

}
