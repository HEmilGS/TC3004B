package com.example.teammanagement.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.teammanagement.entity.EquipoDeTrabajo;
import com.example.teammanagement.entity.Trabajador;
import com.example.teammanagement.repository.EquipoDeTrabajoRepository;
import com.example.teammanagement.repository.TrabajadorRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EquipoDeTrabajoRepository equipoRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear equipos de prueba
        EquipoDeTrabajo equipo1 = new EquipoDeTrabajo(
                "Equipo Alpha",
                "Sistema de Gestión",
                "Ana García",
                LocalDate.of(2024, 1, 15),
                "Equipo encargado del desarrollo del sistema de gestión empresarial"
        );

        EquipoDeTrabajo equipo2 = new EquipoDeTrabajo(
                "Equipo Beta",
                "App Móvil",
                "Carlos Ruiz",
                LocalDate.of(2024, 2, 1),
                "Equipo de desarrollo de aplicación móvil"
        );

        equipoRepository.save(equipo1);
        equipoRepository.save(equipo2);

        // Crear trabajadores de prueba
        Trabajador trabajador1 = new Trabajador("Juan", "Pérez", "Desarrollador Backend", "juan.perez@email.com");
        trabajador1.setEquipo(equipo1);

        Trabajador trabajador2 = new Trabajador("María", "López", "Desarrolladora Frontend", "maria.lopez@email.com");
        trabajador2.setEquipo(equipo1);

        Trabajador trabajador3 = new Trabajador("Pedro", "Martínez", "Diseñador UX/UI", "pedro.martinez@email.com");
        trabajador3.setEquipo(equipo2);

        Trabajador trabajador4 = new Trabajador("Laura", "González", "Desarrolladora Mobile", "laura.gonzalez@email.com");
        trabajador4.setEquipo(equipo2);

        trabajadorRepository.save(trabajador1);
        trabajadorRepository.save(trabajador2);
        trabajadorRepository.save(trabajador3);
        trabajadorRepository.save(trabajador4);

        System.out.println("Datos de prueba cargados exitosamente!");
    }
}