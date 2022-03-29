package dao.impl;

import dao.IDao;
import dao.config.ConfigJDBC;
import model.Dentista;
import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDaoH2 implements IDao<Endereco> {
    private ConfigJDBC configJDBC;

    public EnderecoDaoH2(){
        this.configJDBC = new ConfigJDBC();
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        Connection connection = configJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;


        String query = String.format("INSERT INTO enderecos " +
                        "(rua ,numero, cidade, bairro, estado) " +
                        "VALUES ('%s','%s','%s','%s', '%s')",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado());


        try {
            pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();

            if (keys.next())
                endereco.setId(keys.getInt(1));

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Endereco> buscarTodos(){
        Connection connection = configJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = "SELECT * FROM enderecos";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                System.out.println(result);
                enderecos.add(criarObjetoEndreco(result));
            }

            pstmt.close();
            connection.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

        return enderecos;
    }

    private Endereco criarObjetoEndreco(ResultSet result) throws SQLException {
        return new Endereco(
                result.getInt("id"),
                result.getString("rua"),
                result.getString("numero"),
                result.getString("cidade"),
                result.getString("bairro"),
                result.getString("estado")
        );
    }
   }
