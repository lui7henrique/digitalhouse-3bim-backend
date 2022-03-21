package dao.impl;

import dao.IDao;
import dao.config.ConfigJDBC;
import model.Dentista;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DentistaDaoH2 implements IDao<Dentista> {
    private ConfigJDBC configJDBC;

    public DentistaDaoH2(){
        this.configJDBC = new ConfigJDBC();
    }

    @Override
    public Dentista salvar(Dentista dentista) {
        Connection connection = configJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;


        String query = String.format("INSERT INTO dentistas " +
                        "(nome ,email, numMatricula, atendeConvenio) " +
                        "VALUES ('%s','%s','%s','%s')",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio());

        try {
            pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();

            if (keys.next())
                dentista.setId(keys.getInt(1));

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
