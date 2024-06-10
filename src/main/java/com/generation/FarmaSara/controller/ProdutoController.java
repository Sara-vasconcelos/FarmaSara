package com.generation.FarmaSara.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.FarmaSara.model.ProdutoModel;
import com.generation.FarmaSara.repository.RepositoryProdutos;


import jakarta.validation.Valid;





@RestController  //anotação indicando para a Spring que essa classe é uma controller
@RequestMapping ("/produtos") //rota para chegar nessa classe no insomnia
@CrossOrigin(origins = "*", allowedHeaders = "*")//liberando acesso
public class ProdutoController {

	
	@Autowired //injeção de dependencia
	private RepositoryProdutos repositoryProdutos;
	
	//@Autowired
	//private CategoriaRepository categoriaRepository;
	
	//Listar todos os produtos 
	
	@GetMapping //Verbo HTTP
	public ResponseEntity<List<ProdutoModel>> getAll(){
		return ResponseEntity.ok(repositoryProdutos.findAll());
	
	}
	
	
	//BUSCAR produto por ID
	
	@GetMapping("/{id}") //busca por id
	public ResponseEntity<ProdutoModel> getById(@PathVariable Long id){
		
		
		return repositoryProdutos.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))//vai mapear as respotas e vai dar uma mensagem de retorno caso dê ok 
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
	
	
	//SELECIONAR por nome
	
		@GetMapping ("/nome/{nome}") 
		public  ResponseEntity<List<ProdutoModel>> getByNome(@PathVariable String nome){
			return  ResponseEntity.ok(repositoryProdutos.findAllByNomeContainingIgnoreCase(nome));
			// retorna o nome , com o metodo personalizado que que criamos na repository
	
		}
		
		//CADASTRAR produtos
		
		@PostMapping //cria uma requisição
		public ResponseEntity<ProdutoModel> post(@Valid @RequestBody ProdutoModel produto){
		
			return  ResponseEntity.status(HttpStatus.CREATED)
					.body(repositoryProdutos.save(produto));
		}	
		
		@PutMapping //altera e atualiza
		public ResponseEntity<ProdutoModel> put(@Valid @RequestBody ProdutoModel produto){
			
			return repositoryProdutos.findById(produto.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(repositoryProdutos.save(produto)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		//DELETAR Produto
		
		//optional : não quebra a aplicação e retorna algo sem quebrar o código mesmo que o dado esteja vazio
		
		 @ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}") // garantir que vai pedir um numero de id para deletar(SEGURANÇA)
		public void delete(@PathVariable Long id) {
			Optional<ProdutoModel> produtos = repositoryProdutos.findById(id); //vai trazer a linha que eu mencionei
			if(produtos.isEmpty())//objeto  postagem está vazio?
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);//retorna um status de exception , caso não encontre o id , com a mensagem NOT FOUND
			
			repositoryProdutos.deleteById(id);
		 }
}
