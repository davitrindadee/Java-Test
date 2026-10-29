package br.com.fiap.soulcode.dao;

import br.com.fiap.soulcode.connection.ConnectionFactory;
import br.com.fiap.soulcode.model.Acao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcaoDao {

    public void inserir(Acao acao) {

        String sql =
                "INSERT INTO T_SG_ACAO " +
                        "(NM_ACAO, DS_ACAO, NR_PONTOS, ST_ACAO) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, acao.getNome());
            comando.setString(2, acao.getDescricao());
            comando.setInt(3, acao.getPontos());
            comando.setInt(
                    4,
                    acao.isStatus() ? 1 : 0
            );

            comando.executeUpdate();

            System.out.println("Ação inserida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir ação: " +
                            e.getMessage()
            );
        }
    }

    public List<Acao> listar() {

        List<Acao> acoes = new ArrayList<>();

        String sql =
                "SELECT * FROM T_SG_ACAO " +
                        "ORDER BY ID_ACAO";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql);

             ResultSet resultado =
                     comando.executeQuery()) {

            while (resultado.next()) {

                Acao acao =
                        new Acao(

                                resultado.getInt("ID_ACAO"),

                                resultado.getString("NM_ACAO"),

                                resultado.getString("DS_ACAO"),

                                resultado.getInt("NR_PONTOS"),

                                resultado.getInt("ST_ACAO") == 1
                        );

                acoes.add(acao);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar ações: " +
                            e.getMessage()
            );
        }

        return acoes;
    }

    public void atualizar(Acao acao) {

        String sql =
                "UPDATE T_SG_ACAO SET " +
                        "NM_ACAO = ?, " +
                        "DS_ACAO = ?, " +
                        "NR_PONTOS = ?, " +
                        "ST_ACAO = ? " +
                        "WHERE ID_ACAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, acao.getNome());
            comando.setString(2, acao.getDescricao());
            comando.setInt(3, acao.getPontos());
            comando.setInt(
                    4,
                    acao.isStatus() ? 1 : 0
            );
            comando.setInt(5, acao.getId());

            comando.executeUpdate();

            System.out.println("Ação atualizada.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar ação: " +
                            e.getMessage()
            );
        }
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_ACAO " +
                        "WHERE ID_ACAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Ação removida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover ação: " +
                            e.getMessage()
            );
        }
    }
}

