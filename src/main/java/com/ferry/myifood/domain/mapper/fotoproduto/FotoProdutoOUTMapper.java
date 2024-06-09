package com.ferry.myifood.domain.mapper.fotoproduto;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.FotoProduto;
import com.ferry.myifood.domain.model.dto.output.FotoProdutoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FotoProdutoOUTMapper extends EntityMapper<FotoProdutoOUT, FotoProduto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FotoProduto partialUpdate(FotoProdutoOUT fotoProdutoOUT, @MappingTarget FotoProduto fotoProduto);
}