package br.com.fiap.soulcode.model;

import java.time.LocalDateTime;

public class Pontuacao {

    private int id;
    private Usuario usuario;
    private int quantidadePontos;
    private LocalDateTime data;

    public Pontuacao(Usuario usuario, int quantidadePontos) {

        this.usuario = usuario;
        this.quantidadePontos = quantidadePontos;
        this.data = LocalDateTime.now();
    }

    public Pontuacao(int id, Usuario usuario,
                     int quantidadePontos,
                     LocalDateTime data) {

        this.id = id;
        this.usuario = usuario;
        this.quantidadePontos = quantidadePontos;
        this.data = data;
    }

    public void adicionarPontos(int pontos) {

        if (pontos > 0) {

            quantidadePontos += pontos;

            if (usuario != null) {
                usuario.adicionarPontos(pontos);
            }
        }
    }

    public boolean validarPontuacao() {

        return quantidadePontos > 0;
    }

    public boolean verificarEngajamento() {

        return usuario != null &&
                usuario.getPontos() >= 30;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

    public void setQuantidadePontos(int quantidadePontos) {
        this.quantidadePontos = quantidadePontos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {

        return id +
                " | Usuário: " +
                usuario.getNome() +
                " | Pontos: " +
                quantidadePontos +
                " | Data: " +
                data;
    }
}

