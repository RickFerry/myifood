package com.ferry.myifood.domain.mapper.cozinha;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaOUTMapper extends EntityMapper<CozinhaOUT, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaOUT cozinhaDto, @MappingTarget Cozinha cozinha);
}
