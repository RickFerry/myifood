package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.FormasPagamentoIN;
import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import com.ferry.myifood.domain.model.dto.update.FormasPagamentoUP;
import com.ferry.myifood.domain.service.FormasPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    private final FormasPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<Page<FormasPagamentoOUT>> findAll(Pageable pageable) {
        return ResponseEntity.ok(formaPagamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormasPagamentoOUT> findById(@PathVariable Long id) {
            return ResponseEntity.ok(formaPagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FormasPagamentoOUT> save(@RequestBody @Valid FormasPagamentoIN in) {
        return ResponseEntity.ok(formaPagamentoService.save(in));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormasPagamentoOUT> update(@PathVariable Long id, @RequestBody @Valid FormasPagamentoUP up) {
            return ResponseEntity.ok(formaPagamentoService.update(id, up));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            formaPagamentoService.delete(id);
            return ResponseEntity.noContent().build();
    }
}
