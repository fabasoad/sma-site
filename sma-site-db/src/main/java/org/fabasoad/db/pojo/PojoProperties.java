package org.fabasoad.db.pojo;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Yevhen Fabizhevskyi
 * @date 28.12.2016.
 */
public interface PojoProperties {

    enum ApplicationForms {
        ID("SAF_ID", "id"), FILE_NAME("SAF_FILE_NAME", "file-name");

        public String DB;
        public String DTO;

        ApplicationForms(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_APPLICATION_FORMS";
    }

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
    enum News {
        ID("SN_ID", "id" ) , TITLE("SN_TITLE", "title"), BODY("SN_BODY", "body"), CREATION_DATE("SN_CREATION_DATE", "creation-date");

        public String DB;
        public String DTO;

        News(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_NEWS";
    }

    enum Vacancies {
        ID("SV_ID", "id"), RANK("SV_RANK", "rank"), VESSEL_TYPE("SV_VESSEL_TYPE", "vessel-type"), JOINING_DATE("SV_JOINING_DATE", "joining-date"),
        CONTRACT_DURATION("SV_CONTRACT_DURATION", "contract-duration"), NATIONALITY("SV_NATIONALITY", "nationality"), WAGE("SV_WAGE", "wage"),
        DESCRIPTION("SV_DESCRIPTION", "description");

        public String DB;
        public String DTO;

        Vacancies(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_VACANCIES";
    }

    enum Contacts {
        PROP_NAME("SP_PROP_NAME", "prop-name"), PROP_VALUE("SP_PROP_VALUE", "prop-value");

        public String DB;
        public String DTO;

        Contacts(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

         public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_PARAMS";
    }

    enum Users {
        ID("SU_ID", "id"), EMAIL("SU_EMAIL", "email"), PASSWORD("SU_PASSWORD", "password"), SECURITY_SCHEMA_ID("SU_SECURITY_SCHEMA_ID", "security-schema-id");

        public String DB;
        public String DTO;

        Users(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_USERS";
    }

    enum SecuritySchemas {
        ID("SSS_ID", "id"), NAME("SSS_NAME", "name");

        public String DB;
        public String DTO;

        SecuritySchemas(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_SECURITY_SCHEMAS";
    }

    enum UserRoles {
        ID("SUR_ID", "id"), NAME("SUR_NAME", "name");

        public String DB;
        public String DTO;

        UserRoles(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_USER_ROLES";
    }

    enum UsersRolesRelations {
        USER_ID("SURR_USER_ID", "user-id"), ROLE_ID("SURR_ROLE_ID", "role-id");

        public String DB;
        public String DTO;

        UsersRolesRelations(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public final static String TABLE_NAME = "SMA_USERS_ROLES_RELATIONS";
    }
}
