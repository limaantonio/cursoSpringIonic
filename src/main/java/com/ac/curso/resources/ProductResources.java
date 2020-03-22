package com.ac.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ac.curso.domain.Category;
import com.ac.curso.domain.Product;
import com.ac.curso.dto.CategoryDTO;
import com.ac.curso.dto.ProductDTO;
import com.ac.curso.resources.util.URL;
import com.ac.curso.services.ProductService;

@RestController
@RequestMapping (value = "/products")
public class ProductResources {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")  Integer linesPorPage, 
			@RequestParam(value = "orderBy", defaultValue = "name")  String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")  String direction){
		
		String decoded = URL.decodeParam(name);
		Iterable<Long> ids = URL.decodeIntList(categories);
		Page<Product> list = service.search(decoded, ids, page, linesPorPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
