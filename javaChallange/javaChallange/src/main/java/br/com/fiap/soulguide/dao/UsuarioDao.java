package br.com.fiap.soulguide.dao;

import br.com.fiap.soulguide.connection.ConnectionFactory;
import br.com.fiap.soulguide.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public void inserir(Usuario usuario) {

        String sql =
                "INSERT INTO T_SG_USUARIO " +
                        "(NM_USUARIO, DS_SENHA, DS_EMAIL, NR_IDADE, " +
                        "DS_CIDADE, DS_INTERESSE, NR_PONTOS) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(
                             sql,
                             new String[]{"ID_USUARIO"})) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getSenhaHash());
            comando.setString(3, usuario.getEmail());
            comando.setInt(4, usuario.getIdade());
            comando.setString(5, usuario.getCidade());
            comando.setString(6, usuario.getInteresse());
            comando.setInt(7, usuario.getPontos());

            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();

            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

            System.out.println("Usuário inserido com sucesso.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir usuário: " +
                            e.getMessage()
            );
        }
    }

    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql =
                "SELECT * FROM T_SG_USUARIO " +
                        "ORDER BY ID_USUARIO";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql);

             ResultSet resultado =
                     comando.executeQuery()) {

            while (resultado.next()) {

                Usuario usuario = new Usuario(

                        resultado.getInt("ID_USUARIO"),

                        resultado.getString("NM_USUARIO"),

                        resultado.getString("DS_SENHA"),

                        resultado.getString("DS_EMAIL"),

                        resultado.getInt("NR_IDADE"),

                        resultado.getString("DS_CIDADE"),

                        resultado.getString("DS_INTERESSE"),

                        resultado.getInt("NR_PONTOS")
                );

                usuarios.add(usuario);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar usuários: " +
                            e.getMessage()
            );
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) {

        String sql =
                "SELECT * FROM T_SG_USUARIO " +
                        "WHERE ID_USUARIO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            ResultSet resultado =
                    comando.executeQuery();

            if (resultado.next()) {

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

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar usuário: " +
                            e.getMessage()
            );
        }

        return null;
    }

    public void atualizar(Usuario usuario) {

        String sql =
                "UPDATE T_SG_USUARIO SET " +
                        "NM_USUARIO = ?, " +
                        "DS_EMAIL = ?, " +
                        "NR_IDADE = ?, " +
                        "DS_CIDADE = ?, " +
                        "DS_INTERESSE = ?, " +
                        "NR_PONTOS = ? " +
                        "WHERE ID_USUARIO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setInt(3, usuario.getIdade());
            comando.setString(4, usuario.getCidade());
            comando.setString(5, usuario.getInteresse());
            comando.setInt(6, usuario.getPontos());
            comando.setInt(7, usuario.getId());

            comando.executeUpdate();

            System.out.println("Usuário atualizado.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar usuário: " +
                            e.getMessage()
            );
        }
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_USUARIO " +
                        "WHERE ID_USUARIO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Usuário removido.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover usuário: " +
                            e.getMessage()
            );
        }
    }
}

