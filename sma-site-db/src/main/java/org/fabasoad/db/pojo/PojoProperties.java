package org.fabasoad.db.pojo;

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

        public final static String TABLE_NAME = "SMA_REFERENCES";
    }
}
