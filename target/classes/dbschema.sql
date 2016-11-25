DROP TABLE IF EXISTS CONTACTS_CONTACT_PERSON;
DROP TABLE IF EXISTS D_CONTACT_TYPE;
DROP TABLE IF EXISTS CONTACT_PERSON;
DROP TABLE IF EXISTS COMPANY;
DROP TABLE IF EXISTS AUTHORITIES;
DROP TABLE IF EXISTS STRUCTURE;
DROP TABLE IF EXISTS STENCIL;
DROP TABLE IF EXISTS D_DUCT;


CREATE TABLE IF NOT EXISTS COMPANY(
  ID_COMPANY SERIAL NOT NULL UNIQUE primary key,
  COMPANY_NAME VARCHAR(45) NOT NULL ,
  TELEPHONE VARCHAR(20) NOT NULL ,
  ADDRESS VARCHAR(200) NOT NULL
);


CREATE TABLE IF NOT EXISTS AUTHORITIES (
  ID_ROLE SERIAL NOT NULL UNIQUE primary key,
  AUTHORITY VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS CONTACT_PERSON (
  ID_CONTACT_PERSON   SERIAL     NOT NULL UNIQUE primary key,
  USERNAME VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  ENABLED  BOOLEAN      NOT NULL,
  FIRSTNAME VARCHAR(255) NOT NULL,
  SECONDNAME VARCHAR(255) NOT NULL,
  DEPARTMENT VARCHAR(255) NOT NULL,
  CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW(),
  LAST_MODIFY TIMESTAMP NOT NULL DEFAULT NOW(),
  CREATOR VARCHAR(255) NOT NULL,
  PRIORITY INT NOT NULL DEFAULT 10,
  ID_COMPANY INT NOT NULL,
  ID_ROLE INT NOT NULL DEFAULT 1,
  CONSTRAINT FK_COMPANY_CONTACT_PERSON FOREIGN KEY (ID_COMPANY) REFERENCES COMPANY(ID_COMPANY),
  CONSTRAINT FK_AUTHORITY_CONTACT_PERSON FOREIGN KEY (ID_ROLE) REFERENCES AUTHORITIES(ID_ROLE)
);


CREATE TABLE D_CONTACT_TYPE(
  ID_CONTACT_TYPE SERIAL NOT NULL UNIQUE primary key,
  NAME VARCHAR(45) NOT NULL
);


CREATE TABLE CONTACTS_CONTACT_PERSON(
  ID_CONTACT_CONTACT_PERSON SERIAL NOT NULL UNIQUE primary key,
  ID_CONTACT_PERSON INT NOT NULL ,
  ID_COMPANY INT NOT NULL ,
  ID_CONTACT_TYPE INT NOT NULL,
  PRIORITY_CALLBACK VARCHAR(255) NOT NULL,
  CONSTRAINT FK_COMPANY_CCP FOREIGN KEY (ID_COMPANY) REFERENCES COMPANY(ID_COMPANY),
  CONSTRAINT FK_PERSON_CPP FOREIGN KEY (ID_CONTACT_PERSON) REFERENCES CONTACT_PERSON(ID_CONTACT_PERSON),
  CONSTRAINT FK_TYPE_CCP FOREIGN KEY (ID_CONTACT_TYPE) REFERENCES D_CONTACT_TYPE(ID_CONTACT_TYPE)
);

CREATE TABLE D_DUCT(
  ID_D_DUCT SERIAL NOT NULL UNIQUE primary key,
  NAME_DUCT VARCHAR(255),
  DUCT_PRIORITY INT
);
CREATE TABLE STENCIL(
  ID_STENCIL SERIAL NOT NULL UNIQUE primary key,
  ID_D_DUCT INT NOT NULL ,
  STENCIL_ENTITY VARCHAR(255),
  CONSTRAINT FK_D_DUCT_STENCIL FOREIGN KEY (ID_D_DUCT) REFERENCES D_DUCT(ID_D_DUCT)
);

CREATE TABLE STRUCTURE(
  ID_STRUCTURE SERIAL NOT NULL UNIQUE primary key,
  ID_COMPANY INT NOT NULL,
  ID_STENCIL INT NOT NULL,
  CONSTRAINT FK_STENCIL_STRUCTURE FOREIGN KEY (ID_STENCIL) REFERENCES STENCIL(ID_STENCIL)
);

DROP TABLE IF EXISTS Message;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Message (
  idMessage serial NOT NULL UNIQUE primary key,
  priority integer DEFAULT 10,
  frequence timestamp DEFAULT NULL,
  departureTime timestamp DEFAULT CURRENT_TIMESTAMP,
  relevantTime timestamp DEFAULT  NULL,
  delay timestamp DEFAULT CURRENT_TIMESTAMP,
  duct TEXT[] DEFAULT NULL,
  message varchar(45),
  address varchar(45) DEFAULT NULL);
