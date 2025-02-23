package com.itic.model;


import java.io.Serializable;
import java.util.Set;

public class Empleat implements Serializable {

    private int idEmpleat;
    private int DNI;
    private String nom;
    private String cognom;
    private Departament departament;
    private Set<Tasques> tasques;

    // Constructor vacío (obligatorio para Hibernate)
    public Empleat() {}

    // Constructor con parámetros
    public Empleat(int DNI, String nom, String cognom, Departament departament) {
        this.DNI = DNI;
        this.nom = nom;
        this.cognom = cognom;
        this.departament = departament;
    }

    // Getters y Setters
    public int getIdEmpleat() {
        return idEmpleat;
    }

    public void setIdEmpleat(int idEmpleat) {
        this.idEmpleat = idEmpleat;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
        if (!departament.getEmpleats().contains(this)) {
            departament.getEmpleats().add(this);
        }
    }

    public Set<Tasques> getTasques() {
        return tasques;
    }

    public void setTasques(Set<Tasques> tasques) {

    }
}

