package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.GrupoNaoEncontradoException;
import com.ferry.myifood.domain.exception.UniqueConstraintViolationException;
import com.ferry.myifood.domain.exception.UsuarioNaoEncontradoException;
import com.ferry.myifood.domain.mapper.usuario.UsuarioINMapper;
import com.ferry.myifood.domain.mapper.usuario.UsuarioOUTMapper;
import com.ferry.myifood.domain.mapper.usuario.UsuarioUPMapper;
import com.ferry.myifood.domain.model.Usuario;
import com.ferry.myifood.domain.model.dto.input.UsuarioIN;
import com.ferry.myifood.domain.model.dto.output.UsuarioOUT;
import com.ferry.myifood.domain.model.dto.update.UsuarioUP;
import com.ferry.myifood.domain.repository.GrupoRepository;
import com.ferry.myifood.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.ferry.myifood.domain.utils.ConstantsUtil.GRUPO_COM_ID_INFORMADO_NAO_EXISTE;
import static com.ferry.myifood.domain.utils.ConstantsUtil.USUARIO_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class UsuarioService {
    /**
     *
     */
    private final UsuarioRepository usuarioRepository;
    /**
     *
     */
    private final GrupoRepository grupoRepository;
    /**
     *
     */
    private final UsuarioINMapper usuarioINMapper;
    /**
     *
     */
    private final UsuarioOUTMapper usuarioOUTMapper;
    /**
     *
     */
    private final UsuarioUPMapper usuarioUPMapper;

    @Transactional(readOnly = true)
    public Page<UsuarioOUT> findAll(Pageable page) {
        return usuarioRepository.findAll(page).map(usuarioOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public UsuarioOUT findById(Long id) {
        return usuarioRepository.findById(id).map(usuarioOUTMapper::toDto).orElseThrow(
                () -> new UsuarioNaoEncontradoException(id, USUARIO_COM_ID_INFORMADO_NAO_EXISTE));
    }

    @Transactional
    public UsuarioOUT save(UsuarioIN in) {
        Usuario usuario = usuarioINMapper.toEntity(in);
        usuario.setGrupos(in.getGrupos().stream().map(grupo -> grupoRepository.findById(grupo.getId()).orElseThrow(
                () -> new GrupoNaoEncontradoException(
                        grupo.getId(), GRUPO_COM_ID_INFORMADO_NAO_EXISTE))).collect(Collectors.toSet()));
        try {
            return usuarioOUTMapper.toDto(usuarioRepository.save(usuario));
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("email", "Email já está em uso");
        }
    }

    @Transactional
    public UsuarioOUT update(Long id, UsuarioUP up) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException(id, USUARIO_COM_ID_INFORMADO_NAO_EXISTE));
        usuario.setGrupos(up.getGrupos().stream().map(grupo -> grupoRepository.findById(grupo.getId()).orElseThrow(
                () -> new GrupoNaoEncontradoException(
                        grupo.getId(), GRUPO_COM_ID_INFORMADO_NAO_EXISTE))).collect(Collectors.toSet()));
        usuarioUPMapper.partialUpdate(up, usuario);
        return usuarioOUTMapper.toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.findById(id).ifPresentOrElse(usuarioRepository::delete, () -> {
            throw new UsuarioNaoEncontradoException(id, USUARIO_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }
}
