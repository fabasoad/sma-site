package io.github.fabasoad.db.dao.context;

import java.nio.file.Path;

public class DaoContextTest implements DaoContext {

    @Override
    public Path getPropertiesFilePath(final Path root) {
        return root.resolve("sma-db-setup.properties");
    }
}