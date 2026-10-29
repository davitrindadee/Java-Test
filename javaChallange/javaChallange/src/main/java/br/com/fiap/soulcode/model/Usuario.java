package br.com.fiap.soulcode.model;

import java.security.MessageDigest;

public class Usuario {

    private int id;
    private String nome;
    private String senhaHash;
    private String email;
    private int idade;
    private String cidade;
    private String interesse;
    private int pontos;

    // Construtor para novo usuário
    public Usuario(String nome, String senha, String email,
                   int idade, String cidade, String interesse) {

        this.nome = nome;
        this.senhaHash = hashSenha(senha);
        this.email = email;
        this.idade = idade;
        this.cidade = cidade;
        this.interesse = interesse;
        this.pontos = 0;
    }

    // Construtor para usuário vindo do banco
    public Usuario(int id, String nome, String senhaHash,
                   String email, int idade, String cidade,
                   String interesse, int pontos) {

        this.id = id;
        this.nome = nome;
        this.senhaHash = senhaHash;
        this.email = email;
        this.idade = idade;
        this.cidade = cidade;
        this.interesse = interesse;
        this.pontos = pontos;
    }

    private String hashSenha(String senha) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {

                sb.append(
                        String.format("%02x", b)
                );
            }

            return sb.toString();

        } catch (Exception e) {

            return null;
        }
    }

    public boolean validarSenha(String senha) {

        return senhaHash.equals(hashSenha(senha));
    }

    public boolean validarEmail() {

        return email != null &&
                email.contains("@") &&
                email.contains(".");
    }

    public boolean validarIdade() {

        return idade >= 12;
    }

    public void adicionarPontos(int pontos) {

        if (pontos > 0) {
            this.pontos += pontos;
        }
    }

    public boolean validarPontuacao() {

        return pontos >= 0;
    }

    public String gerarSugestao() {

        if (interesse == null || interesse.isEmpty()) {

            return "Explore as ações sustentáveis disponíveis.";
        }

        return "Sugestão: confira ações relacionadas a "
                + interesse + ".";
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

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {

        return id + " - " +
                nome +
                " | E-mail: " + email +
                " | Cidade: " + cidade +
                " | Pontos: " + pontos;
    }
}
