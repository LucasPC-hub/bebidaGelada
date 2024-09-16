package com.infnet.bebidagelada.repository;

import com.infnet.bebidagelada.model.HistoricoProduto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class HistoricoProdutoRepositoryTest {

    @Autowired
    private HistoricoProdutoRepository historicoProdutoRepository;

    @Test
    public void testSalvarHistoricoProduto() {
        HistoricoProduto historicoProduto = new HistoricoProduto();
        historicoProduto.setProdutoId(1L);
        historicoProduto.setNome("Coca-Cola");
        historicoProduto.setDescricao("Refrigerante");
        historicoProduto.setTipo("Bebida");
        historicoProduto.setVolume(2.0);
        historicoProduto.setQuantidadeEstoque(100);
        historicoProduto.setDataFabricacao(LocalDate.now());
        historicoProduto.setDataValidade(LocalDate.now().plusMonths(6));
        historicoProduto.setDataAlteracao(LocalDateTime.now());
        historicoProduto.setOperacao("SALVAR");

        HistoricoProduto savedHistoricoProduto = historicoProdutoRepository.save(historicoProduto);

        assertThat(savedHistoricoProduto).isNotNull();
        assertThat(savedHistoricoProduto.getId()).isNotNull();
    }

    @Test
    public void testListarHistoricoProdutos() {
        HistoricoProduto historicoProduto1 = new HistoricoProduto();
        historicoProduto1.setProdutoId(1L);
        historicoProduto1.setNome("Coca-Cola");
        historicoProduto1.setDescricao("Refrigerante");
        historicoProduto1.setTipo("Bebida");
        historicoProduto1.setVolume(2.0);
        historicoProduto1.setQuantidadeEstoque(100);
        historicoProduto1.setDataFabricacao(LocalDate.now());
        historicoProduto1.setDataValidade(LocalDate.now().plusMonths(6));
        historicoProduto1.setDataAlteracao(LocalDateTime.now());
        historicoProduto1.setOperacao("SALVAR");

        HistoricoProduto historicoProduto2 = new HistoricoProduto();
        historicoProduto2.setProdutoId(2L);
        historicoProduto2.setNome("Pepsi");
        historicoProduto2.setDescricao("Refrigerante");
        historicoProduto2.setTipo("Bebida");
        historicoProduto2.setVolume(2.0);
        historicoProduto2.setQuantidadeEstoque(100);
        historicoProduto2.setDataFabricacao(LocalDate.now());
        historicoProduto2.setDataValidade(LocalDate.now().plusMonths(6));
        historicoProduto2.setDataAlteracao(LocalDateTime.now());
        historicoProduto2.setOperacao("SALVAR");

        historicoProdutoRepository.save(historicoProduto1);
        historicoProdutoRepository.save(historicoProduto2);

        List<HistoricoProduto> historicoProdutos = historicoProdutoRepository.findAll();

        assertThat(historicoProdutos).hasSize(2);
    }
}