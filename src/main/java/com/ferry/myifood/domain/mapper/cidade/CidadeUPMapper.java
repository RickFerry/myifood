package com.ferry.myifood.domain.mapper.cidade;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dto.update.CidadeUP;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CidadeUPMapper extends EntityMapper<CidadeUP, Cidade> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeUP cidadeUP, @MappingTarget Cidade cidade);
}
