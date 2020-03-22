package com.ac.curso.service.exceptions;

public class DataIntergrityVicolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntergrityVicolationException(Object id) {
		super("Object not found "+id);
	}
	
}
