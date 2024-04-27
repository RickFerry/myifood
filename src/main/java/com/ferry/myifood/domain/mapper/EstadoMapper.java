package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.EstadoDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoMapper extends EntityMapper<EstadoDto, Estado> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoDto estadoDto, @MappingTarget Estado estado);
}