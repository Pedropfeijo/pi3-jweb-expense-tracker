/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import com.pedropasqualinifeijo.projetointegrador3.repository.UsuariosRepository;
import jakarta.servlet.http.HttpSession;
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
    public String login(@RequestParam String nome, @RequestParam String senha, HttpSession session) {

        Optional<Usuarios> usuario = userRepository.findByNome(nome);

        Usuarios loggedInUser;
        if (usuario.isEmpty()) {
            loggedInUser = new Usuarios();
            loggedInUser.setNome(nome);
            loggedInUser.setSenha(senha);
            userRepository.save(loggedInUser);
        } else {
            loggedInUser = usuario.get();
        }

        session.setAttribute("idUsuario", loggedInUser.getId()); 
        return "redirect:/home";
    }

}
