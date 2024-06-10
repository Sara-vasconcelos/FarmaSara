package com.generation.FarmaSara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity //vai ser uma tabela (entidade)
@Table(name="tb_produtos")  //nome da tabela
public class ProdutoModel {


	@Id // torna o id uma chave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) // faz com que a chave seja auto increment
	private Long id; //tipo long no Java  e no banco de dados será criado como BIGINT
	
	
	@NotBlank(message = "Este campo é obrigatorio")//validation , vai validar o atributo NOT NULL e não deixa ir vazio
	@Size(max=200, message  = "O nome do produto deve ter no maximo 200 caracteres") // tamanho min e max de caracter, caso o usuario não cumpra essa regra aparece essa mensagem
	private String  nome;
	
	@NotBlank(message = "Este campo é obrigatorio")
	@Size(min = 3, max=200, message  = "A descrição  do produto deve ter no maximo 200 caracteres")
	private String descricaoProduto;
	
	@Positive //não pode ser negativo
	@NotNull(message = "Este campo é obrigatorio")
	private float preco;
	
	@ManyToOne //muitos para 1
	@JsonIgnoreProperties("produto")// indica que uma parte do JSON será ignorado, impede que um looping seja criado
	private CategoriaModel categoria;

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

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}


	
	
}
