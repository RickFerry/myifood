package com.ferry.myifood.domain.mapper.grupo;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.PermissaoCompMapper;
import com.ferry.myifood.domain.model.Grupo;
import com.ferry.myifood.domain.model.dtos.input.GrupoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PermissaoCompMapper.class})
public interface GrupoINMapper extends EntityMapper<GrupoIN, Grupo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoIN gruposIN, @MappingTarget Grupo grupos);
}