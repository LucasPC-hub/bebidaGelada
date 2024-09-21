package com.infnet.bebidagelada.services;

import com.infnet.bebidagelada.events.ProdutoAlteradoEvent;
import com.infnet.bebidagelada.events.ProdutoEvent;
import com.infnet.bebidagelada.model.HistoricoProduto;
import com.infnet.bebidagelada.model.Produto;
import com.infnet.bebidagelada.repository.HistoricoProdutoRepository;
import com.infnet.bebidagelada.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HistoricoProdutoRepository historicoProdutoRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        Produto savedProduto = produtoRepository.save(produto);
        eventPublisher.publishEvent(new ProdutoEvent(this, savedProduto));
        return savedProduto;
    }

    public Produto alterarProduto(Produto produto) {
        Produto updatedProduto = produtoRepository.save(produto);
        eventPublisher.publishEvent(new ProdutoAlteradoEvent(this, updatedProduto));
        return updatedProduto;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletarProduto(Long id) {
        produtoRepository.findById(id).ifPresent(produto -> {
            registrarHistorico(produto, "DELETAR");
            produtoRepository.deleteById(id);
        });
    }

    public void deletarTodosProdutos() {
        produtoRepository.findAll().forEach(produto -> registrarHistorico(produto, "DELETAR"));
        produtoRepository.deleteAll();
    }

    public void registrarHistorico(Produto produto, String operacao) {
        HistoricoProduto historico = new HistoricoProduto();
        historico.setProdutoId(produto.getId());
        historico.setNome(produto.getNome());
        historico.setDescricao(produto.getDescricao());
        historico.setTipo(produto.getTipo());
        historico.setVolume(produto.getVolume());
        historico.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        historico.setDataFabricacao(produto.getDataFabricacao());
        historico.setDataValidade(produto.getDataValidade());
        historico.setDataAlteracao(LocalDateTime.now());
        historico.setOperacao(operacao);
        historicoProdutoRepository.save(historico);
    }
}