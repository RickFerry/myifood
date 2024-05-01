package com.ferry.myifood.domain.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestauranteMapper extends
        EntityMapper<RestauranteDto, Restaurante> {
    /**
     * @param restauranteDto
     * @param restaurante
     * @return Restaurante
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteDto restauranteDto,
            @MappingTarget Restaurante restaurante);

    /**
    * @param entity
    * @return RestauranteDto
    */
    @Mapping(source = "endereco.cep", target = "cepEndereco")
    @Mapping(source = "endereco.logradouro", target = "logradouroEndereco")
    @Mapping(source = "endereco.numero", target = "numeroEndereco")
    @Mapping(source = "endereco.complemento", target = "complementoEndereco")
    @Mapping(source = "endereco.bairro", target = "bairroEndereco")
    RestauranteDto toDto(Restaurante entity);

    /**
     * @param restaurante
     */
    @AfterMapping
    default void linkProdutos(@MappingTarget Restaurante restaurante) {
        restaurante.getProdutos().forEach(
            produto -> produto.setRestaurante(restaurante));
    }
}
