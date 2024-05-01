package com.ferry.myifood.domain.mapper.estado;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.output.EstadoOUT;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoOUTMapper extends EntityMapper<EstadoOUT, Estado> {
    /**
     * @param estadoDto
     * @param estado
     * @return Estado
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoOUT estadoDto, @MappingTarget Estado estado);
}
