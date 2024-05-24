package com.ferry.myifood.domain.mapper.cozinha;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dto.update.CozinhaUP;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaUPMapper extends EntityMapper<CozinhaUP, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaUP cozinhaUP, @MappingTarget Cozinha cozinha);
}
