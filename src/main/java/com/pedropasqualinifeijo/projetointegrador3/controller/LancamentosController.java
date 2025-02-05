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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LancamentosController {

    private static final Logger logger = LoggerFactory.getLogger(LancamentosController.class);

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
    public String home(Model model, HttpSession session) {
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/login";  
        }

        List<Lancamentos> lancamentos = lancamentosRepository.findByUsuarioId(idUsuario);
        model.addAttribute("lancamentos", lancamentos);
        return "home";
    }

    @GetMapping("/cadastroLancamento")
    public String mostraCadastroLancamento(@RequestParam(required = false) Integer idLancamento, HttpSession session, Model model) {

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/login";  
        }

        Lancamentos lancamento = new Lancamentos();
        if (idLancamento != null) {
            Optional<Lancamentos> lancamentoAtualizado = lancamentosRepository.findById(idLancamento);
            if (lancamentoAtualizado.isPresent() && lancamentoAtualizado.get().getUsuario().getId().equals(idUsuario)) {
                lancamento = lancamentoAtualizado.get();
            }
        }

        model.addAttribute("lancamento", lancamento);
        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("receitas", tipoReceitaRepository.findAll());
        model.addAttribute("despesas", tipoDespesaRepository.findAll());
        return "cadastroLancamento";
    }

    @PostMapping("/salvarLancamento")
    public String salvarLancamento(@RequestParam(required = false) Integer id, @RequestParam Integer idUsuario, @RequestParam double valor, @RequestParam Integer tipo) {

        Usuarios usuario = usuariosRepository.findById(idUsuario).orElse(null);
        if (usuario == null) {
            return "redirect:/login";  
        }

        Lancamentos lancamento;

        if (id != null) { 
            Optional<Lancamentos> lancamentoAtualizado = lancamentosRepository.findById(id);
            if (lancamentoAtualizado.isPresent() && lancamentoAtualizado.get().getUsuario().getId().equals(idUsuario)) {
                lancamento = lancamentoAtualizado.get();
            } else {
                return "redirect:/home";
            }
        } else { 
            lancamento = new Lancamentos();
            lancamento.setUsuario(usuario);
        }

        lancamento.setValor(valor);

        if (tipo > 0) {
            lancamento.setTipoReceita(tipoReceitaRepository.findById(tipo).orElse(null));
            lancamento.setTipoDespesa(null);  
        } else {
            lancamento.setTipoDespesa(tipoDespesaRepository.findById(-tipo).orElse(null));
            lancamento.setTipoReceita(null);  
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
