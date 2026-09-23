package com.example.lab33;
import java.io.Serializable;

public class Persona implements Serializable {

    private int id;
    private String nombres;
    private String apellidos;
    private String ci;

    // Constructor para crear una persona nueva
    public Persona(String nombres, String apellidos, String ci) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
    }

    // Constructor para una persona que ya tiene ID
    public Persona(int id, String nombres, String apellidos, String ci) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCi() {
        return ci;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}