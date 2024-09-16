package com.infnet.bebidagelada.services;

import com.infnet.bebidagelada.model.HistoricoProduto;
import com.infnet.bebidagelada.repository.HistoricoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoProdutoService {
    @Autowired
    private HistoricoProdutoRepository historicoProdutoRepository;

    public List<HistoricoProduto> listarHistorico() {
        return historicoProdutoRepository.findAll();
    }
}