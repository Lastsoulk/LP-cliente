package com.example.petbookbeta.clases;

public class vacunas {
    public String id;
    public String nombre;
    public String descripion;
    public vacunas(String id, String nombre, String desripcion){
        this.id=id;
        this.nombre=nombre;
        this.descripion=desripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }
}
