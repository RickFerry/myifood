package com.ferry.myifood.domain.mapper.restaurante;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaOUTMapper;
import com.ferry.myifood.domain.mapper.endereco.EnderecoOUTMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoOUTMapper;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dtos.output.EnderecoOUT;
import com.ferry.myifood.domain.model.dtos.output.RestauranteOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EnderecoOUTMapper.class, CozinhaOUTMapper.class, FormaPagamentoOUTMapper.class})
public interface RestauranteOUTMapper extends EntityMapper<RestauranteOUT, Restaurante> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteOUT restauranteOUT, @MappingTarget Restaurante restaurante);
}