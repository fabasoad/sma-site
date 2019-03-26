package org.fabasoad.db.dao.context;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DaoContextImpl implements DaoContext {

    @Override
    public Path getPropertiesFilePath() {
        return FileSystems.getDefault().getPath("sma-db-setup.properties").normalize().toAbsolutePath();
    }
}
