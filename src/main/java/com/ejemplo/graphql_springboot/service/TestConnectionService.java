package com.ejemplo.graphql_springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Servicio para probar la conexión a la base de datos al iniciar la aplicación
 */
@Component
public class TestConnectionService implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TestConnectionService.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("✅ Conexión exitosa a la base de datos PostgreSQL");
            logger.info("📊 URL: {}", connection.getMetaData().getURL());
            logger.info("👤 Usuario: {}", connection.getMetaData().getUserName());
            logger.info("🏷️  Producto: {}", connection.getMetaData().getDatabaseProductName());
            logger.info("📋 Versión: {}", connection.getMetaData().getDatabaseProductVersion());
        } catch (Exception e) {
            logger.error("❌ Error al conectar con la base de datos: {}", e.getMessage());
            logger.error("🔧 Verifica la configuración de conexión y que la base de datos esté disponible");
        }
    }
}