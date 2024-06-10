package com.generation.FarmaSara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.FarmaSara.model.CategoriaModel;


public interface RepositoryCategoria extends JpaRepository<CategoriaModel, Long>{

	public List <CategoriaModel> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
	public List <CategoriaModel> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
