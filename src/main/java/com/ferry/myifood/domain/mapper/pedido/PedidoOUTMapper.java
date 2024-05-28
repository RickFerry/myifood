package com.ferry.myifood.domain.mapper.pedido;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.endereco.EnderecoOUTMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoOUTMapper;
import com.ferry.myifood.domain.mapper.itempedido.ItemPedidoOUTMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteOUTMapper;
import com.ferry.myifood.domain.mapper.usuario.UsuarioOUTMapper;
import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EnderecoOUTMapper.class, FormasPagamentoOUTMapper.class, RestauranteOUTMapper.class, UsuarioOUTMapper.class, ItemPedidoOUTMapper.class})
public interface PedidoOUTMapper extends EntityMapper<PedidoOUT, Pedido> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoOUT pedidoOUT, @MappingTarget Pedido pedido);

    @AfterMapping
    default void linkItens(@MappingTarget Pedido pedido) {
        pedido.getItens().forEach(iten -> iten.setPedido(pedido));
    }
}