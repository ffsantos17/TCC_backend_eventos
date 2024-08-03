package br.com.projeto.api_projeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("")
    public String mensagem(){
        return "Hello World";
    }
}
