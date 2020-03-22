package com.ac.curso.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ac.curso.domain.Category;
import com.ac.curso.dto.CategoryDTO;
import com.ac.curso.repositories.CategoryRespository;
import com.ac.curso.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRespository repository;
	
	public List<Category> findAll (){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	@Transactional
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	
	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
		
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	
		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir uma categoria que possui produtos.");
		}	
	}
	
	public Page<Category> findPage(Integer page, Integer linesPorPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPorPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO obj) {
		return new Category(obj.getId(), obj.getName());
	}
	
	
	
}
