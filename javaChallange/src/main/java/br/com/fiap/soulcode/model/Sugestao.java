package br.com.fiap.soulcode.model;

public class Sugestao {

    private String mensagem;

    public Sugestao(String mensagem) {

        this.mensagem = mensagem;
    }

    public String getMensagem() {

        return mensagem;
    }

    public void setMensagem(String mensagem) {

        this.mensagem = mensagem;
    }

    @Override
    public String toString() {

        return mensagem;
    }
}

