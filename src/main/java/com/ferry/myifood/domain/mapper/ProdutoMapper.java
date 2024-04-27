package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dtos.ProdutoDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper extends EntityMapper<ProdutoDto, Produto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoDto produtoDto, @MappingTarget Produto produto);
}