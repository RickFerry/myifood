package com.ferry.myifood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendaDiaria {
        private LocalDateTime data;
        private Long totalVendas;
        private BigDecimal totalFaturado;
}
