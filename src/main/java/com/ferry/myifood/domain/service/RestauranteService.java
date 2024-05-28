package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CidadeNaoEncontradaException;
import com.ferry.myifood.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.myifood.domain.exception.EstadoNaoEncontradoException;
import com.ferry.myifood.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteINMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteOUTMapper;
import com.ferry.myifood.domain.mapper.restaurante.RestauranteUPMapper;
import com.ferry.myifood.domain.mapper.usuario.UsuarioDetalheMapper;
import com.ferry.myifood.domain.mapper.usuario.UsuarioOUTMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.input.RestauranteIN;
import com.ferry.myifood.domain.model.dto.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dto.output.UsuarioDetalhe;
import com.ferry.myifood.domain.model.dto.output.UsuarioOUT;
import com.ferry.myifood.domain.model.dto.update.RestauranteUP;
import com.ferry.myifood.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.ferry.myifood.domain.utils.ConstantsUtil.*;

@Service
@AllArgsConstructor
public class RestauranteService {
    /**
     *
     */
    private final UsuarioRepository usuarioRepository;
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
     *
     */
    private final UsuarioDetalheMapper usuarioDetalheMapper;

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
                () -> new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
    }

    /**
     * @param in
     * @return RestauranteDto
     */
    @Transactional
    public RestauranteOUT salvar(final RestauranteIN in) {
        Restaurante restaurante = restauranteINMapper.toEntity(in);

        restaurante.setCozinha(cozinhaRepository.findById(in.getCozinha().getId()).orElseThrow(
                () -> new CozinhaNaoEncontradaException(in.getCozinha().getId(), COZINHA_COM_ID_INFORMADO_NAO_EXISTE)));

        restaurante.getEndereco().setCidade(cidadeRepository.findById(restaurante.getEndereco().getCidade().getId()).orElseThrow(
                () -> new CidadeNaoEncontradaException(restaurante.getEndereco().getCidade().getId(), CIDADE_COM_ID_INFORMADO_NAO_EXISTE)));

        restaurante.getEndereco().getCidade().setEstado(estadoRepository.findById(restaurante.getEndereco().getCidade().getEstado().getId()).orElseThrow(
                () -> new EstadoNaoEncontradoException(restaurante.getEndereco().getCidade().getEstado().getId(), ESTADO_COM_ID_INFORMADO_NAO_EXISTE)));

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
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));

        restauranteAtual.setCozinha(cozinhaRepository.findById(up.getCozinha().getId())
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id, COZINHA_COM_ID_INFORMADO_NAO_EXISTE)));

        Cidade novaCidade = cidadeRepository.findById(up.getEndereco().getCidade().getId())
                .orElseThrow(() -> new CidadeNaoEncontradaException(id, CIDADE_COM_ID_INFORMADO_NAO_EXISTE));

        restauranteAtual.getEndereco().setCidade(novaCidade);
        restauranteUPMapper.partialUpdate(up, restauranteAtual);

        return restauranteOUTMapper.toDto(restauranteRepository.save(restauranteAtual));
    }

    /**
     * @param id
     */
    @Transactional
    public void deletar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(restauranteRepository::delete, () -> {
            throw new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }

    /**
     * @param id
     */
    @Transactional
    public void ativar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::ativar, () -> {
            throw new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }

    /**
     * @param id
     */
    @Transactional
    public void inativar(final Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::inativar, () -> {
            throw new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }

    /**
     * @param ids
     */
    @Transactional
    public void ativacoes(Set<Long> ids) {
        ids.forEach(this::ativar);
    }

    /**
     * @param ids
     */
    @Transactional
    public void inativacoes(Set<Long> ids) {
        ids.forEach(this::inativar);
    }

    /**
     * @param id
     */
    @Transactional
    public void abrir(Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::abrir, () -> {
            throw new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }

    /**
     * @param id
     */
    @Transactional
    public void fechar(Long id) {
        restauranteRepository.findById(id).ifPresentOrElse(Restaurante::fechar, () -> {
            throw new RestauranteNaoEncontradoException(id, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }

    /**
     * @param idRestaurante
     * @return Set<UsuarioDto>
     */
    @Transactional(readOnly = true)
    public Set<UsuarioDetalhe> listarResponsaveis(Long idRestaurante) {
        return usuarioDetalheMapper.toDto(restauranteRepository.findById(idRestaurante).orElseThrow(
                () -> new RestauranteNaoEncontradoException(idRestaurante, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)
        ).getResponsaveis());
    }

    /**
     * @param idRestaurante
     * @param idResponsavel
     */
    @Transactional
    public void adicionarResponsavel(Long idRestaurante, Long idResponsavel) {
        Restaurante restaurante = restauranteRepository.findById(idRestaurante).orElseThrow(
                () -> new RestauranteNaoEncontradoException(idRestaurante, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));

        Usuario usuario = usuarioRepository.findById(idResponsavel).orElseThrow(
                () -> new RestauranteNaoEncontradoException(idResponsavel, USUARIO_COM_ID_INFORMADO_NAO_EXISTE));

        restaurante.adicionaUsuario(usuario);
    }

    /**
     * @param idRestaurante
     * @param idResponsavel
     */
    @Transactional
    public void removerResponsavel(Long idRestaurante, Long idResponsavel) {
        Restaurante restaurante = restauranteRepository.findById(idRestaurante).orElseThrow(
                () -> new RestauranteNaoEncontradoException(idRestaurante, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));

        Usuario usuario = usuarioRepository.findById(idResponsavel).orElseThrow(
                () -> new RestauranteNaoEncontradoException(idResponsavel, USUARIO_COM_ID_INFORMADO_NAO_EXISTE));

        restaurante.removeUsuario(usuario);
    }
}
