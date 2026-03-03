package com.myfitlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfitlog.entity.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long>{
    
}
