package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CidadeNaoEncontradaException;
import com.ferry.myifood.domain.exception.PedidoNaoEncontradoException;
import com.ferry.myifood.domain.mapper.pedido.PedidoINMapper;
import com.ferry.myifood.domain.mapper.pedido.PedidoOUTMapper;
import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.input.PedidoIN;
import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import com.ferry.myifood.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.ferry.myifood.domain.utils.ConstantsUtil.*;

@Service
@AllArgsConstructor
public class PedidoService {
    /**
     *
     */
    private final PedidoRepository pedidoRepository;
    private final FormasPagamentoRepository formasPagamentoRepository;
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final CidadeRepository cidadeRepository;
    /**
     *
     */
    private final PedidoOUTMapper pedidoOUTMapper;
    private final PedidoINMapper pedidoINMapper;

    /**
     * @param page
     * @return Page<PedidoOUT>
     */
    @Transactional(readOnly = true)
    public Page<PedidoOUT> buscarTodos(Pageable page) {
        return pedidoRepository.findAll(page).map(pedidoOUTMapper::toDto);
    }

    /**
     * @param id
     * @return PedidoOUT
     */
    @Transactional(readOnly = true)
    public PedidoOUT buscarPorId(Long id) {
        return pedidoOUTMapper.toDto(pedidoRepository.findById(id).orElseThrow(
                () -> new PedidoNaoEncontradoException(id, PEDIDO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    /**
     * @param in
     * @return PedidoOUT
     */
    @Transactional
    public PedidoOUT salvar(PedidoIN in) {
        Pedido pedido = pedidoINMapper.toEntity(in);
        pedido.getEnderecoEntrega().setCidade(cidadeRepository.findById(in.getEnderecoEntrega().getCidade().getId()).orElseThrow(
                () -> new CidadeNaoEncontradaException(in.getEnderecoEntrega().getCidade().getId(), CIDADE_COM_ID_INFORMADO_NAO_EXISTE)));

        pedido.setFormaPagamento(formasPagamentoRepository.findById(in.getFormaPagamento().getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(
                        in.getFormaPagamento().getId(), FORMA_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE)));

        pedido.setRestaurante(restauranteRepository.findById(in.getRestaurante().getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(
                        in.getRestaurante().getId(), RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)));

        pedido.setCliente(usuarioRepository.findById(in.getCliente().getId()).orElseThrow(() -> new PedidoNaoEncontradoException(
                in.getCliente().getId(), USUARIO_COM_ID_INFORMADO_NAO_EXISTE)));

        pedido.setItens(in.getItensPedido().stream().map(
                itemPedidoComp ->  itemPedidoRepository.findById(itemPedidoComp.getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(
                        itemPedidoComp.getId(), ITEM_PEDIDO_COM_ID_INFORMADO_NAO_EXISTE))).collect(Collectors.toSet()));
        return pedidoOUTMapper.toDto(pedidoRepository.save(pedido));
    }
}
