package br.com.fiap.soulguide.service;

import br.com.fiap.soulguide.model.Usuario;

import java.util.Comparator;
import java.util.List;

public class RankingService {

    public void exibirRanking(List<Usuario> usuarios) {

        usuarios.sort(
                Comparator.comparingInt(
                        Usuario::getPontos
                ).reversed()
        );

        System.out.println("\n===== RANKING =====");

        int posicao = 1;

        for (Usuario usuario : usuarios) {

            System.out.println(
                    posicao + "º - " +
                            usuario.getNome() +
                            " | " +
                            usuario.getPontos() +
                            " pontos"
            );

            posicao++;
        }
    }
}

