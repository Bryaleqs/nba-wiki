package com.bquinonez.nbawiki;

public class Equipo {
    private int id;
    private String nombre;
    private String ciudad;
    private String colorPrincipal;
    private String abreviatura;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getColorPrincipal() { return colorPrincipal; }
    public void setColorPrincipal(String colorPrincipal) { this.colorPrincipal = colorPrincipal; }

    public String getAbreviatura() { return abreviatura; }
    public void setAbreviatura(String abreviatura) { this.abreviatura = abreviatura; }
}
