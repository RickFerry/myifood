package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.restaurante.RestauranteINMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteOUTMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteUPMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.input.RestauranteIN;
import com.ferry.myifood.domain.model.dtos.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dtos.update.RestauranteUP;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import com.ferry.myifood.domain.repository.EstadoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Service
@AllArgsConstructor
public class RestauranteService {
    /**
     *
     */
    public static final String NAO_EXISTE_RESTAURANTE_COM_O_ID_INFORMADO = "Não existe restaurante com o id informado";
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
    private final EstadoRepository estadoRepository;
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
    public RestauranteOUT salvar(final RestauranteIN in) {
        Restaurante restaurante = restauranteINMapper.toEntity(in);

        restaurante.setCozinha(cozinhaRepository.findById(in.getCozinha().getId()).orElseThrow(
                () -> new EntityNotFoundException("Não existe cozinha com o id informado")));

        restaurante.getEndereco().setCidade(cidadeRepository.findById(restaurante.getEndereco().getCidade().getId()).orElseThrow(
                () -> new EntityNotFoundException("Não existe cidade com o id informado")));

        restaurante.getEndereco().getCidade().setEstado(estadoRepository.findById(restaurante.getEndereco().getCidade().getEstado().getId()).orElseThrow(
                () -> new EntityNotFoundException("Não existe estado com o id informado")));

        return restauranteOUTMapper.toDto(restauranteRepository.save(restaurante));
    }

    /**
     * @param id
     * @param up
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteOUT atualizar(final Long id, final RestauranteUP up) {
        Restaurante restauranteAtual = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com id informado não encontrado"));

        restauranteAtual.setCozinha(cozinhaRepository.findById(up.getCozinha().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cozinha com id informado não encontrada")));

        Cidade novaCidade = cidadeRepository.findById(up.getEndereco().getCidade().getId())
                .orElseThrow(() -> new EntityNotFoundException("Não existe cidade com o id informado"));

        restauranteAtual.getEndereco().setCidade(novaCidade);

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
            throw new EntityNotFoundException(NAO_EXISTE_RESTAURANTE_COM_O_ID_INFORMADO);
        });
    }

    /**
     * @param id
     */
    @Transactional
    public void ativar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::ativar, () -> {
            throw new EntityNotFoundException(NAO_EXISTE_RESTAURANTE_COM_O_ID_INFORMADO);
        });
    }

    /**
     * @param id
     */
    @Transactional
    public void inativar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::inativar, () -> {
            throw new EntityNotFoundException(NAO_EXISTE_RESTAURANTE_COM_O_ID_INFORMADO);
        });
    }
}
