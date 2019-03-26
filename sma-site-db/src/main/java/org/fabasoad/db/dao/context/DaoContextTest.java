package org.fabasoad.db.dao.context;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DaoContextTest implements DaoContext {

    @Override
    public Path getPropertiesFilePath() {
        return FileSystems.getDefault().getPath("..", "sma-db-setup.properties").normalize().toAbsolutePath();
    }
}