package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        return cidadeRepository.findById(id)
                .map(c -> {
                    c.setEstado(estadoRepository.findById(cidade.getEstado().getId())
                            .orElseThrow(() -> new RuntimeException("Não existe estado com o id informado")));
                    BeanUtils.copyProperties(cidade, c, "id");
                    return cidadeRepository.save(c);
                })
                .orElseThrow(() -> new EntityNotFoundException("Não existe cidade com o id informado"));
    }

    @Transactional
    public void remover(Long id) {
        cidadeRepository.findById(id)
                .ifPresentOrElse(cidadeRepository::delete,
                        () -> {
                            throw new EntityNotFoundException("Não existe cidade com o id informado");
                        });
    }
}
