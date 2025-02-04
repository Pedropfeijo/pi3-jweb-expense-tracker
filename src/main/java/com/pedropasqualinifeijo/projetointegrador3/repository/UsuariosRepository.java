package com.pedropasqualinifeijo.projetointegrador3.repository;

import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
        Optional<Usuarios> findByNome(String nome);
}
