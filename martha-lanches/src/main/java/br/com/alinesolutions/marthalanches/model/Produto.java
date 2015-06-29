package br.com.alinesolutions.marthalanches.model;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.alinesolutions.marthalanches.util.Constante;

@Entity
@XmlRootElement
public class Produto extends BaseEntity {

	private static final long serialVersionUID = -9095763252035874978L;

	@Pattern(regexp = "[^0-9]*", message = Constante.App.MENSAGEM_NOME_INVALIDO)
	private String nome;

	@Digits(fraction = 2, integer = 12, message = Constante.App.MENSAGEM_APENAS_NUMEROS_DECIMAIS)
	private Double precoCusto;
	
	@Digits(fraction = 2, integer = 12, message = Constante.App.MENSAGEM_APENAS_NUMEROS_DECIMAIS)
	private Double precoVenda;
		
	@Digits(fraction = 0, integer = 12, message = Constante.App.MENSAGEM_APENAS_NUMEROS_DECIMAIS)
	private Integer quantidadeEstoque;
	
	private String icone;
	
	public Produto() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}
		
}
