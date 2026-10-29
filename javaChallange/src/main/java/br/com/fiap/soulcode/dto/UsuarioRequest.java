package br.com.fiap.soulcode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotNull(message = "A idade é obrigatória")
        @Min(value = 12, message = "A idade mínima é 12 anos")
        Integer idade,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O interesse é obrigatório")
        String interesse
) {
}