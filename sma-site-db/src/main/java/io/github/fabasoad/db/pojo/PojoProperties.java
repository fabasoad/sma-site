package io.github.fabasoad.db.pojo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.routines.EmailValidator;
import io.github.fabasoad.db.exceptions.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public interface PojoProperties {

    enum ApplicationForms {
        ID("SAF_ID", "id"),
        SENDER_NAME("SAF_SENDER_NAME", "sender-name"),
        FILE_NAME("SAF_FILE_NAME", "file-name") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_APPLICATION_FORMS";
    }

    enum References {
        ID("SR_ID", "id"),
        TITLE("SR_TITLE", "title"),
        FILE_NAME("SR_FILE_NAME", "src") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_REFERENCES";
    }

    enum News {
        ID("SN_ID", "id"),
        TITLE("SN_TITLE", "title") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        }, BODY("SN_BODY", "body") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_NEWS";
    }

    enum Vacancies {
        ID("SV_ID", "id"),
        RANK("SV_RANK", "rank") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        VESSEL_TYPE("SV_VESSEL_TYPE", "vessel-type"),
        JOINING_DATE("SV_JOINING_DATE", "joining-date") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
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
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        NATIONALITY("SV_NATIONALITY", "nationality") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        WAGE("SV_WAGE", "wage") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        },
        DESCRIPTION("SV_DESCRIPTION", "description") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_VACANCIES";
    }

    enum Params {
        PROP_NAME("SP_PROP_NAME", "prop-name"),
        PROP_VALUE("SP_PROP_VALUE", "body") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        };

        public String DB;
        public String DTO;

        Params(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<Params> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_PARAMS";
    }

    enum Users {
        ID("SU_ID", "id") {

            @Override
            public void validateBeforeDelete(Object id) throws ValidationException {
                if (Objects.equals(SUPER_USER_ID, id)) {
                    throw new ValidationException("This user cannot be removed");
                }
            }
        },
        EMAIL("SU_EMAIL", "email") {

            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }

                if (!EmailValidator.getInstance().isValid(value)) {
                    throw new ValidationException(String.format("'%s' is invalid", this.DTO));
                }
            }

            @Override
            public void validateBeforeUpdate(Object id, String value) throws ValidationException {
                if (Objects.equals(SUPER_USER_ID, id)) {
                    throw new ValidationException("This user cannot be updated");
                }

                if (!EmailValidator.getInstance().isValid(value)) {
                    throw new ValidationException(String.format("'%s' is invalid", this.DTO));
                }
            }
        },
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_USERS";
        public final static int SUPER_USER_ID = 1;
    }

    enum UserRoles {
        ID("SUR_ID", "id"), NAME("SUR_NAME", "role-name");

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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_USER_ROLES";

        public interface Values {
            String ADMIN = "admin";
            String BASIC = "basic";
        }
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

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_USERS_ROLES_RELATIONS";
    }

    enum CarouselImages {
        ID("SCI_ID", "id"),
        TITLE("SCI_TITLE", "title"),
        FILE_NAME("SCI_FILE_NAME", "src") {
            @Override
            public void validateBeforeCreate(String value) throws ValidationException {
                if (StringUtils.isEmpty(value)) {
                    throw new ValidationException(String.format("'%s' field cannot be empty", this.DTO));
                }
            }
        };

        public String DB;
        public String DTO;

        CarouselImages(String dbProperty, String dtoProperty) {
            this.DB = dbProperty;
            this.DTO = dtoProperty;
        }

        public static Optional<String> fromDto(String dtoProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DTO, dtoProp)).map(v -> v.DB).findAny();
        }

        public static Optional<CarouselImages> fromDb(String dbProp) {
            return Stream.of(values()).filter(v -> Objects.equals(v.DB, dbProp)).findAny();
        }

        public void validateBeforeCreate(String value) throws ValidationException {
        }

        public void validateBeforeUpdate(Object id, String value) throws ValidationException {
        }

        public void validateBeforeDelete(Object id) throws ValidationException {
        }

        public final static String TABLE_NAME = "SMA_CAROUSEL_IMAGES";
    }
}
