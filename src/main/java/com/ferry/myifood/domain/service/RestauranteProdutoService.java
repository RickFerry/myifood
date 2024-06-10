package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.ProdutoNaoEncontradoException;
import com.ferry.myifood.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.myifood.domain.mapper.fotoproduto.FotoProdutoOUTMapper;
import com.ferry.myifood.domain.mapper.produto.ProdutoOUTMapper;
import com.ferry.myifood.domain.model.FotoProduto;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.dto.input.FotoProdutoIN;
import com.ferry.myifood.domain.model.dto.output.FotoProdutoOUT;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import com.ferry.myifood.domain.repository.ProdutoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import com.ferry.myifood.domain.repository.custom.FotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.ferry.myifood.domain.repository.custom.FotoStorageService.*;
import static com.ferry.myifood.domain.utils.ConstantsUtil.PRODUTO_COM_ID_INFORMADO_NAO_EXISTE;
import static com.ferry.myifood.domain.utils.ConstantsUtil.RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class RestauranteProdutoService {
    /**
     *
     */
    private final RestauranteRepository restauranteRepository;
    /**
     *
     */
    private final FotoStorageService fotoStorageService;
    /**
     *
     */
    private final ProdutoRepository produtoRepository;
    /**
     *
     */
    private final ProdutoOUTMapper produtoOUTMapper;
    /**
     *
     */
    private final FotoProdutoOUTMapper fotoProdutoOUTMapper;

    @Transactional(readOnly = true)
    public Set<ProdutoOUT> buscaProdutos(Long restauranteId) {
        return produtoOUTMapper.toDto(restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)).getProdutos());
    }

    @Transactional(readOnly = true)
    public Set<ProdutoOUT> buscaProdutosAtivos(Long restauranteId) {
        return produtoOUTMapper.toDto(restauranteRepository.buscaProdutosAtivosPorId(restauranteId));
    }

    @Transactional(readOnly = true)
    public ProdutoOUT buscaProduto(Long restauranteId, Long produtoId) {
        return produtoOUTMapper.toDto(restauranteRepository.findById(restauranteId).orElseThrow(
                        () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE))
                .getProdutos().stream().filter(produto -> produto.getId().equals(produtoId)).findFirst().orElseThrow(
                        () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    @Transactional
    public void adicionaProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.adicionaProduto(produto);
    }

    @Transactional
    public void removeProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.removeProduto(produto);
    }

    @Transactional
    public FotoProdutoOUT atualizaFotoProduto(Long restauranteId, Long produtoId, FotoProdutoIN fotoProdutoIN) {
        String nomeAntigoArquivo = null;

        Produto produto = restauranteRepository.findProdutoByRestauranteIdAndProdutoId(restauranteId, produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));

        MultipartFile arquivo = fotoProdutoIN.getArquivo();
        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setDescricao(fotoProdutoIN.getDescricao());
        fotoProduto.setProduto(produto);
        fotoProduto.setNomeArquivo(fotoStorageService.gerarNomeArquivo(arquivo.getOriginalFilename()));
        fotoProduto.setTamanho(arquivo.getSize());
        fotoProduto.setContentType(arquivo.getContentType());

        Optional<FotoProduto> fotoOpcional = restauranteRepository.findFotoById(restauranteId, produtoId);
        if (fotoOpcional.isPresent()) {
            produtoRepository.delete(fotoOpcional.get());
            nomeAntigoArquivo = fotoOpcional.get().getNomeArquivo();
        }

        fotoProduto = produtoRepository.save(fotoProduto);
        produtoRepository.flush();

        NovaFoto novaFoto;
        try {
            novaFoto = NovaFoto.builder()
                    .nomeArquivo(fotoProduto.getNomeArquivo())
                    .inputStream(arquivo.getInputStream())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fotoStorageService.substituir(novaFoto, nomeAntigoArquivo);
        return fotoProdutoOUTMapper.toDto(fotoProduto);
    }
}
