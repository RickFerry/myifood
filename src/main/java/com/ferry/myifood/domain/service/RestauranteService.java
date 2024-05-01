package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.RestauranteMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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
    private final CozinhaRepository cozinhaRepository;
    /**
     *
     */
    private final RestauranteMapper restauranteMapper;

    /**
     * @param page
     * @return Page<RestauranteDto>
     */
    @Transactional(readOnly = true)
    public Page<RestauranteDto> listar(final Pageable page) {
        return restauranteRepository
                    .findAll(page).map(restauranteMapper::toDto);
    }

    /**
         * @param id
         * @return RestauranteDto
         */
        @Transactional(readOnly = true)
    public RestauranteDto buscar(final Long id) {
        return restauranteRepository.findById(id).map(restauranteMapper::toDto)
                .orElseThrow(() -> new RuntimeException(
                    "Restaurante não encontrado"));
    }

    /**
     * @param restaurante
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteDto salvar(final Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository
                            .findById(restaurante.getCozinha().getId())
                .orElseThrow(() ->
                new RuntimeException("Não existe cozinha com o id informado"));
        restaurante.setCozinha(cozinha);
        return restauranteMapper.toDto(restauranteRepository.save(restaurante));
    }

    /**
     * @param id
     * @param dto
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteDto atualizar(
            final Long id, final RestauranteDto dto) {
        return restauranteMapper
                .toDto(restauranteMapper
                .partialUpdate(dto, restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Não existe restaurante com o id informado"))));
    }

    /**
     * @param id
     */
    @Transactional
    public void deletar(final Long id) {
        restauranteRepository.findById(id)
                .ifPresentOrElse(restauranteRepository::delete, () -> {
                    throw new EntityNotFoundException(
                        "Não existe restaurante com o id informado");
                });
    }
}
