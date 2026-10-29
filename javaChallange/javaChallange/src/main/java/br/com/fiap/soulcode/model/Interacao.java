package br.com.fiap.soulcode.model;

import java.time.LocalDateTime;

public class Interacao {

    private int id;
    private Usuario usuario;
    private Acao acao;
    private String tipo;
    private LocalDateTime data;

    public Interacao(Usuario usuario, Acao acao, String tipo) {

        this.usuario = usuario;
        this.acao = acao;
        this.tipo = tipo;
        this.data = LocalDateTime.now();
    }

    public Interacao(int id, Usuario usuario,
                     Acao acao, String tipo,
                     LocalDateTime data) {

        this.id = id;
        this.usuario = usuario;
        this.acao = acao;
        this.tipo = tipo;
        this.data = data;
    }

    public boolean verificarEngajamento() {

        return usuario != null &&
                usuario.getPontos() >= 30;
    }

    public boolean ehConclusao() {

        return tipo != null &&
                tipo.equalsIgnoreCase("CONCLUSAO");
    }

    public int calcularPontos() {

        if (acao != null && acao.estaDisponivel()) {

            return acao.getPontos();
        }

        return 0;
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

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
                " | Ação: " +
                acao.getNome() +
                " | Tipo: " +
                tipo +
                " | Data: " +
                data;
    }
}

