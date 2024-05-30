package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.ItemPedido;
import com.ferry.myifood.domain.model.dto.complemento.ItemPedidoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProdutoCompOutMapper.class})
public interface ItemPedidoCompMapper extends EntityMapper<ItemPedidoComp, ItemPedido> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ItemPedido partialUpdate(ItemPedidoComp itemPedidoComp, @MappingTarget ItemPedido itemPedido);
}