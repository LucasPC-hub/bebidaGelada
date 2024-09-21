
package com.infnet.bebidagelada.listeners;

import com.infnet.bebidagelada.events.ProdutoEvent;
import com.infnet.bebidagelada.events.ProdutoAlteradoEvent;
import com.infnet.bebidagelada.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEventListener {

    @Autowired
    private ProdutoService produtoService;

    @EventListener
    public void handleProdutoEvent(ProdutoEvent event) {
        System.out.println("Produto event received: " + event.getProduto().getNome());
        produtoService.registrarHistorico(event.getProduto(), "SALVAR");
    }

    @EventListener
    public void handleProdutoAlteradoEvent(ProdutoAlteradoEvent event) {
        System.out.println("Produto alterado event received: " + event.getProduto().getNome());
        produtoService.registrarHistorico(event.getProduto(), "ALTERAR");
    }
}