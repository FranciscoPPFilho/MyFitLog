package com.myfitlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfitlog.dto.DietaRequestDTO;
import com.myfitlog.dto.DietaResponseDTO;
import com.myfitlog.service.DietaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dieta")
public class DietaController {
    
    private DietaService dietaService;

    public DietaController(DietaService dietaService) {
        this.dietaService = dietaService;
    }

    @PostMapping
    public ResponseEntity<DietaResponseDTO> salvarDieta(@Valid @RequestBody DietaRequestDTO dietaRequestDTO) {

        DietaResponseDTO response = dietaService.salvarDieta(dietaRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
