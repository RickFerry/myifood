package com.ferry.myifood.domain.mapper.complemento;

import com.ferry.myifood.domain.mapper.EntityMapper;
import com.ferry.myifood.domain.model.Endereco;
import com.ferry.myifood.domain.model.dto.complemento.EnderecoComp;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CidadeCompMapper.class})
public interface EnderecoCompMapper extends EntityMapper<EnderecoComp, Endereco> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco partialUpdate(EnderecoComp enderecoComp, @MappingTarget Endereco endereco);
}