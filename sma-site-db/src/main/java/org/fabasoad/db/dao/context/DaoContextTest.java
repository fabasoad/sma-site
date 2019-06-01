package org.fabasoad.db.dao.context;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DaoContextTest implements DaoContext {

    @Override
    public Path getPropertiesFilePath() {
        return Paths.get(System.getProperty("user.dir"), "sma-site-db", "sma-db-setup.properties");
    }
}