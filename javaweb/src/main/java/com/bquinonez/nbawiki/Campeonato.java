package com.bquinonez.nbawiki;

import java.util.ArrayList;
import java.util.List;

/** Representa una fila combinada: campeonato + equipo campeon + rival + MVP. */
public class Campeonato {

    private int anio;
    private String campeon;
    private String ciudad;
    private String abreviatura;
    private String colorPrincipal;
    private String conferencia;
    private String finalista;
    private String resultadoSerie;
    private String mvp;
    private List<String> roster = new ArrayList<>();
    private int id;
    private boolean esFavorito;
    private int totalFavoritos;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isEsFavorito() { return esFavorito; }
    public void setEsFavorito(boolean esFavorito) { this.esFavorito = esFavorito; }

    public int getTotalFavoritos() { return totalFavoritos; }
    public void setTotalFavoritos(int totalFavoritos) { this.totalFavoritos = totalFavoritos; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getCampeon() { return campeon; }
    public void setCampeon(String campeon) { this.campeon = campeon; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getAbreviatura() { return abreviatura; }
    public void setAbreviatura(String abreviatura) { this.abreviatura = abreviatura; }

    public String getColorPrincipal() { return colorPrincipal; }
    public void setColorPrincipal(String colorPrincipal) { this.colorPrincipal = colorPrincipal; }

    public String getConferencia() { return conferencia; }
    public void setConferencia(String conferencia) { this.conferencia = conferencia; }

    public String getFinalista() { return finalista; }
    public void setFinalista(String finalista) { this.finalista = finalista; }

    public String getResultadoSerie() { return resultadoSerie; }
    public void setResultadoSerie(String resultadoSerie) { this.resultadoSerie = resultadoSerie; }

    public String getMvp() { return mvp; }
    public void setMvp(String mvp) { this.mvp = mvp; }

    public List<String> getRoster() { return roster; }
    public void setRoster(List<String> roster) { this.roster = roster; }
}
