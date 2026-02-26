package com.myfitlog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfitlog.dto.AlimentoRequestDTO;
import com.myfitlog.dto.AlimentoResponseDTO;
import com.myfitlog.service.AlimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {
    
    private AlimentoService alimentoService;

    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @PostMapping
    public ResponseEntity<AlimentoResponseDTO> criarAlimento(@Valid @RequestBody AlimentoRequestDTO dto) {
        
        AlimentoResponseDTO response = alimentoService.salvarAlimento(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlimentoResponseDTO>> listarAlimentos() {
        
        List<AlimentoResponseDTO> response = alimentoService.listarAlimentos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(alimentoService.buscarPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {

        alimentoService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
