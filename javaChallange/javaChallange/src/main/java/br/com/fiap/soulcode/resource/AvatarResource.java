package br.com.fiap.soulcode.resource;

import br.com.fiap.soulcode.bo.AvatarBo;
import br.com.fiap.soulcode.dto.AvatarRequest;
import br.com.fiap.soulcode.model.Avatar;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avatares")
public class AvatarResource {

    private final AvatarBo avatarBo;

    public AvatarResource(AvatarBo avatarBo) {
        this.avatarBo = avatarBo;
    }

    @GetMapping
    public ResponseEntity<List<Avatar>> listar() {
        return ResponseEntity.ok(avatarBo.listar());
    }

    @PostMapping
    public ResponseEntity<Avatar> inserir(
            @Valid @RequestBody AvatarRequest dados) {

        Avatar avatar = avatarBo.inserir(dados);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avatar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @Valid @RequestBody AvatarRequest dados) {

        avatarBo.atualizar(id, dados);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable int id) {

        avatarBo.remover(id);

        return ResponseEntity.noContent().build();
    }
}