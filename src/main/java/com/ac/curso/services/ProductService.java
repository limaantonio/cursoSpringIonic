package com.ac.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ac.curso.domain.Category;
import com.ac.curso.domain.Product;
import com.ac.curso.repositories.CategoryRespository;
import com.ac.curso.repositories.ProductRespository;
import com.ac.curso.service.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private CategoryRespository categoryRepository;
	
	@Autowired
	private ProductRespository respository;
	
	public List<Product> findAll (){
		return respository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = respository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	
	
	public Page<Product> search(String nome, Iterable<Long> ids, Integer page, Integer linesPorPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPorPage, Direction.valueOf(direction),orderBy);
		List<Category> categorias = categoryRepository.findAllById(ids);
		return respository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
		
	}
}
