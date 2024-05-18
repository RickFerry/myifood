package com.ferry.myifood.domain.mapper.restaurante;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.CozinhaCompMapper;
import com.ferry.myifood.domain.mapper.complemento.EnderecoCompMapper;
import com.ferry.myifood.domain.mapper.complemento.FormaPagamentoCompMapper;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.update.RestauranteUP;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EnderecoCompMapper.class, CozinhaCompMapper.class, FormaPagamentoCompMapper.class})
public interface RestauranteUPMapper extends EntityMapper<RestauranteUP, Restaurante> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteUP restauranteUP, @MappingTarget Restaurante restaurante);
}