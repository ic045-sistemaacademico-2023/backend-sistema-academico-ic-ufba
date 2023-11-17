package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.LoginUsuario;
import com.ic045.sistemaacademico.services.AuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginUsuario request) {
        String token = authService.login(request);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
