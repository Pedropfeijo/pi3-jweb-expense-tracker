package com.pedropasqualinifeijo.projetointegrador3.repository;

import com.pedropasqualinifeijo.projetointegrador3.model.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Integer> {

}
