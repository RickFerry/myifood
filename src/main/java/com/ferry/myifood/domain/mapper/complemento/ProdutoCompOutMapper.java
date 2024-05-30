package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dto.output.ProdutoCompOut;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoCompOutMapper extends EntityMapper<ProdutoCompOut, Produto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoCompOut produtoCompOut, @MappingTarget Produto produto);
}