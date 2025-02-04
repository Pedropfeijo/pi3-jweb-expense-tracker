
package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import com.pedropasqualinifeijo.projetointegrador3.repository.UsuariosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController {
    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @GetMapping
    public List<Usuarios> getAllUsers() {
        return usuariosRepository.findAll();
    }
}
