package com.example.teammanagement.resolver;

import com.example.teammanagement.dto.TrabajadorInput;
import com.example.teammanagement.entity.EquipoDeTrabajo;
import com.example.teammanagement.entity.Trabajador;
import com.example.teammanagement.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class TrabajadorResolver {
    
    @Autowired
    private TrabajadorService trabajadorService;
    
    @QueryMapping
    public List<Trabajador> trabajadores() {
        return trabajadorService.findAll();
    }
    
    @QueryMapping
    public Trabajador trabajador(@Argument Long id) {
        return trabajadorService.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
    }
    
    @QueryMapping
    public List<Trabajador> trabajadoresPorEquipo(@Argument Long equipoId) {
        return trabajadorService.findByEquipoId(equipoId);
    }
    
    @MutationMapping
    public Trabajador crearTrabajador(@Argument TrabajadorInput input) {
        return trabajadorService.save(input);
    }
    
    @MutationMapping
    public Trabajador actualizarTrabajador(@Argument Long id, @Argument TrabajadorInput input) {
        return trabajadorService.update(id, input);
    }
    
    @MutationMapping
    public Boolean eliminarTrabajador(@Argument Long id) {
        return trabajadorService.delete(id);
    }
    
    @SchemaMapping
    public EquipoDeTrabajo equipo(Trabajador trabajador) {
        return trabajador.getEquipo();
    }
}
