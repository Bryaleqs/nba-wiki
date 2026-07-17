package com.bquinonez.nbawiki;

public class Aparicion {
    private int anio;
    private boolean campeon; // true = gano el titulo ese año, false = perdio la final
    private String resultadoSerie;
    private String rivalNombre;
    private String rivalCiudad;

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public boolean isCampeon() { return campeon; }
    public void setCampeon(boolean campeon) { this.campeon = campeon; }

    public String getResultadoSerie() { return resultadoSerie; }
    public void setResultadoSerie(String resultadoSerie) { this.resultadoSerie = resultadoSerie; }

    public String getRivalNombre() { return rivalNombre; }
    public void setRivalNombre(String rivalNombre) { this.rivalNombre = rivalNombre; }

    public String getRivalCiudad() { return rivalCiudad; }
    public void setRivalCiudad(String rivalCiudad) { this.rivalCiudad = rivalCiudad; }
}
