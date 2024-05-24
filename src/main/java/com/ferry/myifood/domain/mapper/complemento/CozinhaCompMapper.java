package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dto.complemento.CozinhaComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaCompMapper extends EntityMapper<CozinhaComp, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaComp cozinhaComp, @MappingTarget Cozinha cozinha);
}