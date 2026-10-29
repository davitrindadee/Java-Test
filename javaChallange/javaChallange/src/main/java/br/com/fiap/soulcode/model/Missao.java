package br.com.fiap.soulcode.model;

public class Missao {

    private int id;
    private String nome;
    private String descricao;
    private int pontos;
    private boolean status;

    public Missao(String nome, String descricao, int pontos) {

        this.nome = nome;
        this.descricao = descricao;
        this.pontos = pontos;
        this.status = true;
    }

    public Missao(int id, String nome, String descricao,
                  int pontos, boolean status) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontos = pontos;
        this.status = status;
    }

    public boolean validarPontuacao() {

        return pontos > 0;
    }

    public void ativarMissao() {

        status = true;
    }

    public void concluirMissao() {

        status = false;
    }

    public boolean estaAtiva() {

        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return id + " - " +
                nome +
                " | " + descricao +
                " | " + pontos + " pontos" +
                " | Ativa: " + status;
    }
}

