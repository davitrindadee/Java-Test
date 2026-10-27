package br.com.fiap.soulguide.dao;

import br.com.fiap.soulguide.connection.ConnectionFactory;
import br.com.fiap.soulguide.model.Avatar;
import br.com.fiap.soulguide.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvatarDao {

    public void inserir(Avatar avatar) {

        String sql =
                "INSERT INTO T_SG_AVATAR " +
                        "(ID_USUARIO, NM_AVATAR, NR_NIVEL) " +
                        "VALUES (?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    avatar.getUsuario().getId()
            );

            comando.setString(
                    2,
                    avatar.getNome()
            );

            comando.setInt(
                    3,
                    avatar.getNivel()
            );

            comando.executeUpdate();

            System.out.println("Avatar inserido.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir avatar: " +
                            e.getMessage()
            );
        }
    }

    public List<Avatar> listar() {

        List<Avatar> avatares = new ArrayList<>();

        String sql =
                "SELECT a.ID_AVATAR, a.NM_AVATAR, " +
                        "a.NR_NIVEL, u.ID_USUARIO, " +
                        "u.NM_USUARIO " +
                        "FROM T_SG_AVATAR a " +
                        "INNER JOIN T_SG_USUARIO u " +
                        "ON a.ID_USUARIO = u.ID_USUARIO";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql);

             ResultSet resultado =
                     comando.executeQuery()) {

            while (resultado.next()) {

                Usuario usuario =
                        new Usuario(
                                resultado.getInt("ID_USUARIO"),
                                resultado.getString("NM_USUARIO"),
                                "",
                                "",
                                0,
                                "",
                                "",
                                0
                        );

                Avatar avatar =
                        new Avatar(
                                resultado.getInt("ID_AVATAR"),
                                usuario,
                                resultado.getString("NM_AVATAR"),
                                resultado.getInt("NR_NIVEL")
                        );

                avatares.add(avatar);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar avatares: " +
                            e.getMessage()
            );
        }

        return avatares;
    }

    public void atualizar(Avatar avatar) {

        String sql =
                "UPDATE T_SG_AVATAR SET " +
                        "NM_AVATAR = ?, " +
                        "NR_NIVEL = ? " +
                        "WHERE ID_AVATAR = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, avatar.getNome());
            comando.setInt(2, avatar.getNivel());
            comando.setInt(3, avatar.getId());

            comando.executeUpdate();

            System.out.println("Avatar atualizado.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar avatar: " +
                            e.getMessage()
            );
        }
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_AVATAR " +
                        "WHERE ID_AVATAR = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Avatar removido.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover avatar: " +
                            e.getMessage()
            );
        }
    }
}

