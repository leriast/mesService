DROP TABLE IF EXISTS AUTHORITIES;
DROP TABLE IF EXISTS Phonebook;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS MESSAGE1;


CREATE TABLE IF NOT EXISTS USERS (
  IDUSER   SERIAL       NOT NULL UNIQUE /*AUTO_INCREMENT*/,
  USERNAME VARCHAR(255) NOT NULL UNIQUE,
  PASSWORD VARCHAR(255) NOT NULL,
  ENABLED  BOOLEAN      NOT NULL

);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
  USERNAME  VARCHAR(255) NOT NULL,
  AUTHORITY VARCHAR(255) NOT NULL,
  CONSTRAINT FK_AUTHORITIES_USER FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);
;

CREATE TABLE IF NOT EXISTS MESSAGE1 (
  IDUSER   SERIAL       NOT NULL UNIQUE /*AUTO_INCREMENT*/,
  USERNAME VARCHAR(255) NOT NULL PRIMARY KEY,
  PASSWORD VARCHAR(255) NOT NULL,
  ENABLED  BOOLEAN      NOT NULL

);

DROP TABLE IF EXISTS Message;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Message (
  idMessage serial NOT NULL UNIQUE ,
  priority integer DEFAULT 10,
  frequence timestamp DEFAULT NULL,
  departureTime timestamp DEFAULT CURRENT_TIMESTAMP,
  relevantTime timestamp DEFAULT  NULL,
  delay timestamp DEFAULT CURRENT_TIMESTAMP,
  duct TEXT[] DEFAULT NULL,
  message varchar(45),
  address varchar(45) DEFAULT NULL
 /* `idTask` int(11) NOT NULL,*/
  /*PRIMARY KEY (idMessage)*/
  /*KEY `fk_idTask_1_idx` (`idTask`),
  CONSTRAINT `fk_idTask_1` FOREIGN KEY (`idTask`) REFERENCES `Tasks` (`idTasks`) ON DELETE NO ACTION ON UPDATE NO ACTION*/
) /*ENGINE=InnoDB DEFAULT CHARSET=latin1*/;



/*
DROP TABLE IF EXISTS M_to_M CASCADE ;
DROP TABLE IF EXISTS stencil CASCADE ;
DROP TABLE IF EXISTS duct CASCADE ;
DROP TABLE IF EXISTS language CASCADE ;
DROP TABLE IF EXISTS message CASCADE ;
DROP TABLE IF EXISTS Task CASCADE;
DROP TABLE IF EXISTS event_stencil cascade;
DROP TABLE IF EXISTS event CASCADE;
DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS autor CASCADE;
DROP TABLE IF EXISTS role_site CASCADE;
DROP TABLE IF EXISTS role_service CASCADE ;
CREATE TABLE "company" (
  "id_company"  SERIAL       NOT NULL,
  "name"        VARCHAR(255) NOT NULL,
  "is_active"   BOOLEAN      NOT NULL ,
  CONSTRAINT company_pk PRIMARY KEY ("id_company")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "autor" (
  "id_autor"        SERIAL       NOT NULL,
  "login"           VARCHAR(255) NOT NULL UNIQUE,
  "password"        VARCHAR(255) NOT NULL,
  "id_company"      INTEGER      NOT NULL,
  "is_active"       BOOLEAN      NOT NULL,
  "id_role_site"    INTEGER      NOT NULL,
  "id_role_service" INTEGER      NOT NULL,
  CONSTRAINT autor_pk PRIMARY KEY ("id_autor")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "role_service" (
  "id_role"     SERIAL       NOT NULL,
  "role_name"   VARCHAR(255) NOT NULL UNIQUE,
  "description" VARCHAR(255) NOT NULL,
  "priority"    INTEGER      NOT NULL,

  "is_active"   BOOLEAN      NOT NULL,
  CONSTRAINT role_service_pk PRIMARY KEY ("id_role")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "role_site" (
  "id_role_site" SERIAL       NOT NULL,
  "role_name"    VARCHAR(255) NOT NULL,
  "description"  VARCHAR(255) NOT NULL,
  "is_active"    BOOLEAN      NOT NULL,
  CONSTRAINT role_site_pk PRIMARY KEY ("id_role_site")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "event" (
  "id_event"   SERIAL       NOT NULL,
  "name_event" VARCHAR(255) NOT NULL,
  "is_active"  BOOLEAN      NOT NULL,
  "id_company" INTEGER      NOT NULL,
  CONSTRAINT event_pk PRIMARY KEY ("id_event")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "event_stencil" (
  "id_event_stencil" SERIAL   NOT NULL,
  "curfew"           TIMESTAMP NOT NULL,
  "priority"         INTEGER  NOT NULL,
  "id_event"         INTEGER  NOT NULL,
  "is_active"        BOOLEAN  NOT NULL,
  "duct"             text[]  NOT NULL,
  CONSTRAINT event_stencil_pk PRIMARY KEY ("id_event_stencil")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "stencil" (
  "id_stencil"       SERIAL  NOT NULL,
  "stencil_entity" bytea NOT NULL,
  "id_language"      INTEGER NOT NULL,
  "id_duct"          INTEGER NOT NULL,
  "id_company"       INTEGER NOT NULL,
  "id_event_stencil" INTEGER NOT NULL,
  "is_active"        BOOLEAN NOT NULL,
  CONSTRAINT stencil_pk PRIMARY KEY ("id_stencil")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "language" (
  "id_language" SERIAL       NOT NULL,
  "name"        VARCHAR(255) NOT NULL,
  "is_active"   BOOLEAN      NOT NULL,
  CONSTRAINT language_pk PRIMARY KEY ("id_language")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "duct" (
  "id_duct"   SERIAL       NOT NULL,
  "name"      VARCHAR(255) NOT NULL,
  "is_active" BOOLEAN      NOT NULL,
  CONSTRAINT duct_pk PRIMARY KEY ("id_duct")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "task" (
  "id_task"   SERIAL  NOT NULL,
  "priority"  INTEGER NOT NULL,
  "departure_time" TIMESTAMP NOT NULL,
  "relevant_time" TIMESTAMP NOT NULL,
  "frequence" VARCHAR NOT NULL,
  "id_event"  INTEGER NOT NULL,
  "id_autor"  INTEGER NOT NULL,
  CONSTRAINT task_pk PRIMARY KEY ("id_task")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "message" (
  "id_messqge" SERIAL       NOT NULL,
  "address"    VARCHAR(255) NOT NULL,
  "id_task"    INTEGER      NOT NULL,
  CONSTRAINT message_pk PRIMARY KEY ("id_messqge")
) WITH (
  OIDS= FALSE
);


CREATE TABLE "M_to_M" (
  "id"               SERIAL  NOT NULL,
  "id_event_stencil" INTEGER NOT NULL,
  "id_stencil"       INTEGER NOT NULL,
  CONSTRAINT M_to_M_pk PRIMARY KEY ("id")
) WITH (
  OIDS= FALSE
);*/

/*
ALTER TABLE "autor"
  ADD CONSTRAINT "autor_fk0" FOREIGN KEY ("id_company") REFERENCES "company" ("id_company");
ALTER TABLE "autor"
  ADD CONSTRAINT "autor_fk1" FOREIGN KEY ("id_role_site") REFERENCES "role_site" ("id_role_site");
ALTER TABLE "autor"
  ADD CONSTRAINT "autor_fk2" FOREIGN KEY ("id_role_service") REFERENCES "role_service" ("id_role");


ALTER TABLE "event"
  ADD CONSTRAINT "event_fk0" FOREIGN KEY ("id_company") REFERENCES "company" ("id_company");

ALTER TABLE "event_stencil"
  ADD CONSTRAINT "event_stencil_fk0" FOREIGN KEY ("id_event") REFERENCES "event" ("id_event");

ALTER TABLE "stencil"
  ADD CONSTRAINT "stencil_fk0" FOREIGN KEY ("id_language") REFERENCES "language" ("id_language");
ALTER TABLE "stencil"
  ADD CONSTRAINT "stencil_fk1" FOREIGN KEY ("id_duct") REFERENCES "duct" ("id_duct");


ALTER TABLE "task"
  ADD CONSTRAINT "task_fk0" FOREIGN KEY ("id_event") REFERENCES "event" ("id_event");
ALTER TABLE "task"
  ADD CONSTRAINT "task_fk1" FOREIGN KEY ("id_autor") REFERENCES "autor" ("id_autor");

ALTER TABLE "message"
  ADD CONSTRAINT "message_fk0" FOREIGN KEY ("id_task") REFERENCES "Task" ("id_task");

ALTER TABLE "M_to_M"
  ADD CONSTRAINT "M_to_M_fk0" FOREIGN KEY ("id_event_stencil") REFERENCES "event_stencil" ("id_event_stencil");
ALTER TABLE "M_to_M"
  ADD CONSTRAINT "M_to_M_fk1" FOREIGN KEY ("id_stencil") REFERENCES "stencil" ("id_stencil");
*/

/*
 #
 #
 # CREATE TABLE IF NOT EXISTS USERS (
 #   IDUSER INT NOT NULL UNIQUE AUTO_INCREMENT,
 #   USERNAME VARCHAR(255) NOT NULL PRIMARY KEY ,
 #   PASSWORD  VARCHAR(255) NOT NULL,
 #   ENABLED BOOLEAN NOT NULL
 #
 # );
 #
 #
 # CREATE TABLE IF NOT EXISTS AUTHORITIES(
 #   USERNAME VARCHAR(255) NOT NULL,
 #   AUTHORITY VARCHAR(255) NOT NULL,
 #   CONSTRAINT FK_AUTHORITIES_USER FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME));
 # ;
 #
 #
 # CREATE TABLE IF NOT EXISTS MESSAGE1 (
 #   IDUSER INT NOT NULL UNIQUE AUTO_INCREMENT,
 #   USERNAME VARCHAR(255) NOT NULL PRIMARY KEY ,
 #   PASSWORD  VARCHAR(255) NOT NULL,
 #   ENABLED BOOLEAN NOT NULL
 #
 # );


 #  DROP TABLE IF EXISTS AUTHORITIES CASCADE;
 #  DROP TABLE IF EXISTS USERS CASCADE;
 #  DROP TABLE IF EXISTS MESSAGE CASCADE;
 #  DROP TABLE IF EXISTS LOG CASCADE;*/