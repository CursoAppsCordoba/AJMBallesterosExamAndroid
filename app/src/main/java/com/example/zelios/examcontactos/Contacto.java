package com.example.zelios.examcontactos;

/**
 * Created by Zelios on 26/09/2017.
 */

public class Contacto {
    private String nombre;
    private String telf;

    public Contacto(String nombre, String telf) {
        this.nombre = nombre;
        this.telf = telf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }
}
