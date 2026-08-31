create table item_cardapio (
                               id bigint not null auto_increment,
                               nome varchar (255) not null,
                               descricao varchar(255),
                               categoria ENUM('ENTRADA', 'PRATO_PRINCIPAL', 'SOBREMESA', 'BEBIDAS', 'LANCHES'),
                               preco decimal(10, 2) not null,
                               preco_promocional decimal(10,2),
                               primary key (id)
);
INSERT INTO item_cardapio (id, nome, descricao, categoria, preco, preco_promocional)
VALUES
    (1, 'Refresco do Chaves',
     'Suco de limão que parece de tamarindo e tem gosto de groselha.',
     'BEBIDAS', 2.99, 0),

    (2, 'Sanduíche de Presunto do Chaves',
     'Sanduíche de presunto simples, mas feito com muito amor.',
     'PRATO_PRINCIPAL', 3.50, 0),

    (3, 'Torta de Frango da Dona Florinda',
     'Torta de frango com recheio cremoso e massa crocante.',
     'ENTRADA', 12.99, 0),

    (4, 'Pipoca do Quico',
     'Balde de pipoca preparado com carinho pelo Quico.',
     'LANCHES', 4.99, 0),

    (5, 'Água de Jamaica',
     'Água aromatizada com hibisco e toque de açúcar.',
     'BEBIDAS', 2.50, 0),

    (6, 'Churros do Chaves',
     'Churros recheados com doce de leite, clássicos e irresistíveis.',
     'SOBREMESA', 4.99, 0),

    (7, 'Tacos de Carnitas',
     'Tacos recheados com carne tenra.',
     'PRATO_PRINCIPAL', 25.90, 0),

    (8, 'Batata Doce',
     'Batata fatiada com caramelo.',
     'PRATO_PRINCIPAL', 15.90, 0);

select * from item_cardapio;
