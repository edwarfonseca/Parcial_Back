package com.ejemplo.graphql_springboot.controller;

import com.ejemplo.graphql_springboot.model.Paciente;
import com.ejemplo.graphql_springboot.service.PacienteService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @QueryMapping
    public List<Paciente> getPacientes() {
        return pacienteService.getAllPacientes();
    }

    @QueryMapping
    public Paciente getPacienteById(@Argument String id) {
        return pacienteService.getPacienteById(id);
    }

    @MutationMapping
    public Paciente createPaciente(@Argument PacienteInput input) {
        return pacienteService.createPaciente(input.getName(), input.getEmail());
    }

    @MutationMapping
    public Paciente updatePaciente(@Argument String id, @Argument PacienteInput input) {
        return pacienteService.updatePaciente(id, input.getName(), input.getEmail());
    }

    @MutationMapping
    public boolean deletePaciente(@Argument String id) {
        return pacienteService.deletePaciente(id);
    }

    // Clase para el input del paciente
    public static class PacienteInput {
        private String name;
        private String email;

        // Getters
        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        // Setters (IMPORTANTE para que Spring pueda mapear el JSON)
        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
