package com.ac.curso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.Category;

public interface CategoryRespository extends JpaRepository<Category, Long>{

	
}
