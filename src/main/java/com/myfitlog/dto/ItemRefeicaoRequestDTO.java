package com.myfitlog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ItemRefeicaoRequestDTO(

    @NotNull
    Long alimentoId,
    
    @NotNull
    @PositiveOrZero
    Double quantidade
) {}