package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfiguracaoJDBC;
import com.dhbrasil.springboot.aula21.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDaoDatabase implements IDao<Usuario> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public UsuarioDaoDatabase() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Usuario salvar(Usuario usuario){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = String.format("INSERT INTO usuarios " +
                "(nome, email, senha, nivelAcesso) " +
                "VALUES('%s','%s','%s','%s')",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNivelAcesso());
        try{
            pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next())
                usuario.setId(keys.getInt(1));
            pstmt.close();
            conexao.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    private Usuario criarObjetoUsuario(ResultSet result) throws SQLException {
        return new Usuario(
                result.getInt("id"),
                result.getString("nome"),
                result.getString("email"),
                result.getString("senha"),
                result.getString("nivelAcesso")
        );
    }

    @Override
    public List<Usuario> buscarTodos(){
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        PreparedStatement pstmt = null;

        String query = "SELECT * FROM usuarios;";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();

            while (result.next()){
                usuarios.add(criarObjetoUsuario(result));
            }
            pstmt.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
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
    public Optional<Usuario> buscar(Integer id){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("SELECT id, nome, email, senha, nivelAcesso " +
                "FROM usuarios WHERE id = '%s'", id);
        Usuario usuario = null;

        try{
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()){
                usuario = criarObjetoUsuario(resultado);
            }
            stmt.close();
            conexao.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return usuario != null ? Optional.of(usuario) : Optional.empty();
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
    public Usuario atualizar(Usuario usuario){
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        String query = String.format(
                "UPDATE usuarios SET nome = '%s', email = '%s', " +
                        "senha = '%s', nivelAcesso = '%s' " +
                        "WHERE id = '%s'",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNivelAcesso(),
                usuario.getId()
        );

        execute(conexao, query);

        return usuario;
    }
}
