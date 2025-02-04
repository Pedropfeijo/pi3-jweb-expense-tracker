/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import com.pedropasqualinifeijo.projetointegrador3.repository.UsuariosRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuariosController {

    @GetMapping("/login")
    public String exibirFormulario(Model model) {
        model.addAttribute("Usuarios", new Usuarios());
        return "login";
    }

    @Autowired
    private UsuariosRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String nome, @RequestParam String senha) {
        Optional<Usuarios> user = userRepository.findByNome(nome);
        if (user.isEmpty()) {
            Usuarios novoUsuario = new Usuarios();
            novoUsuario.setNome(nome);
            novoUsuario.setSenha(senha);
            userRepository.save(novoUsuario);
        }
        return "home";
    }
}

