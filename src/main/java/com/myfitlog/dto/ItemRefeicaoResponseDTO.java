package com.myfitlog.dto;

public record ItemRefeicaoResponseDTO(

    Long id,
    String nomeAlimento,
    Double quantidade,
    Double carboidrato,
    Double proteina,
    Double gordura,
    Double calorias
) {}