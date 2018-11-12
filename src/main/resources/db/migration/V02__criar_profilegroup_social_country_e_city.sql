CREATE TABLE social(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	link VARCHAR(100) NOT NULL,
	fk_profile_group BIGINT(20) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE profile_group(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(500),
	founded DATETIME NOT NULL,
	registered TIMESTAMP NOT NULL,
	fk_city INT(10)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE country(
	id INT(6) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	code VARCHAR(2) NOT NULL,
	code3 VARCHAR(3) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE city(
	id INT(10) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	fk_country INT(6)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE social
	ADD CONSTRAINT fk_profile_group FOREIGN KEY (fk_profile_group) REFERENCES profile_group (id);
	
ALTER TABLE profile_group
	ADD CONSTRAINT fk_city FOREIGN KEY (fk_city) REFERENCES city (id);
	
ALTER TABLE city
	ADD CONSTRAINT fk_country FOREIGN KEY (fk_country) REFERENCES country (id);
	

INSERT INTO country (name, code, code3) values('Brasil','BL','BRL' );
	
INSERT INTO city (name, fk_country) values('Acre',1);
INSERT INTO city (name, fk_country) values('Alagoas',1);
INSERT INTO city (name, fk_country) values('Amapá',1);
INSERT INTO city (name, fk_country) values('Amazonas',1);
INSERT INTO city (name, fk_country) values('Bahia',1);
INSERT INTO city (name, fk_country) values('Ceará',1);
INSERT INTO city (name, fk_country) values('Distrito Federal',1);
INSERT INTO city (name, fk_country) values('Espírito Santo',1);
INSERT INTO city (name, fk_country) values('Goiás',1);
INSERT INTO city (name, fk_country) values('Maranhão',1);
INSERT INTO city (name, fk_country) values('Mato Grosso',1);
INSERT INTO city (name, fk_country) values('Mato Grosso do Sul',1);
INSERT INTO city (name, fk_country) values('Pará',1);
INSERT INTO city (name, fk_country) values('Paraíba',1);
INSERT INTO city (name, fk_country) values('Paraná',1);
INSERT INTO city (name, fk_country) values('Pernambuco',1);
INSERT INTO city (name, fk_country) values('Rio de Janeiro',1);
INSERT INTO city (name, fk_country) values('Rio Grande do Norte',1);
INSERT INTO city (name, fk_country) values('Rio Grande do Sul',1);
INSERT INTO city (name, fk_country) values('Rondônia',1);
INSERT INTO city (name, fk_country) values('Roraima',1);
INSERT INTO city (name, fk_country) values('Santa Cararina',1);
INSERT INTO city (name, fk_country) values('São Paulo',1);
INSERT INTO city (name, fk_country) values('Sergipe',1);
INSERT INTO city (name, fk_country) values('Tocantins',1);


INSERT INTO profile_group (name, description, founded, registered, fk_city) VALUES('Calango Careta', 'Cadê? cadê? Nós somos a Orquestra Camaleônica do Calango Careta!!!!!!!!', '2015-04-12 00:00:00' ,SYSDATE(), 7);
INSERT INTO profile_group (name, description, founded, registered, fk_city) VALUES('Tropicaos', '', '2018-01-20 00:00:00' ,SYSDATE() + 1, 7);
INSERT INTO profile_group (name, description, founded, registered, fk_city) VALUES('Capivara Brass Band', '', '2016-08-24 00:00:00' ,SYSDATE() + 2, 7);



INSERT INTO social(name, link, fk_profile_group) VALUES ('FACEBOOK', 'www.facebook.com/calangocareta', 1);
INSERT INTO social(name, link, fk_profile_group) VALUES ('INSTAGRAM', 'www.instagram.com/calangocareta', 1);
INSERT INTO social(name, link, fk_profile_group) VALUES ('EMAIL', 'calangocareta@gmail.com', 1);


