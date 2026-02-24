package com.myfitlog.dto;

public record AlimentoResponseDTO(

    Long id,
    String nome,
    Double proteina,
    Double carboidrato,
    Double gordura,
    Double caloria
) {}