package com.generation.FarmaSara.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_categoria")
public class CategoriaModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório e não pode estar vazio")
	@Size(min = 3, max = 200, message = "O campo nome precisa ter entre 3 e 50 caracteres")
	private String nome;
	
	
	@NotBlank(message = "O atributo nome é obrigatório e não pode estar vazio")
	@Size(min = 3, max = 300, message = "O campo nome precisa ter entre 3 e 50 caracteres")
	private String descricao; //descrição categoria

@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria")
    private List<ProdutoModel> produto;

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

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public List<ProdutoModel> getProduto() {
	return produto;
}

public void setProduto(List<ProdutoModel> produto) {
	this.produto = produto;
}

	
}
