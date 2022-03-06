package org.generation.blogpessoallg.repository;

import java.util.List;

import org.generation.blogpessoallg.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{
	
	public List<TemaModel> findAllByCategoriaContainingIgnoreCase(String categoria);
}
