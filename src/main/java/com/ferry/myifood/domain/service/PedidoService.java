package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.*;
import com.ferry.myifood.domain.mapper.complemento.ItemPedidoCompMapper;
import com.ferry.myifood.domain.mapper.complemento.ProdutoCompOutMapper;
import com.ferry.myifood.domain.mapper.pedido.PedidoINMapper;
import com.ferry.myifood.domain.mapper.pedido.PedidoOUTMapper;
import com.ferry.myifood.domain.mapper.pedido.PedidoUPMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.ItemPedido;
import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dto.VendaDiaria;
import com.ferry.myifood.domain.model.dto.input.PedidoIN;
import com.ferry.myifood.domain.model.dto.output.PedidoOUT;
import com.ferry.myifood.domain.model.dto.output.ProdutoCompOut;
import com.ferry.myifood.domain.model.dto.update.PedidoUP;
import com.ferry.myifood.domain.model.enums.StatusPedido;
import com.ferry.myifood.domain.repository.*;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.ferry.myifood.domain.utils.ConstantsUtil.*;
import static java.lang.String.format;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final FormasPagamentoRepository formasPagamentoRepository;
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final PedidoOUTMapper pedidoOUTMapper;
    private final PedidoINMapper pedidoINMapper;
    private final PedidoUPMapper pedidoUPMapper;
    private final ProdutoCompOutMapper produtoCompOutMapper;
    private final ItemPedidoCompMapper itemPedidoCompMapper;

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

        validaEndereco(in, pedido);
        validaFormaPagamento(in, pedido);
        validaRestaurante(in, pedido);
        validaCliente(in, pedido);
        validaItensPedido(in, pedido);

        return pedidoOUTMapper.toDto(pedidoRepository.save(pedido));
    }

    @Transactional
    public PedidoOUT update(Long id, PedidoUP up) {
        Pedido pedido = getPedido(id);

        Cidade novaCidade = cidadeRepository.findById(up.getEnderecoEntrega().getCidade().getId())
                .orElseThrow(
                        () -> new CidadeNaoEncontradaException(up.getEnderecoEntrega().getCidade().getId(), CIDADE_COM_ID_INFORMADO_NAO_EXISTE));
        pedido.getEnderecoEntrega().setCidade(novaCidade);

        pedido.setFormaPagamento(formasPagamentoRepository.findById(up.getFormaPagamento().getId()).orElseThrow(
                () -> new FormasPagamentoNaoEncontradaException(up.getFormaPagamento().getId(), FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE)
        ));

        pedidoUPMapper.partialUpdate(up, pedido);
        return pedidoOUTMapper.toDto(pedidoRepository.save(pedido));
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.findById(id).ifPresentOrElse(pedidoRepository::delete, () -> {
                    throw new PedidoNaoEncontradoException(id, PEDIDO_COM_ID_INFORMADO_NAO_EXISTE);
                }
        );
    }

    @Transactional(readOnly = true)
    public List<VendaDiaria> totalVendidoPorData(String dataInicio, String dataFim) {
        return pedidoRepository.totalVendidoPorData(LocalDateTime.parse(dataInicio), LocalDateTime.parse(dataFim));
    }

    @Transactional(readOnly = true)
    public byte[] totalVendidoPorDataPdf(String dataInicio, String dataFim) {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
            HashMap<String, Object> map = new HashMap<>();
            map.put("REPORT_LOCALE", new Locale("pt", "BR"));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                    pedidoRepository.totalVendidoPorData(LocalDateTime.parse(dataInicio), LocalDateTime.parse(dataFim)));
            return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(stream, map, dataSource));
        } catch (Exception e) {
            throw new RelatorioException("Erro ao gerar relatório.", e);
        }
    }

    @Transactional
    public void confirmar(Long id) {
        Pedido pedido = getPedido(id);
        verificaStatus(pedido, StatusPedido.CRIADO);
        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(LocalDateTime.now());
    }

    @Transactional
    public void aCaminho(Long id) {
        Pedido pedido = getPedido(id);
        verificaStatus(pedido, StatusPedido.CONFIRMADO);
        pedido.setStatus(StatusPedido.A_CAMINHO);
        pedido.setDataACaminho(LocalDateTime.now());
    }

    @Transactional
    public void entregue(Long id) {
        Pedido pedido = getPedido(id);
        verificaStatus(pedido, StatusPedido.A_CAMINHO);
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setDataEntrega(LocalDateTime.now());
    }

    @Transactional
    public void cancelado(Long id) {
        Pedido pedido = getPedido(id);
        verificaStatus(pedido, StatusPedido.CRIADO);
        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setDataCancelamento(LocalDateTime.now());
    }

    private static void verificaStatus(Pedido pedido, StatusPedido status) {
        if (!pedido.getStatus().equals(status)) throw new StatusInvalidoException("Tentativa de mudança de status.",
                format("Alteração de status inválida! Está alteração somente ocorrerá, após o status de %s.", status));
    }

    private Pedido getPedido(Long id) {
        return pedidoRepository.findById(id).orElseThrow(
                () -> new PedidoNaoEncontradoException(id, PEDIDO_COM_ID_INFORMADO_NAO_EXISTE));
    }

    private void validaEndereco(PedidoIN in, Pedido pedido) {
        pedido.getEnderecoEntrega().setCidade(cidadeRepository.findById(in.getEnderecoEntrega().getCidade().getId()).orElseThrow(
                () -> new CidadeNaoEncontradaException(in.getEnderecoEntrega().getCidade().getId(), CIDADE_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    private void validaFormaPagamento(PedidoIN in, Pedido pedido) {
        pedido.setFormaPagamento(formasPagamentoRepository.findById(in.getFormaPagamento().getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(in.getFormaPagamento().getId(), FORMA_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    private void validaRestaurante(PedidoIN in, Pedido pedido) {
        pedido.setRestaurante(restauranteRepository.findById(in.getRestaurante().getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(in.getRestaurante().getId(), RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    private void validaCliente(PedidoIN in, Pedido pedido) {
        pedido.setCliente(usuarioRepository.findById(in.getCliente().getId()).orElseThrow(
                () -> new PedidoNaoEncontradoException(in.getCliente().getId(), USUARIO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    private void validaItensPedido(PedidoIN in, Pedido pedido) {
        pedido.setItens(in.getItensPedido().stream().map(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow(
                    () -> new ProdutoNaoEncontradoException(item.getProduto().getId(), PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));
            ProdutoCompOut produtoCompOut = produtoCompOutMapper.toDto(produto);
            item.setProduto(produtoCompOut);
            ItemPedido itemPedido = itemPedidoCompMapper.toEntity(item);
            itemPedido.setPrecoUnitario(produto.getPreco());
            itemPedido.setPrecoTotal(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
            itemPedido.setPedido(pedido);
            return itemPedido;
        }).collect(Collectors.toSet()));
    }
}
