package com.ferry.myifood.domain.mapper.usuario;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.GrupoCompMapper;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.input.UsuarioIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GrupoCompMapper.class})
public interface UsuarioINMapper extends EntityMapper<UsuarioIN, Usuario> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioIN usuarioIN, @MappingTarget Usuario usuario);
}