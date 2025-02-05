
package com.pedropasqualinifeijo.projetointegrador3.service;

import com.pedropasqualinifeijo.projetointegrador3.model.Lancamentos;
import com.pedropasqualinifeijo.projetointegrador3.repository.LancamentosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentosService {
    @Autowired
    private LancamentosRepository lancamentosRepository;
    

    public Lancamentos criar(Lancamentos lancamento) {
        lancamento.setId(null);
        lancamentosRepository.save(lancamento);
        return lancamento;
    }
    
    public Lancamentos atualizar(Integer id, Lancamentos lancamento) {
        Lancamentos lancamentoEncontrado = buscarPorId(id);
        lancamentoEncontrado.setTipoDespesa(lancamento.getTipoDespesa());
        lancamentoEncontrado.setTipoReceita(lancamento.getTipoReceita());
        lancamentoEncontrado.setUsuario(lancamento.getUsuario());
        lancamentoEncontrado.setValor(lancamento.getValor());
        lancamentosRepository.save(lancamentoEncontrado);
        return lancamentoEncontrado;
    }
    
     public Lancamentos buscarPorId(Integer id) {
        return lancamentosRepository.findById(id).orElseThrow();
    }


    public List<Lancamentos> buscarTodosLancamentos() {
        return lancamentosRepository.findAll();
    }

    public Lancamentos salvarLancamento(Lancamentos lancamento) {
        return lancamentosRepository.save(lancamento);
    }
    
    public List<Lancamentos> buscarTodosPeloIdUsuario(Integer id) {
        return lancamentosRepository.findByUsuarioId(id);
    }
}
