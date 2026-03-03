package com.myfitlog.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DietaRequestDTO(

    @NotBlank
    String nome,

    @Valid
    List<RefeicaoRequestDTO> refeicoes
) {}
