package com.ecom.blogApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.blogApi.datamodel.BlogData;

public interface BlogRepository extends JpaRepository<BlogData, Integer> {
	
	List<BlogData> findByStatus(String status);

}
