package com.ferry.myifood.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dtos.ProdutoDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper extends EntityMapper<ProdutoDto, Produto> {
    /**
     * @param produtoDto
     * @param produto
     * @return Produto
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoDto produtoDto,
            @MappingTarget Produto produto);
}
