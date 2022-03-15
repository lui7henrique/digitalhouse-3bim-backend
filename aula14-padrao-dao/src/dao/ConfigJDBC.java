package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigJDBC {
    private String jdbcDriver;
    private String dbURL;
    private String nomeUsuario;
    private String senha;

    public ConfigJDBC(String jdbcDriver, String dbURL, String nomeUsuario, String senha) {
        this.jdbcDriver = jdbcDriver;
        this.dbURL = dbURL;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public ConfigJDBC(){
        this.jdbcDriver = "org.H2.Driver";
        this.dbURL = "jdbc:h2:mem:aula14";
        this.nomeUsuario = "sa";
        this.senha = "org.H2.Driver";
    }

    public Connection conectarComBD(){
        Connection connection = null;


        try {
            connection = DriverManager.getConnection(dbURL,nomeUsuario, senha);

            Statement stmt = connection.createStatement();

            String createSQL = "CREATE TABLE IF NOT EXISTS MEDICAMENTOS(ID INT auto_increment PRIMARY KEY, NOME VARCHAR(64), LABORATORIO VARCHAR(64), QUANTIDADE INT, PRECO DOUBLE)";

            stmt.executeUpdate(createSQL);
            System.out.println("Tabela criada!");

            System.out.println("Conectado!");

        } catch(SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
}
