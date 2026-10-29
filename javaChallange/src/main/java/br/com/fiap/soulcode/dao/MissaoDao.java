package br.com.fiap.soulcode.dao;

import br.com.fiap.soulcode.connection.ConnectionFactory;
import br.com.fiap.soulcode.model.Missao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoDao {

    public void inserir(Missao missao) {

        String sql =
                "INSERT INTO T_SG_MISSAO " +
                        "(NM_MISSAO, DS_MISSAO, NR_PONTOS, ST_MISSAO) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, missao.getNome());
            comando.setString(2, missao.getDescricao());
            comando.setInt(3, missao.getPontos());
            comando.setInt(
                    4,
                    missao.isStatus() ? 1 : 0
            );

            comando.executeUpdate();

            System.out.println("Missão inserida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir missão: " +
                            e.getMessage()
            );
        }
    }

    public List<Missao> listar() {

        List<Missao> missoes = new ArrayList<>();

        String sql =
                "SELECT * FROM T_SG_MISSAO " +
                        "ORDER BY ID_MISSAO";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql);

             ResultSet resultado =
                     comando.executeQuery()) {

            while (resultado.next()) {

                Missao missao =
                        new Missao(

                                resultado.getInt("ID_MISSAO"),

                                resultado.getString("NM_MISSAO"),

                                resultado.getString("DS_MISSAO"),

                                resultado.getInt("NR_PONTOS"),

                                resultado.getInt("ST_MISSAO") == 1
                        );

                missoes.add(missao);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar missões: " +
                            e.getMessage()
            );
        }

        return missoes;
    }

    public void atualizar(Missao missao) {

        String sql =
                "UPDATE T_SG_MISSAO SET " +
                        "NM_MISSAO = ?, " +
                        "DS_MISSAO = ?, " +
                        "NR_PONTOS = ?, " +
                        "ST_MISSAO = ? " +
                        "WHERE ID_MISSAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, missao.getNome());
            comando.setString(2, missao.getDescricao());
            comando.setInt(3, missao.getPontos());
            comando.setInt(
                    4,
                    missao.isStatus() ? 1 : 0
            );
            comando.setInt(5, missao.getId());

            comando.executeUpdate();

            System.out.println("Missão atualizada.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar missão: " +
                            e.getMessage()
            );
        }
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_MISSAO " +
                        "WHERE ID_MISSAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Missão removida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover missão: " +
                            e.getMessage()
            );
        }
    }
}

