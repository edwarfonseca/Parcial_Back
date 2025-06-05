package com.ejemplo.graphql_springboot.service;

import com.ejemplo.graphql_springboot.model.Paciente;
import com.ejemplo.graphql_springboot.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Obtiene todos los pacientes del sistema
     */
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAllByOrderByNameAsc();
    }

    /**
     * Obtiene un paciente por su ID
     */
    public Paciente getPacienteById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<Paciente> paciente = pacienteRepository.findById(uuid);
            return paciente.orElse(null);
        } catch (IllegalArgumentException e) {
            // ID no es un UUID válido
            return null;
        }
    }

    /**
     * Crea un nuevo paciente
     */
    public Paciente createPaciente(String name, String email) {
        // Verificar si el email ya existe
        if (pacienteRepository.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un paciente con este email: " + email);
        }
        
        Paciente paciente = new Paciente(name, email);
        return pacienteRepository.save(paciente);
    }

    /**
     * Actualiza un paciente existente
     */
    public Paciente updatePaciente(String id, String name, String email) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<Paciente> pacienteOpt = pacienteRepository.findById(uuid);
            
            if (pacienteOpt.isPresent()) {
                Paciente paciente = pacienteOpt.get();
                
                // Verificar si el nuevo email ya existe en otro paciente
                Optional<Paciente> pacienteConEmail = pacienteRepository.findByEmail(email);
                if (pacienteConEmail.isPresent() && !pacienteConEmail.get().getId().equals(uuid)) {
                    throw new RuntimeException("Ya existe otro paciente con este email: " + email);
                }
                
                paciente.setName(name);
                paciente.setEmail(email);
                return pacienteRepository.save(paciente);
            }
            return null;
        } catch (IllegalArgumentException e) {
            // ID no es un UUID válido
            return null;
        }
    }

    /**
     * Elimina un paciente del sistema
     */
    public boolean deletePaciente(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            if (pacienteRepository.existsById(uuid)) {
                pacienteRepository.deleteById(uuid);
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            // ID no es un UUID válido
            return false;
        }
    }

    /**
     * Busca pacientes por nombre
     */
    public List<Paciente> findPacientesByName(String name) {
        return pacienteRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Busca un paciente por email
     */
    public Paciente findPacienteByEmail(String email) {
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        return paciente.orElse(null);
    }

    /**
     * Cuenta el total de pacientes
     */
    public Long countPacientes() {
        return pacienteRepository.countAllPacientes();
    }
}