package com.ejemplo.graphql_springboot.service;

import com.ejemplo.graphql_springboot.model.Paciente;
import com.ejemplo.graphql_springboot.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(String id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente createPaciente(String name, String email) {
        // Verificar si ya existe un paciente con ese email
        if (pacienteRepository.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un paciente con el email: " + email);
        }
        
        Paciente paciente = new Paciente(name, email);
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(String id, String name, String email) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            
            // Verificar si el nuevo email ya existe en otro paciente
            if (!paciente.getEmail().equals(email) && pacienteRepository.existsByEmail(email)) {
                throw new RuntimeException("Ya existe un paciente con el email: " + email);
            }
            
            paciente.setName(name);
            paciente.setEmail(email);
            return pacienteRepository.save(paciente);
        }
        
        throw new RuntimeException("Paciente no encontrado con ID: " + id);
    }

    public boolean deletePaciente(String id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos adicionales útiles
    public Paciente findByEmail(String email) {
        return pacienteRepository.findByEmail(email).orElse(null);
    }

    public long countPacientes() {
        return pacienteRepository.count();
    }
}