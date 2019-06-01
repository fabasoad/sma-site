package org.fabasoad.db.dao.context;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DaoContextImpl implements DaoContext {

    @Override
    public Path getPropertiesFilePath() {
        return Paths.get(System.getProperty("user.dir"), "sma-db-setup.properties");
    }
}
