package br.com.fiap.soulcode.bo;

import br.com.fiap.soulcode.dao.UsuarioDao;
import br.com.fiap.soulcode.dto.UsuarioRequest;
import br.com.fiap.soulcode.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioBo {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public Usuario buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Usuario usuario = usuarioDao.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(
                    "Usuário não encontrado."
            );
        }

        return usuario;
    }

    public Usuario inserir(UsuarioRequest dados) {
        validarDados(dados);

        Usuario usuario = new Usuario(
                dados.nome().trim(),
                dados.senha(),
                dados.email().trim(),
                dados.idade(),
                dados.cidade().trim(),
                dados.interesse().trim()
        );

        usuarioDao.inserir(usuario);
        return usuario;
    }

    public Usuario atualizar(int id, UsuarioRequest dados) {
        validarDados(dados);

        Usuario atual = buscarPorId(id);

        atual.setNome(dados.nome().trim());
        atual.setEmail(dados.email().trim());
        atual.setIdade(dados.idade());
        atual.setCidade(dados.cidade().trim());
        atual.setInteresse(dados.interesse().trim());

        usuarioDao.atualizar(atual);
        return atual;
    }

    public void remover(int id) {
        buscarPorId(id);
        usuarioDao.remover(id);
    }

    private void validarDados(UsuarioRequest dados) {
        if (dados.idade() < 12) {
            throw new IllegalArgumentException(
                    "A idade mínima é 12 anos."
            );
        }

        if (!dados.email().contains("@")) {
            throw new IllegalArgumentException(
                    "E-mail inválido."
            );
        }
    }

    public static class UsuarioNaoEncontradoException
            extends RuntimeException {

        public UsuarioNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }
}