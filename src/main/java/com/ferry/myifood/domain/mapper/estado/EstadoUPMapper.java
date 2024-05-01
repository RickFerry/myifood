package com.ferry.myifood.domain.mapper.estado;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.update.EstadoUP;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoUPMapper extends EntityMapper<EstadoUP, Estado> {
    /**
     * @param estadoUP
     * @param estado
     * @return Estado
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoUP estadoUP, @MappingTarget Estado estado);
}
