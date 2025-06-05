package com.example.teammanagement.resolver;

import com.example.teammanagement.dto.EquipoDeTrabajoInput;
import com.example.teammanagement.entity.EquipoDeTrabajo;
import com.example.teammanagement.entity.Trabajador;
import com.example.teammanagement.service.EquipoDeTrabajoService;
import com.example.teammanagement.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class EquipoDeTrabajoResolver {
    
    @Autowired
    private EquipoDeTrabajoService equipoService;
    
    @Autowired
    private TrabajadorService trabajadorService;
    
    @QueryMapping
    public List<EquipoDeTrabajo> equipos() {
        return equipoService.findAll();
    }
    
    @QueryMapping
    public EquipoDeTrabajo equipo(@Argument Long id) {
        return equipoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }
    
    @MutationMapping
    public EquipoDeTrabajo crearEquipo(@Argument EquipoDeTrabajoInput input) {
        return equipoService.save(input);
    }
    
    @MutationMapping
    public EquipoDeTrabajo actualizarEquipo(@Argument Long id, @Argument EquipoDeTrabajoInput input) {
        return equipoService.update(id, input);
    }
    
    @MutationMapping
    public Boolean eliminarEquipo(@Argument Long id) {
        return equipoService.delete(id);
    }
    
    @SchemaMapping
    public List<Trabajador> trabajadores(EquipoDeTrabajo equipo) {
        return trabajadorService.findByEquipoId(equipo.getId());
    }
}
