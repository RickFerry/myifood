package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import com.ferry.myifood.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {
    /**
     *
     */
    private final PedidoService pedidoService;

    /**
     *
     * @param page
     * @return ResponseEntity<Page<PedidoOUT>>
     */
    @GetMapping
    public ResponseEntity<Page<PedidoOUT>> buscarTodos(Pageable page) {
        return ResponseEntity.ok(pedidoService.buscarTodos(page));
    }
}
