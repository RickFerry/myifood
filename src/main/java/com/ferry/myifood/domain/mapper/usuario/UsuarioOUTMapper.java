package com.ferry.myifood.domain.mapper.usuario;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.grupo.GrupoOUTMapper;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.output.UsuarioOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GrupoOUTMapper.class})
public interface UsuarioOUTMapper extends EntityMapper<UsuarioOUT, Usuario> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioOUT usuarioOUT, @MappingTarget Usuario usuario);
}