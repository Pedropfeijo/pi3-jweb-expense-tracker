package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.Lancamentos;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoDespesa;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoReceita;
import com.pedropasqualinifeijo.projetointegrador3.repository.LancamentosRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoDespesaRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoReceitaRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosRestController {

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;
    
    @Autowired
    private LancamentosRepository lancamentoRepository;

    @PostMapping("/tipoReceita")
    public TipoReceita addTypeEarning(@RequestBody TipoReceita receita) {
        return tipoReceitaRepository.save(receita);
    }

    @PostMapping("/tipoDespesa")
    public TipoDespesa addTypeLoss(@RequestBody TipoDespesa despesa) {
        return tipoDespesaRepository.save(despesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Integer id, HttpSession session) {
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Lancamentos> lancamento = lancamentoRepository.findById(id);
        if (lancamento.isPresent() && lancamento.get().getUsuario().getId().equals(idUsuario)) {
            lancamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
