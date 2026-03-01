package com.myfitlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfitlog.dto.ItemRefeicaoResponseDTO;
import com.myfitlog.dto.RefeicaoRequestDTO;
import com.myfitlog.dto.RefeicaoResponseDTO;
import com.myfitlog.entity.Alimento;
import com.myfitlog.entity.ItemRefeicao;
import com.myfitlog.entity.Refeicao;
import com.myfitlog.exception.RecursoNaoEncontradoException;
import com.myfitlog.repository.AlimentoRepository;
import com.myfitlog.repository.RefeicaoRepository;

import jakarta.transaction.Transactional;

@Service
public class RefeicaoService {
    
    private RefeicaoRepository refeicaoRepository;
    private AlimentoRepository alimentoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, AlimentoRepository alimentoRepository) {
        this.refeicaoRepository = refeicaoRepository;
        this.alimentoRepository = alimentoRepository;
    }

    //Refator depois
    @Transactional
    public RefeicaoResponseDTO salvarRefeicao(RefeicaoRequestDTO dto) {

        Refeicao novaRefeicao = new Refeicao();
        novaRefeicao.setNome(dto.nome());

        dto.itens().forEach(item -> {
            Alimento alimento = alimentoRepository.findById(item.alimentoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Alimento com ID " + item.alimentoId() + " não encontrado"));

            ItemRefeicao novoItem = new ItemRefeicao();

            novoItem.setAlimento(alimento);
            novoItem.setQuantidade(item.quantidade());
            novaRefeicao.adicionarItem(novoItem);
        });
        
        return converterParaResponseDTO(refeicaoRepository.save(novaRefeicao));
    }

    public List<RefeicaoResponseDTO> listarRefeicoes() {
        
        List<RefeicaoResponseDTO> response = refeicaoRepository.findAll().stream().map(this::converterParaResponseDTO).toList();
        
        return response;
    }

    private RefeicaoResponseDTO converterParaResponseDTO(Refeicao refeicao) {

        return new RefeicaoResponseDTO(
            refeicao.getId(),
            refeicao.getNome(),
            refeicao.getItens().stream().map(this::converterItemParaDTO).toList(),
            refeicao.getTotalCarboidratos(),
            refeicao.getTotalProteinas(),
            refeicao.getTotalGorduras(),
            refeicao.getTotalCalorias()
        );
    }

    private ItemRefeicaoResponseDTO converterItemParaDTO(ItemRefeicao item) {

        return new ItemRefeicaoResponseDTO(
            item.getId(),
            item.getAlimento().getNome(),
            item.getQuantidade(),
            item.getCarboidratoCalculado(),
            item.getProteinaCalculada(),
            item.getGorduraCalculada(),
            item.getCaloriaCalculada()
        );
    }
}
