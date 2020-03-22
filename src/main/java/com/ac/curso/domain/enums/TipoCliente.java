package com.ac.curso.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	int cod;
	String descricao;
	
	private TipoCliente(int id, String descricao) {
		this.cod = id;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int id) {
		this.cod = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoCliente toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(id.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido"+id);
	}
	
	
	
	
	
	
	
}
