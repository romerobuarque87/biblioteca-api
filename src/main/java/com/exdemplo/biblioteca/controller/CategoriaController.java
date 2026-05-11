package com.exdemplo.biblioteca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

    @GetMapping("/categorias")
    public String listarCategorias() {
        return "API de categorias funcionando!";
    }
}