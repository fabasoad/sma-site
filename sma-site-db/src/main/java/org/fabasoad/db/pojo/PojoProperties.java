package org.fabasoad.db.pojo;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Yevhen Fabizhevskyi
 * @date 28.12.2016.
 */
public interface PojoProperties {

    enum ApplicationForms {
        ID("SAF_ID", "id"),
        SENDER_NAME("SAF_SENDER_NAME", "sender-name"),
        FILE_NAME("SAF_FILE_NAME", "file-name") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        };

        public String DB;
        public String DTO;

        ApplicationForms(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<ApplicationForms> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String TABLE_NAME = "SMA_APPLICATION_FORMS";
    }

    enum References {
        ID("SR_ID", "id"),
        TITLE("SR_TITLE", "title"),
        FILE_NAME("SR_FILE_NAME", "src") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        };

        public String DB;
        public String DTO;

        References(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<References> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String TABLE_NAME = "SMA_REFERENCES";
    }

    enum News {
        ID("SN_ID", "id"),
        TITLE("SN_TITLE", "title") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        }, BODY("SN_BODY", "body") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        },
        CREATION_DATE("SN_CREATION_DATE", "creation-date");

        public String DB;
        public String DTO;

        News(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<News> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String TABLE_NAME = "SMA_NEWS";
    }

    enum Vacancies {
        ID("SV_ID", "id"),
        RANK("SV_RANK", "rank") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        },
        VESSEL_TYPE("SV_VESSEL_TYPE", "vessel-type"),
        JOINING_DATE("SV_JOINING_DATE", "joining-date") {
            @Override
            public boolean isValid(String value) {
                if (StringUtils.isEmpty(value)) {
                    return false;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                sdf.setLenient(false);

                try {
                    sdf.parse(value);
                } catch (ParseException e) {
                    return false;
                }

                return true;
            }
        },
        CONTRACT_DURATION("SV_CONTRACT_DURATION", "contract-duration") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        },
        NATIONALITY("SV_NATIONALITY", "nationality") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        },
        WAGE("SV_WAGE", "wage") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        },
        DESCRIPTION("SV_DESCRIPTION", "description") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        };

        public String DB;
        public String DTO;

        Vacancies(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<Vacancies> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String TABLE_NAME = "SMA_VACANCIES";
    }

    enum Contacts {
        PROP_NAME("SP_PROP_NAME", "prop-name"),
        PROP_VALUE("SP_PROP_VALUE", "prop-value") {
            @Override
            public boolean isValid(String value) {
                return StringUtils.isNotEmpty(value);
            }
        };

        public String DB;
        public String DTO;

        Contacts(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<Contacts> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String BODY_KEY = "SMA_CONTACTS_BODY";
        public final static String TABLE_NAME = "SMA_PARAMS";
    }

    enum Users {
        ID("SU_ID", "id"),
        EMAIL("SU_EMAIL", "email"),
        PASSWORD("SU_PASSWORD", "password"),
        SECURITY_SCHEMA_ID("SU_SECURITY_SCHEMA_ID", "security-schema-id");

        public String DB;
        public String DTO;

        Users(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<Users> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
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

        public static Optional<SecuritySchemas> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
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

        public static Optional<UserRoles> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
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

        public static Optional<UsersRolesRelations> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public boolean isValid(String value) {
            return true;
        }

        public final static String TABLE_NAME = "SMA_USERS_ROLES_RELATIONS";
    }
}
