package com.myfitlog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_item_refeicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRefeicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    @PositiveOrZero
    private Double quantidade;

    public Double getCarboidratoCalculado() {
        return (this.alimento.getCarboidrato() / 100.0) * this.quantidade;
    }

    public Double getProteinaCalculada() {
        return (this.alimento.getProteina() / 100.0) * this.quantidade;
    }

    public Double getGorduraCalculada() {
        return (this.alimento.getGordura() / 100.0) * this.quantidade;
    }

    public Double getCaloriaCalculada() {
        return (this.alimento.getCalorias() / 100.0) * this.quantidade;
    }
}
