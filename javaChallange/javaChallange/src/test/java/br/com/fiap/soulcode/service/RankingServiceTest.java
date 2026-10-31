
package br.com.fiap.soulcode.service;

import br.com.fiap.soulcode.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingServiceTest {

    private final RankingService rankingService = new RankingService();

    private Usuario criarUsuario(String nome, int pontos) {
        Usuario usuario = new Usuario(
                nome,
                "123456",
                nome.toLowerCase() + "@email.com",
                18,
                "São Paulo",
                "Sustentabilidade"
        );

        usuario.setPontos(pontos);
        return usuario;
    }

    @Test
    void deveOrdenarUsuariosPorPontuacao() {
        Usuario davi = criarUsuario("Davi", 100);
        Usuario ana = criarUsuario("Ana", 300);
        Usuario joao = criarUsuario("João", 200);

        List<Usuario> usuarios = List.of(davi, ana, joao);

        List<Usuario> resultado = rankingService.gerarRanking(usuarios);

        assertEquals(
                List.of("Ana", "João", "Davi"),
                resultado.stream().map(Usuario::getNome).toList()
        );
    }

    @Test
    void naoDeveAlterarListaOriginal() {
        Usuario davi = criarUsuario("Davi", 100);
        Usuario ana = criarUsuario("Ana", 300);

        List<Usuario> usuarios = new ArrayList<>(List.of(davi, ana));

        rankingService.gerarRanking(usuarios);

        assertEquals(List.of(davi, ana), usuarios);
    }

    @Test
    void deveRetornarListaVazia() {
        List<Usuario> resultado =
                rankingService.gerarRanking(List.of());

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveFuncionarComApenasUmUsuario() {
        Usuario davi = criarUsuario("Davi", 100);

        List<Usuario> resultado =
                rankingService.gerarRanking(List.of(davi));

        assertEquals(1, resultado.size());
        assertEquals("Davi", resultado.get(0).getNome());
    }
}
