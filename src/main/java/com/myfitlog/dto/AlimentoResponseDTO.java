package com.myfitlog.dto;

public record AlimentoResponseDTO(

    Long id,
    String nome,
    Double carboidrato,
    Double proteina,
    Double gordura,
    Double calorias
) {}