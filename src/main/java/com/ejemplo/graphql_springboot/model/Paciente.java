package com.ejemplo.graphql_springboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pacientes")
@Schema(description = "Entidad que representa un paciente en el sistema")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(
        description = "Identificador único del paciente (UUID)", 
        example = "123e4567-e89b-12d3-a456-426614174000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Schema(
        description = "Nombre completo del paciente", 
        example = "Juan Pérez García",
        required = true,
        minLength = 2,
        maxLength = 100
    )
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 150, message = "El email no puede exceder los 150 caracteres")
    @Schema(
        description = "Dirección de correo electrónico del paciente", 
        example = "juan.perez@email.com",
        required = true,
        format = "email"
    )
    private String email;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Schema(
        description = "Fecha y hora de creación del registro",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Schema(
        description = "Fecha y hora de última actualización del registro",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime updatedAt;

    // Constructor vacío (requerido por JPA)
    public Paciente() {}

    // Constructor para crear nuevos pacientes
    public Paciente(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor completo (para testing)
    public Paciente(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Métodos de utilidad
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // toString para debugging
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // equals y hashCode basados en ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id != null && id.equals(paciente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}