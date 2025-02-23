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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Departament")
public class Departament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departament")
    private int idDepartament;

    @ManyToOne
    @JoinColumn(name = "id_gerent_fk", nullable = false)
    private Empleat gerent;

    @OneToMany(mappedBy = "departament", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Empleat> empleats = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "departament_tasques",
        joinColumns = @JoinColumn(name = "id_departament"),
        inverseJoinColumns = @JoinColumn(name = "id_tasques")
    )
    private Set<Tasques> tasques = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_empresa_fk", nullable = false) // Clave foránea
    private Empresa empresa;


    // Constructor vacío (obligatorio para Hibernate)
    public Departament() {}

    // Constructor con parámetros
    public Departament(Empleat gerent) {
        this.gerent = gerent;
    }

    // Getters y Setters
    public int getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(int idDepartament) {
        this.idDepartament = idDepartament;
    }

    public Empleat getGerent() {
        return gerent;
    }

    public void setGerent(Empleat gerent) {
        this.gerent = gerent;
    }

    public Set<Empleat> getEmpleats() {
        return empleats;
    }

    public void addEmpleat(Empleat empleat) {
        empleats.add(empleat);
        empleat.setDepartament(this);
    }

    public void removeEmpleat(Empleat empleat) {
        empleats.remove(empleat);
        empleat.setDepartament(null);
    }

    public Set<Tasques> getTasques() {
        return tasques;
    }

    public void addTasca(Tasques tasca) {
        tasques.add(tasca);
    }

    public void removeTasca(Tasques tasca) {
        tasques.remove(tasca);
    }

    public void setEmpleats(Set<Empleat> empleats) {
        this.empleats = empleats;
    }

    public void setTasques(Set<Tasques> tasques) {
        this.tasques = tasques;
    }

    // Getter and Setter for empresa
    public Empresa getEmpresa() {
    return empresa;
    }

    public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
    }
}

