package com.google.appengine.samples.angularjs_guestbook.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Entity;

@XmlRootElement
public class Produto {
	
	private Long id;
	
	private String nomeProduto;

	private Double precoCusto;

	private Double precoVenda;

	public Produto() {
		super();
	}
	
	public Produto(String nomeProduto, Double precoCusto, Double precoVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
	}

	public static Produto fromEntity(Entity greetingEntity) {
		return new Produto((String) greetingEntity.getProperty("nomeProduto"), 
						   (Double) greetingEntity.getProperty("precoCusto"), 
						   (Double) greetingEntity.getProperty("precoVenda"));
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
