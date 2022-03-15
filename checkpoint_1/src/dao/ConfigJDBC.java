package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        this.dbURL = "jdbc:h2:mem:checkpoint;" +
                "DB_CLOSE_DELAY=-1;" +
                "INIT=RUNSCRIPT FROM 'create.sql'";
        this.nomeUsuario = "sa";
        this.senha = "";
    }

    public Connection conectarComBD(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL,nomeUsuario, senha);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
