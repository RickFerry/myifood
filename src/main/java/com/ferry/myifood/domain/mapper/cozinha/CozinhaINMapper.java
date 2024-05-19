package com.ferry.myifood.domain.mapper.cozinha;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.EstadoCompMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.input.CozinhaIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EstadoCompMapper.class, EstadoCompMapper.class})
public interface CozinhaINMapper extends EntityMapper<CozinhaIN, Cozinha> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaIN cozinhaIN, @MappingTarget Cozinha cozinha);
}
