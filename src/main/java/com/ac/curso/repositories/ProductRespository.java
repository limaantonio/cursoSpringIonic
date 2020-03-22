package com.ac.curso.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ac.curso.domain.Category;
import com.ac.curso.domain.Product;

@Repository
@Transactional(readOnly = true)
public interface ProductRespository extends JpaRepository<Product, Long> {
@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")

	Page<Product> findDistinctByNomeContainingAndCategoriasIn(@Param("name") String name,
			@Param("categories") List<Category> categories, Pageable pageRequest);
}