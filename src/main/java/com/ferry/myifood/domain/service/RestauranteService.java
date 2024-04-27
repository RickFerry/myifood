package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.RestauranteMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;
    private final RestauranteMapper restauranteMapper;

    @Transactional(readOnly = true)
    public Page<RestauranteDto> listar(Pageable page) {
        return restauranteRepository.findAll(page).map(restauranteMapper::toDto);
    }

    @Transactional(readOnly = true)
    public RestauranteDto buscar(Long id) {
        return restauranteRepository.findById(id).map(restauranteMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }

    @Transactional
    public RestauranteDto salvar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId())
                .orElseThrow(() -> new RuntimeException("Não existe cozinha com o id informado"));
        restaurante.setCozinha(cozinha);
        return restauranteMapper.toDto(restauranteRepository.save(restaurante));
    }

    @Transactional
    public RestauranteDto atualizar(Long id, RestauranteDto dto) {
        return restauranteMapper.toDto(restauranteMapper.partialUpdate(dto, restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não existe restaurante com o id informado"))));
    }

    @Transactional
    public void deletar(Long id) {
        restauranteRepository.findById(id)
                .ifPresentOrElse(restauranteRepository::delete, () -> {
                    throw new EntityNotFoundException("Não existe restaurante com o id informado");
                });
    }
}
