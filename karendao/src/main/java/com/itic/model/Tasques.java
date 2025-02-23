package com.itic.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tasques")
public class Tasques implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tasques")
    private int idTasques;

    @Column(name = "descripcio", nullable = false)
    private String descripcio;

    // Relación ManyToMany con Empleat
    @ManyToMany
    @JoinTable(
        name = "empleat_tasques", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_tasca"), // Clave foránea hacia Tasques
        inverseJoinColumns = @JoinColumn(name = "id_empleat") // Clave foránea hacia Empleat
    )
    private Set<Empleat> empleats;

    // Relación ManyToOne con Empleat (empleat principal)
    @ManyToOne
    @JoinColumn(name = "id_empleat_principal", nullable = false)
    private Empleat empleatPrincipal;

    // Constructor vacío (requerido por Hibernate)
    public Tasques() {}

    // Constructor con parámetros
    public Tasques(String descripcio, Empleat empleatPrincipal) {
        this.descripcio = descripcio;
        this.empleatPrincipal = empleatPrincipal;
    }

    // Getters y Setters
    public int getIdTasques() {
        return idTasques;
    }

    public void setIdTasques(int idTasques) {
        this.idTasques = idTasques;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Set<Empleat> getEmpleats() {
        return empleats;
    }

    public void setEmpleats(Set<Empleat> empleats) {
        this.empleats = empleats;
    }

    public Empleat getEmpleatPrincipal() {
        return empleatPrincipal;
    }

    public void setEmpleatPrincipal(Empleat empleatPrincipal) {
        this.empleatPrincipal = empleatPrincipal;
    }
}
