
package br.com.fiap.soulcode.exception;

import br.com.fiap.soulcode.bo.UsuarioBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(UsuarioBo.UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> naoEncontrado(
            UsuarioBo.UsuarioNaoEncontradoException e) {

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
                .body(Map.of("erro", "JSON inválido ou incompleto."));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> tipoInvalido(
            MethodArgumentTypeMismatchException e) {

        return ResponseEntity.badRequest()
                .body(Map.of("erro", "Parâmetro informado inválido."));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> erroDeOperacao(
            IllegalStateException e) {

        logger.error("Erro durante uma operação da API.", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erro", "Não foi possível concluir a operação."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> erroGeral(Exception e) {

        logger.error("Erro inesperado na API.", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erro", "Ocorreu um erro interno no servidor."));
    }
}
