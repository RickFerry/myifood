package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.FotoNaoEncontradaException;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ferry.myifood.domain.repository.custom.FotoStorageService.NovaFoto;
import static com.ferry.myifood.domain.utils.ConstantsUtil.*;

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
                        () -> new ProdutoNaoEncontradoException(produtoId, NAO_EXISTE_PRODUTO_COM_ESTE_ID_VINCULADO_A_ESTE_RESTAURANTE)));
    }

    @Transactional
    public void adicionaProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, NAO_EXISTE_PRODUTO_COM_ESTE_ID_VINCULADO_A_ESTE_RESTAURANTE));
        restaurante.adicionaProduto(produto);
    }

    @Transactional
    public void removeProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, NAO_EXISTE_PRODUTO_COM_ID_INFORMADO));
        restaurante.removeProduto(produto);
    }

    @Transactional
    public FotoProdutoOUT atualizaFotoProduto(Long restauranteId, Long produtoId, FotoProdutoIN fotoProdutoIN) {
        String nomeAntigoArquivo = null;

        Produto produto = restauranteRepository.findProdutoByRestauranteIdAndProdutoId(restauranteId, produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, NAO_EXISTE_PRODUTO_COM_ESTE_ID_VINCULADO_A_ESTE_RESTAURANTE));

        MultipartFile arquivo = fotoProdutoIN.getArquivo();
        FotoProduto fotoProduto = getFotoProduto(fotoProdutoIN, produto, arquivo);

        nomeAntigoArquivo = deletaFotoAntIgaSeExistirPegaNomeAntigoDoArquivo(restauranteId, produtoId, nomeAntigoArquivo);

        fotoProduto = produtoRepository.save(fotoProduto);
        produtoRepository.flush();

        fotoStorageService.substituir(getNovaFoto(fotoProduto, arquivo), nomeAntigoArquivo);
        return fotoProdutoOUTMapper.toDto(fotoProduto);
    }

    @Transactional(readOnly = true)
    public FotoProdutoOUT buscaFotoProduto(Long restauranteId, Long produtoId) {
        return fotoProdutoOUTMapper.toDto(restauranteRepository.findFotoById(restauranteId, produtoId).orElseThrow(
                () -> new FotoNaoEncontradaException(produtoId, NAO_EXISTE_FOTO_PARA_O_PRODUTO_COM_ESTE_ID)));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<InputStreamResource> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) {
        FotoProduto fotoProduto = restauranteRepository.findFotoById(restauranteId, produtoId).orElseThrow(
                () -> new FotoNaoEncontradaException(
                        produtoId, NAO_EXISTE_FOTO_PARA_O_PRODUTO_COM_ESTE_ID));

        try {
            MediaType mediaType = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> acceptsMediaTypes = MediaType.parseMediaTypes(acceptHeader);
            verificaCompatibilidadeMediaType(mediaType, acceptsMediaTypes);
        } catch (HttpMediaTypeNotAcceptableException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(fotoProduto.getContentType()))
                .body(new InputStreamResource(fotoStorageService.recuperar(fotoProduto.getNomeArquivo())));
    }

    private void verificaCompatibilidadeMediaType(MediaType mediaType, List<MediaType> acceptsMediaTypes) throws HttpMediaTypeNotAcceptableException {
        if (!acceptsMediaTypes.stream().anyMatch(mediaType::isCompatibleWith)) {
            throw new HttpMediaTypeNotAcceptableException(acceptsMediaTypes);
        }
    }

    private static NovaFoto getNovaFoto(FotoProduto fotoProduto, MultipartFile arquivo) {
        NovaFoto novaFoto;
        try {
            novaFoto = NovaFoto.builder()
                    .nomeArquivo(fotoProduto.getNomeArquivo())
                    .inputStream(arquivo.getInputStream())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return novaFoto;
    }

    private String deletaFotoAntIgaSeExistirPegaNomeAntigoDoArquivo(Long restauranteId, Long produtoId, String nomeAntigoArquivo) {
        Optional<FotoProduto> fotoOpcional = restauranteRepository.findFotoById(restauranteId, produtoId);
        if (fotoOpcional.isPresent()) {
            produtoRepository.delete(fotoOpcional.get());
            nomeAntigoArquivo = fotoOpcional.get().getNomeArquivo();
        }
        return nomeAntigoArquivo;
    }

    private FotoProduto getFotoProduto(FotoProdutoIN fotoProdutoIN, Produto produto, MultipartFile arquivo) {
        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setDescricao(fotoProdutoIN.getDescricao());
        fotoProduto.setProduto(produto);
        fotoProduto.setNomeArquivo(fotoStorageService.gerarNomeArquivo(arquivo.getOriginalFilename()));
        fotoProduto.setTamanho(arquivo.getSize());
        fotoProduto.setContentType(arquivo.getContentType());
        return fotoProduto;
    }
}
