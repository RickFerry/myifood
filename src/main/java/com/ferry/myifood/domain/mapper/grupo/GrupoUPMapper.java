package com.ferry.myifood.domain.mapper.grupo;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Grupo;
import com.ferry.myifood.domain.model.dto.update.GrupoUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoUPMapper extends EntityMapper<GrupoUP, Grupo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoUP grupoUP, @MappingTarget Grupo grupo);
}