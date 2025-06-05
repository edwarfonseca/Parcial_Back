package com.ejemplo.graphql_springboot.controller;

import com.ejemplo.graphql_springboot.model.Paciente;
import com.ejemplo.graphql_springboot.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "API para la gestión de pacientes")
public class PacienteRestController {

    private final PacienteService pacienteService;

    public PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los pacientes",
        description = "Devuelve una lista con todos los pacientes registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de pacientes obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Paciente.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener paciente por ID",
        description = "Devuelve los datos de un paciente específico mediante su ID único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Paciente encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Paciente.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Paciente no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de paciente inválido"
        )
    })
    public ResponseEntity<Paciente> getPacienteById(
            @Parameter(description = "ID único del paciente", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        Paciente paciente = pacienteService.getPacienteById(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
        summary = "Crear nuevo paciente",
        description = "Crea un nuevo paciente en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Paciente creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Paciente.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Paciente ya existe (email duplicado)"
        )
    })
    public ResponseEntity<Paciente> createPaciente(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos del paciente a crear",
                required = true,
                content = @Content(schema = @Schema(implementation = PacienteInput.class))
            )
            @RequestBody PacienteInput input) {
        Paciente paciente = pacienteService.createPaciente(input.getName(), input.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar paciente",
        description = "Actualiza los datos de un paciente existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Paciente actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Paciente.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Paciente no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos"
        )
    })
    public ResponseEntity<Paciente> updatePaciente(
            @Parameter(description = "ID único del paciente", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Nuevos datos del paciente",
                required = true,
                content = @Content(schema = @Schema(implementation = PacienteInput.class))
            )
            @RequestBody PacienteInput input) {
        Paciente paciente = pacienteService.updatePaciente(id, input.getName(), input.getEmail());
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar paciente",
        description = "Elimina un paciente del sistema de forma permanente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Paciente eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Paciente no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de paciente inválido"
        )
    })
    public ResponseEntity<Void> deletePaciente(
            @Parameter(description = "ID único del paciente a eliminar", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        boolean deleted = pacienteService.deletePaciente(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Clase DTO para documentación
    @Schema(description = "Datos de entrada para crear o actualizar un paciente")
    public static class PacienteInput {
        @Schema(
            description = "Nombre completo del paciente", 
            example = "Juan Pérez García",
            required = true,
            minLength = 2,
            maxLength = 100
        )
        private String name;

        @Schema(
            description = "Dirección de correo electrónico del paciente", 
            example = "juan.perez@email.com",
            required = true,
            format = "email"
        )
        private String email;

        // Getters
        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}