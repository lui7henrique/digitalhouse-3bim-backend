package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoDaoH2 implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public EnderecoDaoH2() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = String.format("INSERT INTO enderecos " +
                        "(rua ,numero , cidade, bairro, estado) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado());
        try {
            pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next())
                endereco.setId(keys.getInt(1));
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @Override
    public Optional<Endereco> buscar(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("SELECT id, rua ,numero , cidade, bairro, estado " +
                " FROM enderecos WHERE id = '%s'", id);
        Endereco endereco = null;
        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                endereco = criarObjetoEndereco(resultado);
            }

            stmt.close();
            conexao.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM enderecos WHERE id = %s", id);
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Endereco> buscarTodos() {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = "SELECT * FROM enderecos;";

        List<Endereco> enderecos = new ArrayList<>();

        try {
            pstmt = conexao.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                enderecos.add(criarObjetoEndereco(result));
            }
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    @Override
    public Endereco atualizar(Endereco endereco) {

        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        String query = String.format(
                "UPDATE enderecos SET rua = '%s', numero = '%s', " +
                        "cidade = '%s', bairro = '%s', "+
                        "estado = '%s' WHERE id = '%s'",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado(),
                endereco.getId());
        execute(connection, query);
        return endereco;
    }

    private Endereco criarObjetoEndereco(ResultSet result) throws SQLException {
        return new Endereco(
                result.getInt("id"),
                result.getString("rua"),
                result.getString("numero"),
                result.getString("cidade"),
                result.getString("bairro"),
                result.getString("estado"));
    }

    private void execute(Connection connection, String query) {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
