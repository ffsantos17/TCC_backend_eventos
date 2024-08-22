package br.com.projeto.api_projeto.controller;

import br.com.projeto.api_projeto.models.Alerta;
import br.com.projeto.api_projeto.repositories.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    AlertaRepository alertaRepository;

    @GetMapping(value = "/listar", produces = "application/json; charset=utf-8")
    public List<Alerta> listar(@RequestHeader("registro_id") int registro_id,@RequestHeader("tabela") String tabela) {
        return alertaRepository.listar(registro_id, tabela);
    }

    @PostMapping("/criar")
    public int criar(Alerta alerta, @RequestHeader("registro_id") int registro_id,@RequestHeader("tabela") String tabela) {
        return alertaRepository.criar(alerta, registro_id, tabela);
    }

    @GetMapping("/ler_alerta")
    public int lerAlerta(@RequestHeader("alerta_id") int alerta_id) {
        return alertaRepository.lerAlerta(alerta_id);
    }

}
