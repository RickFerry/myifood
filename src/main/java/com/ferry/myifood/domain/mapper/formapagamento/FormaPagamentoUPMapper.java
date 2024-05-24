package com.ferry.myifood.domain.mapper.formapagamento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.update.FormaPagamentoUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoUPMapper extends EntityMapper<FormaPagamentoUP, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoUP formaPagamentoUP, @MappingTarget FormaPagamento formaPagamento);
}