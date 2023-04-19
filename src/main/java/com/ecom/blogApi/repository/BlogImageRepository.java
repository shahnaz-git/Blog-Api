package com.ecom.blogApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.blogApi.datamodel.BlogImageData;

public interface BlogImageRepository extends JpaRepository<BlogImageData, Integer> {

}
