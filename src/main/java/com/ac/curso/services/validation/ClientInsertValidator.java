package com.ac.curso.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ac.curso.domain.Client;
import com.ac.curso.domain.enums.TipoCliente;
import com.ac.curso.dto.ClientNewDTO;
import com.ac.curso.repositories.ClientRespository;
import com.ac.curso.resources.exceptions.FieldMessage;
import com.ac.curso.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRespository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}
	
	

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo() == TipoCliente.PESSOAFISICA.getCod() && !BR.isValidCPF(objDto.getCep())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		
		if(objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getCod() && !BR.isValidCNPJ(objDto.getCep())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}