package com.ferry.myifood.domain.mapper.endereco;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeOUTMapper;
import com.ferry.myifood.domain.model.Endereco;
import com.ferry.myifood.domain.model.dto.output.EnderecoOUT;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CidadeOUTMapper.class})
public interface EnderecoOUTMapper extends EntityMapper<EnderecoOUT, Endereco> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco partialUpdate(EnderecoOUT enderecoOUT, @MappingTarget Endereco endereco);
}