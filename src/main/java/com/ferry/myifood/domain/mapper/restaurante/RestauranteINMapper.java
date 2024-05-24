package com.ferry.myifood.domain.mapper.restaurante;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.CozinhaCompMapper;
import com.ferry.myifood.domain.mapper.complemento.EnderecoCompMapper;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dto.input.RestauranteIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CozinhaCompMapper.class, EnderecoCompMapper.class})
public interface RestauranteINMapper extends EntityMapper<RestauranteIN, Restaurante> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteIN restauranteIN, @MappingTarget Restaurante restaurante);
}