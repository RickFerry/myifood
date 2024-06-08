package com.ferry.myifood.domain.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class VendaDiaria {
    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    public VendaDiaria(LocalDateTime data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = Date.from(data.atZone(ZoneId.systemDefault()).toInstant());
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
}
