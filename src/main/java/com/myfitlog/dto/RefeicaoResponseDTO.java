package com.myfitlog.dto;

import java.util.List;

public record RefeicaoResponseDTO(

    Long id,
    String nome,
    List<ItemRefeicaoResponseDTO> itens,
    Double totalCarboidratos,
    Double totalProteinas,
    Double totalGorduras,
    Double totalCalorias
) {}