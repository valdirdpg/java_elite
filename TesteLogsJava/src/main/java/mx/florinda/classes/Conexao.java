package mx.florinda.classes;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class Conexao {
    Logger logger = Logger.getLogger(Conexao.class.getName());
    public Conexao() throws SQLException {
        conexaoSql();
    }
    public Connection conexaoSql() throws SQLException {
        try {
            logger.info("Abrindo conexão");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/produtos?useSSL=false&serverTimezone=UTC",
                            "root", "root123456");
        } catch (SQLException e) {
            logger.severe("Falha na conexão com o banco de dados");
            throw new RuntimeException(e);
        }


    }
}
