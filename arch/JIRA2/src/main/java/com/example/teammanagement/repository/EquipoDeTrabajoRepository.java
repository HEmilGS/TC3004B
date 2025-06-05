package com.example.teammanagement.repository;

import com.example.teammanagement.entity.EquipoDeTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoDeTrabajoRepository extends JpaRepository<EquipoDeTrabajo, Long> {
}
