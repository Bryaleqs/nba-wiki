package com.bquinonez.nbawiki;

public class Equipo {
    private int id;
    private String nombre;
    private String ciudad;
    private String colorPrincipal;
    private String abreviatura;
    private int titulos;
    private int apariciones;

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

    public int getTitulos() { return titulos; }
    public void setTitulos(int titulos) { this.titulos = titulos; }

    public int getApariciones() { return apariciones; }
    public void setApariciones(int apariciones) { this.apariciones = apariciones; }
}
