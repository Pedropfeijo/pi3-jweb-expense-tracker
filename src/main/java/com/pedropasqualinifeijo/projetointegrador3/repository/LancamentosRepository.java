package com.pedropasqualinifeijo.projetointegrador3.repository;

import com.pedropasqualinifeijo.projetointegrador3.model.Lancamentos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentosRepository extends JpaRepository<Lancamentos, Integer> {

    List<Lancamentos> findByUsuarioId(Integer id);
}
