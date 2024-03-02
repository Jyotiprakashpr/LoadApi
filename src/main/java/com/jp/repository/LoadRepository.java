package com.jp.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.entities.Load;

public interface LoadRepository extends JpaRepository<Load, Serializable> {
	
	List<Load> findByShipperId(String shipperId);

	

}
