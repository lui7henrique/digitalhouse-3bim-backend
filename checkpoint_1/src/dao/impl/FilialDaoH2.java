package dao.impl;

import dao.ConfigJDBC;
import dao.IDao;
import model.Filial;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class FilialDaoH2 implements IDao<Filial> {
    private ConfigJDBC configJDBC;

    public FilialDaoH2(ConfigJDBC configJDBC){
        this.configJDBC = configJDBC;
    }


    @Override
    public Filial salvar(Filial filial){
        Connection connection = configJDBC.conectarComBD();
        Statement stmt = null;

        String query = String.format(
                "INSERT INTO FILIAIS " +
                        "(NOME, ENDERECO, EH5ESTRELAS) " +
                        "VALUES('%s', '%s', '%s')",
                filial.getNome(),
                filial.getEndereco(),
                filial.isEh5Estrelas()
        );


        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet keys = stmt.getGeneratedKeys();

            if(keys.next())
                filial.setId(keys.getInt(1));

            stmt.close();
            connection.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}
