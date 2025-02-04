
package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.TipoDespesa;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoReceita;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoDespesaRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/tipoReceita")
    public TipoReceita addTypeEarning(@RequestBody TipoReceita receita) {
        return tipoReceitaRepository.save(receita);
    }

    @PostMapping("/tipoDespesa")
    public TipoDespesa addTypeLoss(@RequestBody TipoDespesa despesa) {
        return tipoDespesaRepository.save(despesa);
    }
}
