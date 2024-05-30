package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dto.complemento.ProdutoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoCompMapper extends EntityMapper<ProdutoComp, Produto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoComp produtoComp, @MappingTarget Produto produto);
}