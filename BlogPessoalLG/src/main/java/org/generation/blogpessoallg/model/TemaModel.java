package org.generation.blogpessoallg.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="tb_temas")
public class TemaModel {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 100)
	private String categoria;
	
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<PostagemModel> postagem;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public List<PostagemModel> getPostagem() {
		return postagem;
	}


	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}


	
}
