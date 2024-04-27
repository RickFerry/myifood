package com.ferry.myifood.domain.mapper;

import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestauranteMapper extends EntityMapper<RestauranteDto, Restaurante> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteDto restauranteDto, @MappingTarget Restaurante restaurante);

    @Mapping(source = "endereco.cep", target = "cepEndereco")
    @Mapping(source = "endereco.logradouro", target = "logradouroEndereco")
    @Mapping(source = "endereco.numero", target = "numeroEndereco")
    @Mapping(source = "endereco.complemento", target = "complementoEndereco")
    @Mapping(source = "endereco.bairro", target = "bairroEndereco")
    RestauranteDto toDto(Restaurante entity);

    @AfterMapping
    default void linkProdutos(@MappingTarget Restaurante restaurante) {
        restaurante.getProdutos().forEach(produto -> produto.setRestaurante(restaurante));
    }
}