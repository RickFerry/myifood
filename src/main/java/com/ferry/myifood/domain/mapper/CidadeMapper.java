package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dtos.CidadeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CidadeMapper extends EntityMapper<CidadeDto, Cidade> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeDto cidadeDto, @MappingTarget Cidade cidade);
}