package com.ferry.myifood.domain.mapper.restaurante;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaCompMapper;
import com.ferry.myifood.domain.mapper.endereco.EnderecoCompMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoCompMapper;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.input.RestauranteIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EnderecoCompMapper.class, CozinhaCompMapper.class, FormaPagamentoCompMapper.class})
public interface RestauranteINMapper extends EntityMapper<RestauranteIN, Restaurante> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteIN restauranteIN, @MappingTarget Restaurante restaurante);
}