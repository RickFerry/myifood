package com.ferry.myifood.domain.mapper.formapagamento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.output.FormaPagamentoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoOUTMapper extends EntityMapper<FormaPagamentoOUT, FormaPagamento> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoOUT formasPagamentoOUT, @MappingTarget FormaPagamento formaPagamento);
}