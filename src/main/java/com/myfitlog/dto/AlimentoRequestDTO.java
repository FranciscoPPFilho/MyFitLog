package com.myfitlog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AlimentoRequestDTO(

    @NotBlank(message = "O nome do alimento é obrigatório")
    String nome, 

    @NotNull(message = "Proteína é obrigatória")
    @PositiveOrZero(message = "Proteína deve ser maior que zero")
    Double proteina, 

    @NotNull(message = "Carboidrato é obrigatório")
    @PositiveOrZero(message = "Carboidrato deve ser maior que zero")
    Double carboidrato, 

    @NotNull(message = "Gordura é obrigatória")
    @PositiveOrZero(message = "Gordura deve ser maior que zero")
    Double gordura, 

    @NotNull(message = "Calorias é obrigatória")
    @PositiveOrZero(message = "Calorias deve ser maior que zero")
    Double calorias
) {}