package br.com.fiap.soulcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AvatarRequest(

        @Positive(message = "O ID do usuário deve ser válido.")
        int idUsuario,

        @NotBlank(message = "O nome do avatar é obrigatório.")
        String nome

) {
}