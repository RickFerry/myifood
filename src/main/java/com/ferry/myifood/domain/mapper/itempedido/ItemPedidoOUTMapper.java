package com.ferry.myifood.domain.mapper.itempedido;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.produto.ProdutoOUTMapper;
import com.ferry.myifood.domain.model.ItemPedido;
import com.ferry.myifood.domain.model.dto.output.ItemPedidoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProdutoOUTMapper.class})
public interface ItemPedidoOUTMapper extends EntityMapper<ItemPedidoOUT, ItemPedido> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ItemPedido partialUpdate(ItemPedidoOUT itemPedidoOUT, @MappingTarget ItemPedido itemPedido);
}