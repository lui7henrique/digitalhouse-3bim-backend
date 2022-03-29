package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoDaoDatabase implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public EnderecoDaoDatabase() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = String.format("INSERT INTO enderecos " +
                        "(rua, numero, cidade, bairro, estado) " +
                        "VALUES ('%s','%s','%s','%s','%s')",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado());

        try {
            pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next())
                endereco.setId(keys.getInt(1));
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @Override
    public List<Endereco> buscarTodos(){
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = "SELECT * FROM endereco;";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();

            while (result.next()){
                enderecos.add(criarObjetoEndereco(result));
            }
            pstmt.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return enderecos;
    }

    private Endereco criarObjetoEndereco(ResultSet result) throws SQLException {
        return new Endereco(
                result.getInt("id"),
                result.getString("rua"),
                result.getString("numero"),
                result.getString("cidade"),
                result.getString("bairro"),
                result.getString("estado")
        );
    }

    @Override
    public void excluir(Integer id){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM usuarios WHERE id = '%s'", id);
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conexao.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Endereco> buscar(Integer id){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("SELECT id, rua, numero, cidade, bairro, estado " +
                "FROM enderecos WHERE id = '%s'", id);
        Endereco endereco = null;

        try{
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()){
                endereco = criarObjetoEndereco(resultado);
            }
            stmt.close();
            conexao.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    public void execute(Connection conexao, String query){
        try{
            PreparedStatement pstmt = null;
            pstmt = conexao.prepareStatement(query);
            pstmt.executeUpdate();
            conexao.close();
            pstmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Endereco atualizar(Endereco endereco){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        String query = String.format(
                "UPDATE enderecos SET rua = '%s', numero = '%s', " +
                        "cidade = '%s', bairro = '%s', estado = '%s' " +
                        "WHERE id = '%s'",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado(),
                endereco.getId()
        );

        execute(conexao, query);

        return endereco;
    }
}
