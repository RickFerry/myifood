package com.ferry.myifood.domain.mapper.endereco;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeCompMapper;
import com.ferry.myifood.domain.model.Endereco;
import com.ferry.myifood.domain.model.dtos.input.EnderecoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CidadeCompMapper.class})
public interface EnderecoCompMapper extends EntityMapper<EnderecoComp, Endereco> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco partialUpdate(EnderecoComp enderecoComp, @MappingTarget Endereco endereco);
}