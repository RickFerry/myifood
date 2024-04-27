package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dtos.FormaPagamentoDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoMapper extends EntityMapper<FormaPagamentoDto, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoDto formaPagamentoDto, @MappingTarget FormaPagamento formaPagamento);
}