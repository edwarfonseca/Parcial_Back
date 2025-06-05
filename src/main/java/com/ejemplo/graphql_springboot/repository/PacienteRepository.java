package com.ejemplo.graphql_springboot.repository;

import com.ejemplo.graphql_springboot.model.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
    
    // Método para buscar por email
    Optional<Paciente> findByEmail(String email);
    
    // Método para verificar si existe un email
    boolean existsByEmail(String email);
    
    // Método para buscar por nombre (case-insensitive)
    Optional<Paciente> findByNameIgnoreCase(String name);
}