package com.infnet.bebidagelada.repository;

import com.infnet.bebidagelada.model.HistoricoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoProdutoRepository extends JpaRepository<HistoricoProduto, Long> {
}