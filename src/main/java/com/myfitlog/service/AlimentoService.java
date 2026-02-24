package com.myfitlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfitlog.entity.Alimento;
import com.myfitlog.repository.AlimentoRepository;

@Service
public class AlimentoService {
    
    private AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public Alimento salvarAlimento(Alimento alimento) {


        return alimentoRepository.save(alimento);
    }

    public List<Alimento> listarAlimentos() {
        return alimentoRepository.findAll();
    }

    public Alimento buscarPorId(Long id) {
        return alimentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alimento não encontrado"));
    }

    public void deletarPorId(Long id) {
        Alimento alimento = alimentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alimento não encontrado"));
            
        alimentoRepository.delete(alimento);
    }

    //-------------------------------------------------------------------------------------------------------/

    private Alimento converterParaEntidade()
        
}
