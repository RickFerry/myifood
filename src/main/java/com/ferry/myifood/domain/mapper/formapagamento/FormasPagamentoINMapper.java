package com.ferry.myifood.domain.mapper.formapagamento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.input.FormasPagamentoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormasPagamentoINMapper extends EntityMapper<FormasPagamentoIN, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormasPagamentoIN formaPagamentoIN, @MappingTarget FormaPagamento formaPagamento);
}