package mx.florinda.mains;

import mx.florinda.classes.BancoDeDados;
import mx.florinda.classes.Produtos;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main  {
Logger logger = Logger.getLogger(Main.class.getName());


    public void main() throws Exception{

        BancoDeDados database = new BancoDeDados();
        Produtos inserir;
        Produtos atualizar;
        boolean remover;
        //inserir = database.inserirProduto(12L, "Pendrive", 62.50);
        atualizar = database.atualizarProduto(9L, "Processador MR5", 980.00);
        logger.info(() ->"Produto Atualizado: " +  atualizar);
        remover = database.removerProduto(12L);
        logger.info(() -> "Produto Removido: " + remover);
        var prod = database.listarProdutos();
        logger.info(() -> "Lista de Produtos: " +prod.toString());
        prod.forEach(System.out::println);

    }
}
