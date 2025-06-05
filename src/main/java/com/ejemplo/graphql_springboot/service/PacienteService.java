package com.ejemplo.graphql_springboot.service;

import com.ejemplo.graphql_springboot.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService {

    private final Map<String, Paciente> pacientes = new HashMap<>();

    public List<Paciente> getAllPacientes() {
        return new ArrayList<>(pacientes.values());
    }

    public Paciente getPacienteById(String id) {
        return pacientes.get(id);
    }

    public Paciente createPaciente(String name, String email) {
        String id = UUID.randomUUID().toString();
        Paciente paciente = new Paciente(id, name, email);
        pacientes.put(id, paciente);
        return paciente;
    }

    public Paciente updatePaciente(String id, String name, String email) {
        Paciente paciente = pacientes.get(id);
        if (paciente != null) {
            paciente.setName(name);
            paciente.setEmail(email);
        }
        return paciente;
    }

    public boolean deletePaciente(String id) {
        return pacientes.remove(id) != null;
    }
}
