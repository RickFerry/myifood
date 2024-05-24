package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.FormaPagamentoIN;
import com.ferry.myifood.domain.model.dto.output.FormaPagamentoOUT;
import com.ferry.myifood.domain.model.dto.update.FormaPagamentoUP;
import com.ferry.myifood.domain.service.FormaPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {
    private final FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<Page<FormaPagamentoOUT>> findAll(Pageable pageable) {
        return ResponseEntity.ok(formaPagamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoOUT> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(formaPagamentoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoOUT> save(@RequestBody @Valid FormaPagamentoIN in) {
        return ResponseEntity.ok(formaPagamentoService.save(in));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoOUT> update(@PathVariable Long id, @RequestBody @Valid FormaPagamentoUP up) {
        try {
            return ResponseEntity.ok(formaPagamentoService.update(id, up));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            formaPagamentoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
