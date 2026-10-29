package br.com.fiap.soulcode.model;

public class Avatar {

    private int id;
    private Usuario usuario;
    private String nome;
    private int nivel;

    public Avatar(Usuario usuario, String nome) {

        this.usuario = usuario;
        this.nome = nome;
        this.nivel = 1;
    }

    public Avatar(int id, Usuario usuario,
                  String nome, int nivel) {

        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.nivel = nivel;
    }

    public void subirNivel() {

        if (usuario != null && usuario.getPontos() >= nivel * 100) {

            nivel++;
        }
    }

    public boolean verificarNivel() {

        return nivel >= 1;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {

        return id + " - Avatar: " +
                nome +
                " | Nível: " + nivel;
    }
}

