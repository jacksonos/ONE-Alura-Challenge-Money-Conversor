package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiActions {
    public String getApiKey() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            props.load(fis);
            String apiKey = props.getProperty("EXCHANGE_RATE_API_KEY");
            if (apiKey == null || apiKey.isEmpty()) {
                throw new RuntimeException("API key no encontrado en el archivo .env.");
            }
            return apiKey;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo .env: " + e.getMessage());
        }
    }
}
