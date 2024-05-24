package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Grupo;
import com.ferry.myifood.domain.model.dto.complemento.GrupoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoCompMapper extends EntityMapper<GrupoComp, Grupo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoComp gruposComp, @MappingTarget Grupo grupo);
}