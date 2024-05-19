package com.ferry.myifood.domain.mapper.formapagamento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dtos.input.FormaPagamentoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoINMapper extends EntityMapper<FormaPagamentoIN, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoIN formaPagamentoIN, @MappingTarget FormaPagamento formaPagamento);
}