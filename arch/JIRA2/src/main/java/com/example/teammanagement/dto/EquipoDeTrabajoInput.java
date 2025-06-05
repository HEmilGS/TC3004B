package com.example.teammanagement.dto;

import java.time.LocalDate;

public class EquipoDeTrabajoInput {
    private String nombre;
    private String proyecto;
    private String lider;
    private LocalDate fechaCreacion;
    private String descripcion;
    
    // Constructors
    public EquipoDeTrabajoInput() {}
    
    // Getters and Setters
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
}
