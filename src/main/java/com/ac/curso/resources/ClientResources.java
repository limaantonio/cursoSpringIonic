package com.ac.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ac.curso.domain.Client;
import com.ac.curso.dto.ClientDTO;
import com.ac.curso.dto.ClientNewDTO;
import com.ac.curso.services.ClientService;

@RestController
@RequestMapping (value = "/clients")
public class ClientResources {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> list = service.findAll();
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")  Integer linesPorPage, 
			@RequestParam(value = "orderBy", defaultValue = "name")  String orderBy,
			@RequestParam(value = "diretion", defaultValue = "ASC")  String direction){
		
		Page<Client> list = service.findPage(page, linesPorPage, orderBy, direction);
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody ClientDTO objDTO){
		Client obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO){
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	}
	
}
