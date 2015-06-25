package br.com.alinesolutions.marthalanches.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.alinesolutions.marthalanches.util.Constante;

@Entity
@XmlRootElement
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Pattern(regexp = "[^0-9]*", message = Constante.App.MENSAGEM_NOME_INVALIDO)
	private String nome;

	@NotNull
	@Size(min = 10, max = 10, message = Constante.App.MENSAGEM_TELEFONE_DEZ_NUMEROS)
	@Digits(fraction = 0, integer = 12, message = Constante.App.MENSAGEM_APENAS_NUMEROS)
	private String telefone;

	@NotNull
	private String endereco;

	@NotNull
	@Pattern(regexp = Constante.App.PATTERN_EMAIL, message = Constante.App.MENSAGEM_EMAIL_INVALIDO)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
