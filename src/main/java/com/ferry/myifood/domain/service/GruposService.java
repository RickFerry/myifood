package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.grupo.GrupoINMapper;
import com.ferry.myifood.domain.mapper.grupo.GrupoOUTMapper;
import com.ferry.myifood.domain.mapper.grupo.GrupoUPMapper;
import com.ferry.myifood.domain.model.Grupo;
import com.ferry.myifood.domain.model.dto.input.GrupoIN;
import com.ferry.myifood.domain.model.dto.output.GrupoOUT;
import com.ferry.myifood.domain.model.dto.update.GrupoUP;
import com.ferry.myifood.domain.repository.GrupoRepository;
import com.ferry.myifood.domain.repository.PermissaoRepository;
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
public class GruposService {
    public static final String GRUPO_COM_ID_INFORMADO_NAO_EXISTE = "Grupo com id informado não existe";
    /**
     *
     */
    private final GrupoRepository gruposRepository;
    /**
     *
     */
    private final PermissaoRepository permissaoRepository;
    /**
     *
     */
    private final GrupoOUTMapper grupoOUTMapper;
    /**
     *
     */
    private final GrupoINMapper grupoINMapper;
    /**
     *
     */
    private final GrupoUPMapper grupoUPMapper;

    @Transactional(readOnly = true)
    public Page<GrupoOUT> listar(Pageable page) {
        return gruposRepository.findAll(page).map(grupoOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public GrupoOUT buscar(Long id) {
        return grupoOUTMapper.toDto(gruposRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(GRUPO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    @Transactional
    public GrupoOUT salvar(GrupoIN in) {
        Grupo novoGrupo = grupoINMapper.toEntity(in);
        novoGrupo.setPermissoes(in.getPermissoes().stream()
                .map(permissaoComp -> permissaoRepository.findById(permissaoComp.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada")))
                .collect(Collectors.toSet()));
        return grupoOUTMapper.toDto(gruposRepository.save(novoGrupo));
    }

    @Transactional
    public GrupoOUT atualizar(Long id, @Valid GrupoUP in) {
        return grupoOUTMapper.toDto(gruposRepository.save(grupoUPMapper.partialUpdate(in, gruposRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(GRUPO_COM_ID_INFORMADO_NAO_EXISTE)))));
    }

    @Transactional
    public void deletar(Long id) {
        gruposRepository.findById(id).ifPresentOrElse(gruposRepository::delete,
                () -> {
                    throw new EntityNotFoundException(GRUPO_COM_ID_INFORMADO_NAO_EXISTE);
                });
    }
}
