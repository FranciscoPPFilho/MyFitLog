package com.myfitlog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_dieta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dieta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Refeicao> refeicoes = new ArrayList<>();

    public void adicionarRefeicao(Refeicao refeicao) {
        refeicoes.add(refeicao);
        refeicao.setDieta(this);
    }

    public double somarCarboidratos() {
        return this.refeicoes.stream().mapToDouble(Refeicao::getTotalCarboidratos).sum();
    }

    public double somarProteinas() {
        return this.refeicoes.stream().mapToDouble(Refeicao::getTotalProteinas).sum();
    }

    public double somarGorduras() {
        return this.refeicoes.stream().mapToDouble(Refeicao::getTotalGorduras).sum();
    }

    public double somarCalorias() {
        return this.refeicoes.stream().mapToDouble(Refeicao::getTotalCalorias).sum();
    }
}
