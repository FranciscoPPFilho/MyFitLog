package com.myfitlog.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RefeicaoRequestDTO(

    @NotBlank
    String nome,
    
    @NotEmpty
    @Valid
    List<ItemRefeicaoRequestDTO> itens
) {}