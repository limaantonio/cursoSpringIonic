package com.ac.curso.domain.enums;

public enum StatusPayment {

	PENDETE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	int cod;
	String descricao;
	
	private StatusPayment(int id, String descricao) {
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
	

	public static StatusPayment toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(StatusPayment x : StatusPayment.values()) {
			if(id.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido"+id);
	}
	
}
