package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.CozinhaDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaMapper extends EntityMapper<CozinhaDto, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaDto cozinhaDto, @MappingTarget Cozinha cozinha);
}