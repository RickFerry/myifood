package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Permissao;
import com.ferry.myifood.domain.model.dtos.complemento.PermissaoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissaoCompMapper extends EntityMapper<PermissaoComp, Permissao> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permissao partialUpdate(PermissaoComp permissaoComp, @MappingTarget Permissao permissao);
}