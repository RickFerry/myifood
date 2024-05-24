package com.ferry.myifood.domain.mapper.endereco;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.complemento.CidadeCompMapper;
import com.ferry.myifood.domain.model.Endereco;
import com.ferry.myifood.domain.model.dto.input.EnderecoIN;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CidadeCompMapper.class})
public interface EnderecoINMapper extends EntityMapper<EnderecoIN, Endereco> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco partialUpdate(EnderecoIN enderecoIN, @MappingTarget Endereco endereco);
}