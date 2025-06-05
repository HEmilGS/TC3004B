package com.example.teammanagement.service;

import com.example.teammanagement.dto.TrabajadorInput;
import com.example.teammanagement.entity.EquipoDeTrabajo;
import com.example.teammanagement.entity.Trabajador;
import com.example.teammanagement.repository.EquipoDeTrabajoRepository;
import com.example.teammanagement.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorService {
    
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    
    @Autowired
    private EquipoDeTrabajoRepository equipoRepository;
    
    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }
    
    public Optional<Trabajador> findById(Long id) {
        return trabajadorRepository.findById(id);
    }
    
    public List<Trabajador> findByEquipoId(Long equipoId) {
        return trabajadorRepository.findByEquipoId(equipoId);
    }
    
    public Trabajador save(TrabajadorInput input) {
        Trabajador trabajador = new Trabajador();
        trabajador.setNombre(input.getNombre());
        trabajador.setApellido(input.getApellido());
        trabajador.setRol(input.getRol());
        trabajador.setEmail(input.getEmail());
        
        if (input.getEquipoId() != null) {
            Optional<EquipoDeTrabajo> equipo = equipoRepository.findById(input.getEquipoId());
            equipo.ifPresent(trabajador::setEquipo);
        }
        
        return trabajadorRepository.save(trabajador);
    }
    
    public Trabajador update(Long id, TrabajadorInput input) {
        Optional<Trabajador> trabajadorOpt = trabajadorRepository.findById(id);
        if (trabajadorOpt.isPresent()) {
            Trabajador trabajador = trabajadorOpt.get();
            trabajador.setNombre(input.getNombre());
            trabajador.setApellido(input.getApellido());
            trabajador.setRol(input.getRol());
            trabajador.setEmail(input.getEmail());
            
            if (input.getEquipoId() != null) {
                Optional<EquipoDeTrabajo> equipo = equipoRepository.findById(input.getEquipoId());
                equipo.ifPresent(trabajador::setEquipo);
            }
            
            return trabajadorRepository.save(trabajador);
        }
        throw new RuntimeException("Trabajador no encontrado con ID: " + id);
    }
    
    public boolean delete(Long id) {
        if (trabajadorRepository.existsById(id)) {
            trabajadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
