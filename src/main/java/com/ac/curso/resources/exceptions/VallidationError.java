package com.ac.curso.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class VallidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public VallidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String name, String message) {
		errors.add(new FieldMessage(name, message));  
	}
	
	
	

}
