package com.infnet.bebidagelada.events;

import com.infnet.bebidagelada.model.Produto;
import org.springframework.context.ApplicationEvent;

public class ProdutoAlteradoEvent extends ApplicationEvent {
    private final Produto produto;

    public ProdutoAlteradoEvent(Object source, Produto produto) {
        super(source);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }
}