package com.dhbrasil.springboot.aula21.controller;

import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;

import com.dhbrasil.springboot.aula21.dao.impl.EnderecoDaoH2;
import com.dhbrasil.springboot.aula21.dao.impl.PacienteDaoH2;
import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private ConfiguracaoJDBC configuracaoJDBC;
    private PacienteService pacienteService = new PacienteService(
            new PacienteDaoH2(new EnderecoDaoH2()));

    @PostMapping()
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.salvar(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteService.buscar(id).orElse(null));
    }
}
