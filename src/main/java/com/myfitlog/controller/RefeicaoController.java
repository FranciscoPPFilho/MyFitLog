package com.myfitlog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfitlog.dto.RefeicaoRequestDTO;
import com.myfitlog.dto.RefeicaoResponseDTO;
import com.myfitlog.service.RefeicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/refeicoes")
public class RefeicaoController {
    
    RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping
    public ResponseEntity<RefeicaoResponseDTO> criarRefeicao(@Valid @RequestBody RefeicaoRequestDTO dto) {

        RefeicaoResponseDTO response = refeicaoService.salvarRefeicao(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
    }

    @GetMapping
    public ResponseEntity<List<RefeicaoResponseDTO>> listarRefeicoes() {

        List<RefeicaoResponseDTO> response = refeicaoService.listarRefeicoes();

        return ResponseEntity.ok(response);
    }
}