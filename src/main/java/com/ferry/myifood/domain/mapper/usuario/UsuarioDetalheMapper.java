package com.ferry.myifood.domain.mapper.usuario;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.output.UsuarioDetalhe;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioDetalheMapper extends EntityMapper<UsuarioDetalhe, Usuario> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioDetalhe usuarioDetalhe, @MappingTarget Usuario usuario);
}