package com.infnet.bebidagelada.services;

import com.infnet.bebidagelada.model.HistoricoProduto;
import com.infnet.bebidagelada.model.Produto;
import com.infnet.bebidagelada.repository.HistoricoProdutoRepository;
import com.infnet.bebidagelada.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HistoricoProdutoRepository historicoProdutoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        Produto savedProduto = produtoRepository.save(produto);
        registrarHistorico(savedProduto, "SALVAR");
        return savedProduto;
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

    private void registrarHistorico(Produto produto, String operacao) {
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