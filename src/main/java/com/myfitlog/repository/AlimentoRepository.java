package com.myfitlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfitlog.entity.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    
}
