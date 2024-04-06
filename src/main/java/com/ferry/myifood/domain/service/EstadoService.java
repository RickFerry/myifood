package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
}
