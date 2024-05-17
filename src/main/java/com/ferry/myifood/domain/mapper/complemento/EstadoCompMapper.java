package com.ferry.myifood.domain.mapper.complemento;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.complemento.EstadoComp;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoCompMapper extends EntityMapper<EstadoComp, Estado> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoComp estadoComp, @MappingTarget Estado estado);
}
