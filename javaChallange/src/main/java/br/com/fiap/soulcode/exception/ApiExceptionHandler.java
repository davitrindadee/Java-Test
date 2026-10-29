package br.com.fiap.soulcode.exception;

import br.com.fiap.soulcode.bo.UsuarioBo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(
            UsuarioBo.UsuarioNaoEncontradoException.class
    )
    public ResponseEntity<Map<String, String>> naoEncontrado(
            RuntimeException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(
            IllegalArgumentException e) {

        return ResponseEntity.badRequest()
                .body(Map.of("erro", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacao(
            MethodArgumentNotValidException e) {

        String mensagem = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(erro -> erro.getDefaultMessage())
                .orElse("Dados inválidos.");

        return ResponseEntity.badRequest()
                .body(Map.of("erro", mensagem));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> jsonInvalido(
            HttpMessageNotReadableException e) {

        return ResponseEntity.badRequest()
                .body(Map.of("erro", "JSON inválido."));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> erroInterno(
            IllegalStateException e) {

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "erro",
                        "Ocorreu um erro ao acessar os dados."
                ));
    }
}