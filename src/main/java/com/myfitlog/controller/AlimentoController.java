package com.myfitlog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfitlog.dto.AlimentoRequestDTO;
import com.myfitlog.dto.AlimentoResponseDTO;
import com.myfitlog.entity.Alimento;
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
    public ResponseEntity<Alimento> criarAlimento(@Valid @RequestBody AlimentoRequestDTO dto) {
        /*Alimento novoAlimento = alimentoService.salvarAlimento(alimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlimento);*/
        Alimento novoAlimento = new Alimento(dto);

        Alimento alimentoSalvo = alimentoService.salvarAlimento(novoAlimento);

        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<AlimentoResponseDTO>> listarAlimentos() {
        
        List<AlimentoResponseDTO> alimentos = alimentoService.listarAlimentos()
            .stream()
            .map(item -> new AlimentoResponseDTO(
                item.getId(),
                item.getNome(),
                item.getProteina(),
                item.getCarboidrato(),
                item.getGordura(),
                item.getCalorias()
            )).toList();

        return ResponseEntity.ok(alimentos);
    }
}
