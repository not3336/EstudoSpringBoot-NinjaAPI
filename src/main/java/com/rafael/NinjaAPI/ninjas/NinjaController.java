package com.rafael.NinjaAPI.ninjas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {
    @GetMapping("/boasvindas")
    public String mensagemBoasVindas(){
        return "Seja bem-vindo a primeira roda da API Ninja";
    }
}
