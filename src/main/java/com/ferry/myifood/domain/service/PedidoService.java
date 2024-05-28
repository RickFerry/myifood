package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.pedido.PedidoOUTMapper;
import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import com.ferry.myifood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PedidoService {
    /**
     *
     */
    private final PedidoRepository pedidoRepository;
    /**
     *
     */
    private final PedidoOUTMapper pedidoOUTMapper;

    /**
     *
     * @param page
     * @return Page<PedidoOUT>
     */
    @Transactional(readOnly = true)
    public Page<PedidoOUT> buscarTodos(Pageable page) {
        return pedidoRepository.findAll(page).map(pedidoOUTMapper::toDto);
    }
}
