package com.example.teammanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos_trabajo")
public class EquipoDeTrabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String proyecto;
    
    @Column(nullable = false)
    private String lider;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    
    @Column(length = 500)
    private String descripcion;
    
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trabajador> trabajadores = new ArrayList<>();
    
    // Constructors
    public EquipoDeTrabajo() {}
    
    public EquipoDeTrabajo(String nombre, String proyecto, String lider, 
                          LocalDate fechaCreacion, String descripcion) {
        this.nombre = nombre;
        this.proyecto = proyecto;
        this.lider = lider;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getProyecto() { return proyecto; }
    public void setProyecto(String proyecto) { this.proyecto = proyecto; }
    
    public String getLider() { return lider; }
    public void setLider(String lider) { this.lider = lider; }
    
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public List<Trabajador> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(List<Trabajador> trabajadores) { this.trabajadores = trabajadores; }
}
