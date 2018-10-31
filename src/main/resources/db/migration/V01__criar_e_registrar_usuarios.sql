CREATE TABLE user(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    mail VARCHAR(20) NOT NULL,
    password VARCHAR(70) NOT NULL,
    enabled BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE profile_user(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    cellphone VARCHAR(20),
    description VARCHAR(300),
    facebook VARCHAR(80),
    instagram VARCHAR(80),
    date_profile TIMESTAMP NOT NULL,
    brithday DATETIME,
    fk_user BIGINT(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE profile_user
	ADD CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES user (id);
 
insert into user(mail, password, enabled) values ('lerototi@gmail.com','1234',true);
insert into user(mail, password, enabled) values ('anaabrahao@gmail.com','1234',true);

insert into profile_user(name, surname, date_profile, fk_user) values ('Leonardo', 'Torres', sysdate(),1);
insert into profile_user(name, surname, date_profile, fk_user) values ('Ana', 'Abrahão', sysdate(),2);