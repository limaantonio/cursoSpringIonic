package com.ac.curso.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemOrderPk id = new ItemOrderPk();
	
	private Double desconto;
	private Integer qtde;
	private Double price;
	
	public ItemOrder() {}

	public ItemOrder(Order order, Product product, Double desconto, Integer qtde, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.desconto = desconto;
		this.qtde = qtde;
		this.price = price;
	}

	public ItemOrderPk getId() {
		return id;
	}

	public void setId(ItemOrderPk id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order o) {
		id.setOrder(o);
	}
	
	public void setProduct(Product p) {
		id.setProduct(p);
	}
	
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public Double getSubtotal() {
		return (price - desconto) * qtde;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrder other = (ItemOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Qtde: ");
		builder.append(nf.format(getQtde()));
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubtotal()));
		builder.append("\n");
		
		return builder.toString();
	}
	
	
	
	
	
}
