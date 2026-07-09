package com.bquinonez.nbawiki;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String email;

    public Usuario() { }

    public Usuario(int id, String nombreUsuario, String email) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
