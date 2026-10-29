
package br.com.fiap.soulcode.dao;

import br.com.fiap.soulcode.connection.ConnectionFactory;
import br.com.fiap.soulcode.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO T_SG_USUARIO " +
                "(NM_USUARIO, DS_SENHA, DS_EMAIL, NR_IDADE, " +
                "DS_CIDADE, DS_INTERESSE, NR_PONTOS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.abrirConexao();
             PreparedStatement comando = conexao.prepareStatement(
                     sql, new String[]{"ID_USUARIO"})) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getSenhaHash());
            comando.setString(3, usuario.getEmail());
            comando.setInt(4, usuario.getIdade());
            comando.setString(5, usuario.getCidade());
            comando.setString(6, usuario.getInteresse());
            comando.setInt(7, usuario.getPontos());

            comando.executeUpdate();

            try (ResultSet resultado = comando.getGeneratedKeys()) {
                if (resultado.next()) {
                    usuario.setId(resultado.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Não foi possível cadastrar o usuário.", e
            );
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM T_SG_USUARIO ORDER BY ID_USUARIO";

        try (Connection conexao = ConnectionFactory.abrirConexao();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(mapearUsuario(resultado));
            }

            return usuarios;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Não foi possível listar os usuários.", e
            );
        }
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM T_SG_USUARIO WHERE ID_USUARIO = ?";

        try (Connection conexao = ConnectionFactory.abrirConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return mapearUsuario(resultado);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Não foi possível buscar o usuário.", e
            );
        }
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE T_SG_USUARIO SET " +
                "NM_USUARIO = ?, DS_EMAIL = ?, NR_IDADE = ?, " +
                "DS_CIDADE = ?, DS_INTERESSE = ?, NR_PONTOS = ? " +
                "WHERE ID_USUARIO = ?";

        try (Connection conexao = ConnectionFactory.abrirConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setInt(3, usuario.getIdade());
            comando.setString(4, usuario.getCidade());
            comando.setString(5, usuario.getInteresse());
            comando.setInt(6, usuario.getPontos());
            comando.setInt(7, usuario.getId());

            int linhas = comando.executeUpdate();

            if (linhas == 0) {
                throw new IllegalStateException(
                        "Nenhum usuário foi atualizado."
                );
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Não foi possível atualizar o usuário.", e
            );
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM T_SG_USUARIO WHERE ID_USUARIO = ?";

        try (Connection conexao = ConnectionFactory.abrirConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            int linhas = comando.executeUpdate();

            if (linhas == 0) {
                throw new IllegalStateException(
                        "Nenhum usuário foi removido."
                );
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Não foi possível remover o usuário.", e
            );
        }
    }

    private Usuario mapearUsuario(ResultSet resultado)
            throws SQLException {

        return new Usuario(
                resultado.getInt("ID_USUARIO"),
                resultado.getString("NM_USUARIO"),
                resultado.getString("DS_SENHA"),
                resultado.getString("DS_EMAIL"),
                resultado.getInt("NR_IDADE"),
                resultado.getString("DS_CIDADE"),
                resultado.getString("DS_INTERESSE"),
                resultado.getInt("NR_PONTOS")
        );
    }
}
