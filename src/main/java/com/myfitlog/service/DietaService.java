package com.myfitlog.service;

import org.springframework.stereotype.Service;

import com.myfitlog.dto.DietaRequestDTO;
import com.myfitlog.dto.DietaResponseDTO;
import com.myfitlog.dto.RefeicaoRequestDTO;
import com.myfitlog.entity.Alimento;
import com.myfitlog.entity.Dieta;
import com.myfitlog.entity.ItemRefeicao;
import com.myfitlog.entity.Refeicao;
import com.myfitlog.exception.RecursoNaoEncontradoException;
import com.myfitlog.repository.AlimentoRepository;
import com.myfitlog.repository.DietaRepository;

import jakarta.transaction.Transactional;

@Service
public class DietaService {
    
    private DietaRepository dietaRepository;
    private RefeicaoService refeicaoService;
    private AlimentoRepository alimentoRepository;

    public DietaService(DietaRepository dietaRepository, RefeicaoService refeicaoService, AlimentoRepository alimentoRepository) {
        this.dietaRepository = dietaRepository;
        this.refeicaoService = refeicaoService;
        this.alimentoRepository = alimentoRepository;
    }

    //Refatorar depois
    @Transactional
    public DietaResponseDTO salvarDieta(DietaRequestDTO dto) {
        Dieta novaDieta = new Dieta();
        novaDieta.setNome(dto.nome());

        dto.refeicoes().forEach(refeicaoDTO -> {
            Refeicao refeicaoMontada = montarRefeicao(refeicaoDTO);
            novaDieta.adicionarRefeicao(refeicaoMontada);
        });

        Dieta dietaSalva = dietaRepository.save(novaDieta);
        return converterParaDietaResponseDTO(dietaSalva);
    }

    public Refeicao montarRefeicao(RefeicaoRequestDTO refeicaoDTO) {

        Refeicao novaRefeicao = new Refeicao();
        novaRefeicao.setNome(refeicaoDTO.nome());

        refeicaoDTO.itens().forEach(itemDTO -> {

            Alimento alimento = alimentoRepository.findById(itemDTO.alimentoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Alimento com ID " + itemDTO.alimentoId() + " não encontrado"));

            ItemRefeicao novoItem = new ItemRefeicao();

            novoItem.setAlimento(alimento);
            novoItem.setQuantidade(itemDTO.quantidade());
            novaRefeicao.adicionarItem(novoItem);
        });

        return novaRefeicao;
    }

    private DietaResponseDTO converterParaDietaResponseDTO(Dieta dieta) {

        return new DietaResponseDTO(
            dieta.getId(),
            dieta.getNome(),
            dieta.getRefeicoes().stream().map(refeicaoService::converterParaResponseDTO).toList(),
            dieta.somarCarboidratos(),
            dieta.somarProteinas(),
            dieta.somarGorduras(),
            dieta.somarCalorias()
        );
    }
}
