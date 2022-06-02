BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_info_reading_log;
DROP TABLE IF EXISTS reading_log;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS seq_ui_rl_id;
DROP SEQUENCE IF EXISTS seq_reading_log_id;
DROP SEQUENCE IF EXISTS seq_user_info_id;
DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_user_info_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_reading_log_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_ui_rl_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	firstName varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE user_info (
                       user_info_id int DEFAULT nextval('seq_user_info_id'::regclass) NOT NULL,
--                       user_info_id SERIAL NOT NULL PRIMARY KEY,
                       user_id int NOT NULL,
                       yob DATE NOT NULL,
                       sex varchar (50) NOT NULL,
                       weight DECIMAL(5,2) NOT NULL,
                       bolus_insulin_name varchar (50) NOT NULL,
                       insulin_strength varchar (50) NOT NULL,
                       target_min int NOT NULL,
                       target_max int NOT NULL,
                       basal_rate DECIMAL(4,2) NOT NULL,
                       sensitivity DECIMAL(4,2) NOT NULL,
                       carb_insulin_ratio DECIMAL(4,2) NOT NULL,
                       CONSTRAINT PK_user_info PRIMARY KEY (user_info_id)
--                        CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE reading_log (
                           reading_log_id int DEFAULT nextval('seq_reading_log_id'::regclass) NOT NULL,
--                           reading_log SERIAL NOT NULL PRIMARY KEY,
                           user_id int NOT NULL,
                           carb_intake DECIMAL(4,2) NOT NULL,
                           blood_sugar_reading int NOT NULL,
                           date_and_time varchar(50) NOT NULL,
                           CONSTRAINT PK_reading_log PRIMARY KEY (reading_log_id)
--                            CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_info_reading_log (
                        ui_rl_id int DEFAULT nextval('seq_ui_rl_id'::regclass) NOT NULL,
                        user_info_id int NOT NULL,
                        reading_log_id int NOT NULL,
                        CONSTRAINT PK_user_info_reading_log PRIMARY KEY (ui_rl_id)
);



INSERT INTO users (firstName, lastName, email, password_hash,role) VALUES ('user','user', 'kinjal@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (firstName, lastName, email, password_hash,role) VALUES ('admin','admin', 'eric@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

ALTER TABLE user_info
ADD CONSTRAINT FK_user
FOREIGN KEY (user_id)
    REFERENCES users(user_id);

ALTER TABLE reading_log
ADD CONSTRAINT FK_user
FOREIGN KEY (user_id)
    REFERENCES users(user_id);

ALTER TABLE user_info_reading_log
ADD CONSTRAINT FK_user_info
FOREIGN KEY (user_info_id)
    REFERENCES user_info(user_info_id);

 ALTER TABLE user_info_reading_log
 ADD CONSTRAINT FK_reading_log
 FOREIGN KEY (reading_log_id)
     REFERENCES reading_log(reading_log_id);



COMMIT TRANSACTION;
