package org.fabasoad.db.pojo;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Yevhen Fabizhevskyi
 * @date 26.11.2016.
 */
public interface PojoProperties {
    enum References {
        ID("SR_ID", "id"), TITLE("SR_TITLE", "title"), FILE_NAME("SR_FILE_NAME", "src");

        public String DB;
        public String DTO;

        References(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_REFERENCES";
    }
}
