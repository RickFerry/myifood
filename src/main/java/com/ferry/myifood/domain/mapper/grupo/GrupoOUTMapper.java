package com.ferry.myifood.domain.mapper.grupo;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.permissao.PermissaoOUTMapper;
import com.ferry.myifood.domain.model.Grupo;
import com.ferry.myifood.domain.model.dto.output.GrupoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PermissaoOUTMapper.class})
public interface GrupoOUTMapper extends EntityMapper<GrupoOUT, Grupo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoOUT grupoOUT, @MappingTarget Grupo grupo);
}