CREATE TABLE IF NOT EXISTS SMA_PARAMS (
  SP_PROP_NAME TEXT PRIMARY KEY,
  SP_PROP_VALUE TEXT NOT NULL
);

INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_MAIN_BODY', '');
INSERT OR IGNORE INTO SMA_PARAMS VALUES ('SMA_CONTACTS_BODY', '');

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
  SR_TITLE TEXT NOT NULL,
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

CREATE TABLE IF NOT EXISTS SMA_SECURITY_SCHEMAS (
  SSS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SSS_NAME TEXT NOT NULL
);

INSERT OR IGNORE INTO SMA_SECURITY_SCHEMAS (SSS_NAME) VALUES ('Basic');

CREATE TABLE IF NOT EXISTS SMA_USERS (
  SU_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SU_EMAIL TEXT NOT NULL,
  SU_PASSWORD TEXT NOT NULL,
  SU_SECURITY_SCHEMA_ID INTEGER,
  FOREIGN KEY (SU_SECURITY_SCHEMA_ID) REFERENCES SMA_SECURITY_SCHEMAS (SSS_ID)
);

INSERT OR IGNORE INTO SMA_USERS (SU_EMAIL, SU_PASSWORD, SU_SECURITY_SCHEMA_ID)
  SELECT 'bulas.business@gmail.com', 'YnVsYXMuYnVzaW5lc3NAZ21haWwuY29tOjJxYXpYU1dAMg==', SSS_ID
  FROM SMA_SECURITY_SCHEMAS WHERE SSS_NAME = 'Basic';

CREATE TABLE IF NOT EXISTS SMA_USERS_ROLES_RELATIONS (
  SURR_USER_ID INTEGER,
  SURR_ROLE_ID INTEGER,
  PRIMARY KEY (SURR_USER_ID, SURR_ROLE_ID),
  FOREIGN KEY (SURR_USER_ID) REFERENCES SMA_USERS (SU_ID),
  FOREIGN KEY (SURR_ROLE_ID) REFERENCES SMA_USER_ROLES (SUR_ID)
);

INSERT OR IGNORE INTO SMA_USERS_ROLES_RELATIONS (SURR_USER_ID, SURR_ROLE_ID)
  SELECT SU_ID, SUR_ID FROM SMA_USERS, SMA_USER_ROLES
  WHERE SMA_USERS.SU_EMAIL IN ('bulas.business@gmail.com') AND SMA_USER_ROLES.SUR_NAME = 'admin';