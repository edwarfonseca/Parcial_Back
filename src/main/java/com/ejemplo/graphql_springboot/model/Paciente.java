package com.ejemplo.graphql_springboot.model;

public class Paciente {

    private String id;
    private String name;
    private String email;

    // Constructor
    public Paciente(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Constructor vacío (opcional, útil para frameworks como Spring)
    public Paciente() {}

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
}
