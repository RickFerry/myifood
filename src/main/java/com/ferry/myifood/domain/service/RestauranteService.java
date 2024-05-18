package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.restaurante.RestauranteINMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteOUTMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteUPMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.input.RestauranteIN;
import com.ferry.myifood.domain.model.dtos.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dtos.update.RestauranteUP;
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
    private final RestauranteINMapper restauranteINMapper;
    /**
     *
     */
    private final RestauranteOUTMapper restauranteOUTMapper;
    /**
     *
     */
    private final RestauranteUPMapper restauranteUPMapper;

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

        restaurante.setCozinha(cozinhaRepository.findById(in.getCozinha().getId()).orElseThrow(
                () -> new EntityNotFoundException("Não existe cozinha com o id informado")));

        restaurante.getEndereco().setCidade(cidadeRepository.findById(in.getEndereco().getCidade().getId()).orElseThrow(
                () -> new EntityNotFoundException("Não existe cidade com o id informado")));

        restaurante.getFormasPagamento().addAll(in.getFormasPagamento().stream().map(
                formaPagamentoComp -> formaPagamentoRepository.findById(formaPagamentoComp.getId()).orElseThrow(
                        () -> new EntityNotFoundException("Não existe forma de pagamento com o id informado"))).collect(Collectors.toSet()));
        return restauranteOUTMapper.toDto(restauranteRepository.save(restaurante));
    }

    /**
     * @param id
     * @param up
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteOUT atualizar(final Long id, final @Valid RestauranteUP up) {
        Restaurante restauranteAtual = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com id informado não encontrado"));

        Cozinha novaCozinha = cozinhaRepository.findById(up.getCozinha().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cozinha com id informado não encontrada"));

        restauranteAtual.setCozinha(novaCozinha);

        restauranteUPMapper.partialUpdate(up, restauranteAtual);
        Restaurante restauranteSalvo = restauranteRepository.save(restauranteAtual);

        return restauranteOUTMapper.toDto(restauranteSalvo);
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
