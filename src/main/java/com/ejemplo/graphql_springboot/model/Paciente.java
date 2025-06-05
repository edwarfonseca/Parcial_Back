package com.ejemplo.graphql_springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "pacientes")
public class Paciente {

    @Id
    private String id;
    
    private String name;
    
    @Indexed(unique = true)
    private String email;

    // Constructor
    public Paciente(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor vacío (requerido por MongoDB)
    public Paciente() {}

    // Constructor completo
    public Paciente(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}