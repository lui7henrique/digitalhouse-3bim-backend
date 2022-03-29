package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;
import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDaoDatabase implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;
    private EnderecoDaoDatabase enderecoDaoDatabase;

    public PacienteDaoDatabase() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
        this.enderecoDaoDatabase = new EnderecoDaoDatabase();
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        paciente.setEndereco(enderecoDaoDatabase.salvar(paciente.getEndereco()));

        String query = String.format("INSERT INTO pacientes " +
                        "(nome, sobrenome, cpf, dataCad, id_endereco) " +
                        "VALUES ('%s','%s','%s','%s','%s')",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                Util.dataToTimestamp(paciente.getDataCad()),
                paciente.getEndereco().getId());

        try {
            pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    private Paciente criarObjetoPaciente(ResultSet resultado) throws SQLException {
        Integer idRec = resultado.getInt("id");
        String nomeRec = resultado.getString("nome");
        String sobrenomeRec = resultado.getString("sobrenome");
        String cpfRec = resultado.getString("cpf");
        Date dataCadRec = resultado.getDate("dataCad");
        Endereco enderecoRec = enderecoDaoDatabase.buscar(resultado.getInt("id_endereco")).orElse(null);

        return new Paciente(idRec, nomeRec, sobrenomeRec, cpfRec, dataCadRec, enderecoRec);

    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format(
                "SELECT id, nome, sobrenome, cpf, dataCad, id_endereco " +
                "FROM pacientes WHERE id = '%s'", id);
        Paciente paciente = null;
        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()){
                paciente = criarObjetoPaciente(resultado);
            }
            stmt.close();
            conexao.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format(
                "DELETE FROM pacientes WHERE id = '%s'", id);
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
    public List<Paciente> buscarTodos() {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM pacientes;";

        List<Paciente> pacientes = new ArrayList<>();

        try{
            pstmt = conexao.prepareStatement(query);
            ResultSet resultado = pstmt.executeQuery();
            while (resultado.next()){
                pacientes.add(criarObjetoPaciente(resultado));
            }
            pstmt.close();
            conexao.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return pacientes;
    }

    private void execute(Connection conexao, String query){
        try {
            Statement stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conexao.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();

        if (paciente.getEndereco() != null && paciente.getId() != null) {
            enderecoDaoDatabase.atualizar(paciente.getEndereco());
        }

        String query = String.format(
                "UPDATE pacientes SET nome = '%s', sobrenome = '%s', " +
                "cpf = '%s' WHERE id = '%s'",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                paciente.getId());

        execute(conexao, query);

        return paciente;
    }
}