package br.com.fiap.soulcode.resource;

import br.com.fiap.soulcode.bo.UsuarioBo;
import br.com.fiap.soulcode.dto.UsuarioRequest;
import br.com.fiap.soulcode.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    private final UsuarioBo usuarioBo;

    public UsuarioResource(UsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioBo.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(usuarioBo.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> inserir(
            @Valid @RequestBody UsuarioRequest dados) {
        Usuario usuario = usuarioBo.inserir(dados);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable int id,
            @Valid @RequestBody UsuarioRequest dados) {
        return ResponseEntity.ok(
                usuarioBo.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        usuarioBo.remover(id);
        return ResponseEntity.noContent().build();
    }
}