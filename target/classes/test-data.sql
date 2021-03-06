--COMPANY
INSERT INTO COMPANY (COMPANY_NAME,TELEPHONE,ADDRESS,PRIORITY) VALUES ('NPI','09309090909','LEPSE 6',1);
INSERT INTO COMPANY (COMPANY_NAME,TELEPHONE,ADDRESS,PRIORITY) VALUES ('NP','0909009090','STOLICHNOE',2);
--AUTHORITIES
INSERT INTO AUTHORITIES (AUTHORITY) VALUES ('ADMIN');
INSERT INTO AUTHORITIES (AUTHORITY) VALUES ('USER');
INSERT INTO AUTHORITIES (AUTHORITY) VALUES ('SUPERUSER');

--USERS
INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('superuser@outlook.com','$2a$10$hzIJysVpYDtbzFMMzJWPXOSN/6GSwByAG/RPb145s4K7nQyfUiOam',TRUE,'TEST','TEST','TEST','ADMIN',1,3);

INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('user@outlook.com','12345',TRUE,'TEST','TEST','TEST','ADMIN',2,2);

INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('admin@outlook.com','12345',TRUE,'TEST','TEST','TEST','ADMIN',1,1);
INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('login','$2a$10$eDuInU29Dqn1oZFMMvskl.W/GNATatC8v1AKTdTWCfbl7GSeSsb7.',TRUE,'TEST','TEST','TEST','ADMIN',1,1);

--CONTACTS_DICTONARY
INSERT INTO D_CONTACT_TYPE (NAME) VALUES ('SMS');
INSERT INTO D_CONTACT_TYPE (NAME) VALUES ('VIBER');
INSERT INTO D_CONTACT_TYPE (NAME) VALUES ('TELEPHONE');


--LANGUAGE
INSERT INTO LANGUAGE (NAME,ID_CREATOR) VALUES('UA',1);

--CONTACTS_CONTACT_PERSON
INSERT INTO CONTACTS_CONTACT_PERSON (id_contact_person,id_company,id_contact_type,priority_callback) VALUES (1,1,1,1);
INSERT INTO CONTACTS_CONTACT_PERSON (id_contact_person,id_company,id_contact_type,priority_callback) VALUES (1,1,2,1);
INSERT INTO CONTACTS_CONTACT_PERSON (id_contact_person,id_company,id_contact_type,priority_callback) VALUES (1,1,3,1);


-- DUCTS
INSERT INTO D_DUCT (NAME_DUCT,DUCT_PRIORITY,ID_CREATOR) VALUES ('PUSH',1,1);
INSERT INTO D_DUCT (NAME_DUCT,DUCT_PRIORITY,ID_CREATOR) VALUES ('SMS',3,1);
INSERT INTO D_DUCT (NAME_DUCT,DUCT_PRIORITY,ID_CREATOR) VALUES ('VIBER',2,1);
INSERT INTO D_DUCT (NAME_DUCT,DUCT_PRIORITY,ID_CREATOR) VALUES ('TELEGRAM',4,1);


--STRUCTURE
INSERT INTO STRUCTURE (ID_COMPANY,ID_LANGUAGE,ALGORITM,PRIORITY,PARAMS,NAME,ID_CREATOR) VALUES(1,1,'{SMS,VIBER}',1,'params','FIRST',1);
INSERT INTO STRUCTURE (ID_COMPANY,ID_LANGUAGE,ALGORITM,PRIORITY,PARAMS,NAME,ID_CREATOR) VALUES(1,1,'{SMS,VIBER}',1,'params','SECOND',1);

--STENCIL
-- INSERT INTO STENCIL (ID_D_DUCT,ID_STRUCTURE,STENCIL_ENTITY) VALUES (1,1,'ASD #NAME QWE #VALUE');
INSERT INTO STENCIL (ID_D_DUCT,ID_STRUCTURE,STENCIL_ENTITY,NAME,ID_CREATOR ) VALUES (2,2,'ASD #NAME# QWE #VALUE#','sometestname1',1);
INSERT INTO STENCIL (ID_D_DUCT,ID_STRUCTURE,STENCIL_ENTITY,NAME,ID_CREATOR ) VALUES (3,2,'ASD #NAME# QWE #VALUE#','sometestname2',1);
insert into stencil (id_d_duct,id_structure,stencil_entity,NAME,ID_CREATOR ) values(1,1,'Как ныне сбирается вещий {name} Отмстить неразумным {enemy}:' ||
 ' Их села и нивы за буйный набег Обрек он мечам и пожарам;' ||
  'С дружиной своей, в цареградской броне, ' ||
   '{boss} по полю едет на верном коне.','PUSHKIN_OLEKSANDR',1);
--
   insert into stencil (id_d_duct,id_structure,stencil_entity,NAME,ID_CREATOR ) values(4,1,' {name}  {enemy}:','sometestname4',1);
    insert into stencil (id_d_duct,id_structure,stencil_entity,NAME,ID_CREATOR ) values(2,1,' {name}  {boss}:','sometestname0',1);





--TASK
--insert into task (id_contact_person,id_structure,algoritm,variables,priority,ID_LANGUAGE,PARAMS) VALUES (1,1,'{SMS,PUSH}','{"1":"jghfgdd","asd":"qweqweqw"}',1,1,'{"4":"jghfgdd","asd":"qweqweqw"}');
--insert into task (id_contact_person,id_structure,algoritm,variables,priority,ID_LANGUAGE,PARAMS) VALUES (2,1,'{VIBER,PUSH}','{"2":"qwe"}',1,1,'{"3":"4"}');




--MSG
--insert into MSG (id_contact_person,id_structure,algoritm,variables,priority,ID_LANGUAGE,PARAMS,MESSAGE,ID_TASK) VALUES (1,1,'{SMS,PUSH}','{"A":"B"}',1,1,'{"A":"B"}','HELLO MR. NOBODY',1);

--message

--insert into message (priority,frequence,delay,departuretime,relevanttime,duct,message,address) values (1,NOW(),NOW(),NOW(),NOW(),'{asd,asd}','messs','addr');
--insert into message (priority,frequence,delay,departuretime,relevanttime,duct,message,address) values (1,NOW(),NOW(),NOW(),NOW(),'{asd,asd}','messs','addr');
--insert into message (priority,frequence,delay,departuretime,relevanttime,duct,message,address) values (1,NOW(),NOW(),NOW(),NOW(),'{asd,asd}','messs','addr');

--LIMITATIONS

INSERT INTO D_LIMITATIONS (LIMITATION,ID_D_DUCT,DESCRIPTION) VALUES (120,2,'EN_SMS');
INSERT INTO D_LIMITATIONS (LIMITATION,ID_D_DUCT,DESCRIPTION) VALUES (60,2,'RU_SMS');
INSERT INTO D_LIMITATIONS (LIMITATION,ID_D_DUCT,DESCRIPTION) VALUES (500,1,'PUSH');
INSERT INTO D_LIMITATIONS (LIMITATION,ID_D_DUCT,DESCRIPTION) VALUES (1000,4,'TELEGRAM');


INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('superuser@outlook.com','12345',TRUE,'TEST','TEST','TEST','ADMIN',1,3);

INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('user@outlook.com','12345',TRUE,'TEST','TEST','TEST','ADMIN',2,2);

INSERT INTO CONTACT_PERSON (USERNAME, PASSWORD, ENABLED,FIRSTNAME,SECONDNAME,DEPARTMENT,CREATOR,ID_COMPANY,ID_ROLE)
VALUES ('admin@outlook.com','12345',TRUE,'TEST','TEST','TEST','ADMIN',1,1);


INSERT INTO STATUS (NUMBER,DESCRIPTION) values(1,'message was select for sending');
INSERT INTO STATUS (NUMBER,DESCRIPTION) values(2,'message was send');
INSERT INTO STATUS (NUMBER,DESCRIPTION) values(3,'message has used all attempts');
INSERT INTO STATUS (NUMBER,DESCRIPTION) values(7,'message needs to change stencil');
INSERT INTO STATUS (NUMBER,DESCRIPTION) values(11,'message in work');





-- drop function if EXISTS delete_old_rows();
-- create function delete_old_rows() returns trigger language plpgsql as $$
-- begin
-- delete from message where idMessage=(NEW).idMessage;
-- return null;
-- end;
-- $$;



drop trigger if EXISTS del_old_rows on sent_message;
create trigger del_old_rows
 after insert on sent_message
 FOR EACH ROW
 execute procedure delete_old_rows();



--  drop function if EXISTS update_messages();
-- create function update_messages() returns trigger language plpgsql as $$
-- begin
-- delete from message where idMessage=(NEW).idMessage;
-- return null;
-- end;
-- $$;
--
--
--  drop trigger if EXISTS update_journal on task;
-- create trigger update_journal
--  after update on task
--  FOR EACH ROW
--  execute procedure update_messages();



