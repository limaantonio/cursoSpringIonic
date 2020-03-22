package com.ac.curso.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ac.curso.domain.Client;
import com.ac.curso.services.validation.ClientInsert;
import com.ac.curso.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5)
	private String name;
	@NotEmpty(message = "Preechimento obrigatorio")
	@Email(message = "E-mail invalido")
	private String email;
	
	public ClientDTO() {}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getEmail();
		email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
