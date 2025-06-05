package com.ejemplo.graphql_springboot.controller;

import com.ejemplo.graphql_springboot.model.Paciente;
import com.ejemplo.graphql_springboot.service.PacienteService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controlador GraphQL para la gestión de pacientes.
 * Este controlador maneja todas las operaciones GraphQL relacionadas con pacientes.
 * 
 * Para probar las operaciones GraphQL, accede a: http://localhost:8080/graphiql
 * Para ver la documentación REST con Swagger: http://localhost:8080/swagger-ui.html
 */
@Controller
@Hidden // Oculta este controlador de la documentación Swagger ya que es específico de GraphQL
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Obtiene todos los pacientes del sistema
     * 
     * Query GraphQL:
     * {
     *   getPacientes {
     *     id
     *     name
     *     email
     *     createdAt
     *     updatedAt
     *   }
     * }
     */
    @QueryMapping
    public List<Paciente> getPacientes() {
        return pacienteService.getAllPacientes();
    }

    /**
     * Obtiene un paciente específico por su ID
     * 
     * Query GraphQL:
     * {
     *   getPacienteById(id: "tu-uuid-aqui") {
     *     id
     *     name
     *     email
     *     createdAt
     *     updatedAt
     *   }
     * }
     */
    @QueryMapping
    public Paciente getPacienteById(@Argument String id) {
        return pacienteService.getPacienteById(id);
    }

    /**
     * Busca pacientes por nombre
     * 
     * Query GraphQL:
     * {
     *   findPacientesByName(name: "Juan") {
     *     id
     *     name
     *     email
     *   }
     * }
     */
    @QueryMapping
    public List<Paciente> findPacientesByName(@Argument String name) {
        return pacienteService.findPacientesByName(name);
    }

    /**
     * Busca un paciente por email
     * 
     * Query GraphQL:
     * {
     *   findPacienteByEmail(email: "juan@email.com") {
     *     id
     *     name
     *     email
     *   }
     * }
     */
    @QueryMapping
    public Paciente findPacienteByEmail(@Argument String email) {
        return pacienteService.findPacienteByEmail(email);
    }

    /**
     * Cuenta el total de pacientes
     * 
     * Query GraphQL:
     * {
     *   countPacientes
     * }
     */
    @QueryMapping
    public Long countPacientes() {
        return pacienteService.countPacientes();
    }

    /**
     * Crea un nuevo paciente
     * 
     * Mutation GraphQL:
     * mutation {
     *   createPaciente(input: {
     *     name: "Nombre del Paciente"
     *     email: "email@ejemplo.com"
     *   }) {
     *     id
     *     name
     *     email
     *     createdAt
     *   }
     * }
     */
    @MutationMapping
    public Paciente createPaciente(@Argument PacienteInput input) {
        try {
            return pacienteService.createPaciente(input.getName(), input.getEmail());
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al crear paciente: " + e.getMessage());
        }
    }

    /**
     * Actualiza un paciente existente
     * 
     * Mutation GraphQL:
     * mutation {
     *   updatePaciente(id: "tu-uuid-aqui", input: {
     *     name: "Nuevo Nombre"
     *     email: "nuevo-email@ejemplo.com"
     *   }) {
     *     id
     *     name
     *     email
     *     updatedAt
     *   }
     * }
     */
    @MutationMapping
    public Paciente updatePaciente(@Argument String id, @Argument PacienteInput input) {
        try {
            return pacienteService.updatePaciente(id, input.getName(), input.getEmail());
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar paciente: " + e.getMessage());
        }
    }

    /**
     * Elimina un paciente del sistema
     * 
     * Mutation GraphQL:
     * mutation {
     *   deletePaciente(id: "tu-uuid-aqui")
     * }
     */
    @MutationMapping
    public boolean deletePaciente(@Argument String id) {
        return pacienteService.deletePaciente(id);
    }

    /**
     * Clase de entrada para las operaciones de GraphQL
     */
    @Schema(description = "Datos de entrada para operaciones GraphQL de pacientes")
    public static class PacienteInput {
        @Schema(description = "Nombre del paciente", example = "Juan Pérez")
        private String name;
        
        @Schema(description = "Email del paciente", example = "juan@email.com")
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