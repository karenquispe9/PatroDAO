package com.itic.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int idEmpresa;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "descripcio", nullable = false)
    private String descripcio;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Departament> departaments = new HashSet<>();

    // Constructor vacío (obligatorio para Hibernate)
    public Empresa() {}

    // Constructor con parámetros
    public Empresa(String descripcio) {
        this.descripcio = descripcio;
    }

    // Getters y Setters
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Set<Departament> getDepartaments() {
        return departaments;
    }

    public void addDepartament(Departament departament) {
        departaments.add(departament);
    }

    public void removeDepartament(Departament departament) {
        departaments.remove(departament);
    }

    public void setDepartaments(Set<Departament> departaments) {
        this.departaments = departaments;
    }
}

