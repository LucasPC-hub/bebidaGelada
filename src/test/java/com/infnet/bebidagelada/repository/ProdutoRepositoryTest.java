package com.infnet.bebidagelada.repository;

import com.infnet.bebidagelada.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSalvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Coca-Cola");
        produto.setDescricao("Refrigerante");
        produto.setTipo("Bebida");
        produto.setVolume(2.0);
        produto.setQuantidadeEstoque(100);
        produto.setDataFabricacao(LocalDate.now());
        produto.setDataValidade(LocalDate.now().plusMonths(6));

        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto).isNotNull();
        assertThat(savedProduto.getId()).isNotNull();
    }

    @Test
    public void testListarProdutos() {
        Produto produto1 = new Produto();
        produto1.setNome("Coca-Cola");
        produto1.setDescricao("Refrigerante");
        produto1.setTipo("Bebida");
        produto1.setVolume(2.0);
        produto1.setQuantidadeEstoque(100);
        produto1.setDataFabricacao(LocalDate.now());
        produto1.setDataValidade(LocalDate.now().plusMonths(6));

        Produto produto2 = new Produto();
        produto2.setNome("Pepsi");
        produto2.setDescricao("Refrigerante");
        produto2.setTipo("Bebida");
        produto2.setVolume(2.0);
        produto2.setQuantidadeEstoque(100);
        produto2.setDataFabricacao(LocalDate.now());
        produto2.setDataValidade(LocalDate.now().plusMonths(6));

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        List<Produto> produtos = produtoRepository.findAll();

        assertThat(produtos).hasSize(2);
    }
}