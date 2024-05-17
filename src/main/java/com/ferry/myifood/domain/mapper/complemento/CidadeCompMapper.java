package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dtos.complemento.CidadeComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EstadoCompMapper.class})
public interface CidadeCompMapper extends EntityMapper<CidadeComp, Cidade> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeComp cidadeComp, @MappingTarget Cidade cidade);
}