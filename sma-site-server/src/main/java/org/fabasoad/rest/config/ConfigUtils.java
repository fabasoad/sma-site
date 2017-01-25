package org.fabasoad.rest.config;

import org.fabasoad.auth.AuthenticateResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.fabasoad.api.Logger.getLogger;

/**
 * @author efabizhevsky
 * @date 1/25/2017.
 */
public class ConfigUtils {

    private static String CRYPTO_SALT_PROPERTY_NAME = "crypto-salt";
    private static Properties config = new Properties();
    static {
        InputStream inputStream =
                AuthenticateResource.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            config.load(inputStream);
        } catch (IOException e) {
            getLogger().error(AuthenticateResource.class, e.getMessage());
        }
    }

    public static String getCryptoSalt() {
        return config.getProperty(CRYPTO_SALT_PROPERTY_NAME);
    }
}
