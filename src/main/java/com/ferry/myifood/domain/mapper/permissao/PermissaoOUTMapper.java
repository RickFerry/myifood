package com.ferry.myifood.domain.mapper.permissao;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Permissao;
import com.ferry.myifood.domain.model.dtos.output.PermissaoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissaoOUTMapper extends EntityMapper<PermissaoOUT, Permissao> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permissao partialUpdate(PermissaoOUT permissaoOUT, @MappingTarget Permissao permissao);
}