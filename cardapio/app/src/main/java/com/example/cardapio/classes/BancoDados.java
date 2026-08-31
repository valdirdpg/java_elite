package com.example.cardapio.classes;

import java.util.List;
import java.util.Optional;

public interface BancoDados {
    List<ItemCardapio> itensDoCardapio();

    Optional<ItemCardapio> itemCardapioPorId(Long itemId);

    boolean removerItemCardpio(Long idParaRemover);

    int totalItemCardapio();

    boolean alterarPrecoItemCardapio(Long idSelecionado, Double novoPreco);

    ItemCardapio adcionarItemCardapio(Long id, String nome, String descricao, double preco,
                                      double preco_promocional,
                                      ItemCardapio.CategoriaCardapio categoria);
}
