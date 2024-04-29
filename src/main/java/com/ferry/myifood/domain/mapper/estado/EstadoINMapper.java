package com.ferry.myifood.domain.mapper.estado;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.input.EstadoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoINMapper extends EntityMapper<EstadoIN, Estado> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoIN estadoIN, @MappingTarget Estado estado);
}