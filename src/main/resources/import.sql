INSERT INTO tb_department(name) VALUES ('FLEXPARK');
INSERT INTO tb_department(name) VALUES ('AGETRAN');
INSERT INTO tb_department(name) VALUES ('GM');

INSERT INTO tb_user (name, email, password, department_id) VALUES ('Ana', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',1);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Bob', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',1);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Paulo Ferraz', 'pauloferraz@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',1);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Mario César', 'mariocesar@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',1);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Gilberto Pereira', 'gpereira@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',1);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Maria de Oliveira', 'mariaoliveira@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',2);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Carla Sanches', 'carlasanches@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',2);
INSERT INTO tb_user (name, email, password, department_id) VALUES ('Pedro Ferreira', 'pedroferreira@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',3);

INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_provider (name) VALUES ('Claro');

INSERT INTO tb_sim_card (number, provider_id) VALUES('89550534680012152692', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009981615', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550534680012152981', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550534680012152569', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550534680012152775', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009981482', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550534680012152403', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009981474', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009981664', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009961475', 1);
INSERT INTO tb_sim_card (number, provider_id) VALUES('89550539640009971666', 1);

INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z','LG Electronics', 'K10',1);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T10:50:07.12345Z','LG Electronics', 'K10',2);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T11:50:07.12345Z','LG Electronics', 'K10',3);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T12:50:07.12345Z','LG Electronics', 'K10',4);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T12:50:07.12345Z','LG Electronics', 'K10',5);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T09:50:07.12345Z','LG Electronics', 'K10',6);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T17:50:07.12345Z','LG Electronics', 'K10',7);
INSERT INTO tb_device (activation_date, manufacture, model, user_id) VALUES(TIMESTAMP WITH TIME ZONE '2020-07-13T16:50:07.12345Z','LG Electronics', 'K10',8);

INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991978923', 1,1);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991992357', 2,2);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67992072704', 3,3);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991970690', 4,4);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991984022', 5,5);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991997234', 6,6);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991990449', 7,7);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991978126', 8,8);
INSERT INTO tb_telephone_line (LINE_NUMBER,SIM_CARD_ID,USER_ID) VALUES('67991978925', 9,null);

INSERT INTO tb_expenditure (REFERENCE_DATE, LOCAL_MINUTES, LONG_DISTANCE_MINUTES, QUANTITY_DATAMB, VALUE, TELEFONE_LINE_ID) VALUES ('2020-05-01', 0,0,165.15,10.76,1);
INSERT INTO tb_expenditure (REFERENCE_DATE, LOCAL_MINUTES, LONG_DISTANCE_MINUTES, QUANTITY_DATAMB, VALUE, TELEFONE_LINE_ID) VALUES ('2020-05-01', 0,0,300.45,10.76,2);
INSERT INTO tb_expenditure (REFERENCE_DATE, LOCAL_MINUTES, LONG_DISTANCE_MINUTES, QUANTITY_DATAMB, VALUE, TELEFONE_LINE_ID) VALUES ('2020-05-01', 0,0,145.78,10.76,3);
INSERT INTO tb_expenditure (REFERENCE_DATE, LOCAL_MINUTES, LONG_DISTANCE_MINUTES, QUANTITY_DATAMB, VALUE, TELEFONE_LINE_ID) VALUES ('2020-05-01', 0,0,66.95,10.76,4);
INSERT INTO tb_expenditure (REFERENCE_DATE, LOCAL_MINUTES, LONG_DISTANCE_MINUTES, QUANTITY_DATAMB, VALUE, TELEFONE_LINE_ID) VALUES ('2020-05-01', 0,0,134.15,10.76,5);


