package com.ac.curso.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbOrder")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;

	
	@OneToOne(mappedBy="pedido", cascade=CascadeType.ALL)
	private Payment pagamento;

	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Client cliente;

	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Address enderecoDeEntrega;
	
	
	@OneToMany(mappedBy = "id.order")
	private Set<ItemOrder> itens = new HashSet<>();

	public Order() {
	}

	public Order(Long id, Date instante, Client cliente, Address enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Payment getPayment() {
		return pagamento;
	}

	public void setPayment(Payment pagamento) {
		this.pagamento = pagamento;
	}

	public Client getClient() {
		return cliente;
	}

	public void setClient(Client cliente) {
		this.cliente = cliente;
	}

	public Address getAddressDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setAddressDeEntrega(Address enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	public Double getValorTotal() {
		Double soma = 0.0;
		
		for(ItemOrder ip : itens) {
			soma += ip.getSubtotal();
		}
		return soma;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<ItemOrder> getItems() {
		return itens;
	}
	
	

	public void setItens(Set<ItemOrder> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy : HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getClient().getName());
		builder.append(", Situação do pagamento");
		builder.append(getPayment().getEstado().getDescricao());
		builder.append("\nDatalhes\n");
		for(ItemOrder ip : getItems()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}

	


}