package com.ferry.myifood.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dtos.FormaPagamentoDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoMapper extends
        EntityMapper<FormaPagamentoDto, FormaPagamento> {
    /**
     * @param formaPagamentoDto
     * @param formaPagamento
     * @return FormaPagamento
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoDto formaPagamentoDto,
            @MappingTarget FormaPagamento formaPagamento);
}
