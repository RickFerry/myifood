package com.ferry.myifood.domain.mapper.fotoproduto;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FotoProduto;
import com.ferry.myifood.domain.model.dto.input.FotoProdutoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FotoProdutoINMapper extends EntityMapper<FotoProdutoIN, FotoProduto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FotoProduto partialUpdate(FotoProdutoIN fotoProdutoIN, @MappingTarget FotoProduto fotoProduto);
}