package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.RestauranteMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeCompMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaCompMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoCompMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteINMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteOUTMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import com.ferry.myifood.domain.model.dtos.input.CidadeComp;
import com.ferry.myifood.domain.model.dtos.input.RestauranteIN;
import com.ferry.myifood.domain.model.dtos.output.RestauranteOUT;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import com.ferry.myifood.domain.repository.FormaPagamentoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestauranteService {
    /**
     *
     */
    private final RestauranteRepository restauranteRepository;
    /**
     *
     */
    private final CidadeRepository cidadeRepository;
    /**
     *
     */
    private final FormaPagamentoRepository formaPagamentoRepository;
    /**
     *
     */
    private final CozinhaRepository cozinhaRepository;
    /**
     *
     */
    private final RestauranteMapper restauranteMapper;
    /**
     *
     */
    private final RestauranteINMapper restauranteINMapper;
    /**
     *
     */
    private final RestauranteOUTMapper restauranteOUTMapper;

    /**
     * @param page
     * @return Page<RestauranteDto>
     */
    @Transactional(readOnly = true)
    public Page<RestauranteOUT> listar(final Pageable page) {
        return restauranteRepository.findAll(page).map(restauranteOUTMapper::toDto);
    }

    /**
     * @param id
     * @return RestauranteDto
     */
    @Transactional(readOnly = true)
    public RestauranteOUT buscar(final Long id) {
        return restauranteRepository.findById(id).map(restauranteOUTMapper::toDto).orElseThrow(
                () -> new RuntimeException("Restaurante não encontrado"));
    }

    /**
     * @param in
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteOUT salvar(final @Valid RestauranteIN in) {
        Restaurante restaurante = restauranteINMapper.toEntity(in);

        restaurante.setCozinha(cozinhaRepository.findById(in.getCozinhaComp().getId()).orElseThrow(() -> new EntityNotFoundException("Não existe cozinha com o id informado")));

        restaurante.getEndereco().setCidade(cidadeRepository.findById(in.getEndereco().getCidadeComp().getId()).orElseThrow(() -> new EntityNotFoundException("Não existe cidade com o id informado")));

        restaurante.getFormasPagamento().addAll(in.getFormasPagamentoComp().stream().map(formaPagamentoComp -> formaPagamentoRepository.findById(formaPagamentoComp.getId()).orElseThrow(() -> new EntityNotFoundException("Não existe forma de pagamento com o id informado"))).collect(Collectors.toSet()));
        return restauranteOUTMapper.toDto(restauranteRepository.save(restaurante));
    }

    /**
     * @param id
     * @param dto
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteDto atualizar(final Long id, final RestauranteDto dto) {
        return restauranteMapper.toDto(restauranteMapper.partialUpdate(dto, restauranteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe restaurante com o id informado"))));
    }

    /**
     * @param id
     */
    @Transactional
    public void deletar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(restauranteRepository::delete, () -> {
            throw new EntityNotFoundException("Não existe restaurante com o id informado");
        });
    }
}
