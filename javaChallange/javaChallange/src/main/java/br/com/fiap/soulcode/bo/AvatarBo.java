package br.com.fiap.soulcode.bo;

import br.com.fiap.soulcode.dao.AvatarDao;
import br.com.fiap.soulcode.dao.UsuarioDao;
import br.com.fiap.soulcode.dto.AvatarRequest;
import br.com.fiap.soulcode.model.Avatar;
import br.com.fiap.soulcode.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarBo {

    private final AvatarDao avatarDao = new AvatarDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public List<Avatar> listar() {
        return avatarDao.listar();
    }

    public Avatar inserir(AvatarRequest dados) {

        Usuario usuario = usuarioDao.buscarPorId(dados.idUsuario());

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuário não encontrado."
            );
        }

        Avatar avatar = new Avatar(
                usuario,
                dados.nome().trim()
        );

        avatarDao.inserir(avatar);

        return avatar;
    }

    public void atualizar(int id, AvatarRequest dados) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        if (dados.nome() == null || dados.nome().isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do avatar obrigatório."
            );
        }

        Avatar avatar = buscarAvatar(id);

        avatar.setNome(dados.nome().trim());
        avatarDao.atualizar(avatar);
    }

    public void remover(int id) {
        buscarAvatar(id);
        avatarDao.remover(id);
    }

    private Avatar buscarAvatar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        return avatarDao.listar()
                .stream()
                .filter(avatar -> avatar.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Avatar não encontrado."
                        )
                );
    }
}