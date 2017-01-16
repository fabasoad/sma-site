package org.fabasoad.db.pojo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.fabasoad.db.exceptions.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_APPLICATION_FORMS";
    }

    enum References {
        ID("SR_ID", "id"),
        TITLE("SR_TITLE", "title"),
        FILE_NAME("SR_FILE_NAME", "src") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_REFERENCES";
    }

    enum News {
        ID("SN_ID", "id"),
        TITLE("SN_TITLE", "title") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        }, BODY("SN_BODY", "body") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_NEWS";
    }

    enum Vacancies {
        ID("SV_ID", "id"),
        RANK("SV_RANK", "rank") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        VESSEL_TYPE("SV_VESSEL_TYPE", "vessel-type"),
        JOINING_DATE("SV_JOINING_DATE", "joining-date") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);

                Date date;
                try {
                    date = sdf.parse(value);
                } catch (ParseException e) {
                    throw new ValidationException(String.format("'%s' field is invalid. Valid format is \"YYYY-MM-DD\"", this.DTO));
                }

                if (DateUtils.truncatedCompareTo(date, new Date(), Calendar.DAY_OF_MONTH) < 0) {
                    throw new ValidationException(String.format("'%s' field cannot be less than today's date", this.DTO));
                }
            }
        },
        CONTRACT_DURATION("SV_CONTRACT_DURATION", "contract-duration") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        NATIONALITY("SV_NATIONALITY", "nationality") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        WAGE("SV_WAGE", "wage") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        DESCRIPTION("SV_DESCRIPTION", "description") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_VACANCIES";
    }

    enum Contacts {
        PROP_NAME("SP_PROP_NAME", "prop-name"),
        PROP_VALUE("SP_PROP_VALUE", "prop-value") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
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

        public void validate(String value) throws ValidationException {
        }

        public final static String BODY_KEY = "SMA_CONTACTS_BODY";
        public final static String TABLE_NAME = "SMA_PARAMS";
    }

    enum Main {
        PROP_NAME("SP_PROP_NAME", "prop-name"),
        PROP_VALUE("SP_PROP_VALUE", "prop-value") {
            @Override
            public void validate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        };

        public String DB;
        public String DTO;

        Main(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<Main> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public void validate(String value) throws ValidationException {
        }

        public final static String BODY_KEY = "SMA_MAIN_BODY";
        public final static String TABLE_NAME = "SMA_PARAMS";
    }

    enum Users {
        ID("SU_ID", "id"),
        EMAIL("SU_EMAIL", "email"),
        PASSWORD("SU_PASSWORD", "password");

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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_USERS";
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

        public void validate(String value) throws ValidationException {
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

        public void validate(String value) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_USERS_ROLES_RELATIONS";
    }
}
