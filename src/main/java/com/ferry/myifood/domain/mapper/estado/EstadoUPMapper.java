package com.ferry.myifood.domain.mapper.estado;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.update.EstadoUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoUPMapper extends EntityMapper<EstadoUP, Estado> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoUP estadoUP, @MappingTarget Estado estado);
}