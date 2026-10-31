
package br.com.fiap.soulcode.service;

import br.com.fiap.soulcode.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {

    public List<Usuario> gerarRanking(List<Usuario> usuarios) {

        List<Usuario> ranking = new ArrayList<>(usuarios);

        ranking.sort(
                Comparator.comparingInt(Usuario::getPontos).reversed()
        );

        return ranking;
    }

    public void exibirRanking(List<Usuario> usuarios) {

        List<Usuario> ranking = gerarRanking(usuarios);

        System.out.println("\n===== RANKING =====");

        int posicao = 1;

        for (Usuario usuario : ranking) {

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
