package io.github.fabasoad.db.dao.context;

import java.nio.file.Path;

public interface DaoContext {

    Path getPropertiesFilePath(Path root);
}
