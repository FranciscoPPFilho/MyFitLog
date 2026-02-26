package com.myfitlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfitlog.dto.AlimentoRequestDTO;
import com.myfitlog.dto.AlimentoResponseDTO;
import com.myfitlog.entity.Alimento;
import com.myfitlog.repository.AlimentoRepository;

@Service
public class AlimentoService {
    
    private AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public AlimentoResponseDTO salvarAlimento(AlimentoRequestDTO dto) {

        Alimento alimento = converterParaEntidade(dto);

        AlimentoResponseDTO response = converterParaDTO(alimentoRepository.save(alimento));

        return response;
    }

    public List<AlimentoResponseDTO> listarAlimentos() {
        return alimentoRepository.findAll()
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    /*public Alimento buscarPorId(Long id) {
        return alimentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alimento não encontrado"));
    }

    public void deletarPorId(Long id) {
        Alimento alimento = alimentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alimento não encontrado"));
            
        alimentoRepository.delete(alimento);
    }*/

    //-------------------------------------------------------------------------------------------------------/

    private Alimento converterParaEntidade(AlimentoRequestDTO dto) {

        Alimento alimento = new Alimento();
        alimento.setNome(dto.nome());
        alimento.setCarboidrato(dto.carboidrato());
        alimento.setProteina(dto.proteina());
        alimento.setGordura(dto.gordura());
        alimento.setCalorias(dto.calorias());

        return alimento;
    }
    
    private AlimentoResponseDTO converterParaDTO(Alimento alimento) {

        AlimentoResponseDTO dto = new AlimentoResponseDTO(
            alimento.getId(),
            alimento.getNome(),
            alimento.getCarboidrato(),
            alimento.getProteina(),
            alimento.getGordura(),
            alimento.getCalorias()
        );
        return dto;
    }
}
