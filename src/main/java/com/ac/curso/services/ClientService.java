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

import com.ac.curso.domain.Address;
import com.ac.curso.domain.City;
import com.ac.curso.domain.Client;
import com.ac.curso.domain.enums.TipoCliente;
import com.ac.curso.dto.ClientDTO;
import com.ac.curso.dto.ClientNewDTO;
import com.ac.curso.repositories.AddressRespository;
import com.ac.curso.repositories.CityRespository;
import com.ac.curso.repositories.ClientRespository;
import com.ac.curso.service.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRespository repository;
	
	@Autowired
	private CityRespository cityRepository;
	
	@Autowired
	private AddressRespository addressRespository;
	
	public List<Client> findAll (){
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
		
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir um cliente por que há pedidos relacionados.");
		}	
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repository.save(obj);
		addressRespository.saveAll(obj.getAddresses());
		return repository.save(obj);
	}
	
	public Page<Client> findPage(Integer page, Integer linesPorPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPorPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), objDto.getTipo());
		
		City c = cityRepository.getOne(objDto.getCidadeId());
		
		Address end = new Address(null, objDto.getLogradoro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, c);
		
		cli.getAddresses().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
			
	}
	
}
