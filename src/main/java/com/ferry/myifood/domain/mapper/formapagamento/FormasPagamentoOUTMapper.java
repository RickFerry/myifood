package com.ferry.myifood.domain.mapper.formapagamento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormasPagamentoOUTMapper extends EntityMapper<FormasPagamentoOUT, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormasPagamentoOUT formasPagamentoOUT, @MappingTarget FormaPagamento formaPagamento);
}