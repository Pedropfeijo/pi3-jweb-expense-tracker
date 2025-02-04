
package com.pedropasqualinifeijo.projetointegrador3.service;

import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import com.pedropasqualinifeijo.projetointegrador3.repository.UsuariosRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepository userRepository;

    public Optional<Usuarios> findByNome(String nome) {
        return userRepository.findByNome(nome);
    }

    public Usuarios salvarUsuario(Usuarios usuario) {
        return userRepository.save(usuario);
    }
}
