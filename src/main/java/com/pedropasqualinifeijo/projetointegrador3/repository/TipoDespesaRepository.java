package com.pedropasqualinifeijo.projetointegrador3.repository;

import com.pedropasqualinifeijo.projetointegrador3.model.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Integer> {

}
