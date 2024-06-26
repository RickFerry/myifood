package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.VendaDiaria;
import com.ferry.myifood.domain.model.dto.input.PedidoIN;
import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import com.ferry.myifood.domain.model.dto.update.PedidoUP;
import com.ferry.myifood.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {
    /**
     *
     */
    private final PedidoService pedidoService;

    /**
     * @param page
     * @return ResponseEntity<Page < PedidoOUT>>
     */
    @GetMapping
    public ResponseEntity<Page<PedidoOUT>> buscarTodos(Pageable page) {
        return ResponseEntity.ok(pedidoService.buscarTodos(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoOUT> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoOUT> salvar(@RequestBody @Valid PedidoIN in, UriComponentsBuilder uriBuilder) {
        PedidoOUT novo = pedidoService.salvar(in);
        var uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoOUT> update(@PathVariable Long id, @RequestBody @Valid PedidoUP up) {
        return ResponseEntity.ok(pedidoService.update(id, up));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/total-vendido-por-data", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> totalVendidoPorDataPdf(@RequestParam String dataInicio, @RequestParam String dataFim) {
        byte[] dataPdf = pedidoService.totalVendidoPorDataPdf(dataInicio, dataFim);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=total-vendido-por-data.pdf");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(dataPdf);
    }

    @GetMapping(path = "/total-vendido-por-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VendaDiaria>> totalVendidoPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
        return ResponseEntity.ok(pedidoService.totalVendidoPorData(dataInicio, dataFim));
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        pedidoService.confirmar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/a-caminho")
    public ResponseEntity<?> aCaminho(@PathVariable Long id) {
        pedidoService.aCaminho(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/entregue")
    public ResponseEntity<?> entregue(@PathVariable Long id) {
        pedidoService.entregue(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cancelado")
    public ResponseEntity<?> cancelado(@PathVariable Long id) {
        pedidoService.cancelado(id);
        return ResponseEntity.noContent().build();
    }
}
