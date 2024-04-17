package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.Restaurante;
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

    @Transactional(readOnly = true)
    public Page<Restaurante> listar(Pageable page) {
        return restauranteRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId())
                .orElseThrow(() -> new RuntimeException("Não existe cozinha com o id informado"));
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        return restauranteRepository.findById(id)
                .map(r -> {
                    Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId())
                            .orElseThrow(() -> new RuntimeException("Não existe cozinha com o id informado"));
                    BeanUtils.copyProperties(
                            restaurante, r, "id", "formasPagamento", "endereco", "dataCadastro"
                    );
                    r.setCozinha(cozinha);
                    return restauranteRepository.save(r);
                })
                .orElseThrow(() -> new EntityNotFoundException("Não existe restaurante com o id informado"));
    }

    @Transactional
    public void deletar(Long id) {
        restauranteRepository.findById(id)
                .ifPresentOrElse(restauranteRepository::delete, () -> {
                    throw new EntityNotFoundException("Não existe restaurante com o id informado");
                });
    }
}
