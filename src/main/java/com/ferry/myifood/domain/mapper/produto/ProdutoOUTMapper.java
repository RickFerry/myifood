package com.ferry.myifood.domain.mapper.produto;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoOUTMapper extends EntityMapper<ProdutoOUT, Produto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoOUT produtoOUT, @MappingTarget Produto produto);
}