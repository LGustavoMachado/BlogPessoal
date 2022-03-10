package org.generation.blogpessoallg.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoallg.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{
    
    public List <TemaModel> findAllByDescricaoContainingIgnoreCase (String descricao);

	public Optional<TemaModel> findByDescricao(String string);

}