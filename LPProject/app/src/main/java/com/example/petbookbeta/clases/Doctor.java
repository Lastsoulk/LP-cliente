package com.example.petbookbeta.clases;

public class Doctor {
    public String cedula;
    public String Nombre;

    public Doctor(String cedula, String nombre) {
        this.cedula = cedula;
        Nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
