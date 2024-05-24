package com.ferry.myifood.domain.mapper.cidade;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.EstadoCompMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dto.input.CidadeIN;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EstadoCompMapper.class})
public interface CidadeINMapper extends EntityMapper<CidadeIN, Cidade> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeIN cidadeIN, @MappingTarget Cidade cidade);
}
