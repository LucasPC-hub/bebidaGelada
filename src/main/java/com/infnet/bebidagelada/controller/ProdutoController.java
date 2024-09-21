package com.infnet.bebidagelada.controller;

import com.infnet.bebidagelada.model.HistoricoProduto;
import com.infnet.bebidagelada.services.HistoricoProdutoService;
import com.infnet.bebidagelada.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.infnet.bebidagelada.model.Produto;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private HistoricoProdutoService historicoProdutoService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deletarTodosProdutos() {
        produtoService.deletarTodosProdutos();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/historico")
    public List<HistoricoProduto> listarHistorico() {
        return historicoProdutoService.listarHistorico();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.buscarPorId(id)
                .map(existingProduto -> {
                    produto.setId(id);
                    Produto updatedProduto = produtoService.alterarProduto(produto);
                    return ResponseEntity.ok(updatedProduto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}