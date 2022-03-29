package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;
import com.dhbrasil.springboot.aula21.model.Dentista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DentistaDaoH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public DentistaDaoH2() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    // Salvar
    @Override
    public Dentista salvar(Dentista dentista) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = String.format("INSERT INTO dentistas " +
                        "(nome ,email, numMatricula, atendeConvenio) " +
                        "VALUES ('%s','%s','%s','%s')",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio());

        try {
            pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next())
                dentista.setId(keys.getInt(1));
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista;
    }

    // Buscar por ID
    @Override
    public Optional<Dentista> buscar(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format(
                "SELECT id, nome, email, numMatricula, atendeConvenio " +
                        "FROM dentistas WHERE id = '%s'", id);
        Dentista dentista = null;

        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                dentista = criarObjetoDentista(resultado);
            }
            stmt.close();
            conexao.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    // O método .of utilizamos quando se tem certeza que o objeto tem dados
    // O método .empty retorna como vazio
    // Estes métodos do Objeto Optional<T> servem para evitar o NullPointerException

    // Buscar todos os registros
    @Override
    public List<Dentista> buscarTodos() {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = "SELECT * FROM dentistas;";
        List<Dentista> dentistas = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                dentistas.add(criarObjetoDentista(result));
            }
            pstmt.close();
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentistas;
    }

    // Atualizar
    @Override
    public Dentista atualizar(Dentista dentista) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        String query = String.format(
                "UPDATE dentistas SET nome = '%s', email = '%s', " +
                "numMatricula = '%s', atendeConvenio = '%s' " +
                "WHERE id = '%s'",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio(),
                dentista.getId());
        execute(conexao, query);

        return dentista;
    }

    // Excluir
    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM dentistas WHERE id = '%s'", id);
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conexao.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dentista criarObjetoDentista(ResultSet result) throws SQLException {
        return new Dentista(
                result.getInt("id"),
                result.getString("nome"),
                result.getString("email"),
                result.getInt("numMatricula"),
                result.getInt("atendeConvenio"));
    }

    private void execute(Connection conexao, String query) {

        try {
            PreparedStatement pstmt = null;
            pstmt = conexao.prepareStatement(query);
            pstmt.executeUpdate();
            conexao.close();
            pstmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}