package ru.vsu.cs.selyutinrv.repository.impl.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    private static final String CONFIG_FILENAME = "application.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        String configPathStr = System.getProperty("config.path");
        Path configPath;

        if (configPathStr != null && !configPathStr.isBlank()) {
            configPath = Path.of(configPathStr);
        } else {
            configPath = Path.of(System.getProperty("user.dir"), "resources", CONFIG_FILENAME);
        }

        if (Files.exists(configPath) && Files.isReadable(configPath)) {
            try (InputStream input = Files.newInputStream(configPath)) {
                properties.load(input);
                System.out.println("✓ Configuration loaded from: " + configPath.toAbsolutePath());
                return;
            } catch (IOException e) {
                System.err.println("Error reading config file: " + configPath.toAbsolutePath());
                e.printStackTrace();
            }
        }

       try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME)) {
            if (input != null) {
                properties.load(input);
                System.out.println("✓ Configuration loaded from classpath: " + CONFIG_FILENAME);
                return;
            }
        } catch (IOException e) {
            System.err.println("Error reading config from classpath");
        }

        System.err.println("Config file not found. Using default properties.");
        setDefaultProperties();
    }

    private static void setDefaultProperties() {
        properties.setProperty("db.url", "jdbc:postgresql://localhost:5432/dealership");
        properties.setProperty("db.user", "postgres");
        properties.setProperty("db.password", "password");
        properties.setProperty("db.init.on.start", "true");
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}