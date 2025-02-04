package com.pedropasqualinifeijo.projetointegrador3.controller;

import com.pedropasqualinifeijo.projetointegrador3.model.Lancamentos;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoDespesa;
import com.pedropasqualinifeijo.projetointegrador3.model.TipoReceita;
import com.pedropasqualinifeijo.projetointegrador3.model.Usuarios;
import com.pedropasqualinifeijo.projetointegrador3.repository.LancamentosRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoDespesaRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.TipoReceitaRepository;
import com.pedropasqualinifeijo.projetointegrador3.repository.UsuariosRepository;
import com.pedropasqualinifeijo.projetointegrador3.service.LancamentosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class LancamentosController {

    @Autowired
    LancamentosService lancamentosService;

    @Autowired
    private LancamentosRepository lancamentosRepository;

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping("/home")
    public String home(Model model, @RequestParam String id) {
        Integer idUsuario = Integer.parseInt(id);
        model.addAttribute("lancamentos", lancamentosService.buscarTodosPeloIdUsuario(idUsuario));
        return "home";
    }

    @GetMapping("/cadastroLancamento")
    public String mostraCadastroLancamento(Model model) {
        model.addAttribute("lancamento", new Lancamentos());
        model.addAttribute("earnings", tipoReceitaRepository.findAll());
        model.addAttribute("losses", tipoDespesaRepository.findAll());
        return "cadastroLancamento";
    }

    @PostMapping("/salvarLancamento")
    public String salvarLancamento(@RequestParam double valor, @RequestParam Integer tipo, Model model) {
        Lancamentos lancamento = new Lancamentos();
        lancamento.setValor(valor);

        if (tipo > 0) {
            lancamento.setTipoReceita(tipoReceitaRepository.findById(tipo).orElse(null));
        } else {
            lancamento.setTipoDespesa(tipoDespesaRepository.findById(-tipo).orElse(null));
        }

        lancamentosRepository.save(lancamento);
        return "redirect:/home";
    }

    @PostMapping("/adicionarTipoReceita")
    public String adicionarTipoReceita(@RequestParam String descricao) {
        TipoReceita novaReceita = new TipoReceita();
        novaReceita.setDescricao(descricao);
        tipoReceitaRepository.save(novaReceita);
        return "redirect:/cadastroLancamento";
    }

    @PostMapping("/adicionarTipoDespesa")
    public String adicionarTipoDespesa(@RequestParam String descricao) {
        TipoDespesa novaDespesa = new TipoDespesa();
        novaDespesa.setDescricao(descricao);
        tipoDespesaRepository.save(novaDespesa);
        return "redirect:/cadastroLancamento";
    }
}
