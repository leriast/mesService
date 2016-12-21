DROP TABLE IF EXISTS CONTACTS_CONTACT_PERSON CASCADE;
DROP TABLE IF EXISTS D_CONTACT_TYPE CASCADE;
DROP TABLE IF EXISTS MSG CASCADE;
DROP TABLE IF EXISTS TASK CASCADE;
DROP TABLE IF EXISTS LANGUAGE CASCADE;
DROP TABLE IF EXISTS CONTACT_PERSON CASCADE;
DROP TABLE IF EXISTS COMPANY CASCADE;
DROP TABLE IF EXISTS AUTHORITIES CASCADE;
DROP TABLE IF EXISTS STENCIL CASCADE;
DROP TABLE IF EXISTS STRUCTURE CASCADE;
DROP TABLE IF EXISTS D_LIMITATIONS CASCADE;
DROP TABLE IF EXISTS D_DUCT CASCADE;


CREATE TABLE IF NOT EXISTS COMPANY (
  ID_COMPANY   SERIAL       NOT NULL UNIQUE PRIMARY KEY,
  COMPANY_NAME VARCHAR(45)  NOT NULL,
  TELEPHONE    VARCHAR(20)  NOT NULL,
  ADDRESS      VARCHAR(200) NOT NULL
);
CREATE TABLE LANGUAGE (
  ID_LANGUAGE SERIAL      NOT NULL PRIMARY KEY,
  NAME        VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
  ID_ROLE   SERIAL       NOT NULL UNIQUE PRIMARY KEY,
  AUTHORITY VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS CONTACT_PERSON (
  ID_CONTACT_PERSON SERIAL       NOT NULL PRIMARY KEY,
  USERNAME          VARCHAR(255) NOT NULL,
  PASSWORD          VARCHAR(255) NOT NULL,
  ENABLED           BOOLEAN      NOT NULL,
  FIRSTNAME         VARCHAR(255) NOT NULL,
  SECONDNAME        VARCHAR(255) NOT NULL,
  DEPARTMENT        VARCHAR(255) NOT NULL,
  CREATE_DATE       TIMESTAMP    NOT NULL DEFAULT NOW(),
  LAST_MODIFY       TIMESTAMP    NOT NULL DEFAULT NOW(),
  CREATOR           VARCHAR(255) NOT NULL,
  PRIORITY          INT          NOT NULL DEFAULT 10,
  ID_COMPANY        INT          NOT NULL,
  ID_ROLE           INT          NOT NULL DEFAULT 1,
  CONSTRAINT FK_COMPANY_CONTACT_PERSON FOREIGN KEY (ID_COMPANY) REFERENCES COMPANY (ID_COMPANY),
  CONSTRAINT FK_AUTHORITY_CONTACT_PERSON FOREIGN KEY (ID_ROLE) REFERENCES AUTHORITIES (ID_ROLE)
  --   ,  UNIQUE (USERNAME)
);


CREATE TABLE D_CONTACT_TYPE (
  ID_CONTACT_TYPE SERIAL      NOT NULL UNIQUE PRIMARY KEY,
  NAME            VARCHAR(45) NOT NULL
);


CREATE TABLE CONTACTS_CONTACT_PERSON (
  ID_CONTACT_CONTACT_PERSON SERIAL       NOT NULL UNIQUE PRIMARY KEY,
  ID_CONTACT_PERSON         INT          NOT NULL,
  ID_COMPANY                INT          NOT NULL,
  ID_CONTACT_TYPE           INT          NOT NULL,
  PRIORITY_CALLBACK         VARCHAR(255) NOT NULL,
  CONSTRAINT FK_COMPANY_CCP FOREIGN KEY (ID_COMPANY) REFERENCES COMPANY (ID_COMPANY),
  CONSTRAINT FK_PERSON_CPP FOREIGN KEY (ID_CONTACT_PERSON) REFERENCES CONTACT_PERSON (ID_CONTACT_PERSON),
  CONSTRAINT FK_TYPE_CCP FOREIGN KEY (ID_CONTACT_TYPE) REFERENCES D_CONTACT_TYPE (ID_CONTACT_TYPE)
);

CREATE TABLE D_DUCT (
  ID_D_DUCT     SERIAL NOT NULL UNIQUE PRIMARY KEY,
  NAME_DUCT     VARCHAR(255),
  DUCT_PRIORITY INT
);


CREATE TABLE STRUCTURE (
  ID_STRUCTURE SERIAL NOT NULL UNIQUE PRIMARY KEY,
  NAME VARCHAR(50) NOT NULL,
  ID_COMPANY   INT    NOT NULL,
  ID_LANGUAGE  INT    NOT NULL,
  ALGORITM          VARCHAR(1000) NOT NULL,
  PRIORITY          INT     NOT NULL,
  PARAMS            VARCHAR(100000),
  CONSTRAINT FK_STENCIL_COMPANY FOREIGN KEY (ID_COMPANY) REFERENCES COMPANY (ID_COMPANY),
  CONSTRAINT FK_STENCIL_LANGUAGE FOREIGN KEY (ID_LANGUAGE) REFERENCES LANGUAGE (ID_LANGUAGE)
  /* ID_STENCIL INT NOT NULL,
   ID_D_DUCT INT NOT NULL ,
   CONSTRAINT FK_STENCIL_STRUCTURE FOREIGN KEY (ID_STENCIL) REFERENCES STENCIL(ID_STENCIL),
     CONSTRAINT FK_D_DUCT_STRUCTURE FOREIGN KEY (ID_D_DUCT) REFERENCES STENCIL(ID_D_DUCT)*/
);
CREATE TABLE STENCIL (
  ID_STENCIL     SERIAL NOT NULL UNIQUE PRIMARY KEY,
  NAME VARCHAR(50) NOT NULL,
  ID_D_DUCT      INT    NOT NULL,
  ID_STRUCTURE   INT    NOT NULL,
  STENCIL_ENTITY VARCHAR(255),
  CONSTRAINT FK_D_DUCT_STENCIL FOREIGN KEY (ID_D_DUCT) REFERENCES D_DUCT (ID_D_DUCT),
  CONSTRAINT FK_D_STRUCTURE_STENCIL FOREIGN KEY (ID_STRUCTURE) REFERENCES STRUCTURE (ID_STRUCTURE)
);


CREATE TABLE TASK (
  ID_TASK           SERIAL  NOT NULL PRIMARY KEY,
  ID_CONTACT_PERSON INT     NOT NULL,
  ID_STRUCTURE      INT     NOT NULL,
  ALGORITM          TEXT [] NOT NULL,
  VARIABLES         BYTEA DEFAULT NULL,
  PRIORITY          INT     NOT NULL,
  ID_LANGUAGE       INT     NOT NULL,
  PARAMS            VARCHAR(100000),
  CONSTRAINT FK_STRUCTURE_TASK FOREIGN KEY (ID_STRUCTURE) REFERENCES STRUCTURE (ID_STRUCTURE),
  CONSTRAINT FK_CONTACT_PERSON_TASK FOREIGN KEY (ID_CONTACT_PERSON) REFERENCES CONTACT_PERSON (ID_CONTACT_PERSON),
  CONSTRAINT FK_LANGUAGE_TASK FOREIGN KEY (ID_LANGUAGE) REFERENCES LANGUAGE (ID_LANGUAGE)
);
-- ALTER DATABASE test_db SET bytea_output TO 'escape';
CREATE TABLE D_LIMITATIONS (
  ID         SERIAL NOT NULL UNIQUE PRIMARY KEY,
  LIMITATION INT    NOT NULL,
  ID_D_DUCT  INT    NOT NULL,
  CONSTRAINT FK_LIMITATION_DUCT FOREIGN KEY (ID_D_DUCT) REFERENCES D_DUCT (ID_D_DUCT)
);




DROP TABLE IF EXISTS common_message CASCADE ;
CREATE TABLE common_message (
  idMessage     SERIAL NOT NULL  PRIMARY KEY,
  priority      INTEGER          DEFAULT 10,
  departureTime TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  relevantTime  TIMESTAMP        DEFAULT NULL,
  duct          TEXT []          DEFAULT NULL,
  message       VARCHAR(1000000),
  stencil       VARCHAR(1000000),
  address       VARCHAR(1000000) DEFAULT NULL,
  status        INT    NOT NULL,
  id_task       INT    NOT NULL,
  params        VARCHAR(10000000),
  statistic     VARCHAR(10000000)
);

DROP TABLE IF EXISTS Message;
CREATE TABLE Message (
  idMessage     SERIAL NOT NULL  PRIMARY KEY,
  priority      INTEGER          DEFAULT 10,
  departureTime TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  relevantTime  TIMESTAMP        DEFAULT NULL,
  duct          TEXT []          DEFAULT NULL,
  message       VARCHAR(1000000),
  stencil       VARCHAR(1000000),
  address       VARCHAR(1000000) DEFAULT NULL,
  status        INT    NOT NULL,
  id_task       INT    NOT NULL,
  params        VARCHAR(10000000),
  statistic     VARCHAR(10000000),
  next_duct     VARCHAR(33),
  CONSTRAINT fk_Mess_task FOREIGN KEY (id_task) REFERENCES TASK (id_task)
)INHERITS (common_message);



DROP TABLE IF EXISTS sent_message;
CREATE TABLE sent_message (
  idMessage     SERIAL NOT NULL  PRIMARY KEY,
  priority      INTEGER          DEFAULT 10,
  departureTime TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  relevantTime  TIMESTAMP        DEFAULT NULL,
  duct          TEXT []          DEFAULT NULL,
  message       VARCHAR(1000000),
  stencil       VARCHAR(1000000),
  address       VARCHAR(1000000) DEFAULT NULL,
  status        INT    NOT NULL,
  id_task       integer    NOT NULL,
  params        VARCHAR(10000000),
  statistic     VARCHAR(10000000),
  next_duct     VARCHAR(33),
  CONSTRAINT fk_Mess_task FOREIGN KEY (id_task) REFERENCES TASK (id_task)
)INHERITS (common_message);

drop table if exists status;
create table status(
  id serial not null unique,
  number int
);
