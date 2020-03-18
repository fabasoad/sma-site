package io.github.fabasoad.rest.config;

import io.github.fabasoad.auth.AuthenticateResource;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigUtils {

    private final static String CRYPTO_SALT_PROPERTY_NAME = "crypto-salt";
    private final static Properties config = new Properties();
    static {
        try (final InputStream inputStream =
                 AuthenticateResource.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                log.error("Failed to load properties file.");
            } else {
                config.load(inputStream);
            }
        } catch (IOException e) {
            log.error("Failed to load properties file.", e);
        }
    }

    public static String getCryptoSalt() {
        return config.getProperty(CRYPTO_SALT_PROPERTY_NAME);
    }
}
