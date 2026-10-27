package br.com.fiap.soulguide.test;

import br.com.fiap.soulguide.dao.AcaoDao;
import br.com.fiap.soulguide.dao.MissaoDao;
import br.com.fiap.soulguide.dao.UsuarioDao;
import br.com.fiap.soulguide.model.Acao;
import br.com.fiap.soulguide.model.Missao;
import br.com.fiap.soulguide.model.Usuario;

import java.util.List;

public class TesteSistema {

    public static void main(String[] args) {

        UsuarioDao usuarioDao =
                new UsuarioDao();

        MissaoDao missaoDao =
                new MissaoDao();

        AcaoDao acaoDao =
                new AcaoDao();

        System.out.println(
                "===== SOULGUIDE ====="
        );

        // TESTE DE USUARIO

        Usuario usuario =
                new Usuario(
                        "Davi",
                        "123456",
                        "davi@email.com",
                        19,
                        "São Paulo",
                        "reciclagem"
                );

        System.out.println(
                "\nE-mail válido: " +
                        usuario.validarEmail()
        );

        System.out.println(
                "Idade válida: " +
                        usuario.validarIdade()
        );

        usuarioDao.inserir(usuario);

        System.out.println(
                "\n===== USUÁRIOS ====="
        );

        List<Usuario> usuarios =
                usuarioDao.listar();

        for (Usuario u : usuarios) {

            System.out.println(u);
        }

        // TESTE DE MISSAO

        Missao missao =
                new Missao(
                        "Reciclagem",
                        "Separe corretamente os materiais recicláveis.",
                        10
                );

        System.out.println(
                "\nPontuação da missão válida: " +
                        missao.validarPontuacao()
        );

        missaoDao.inserir(missao);

        System.out.println(
                "\n===== MISSÕES ====="
        );

        List<Missao> missoes =
                missaoDao.listar();

        for (Missao m : missoes) {

            System.out.println(m);
        }

        // TESTE DE ACAO

        Acao acao =
                new Acao(
                        "Economizar água",
                        "Reduza o consumo de água durante o dia.",
                        10
                );

        System.out.println(
                "\nAção disponível: " +
                        acao.estaDisponivel()
        );

        acaoDao.inserir(acao);

        System.out.println(
                "\n===== AÇÕES ====="
        );

        List<Acao> acoes =
                acaoDao.listar();

        for (Acao a : acoes) {

            System.out.println(a);
        }

        // REGRAS DE NEGOCIO

        usuario.adicionarPontos(30);

        System.out.println(
                "\nPontos do usuário: " +
                        usuario.getPontos()
        );

        System.out.println(
                "Sugestão: " +
                        usuario.gerarSugestao()
        );
    }
}
