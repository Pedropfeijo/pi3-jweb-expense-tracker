
package com.pedropasqualinifeijo.projetointegrador3.service;

import com.pedropasqualinifeijo.projetointegrador3.model.TipoDespesa;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoReceita;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoDespesaRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoReceitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoService {
    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;
    
    @Autowired
    private TipoDespesaRepository tipoDepesaRepository;

    public List<TipoReceita> buscarTodasReceitas() {
        return tipoReceitaRepository.findAll();
    }

    public List<TipoDespesa> buscarTodasDespesas() {
        return tipoDepesaRepository.findAll();
    }
}
