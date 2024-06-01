package com.ferry.myifood.domain.mapper.pedido;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.EnderecoCompMapper;
import com.ferry.myifood.domain.mapper.complemento.FormaPagamentoCompMapper;
import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.update.PedidoUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EnderecoCompMapper.class, FormaPagamentoCompMapper.class})
public interface PedidoUPMapper extends EntityMapper<PedidoUP, Pedido> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoUP pedidoUP, @MappingTarget Pedido pedido);
}