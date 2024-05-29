package com.ferry.myifood.domain.mapper.pedido;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.*;
import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.input.PedidoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ItemPedidoCompMapper.class, EnderecoCompMapper.class, UsuarioCompMapper.class, RestauranteCompMapper.class, FormaPagamentoCompMapper.class})
public interface PedidoINMapper extends EntityMapper<PedidoIN, Pedido> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoIN pedidoIN, @MappingTarget Pedido pedido);
}