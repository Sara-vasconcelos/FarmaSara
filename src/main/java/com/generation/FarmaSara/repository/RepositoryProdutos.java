package com.generation.FarmaSara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.FarmaSara.model.ProdutoModel;



public interface RepositoryProdutos extends JpaRepository<ProdutoModel,Long>{

	
	
	public List <ProdutoModel> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);

}
