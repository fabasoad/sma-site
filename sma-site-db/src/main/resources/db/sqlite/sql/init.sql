CREATE TABLE IF NOT EXISTS SMA_PARAMS (
  SP_PROP_NAME TEXT PRIMARY KEY,
  SP_PROP_VALUE TEXT NOT NULL
);

INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_MAIN_BODY', '');
INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_CONTACTS_BODY', '');
INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_COMPANY_NAME', 'Southern Marine Agency');
INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_FOOTER_YEAR', '2017');

CREATE TABLE IF NOT EXISTS SMA_NEWS (
  SN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SN_TITLE TEXT NOT NULL,
  SN_BODY TEXT NOT NULL,
  SN_CREATION_DATE DATETIME DEFAULT (DATETIME('now'))
);

CREATE TABLE IF NOT EXISTS SMA_VACANCIES (
  SV_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SV_RANK TEXT NOT NULL,
  SV_VESSEL_TYPE TEXT,
  SV_JOINING_DATE DATE NOT NULL,
  SV_CONTRACT_DURATION TEXT NOT NULL,
  SV_NATIONALITY TEXT NOT NULL,
  SV_WAGE TEXT NOT NULL,
  SV_DESCRIPTION TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS SMA_REFERENCES (
  SR_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SR_TITLE TEXT,
  SR_FILE_NAME TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS SMA_APPLICATION_FORMS (
  SAF_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SAF_SENDER_NAME TEXT,
  SAF_FILE_NAME TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS SMA_USER_ROLES (
  SUR_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SUR_NAME TEXT NOT NULL
);

INSERT OR IGNORE INTO SMA_USER_ROLES (SUR_NAME) VALUES ('admin');
INSERT OR IGNORE INTO SMA_USER_ROLES (SUR_NAME) VALUES ('basic');

CREATE TABLE IF NOT EXISTS SMA_USERS (
  SU_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SU_EMAIL TEXT NOT NULL,
  SU_PASSWORD TEXT,
  CONSTRAINT SU_EMAIL_UNIQUE UNIQUE (SU_EMAIL)
);

INSERT OR IGNORE INTO SMA_USERS (SU_EMAIL, SU_PASSWORD)
  VALUES ('bulas.business@gmail.com', '$2a$12$n7qQPvkwjpJLWNgISDf4YevMaq7lDge4iaoZTRmDTJcwzU09wsHPi');
INSERT OR IGNORE INTO SMA_USERS (SU_EMAIL, SU_PASSWORD)
  VALUES ('fabasoad@gmail.com', '$2a$12$n7qQPvkwjpJLWNgISDf4YevMaq7lDge4iaoZTRmDTJcwzU09wsHPi');

CREATE TABLE IF NOT EXISTS SMA_USERS_ROLES_RELATIONS (
  SURR_USER_ID INTEGER,
  SURR_ROLE_ID INTEGER,
  PRIMARY KEY (SURR_USER_ID, SURR_ROLE_ID),
  FOREIGN KEY (SURR_USER_ID) REFERENCES SMA_USERS (SU_ID),
  FOREIGN KEY (SURR_ROLE_ID) REFERENCES SMA_USER_ROLES (SUR_ID)
);

INSERT OR IGNORE INTO SMA_USERS_ROLES_RELATIONS (SURR_USER_ID, SURR_ROLE_ID)
  SELECT SU_ID, SUR_ID FROM SMA_USERS, SMA_USER_ROLES
  WHERE SMA_USERS.SU_EMAIL IN ('bulas.business@gmail.com', 'fabasoad@gmail.com') AND SMA_USER_ROLES.SUR_NAME = 'admin';

CREATE TABLE IF NOT EXISTS SMA_CAROUSEL_IMAGES (
  SCI_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SCI_TITLE TEXT,
  SCI_FILE_NAME TEXT NOT NULL
);