package com.example.teammanagement.service;

import com.example.teammanagement.dto.EquipoDeTrabajoInput;
import com.example.teammanagement.entity.EquipoDeTrabajo;
import com.example.teammanagement.repository.EquipoDeTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoDeTrabajoService {
    
    @Autowired
    private EquipoDeTrabajoRepository equipoRepository;
    
    public List<EquipoDeTrabajo> findAll() {
        return equipoRepository.findAll();
    }
    
    public Optional<EquipoDeTrabajo> findById(Long id) {
        return equipoRepository.findById(id);
    }
    
    public EquipoDeTrabajo save(EquipoDeTrabajoInput input) {
        EquipoDeTrabajo equipo = new EquipoDeTrabajo();
        equipo.setNombre(input.getNombre());
        equipo.setProyecto(input.getProyecto());
        equipo.setLider(input.getLider());
        equipo.setFechaCreacion(input.getFechaCreacion());
        equipo.setDescripcion(input.getDescripcion());
        
        return equipoRepository.save(equipo);
    }
    
    public EquipoDeTrabajo update(Long id, EquipoDeTrabajoInput input) {
        Optional<EquipoDeTrabajo> equipoOpt = equipoRepository.findById(id);
        if (equipoOpt.isPresent()) {
            EquipoDeTrabajo equipo = equipoOpt.get();
            equipo.setNombre(input.getNombre());
            equipo.setProyecto(input.getProyecto());
            equipo.setLider(input.getLider());
            equipo.setFechaCreacion(input.getFechaCreacion());
            equipo.setDescripcion(input.getDescripcion());
            
            return equipoRepository.save(equipo);
        }
        throw new RuntimeException("Equipo no encontrado con ID: " + id);
    }
    
    public boolean delete(Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
