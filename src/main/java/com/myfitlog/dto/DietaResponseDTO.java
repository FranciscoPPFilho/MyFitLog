package com.myfitlog.dto;

import java.util.List;

public record DietaResponseDTO(

    Long id,
    String nome,
    List<RefeicaoResponseDTO> refeicoes,
    Double totalCarboidratos,
    Double totalProteinas,
    Double totalGorduras,
    Double totalCalorias
) {}
