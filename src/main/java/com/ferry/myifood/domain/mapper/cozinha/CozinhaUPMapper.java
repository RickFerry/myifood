package com.ferry.myifood.domain.mapper.cozinha;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.update.CozinhaUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaUPMapper extends EntityMapper<CozinhaUP, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaUP cozinhaUP, @MappingTarget Cozinha cozinha);
}