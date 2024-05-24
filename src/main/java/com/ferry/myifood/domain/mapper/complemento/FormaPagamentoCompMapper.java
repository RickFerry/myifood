package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.complemento.FormasPagamentoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoCompMapper extends EntityMapper<FormasPagamentoComp, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormasPagamentoComp formasPagamentoComp, @MappingTarget FormaPagamento formaPagamento);
}