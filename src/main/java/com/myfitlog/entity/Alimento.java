package com.myfitlog.entity;

import com.myfitlog.dto.AlimentoRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_alimento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    @PositiveOrZero
    private Double carboidrato;

    @Column(nullable = false)
    @PositiveOrZero
    private Double proteina;

    @Column(nullable = false)
    @PositiveOrZero
    private Double gordura;

    @Column(nullable = false)
    @PositiveOrZero
    private Double calorias;
}
