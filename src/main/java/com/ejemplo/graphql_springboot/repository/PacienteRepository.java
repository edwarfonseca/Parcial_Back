package com.ejemplo.graphql_springboot.repository;

import com.ejemplo.graphql_springboot.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    /**
     * Busca un paciente por su email (debe ser único)
     */
    Optional<Paciente> findByEmail(String email);

    /**
     * Verifica si existe un paciente con el email dado
     */
    boolean existsByEmail(String email);

    /**
     * Busca pacientes por nombre (búsqueda parcial, insensible a mayúsculas)
     */
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Paciente> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Busca pacientes por dominio de email
     */
    @Query("SELECT p FROM Paciente p WHERE p.email LIKE CONCAT('%', :domain)")
    List<Paciente> findByEmailDomain(@Param("domain") String domain);

    /**
     * Obtiene todos los pacientes ordenados por fecha de creación (más recientes primero)
     */
    @Query("SELECT p FROM Paciente p ORDER BY p.createdAt DESC")
    List<Paciente> findAllOrderByCreatedAtDesc();

    /**
     * Obtiene todos los pacientes ordenados por nombre
     */
    List<Paciente> findAllByOrderByNameAsc();

    /**
     * Cuenta el número total de pacientes
     */
    @Query("SELECT COUNT(p) FROM Paciente p")
    Long countAllPacientes();

    /**
     * Busca pacientes creados después de una fecha específica
     */
    @Query("SELECT p FROM Paciente p WHERE p.createdAt >= :fecha")
    List<Paciente> findPacientesCreatedAfter(@Param("fecha") java.time.LocalDateTime fecha);
}