package com.ticket.code.tickets.usuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/app/foo")
    public Map<String, String> foo() {
        Map<String, String> json = new HashMap<>();
        json.put("Mensaje: ", "Hola api");

        return json;
    }

}
