package br.com.fiap.soulcode.dao;

import br.com.fiap.soulcode.connection.ConnectionFactory;
import br.com.fiap.soulcode.model.Acao;
import br.com.fiap.soulcode.model.Interacao;
import br.com.fiap.soulcode.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteracaoDao {

    public void inserir(Interacao interacao) {

        String sql =
                "INSERT INTO T_SG_INTERACAO " +
                        "(ID_USUARIO, ID_ACAO, " +
                        "DS_TIPO, DT_INTERACAO) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    interacao.getUsuario().getId()
            );

            comando.setInt(
                    2,
                    interacao.getAcao().getId()
            );

            comando.setString(
                    3,
                    interacao.getTipo()
            );

            comando.setTimestamp(
                    4,
                    Timestamp.valueOf(
                            interacao.getData()
                    )
            );

            comando.executeUpdate();

            System.out.println("Interação registrada.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir interação: " +
                            e.getMessage()
            );
        }
    }

    public List<Interacao> listar() {

        List<Interacao> interacoes =
                new ArrayList<>();

        String sql =
                "SELECT i.ID_INTERACAO, " +
                        "i.DS_TIPO, i.DT_INTERACAO, " +
                        "u.ID_USUARIO, u.NM_USUARIO, " +
                        "a.ID_ACAO, a.NM_ACAO, " +
                        "a.DS_ACAO, a.NR_PONTOS, " +
                        "a.ST_ACAO " +
                        "FROM T_SG_INTERACAO i " +
                        "INNER JOIN T_SG_USUARIO u " +
                        "ON i.ID_USUARIO = u.ID_USUARIO " +
                        "INNER JOIN T_SG_ACAO a " +
                        "ON i.ID_ACAO = a.ID_ACAO";

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

                Acao acao =
                        new Acao(
                                resultado.getInt("ID_ACAO"),
                                resultado.getString("NM_ACAO"),
                                resultado.getString("DS_ACAO"),
                                resultado.getInt("NR_PONTOS"),
                                resultado.getInt("ST_ACAO") == 1
                        );

                Interacao interacao =
                        new Interacao(
                                resultado.getInt(
                                        "ID_INTERACAO"
                                ),
                                usuario,
                                acao,
                                resultado.getString(
                                        "DS_TIPO"
                                ),
                                resultado.getTimestamp(
                                        "DT_INTERACAO"
                                ).toLocalDateTime()
                        );

                interacoes.add(interacao);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar interações: " +
                            e.getMessage()
            );
        }

        return interacoes;
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM T_SG_INTERACAO " +
                        "WHERE ID_INTERACAO = ?";

        try (Connection conexao =
                     ConnectionFactory.abrirConexao();

             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Interação removida.");

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover interação: " +
                            e.getMessage()
            );
        }
    }
}

