package br.com.fiap.soulguide.dao;

import br.com.fiap.soulguide.connection.ConnectionFactory;
import br.com.fiap.soulguide.model.Pontuacao;
import br.com.fiap.soulguide.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontuacaoDao {

    public void inserir(Pontuacao pontuacao) {

        String sql =
                "INSERT INTO T_SG_PONTUACAO " +
                        "(ID_USUARIO, NR_PONTOS, DT_PONTUACAO) " +
                        "VALUES (?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    pontuacao.getUsuario().getId()
            );

            comando.setInt(
                    2,
                    pontuacao.getQuantidadePontos()
            );

            comando.setTimestamp(
                    3,
                    Timestamp.valueOf(
                            pontuacao.getData()
                    )
            );

            comando.executeUpdate();

            System.out.println("Pontuação inserida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir pontuação: " +
                            e.getMessage()
            );
        }
    }

    public List<Pontuacao> listar() {

        List<Pontuacao> pontuacoes =
                new ArrayList<>();

        String sql =
                "SELECT p.ID_PONTUACAO, " +
                        "p.ID_USUARIO, p.NR_PONTOS, " +
                        "p.DT_PONTUACAO, u.NM_USUARIO " +
                        "FROM T_SG_PONTUACAO p " +
                        "INNER JOIN T_SG_USUARIO u " +
                        "ON p.ID_USUARIO = u.ID_USUARIO";

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

                Pontuacao pontuacao =
                        new Pontuacao(

                                resultado.getInt(
                                        "ID_PONTUACAO"
                                ),

                                usuario,

                                resultado.getInt(
                                        "NR_PONTOS"
                                ),

                                resultado.getTimestamp(
                                        "DT_PONTUACAO"
                                ).toLocalDateTime()
                        );

                pontuacoes.add(pontuacao);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar pontuações: " +
                            e.getMessage()
            );
        }

        return pontuacoes;
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_PONTUACAO " +
                        "WHERE ID_PONTUACAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Pontuação removida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover pontuação: " +
                            e.getMessage()
            );
        }
    }
}

