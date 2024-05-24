package com.ferry.myifood.domain.mapper.usuario;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.GrupoCompMapper;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.update.UsuarioUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GrupoCompMapper.class})
public interface UsuarioUPMapper extends EntityMapper<UsuarioUP, Usuario> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioUP usuarioUP, @MappingTarget Usuario usuario);
}