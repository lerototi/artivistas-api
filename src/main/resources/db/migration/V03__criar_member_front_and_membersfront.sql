CREATE TABLE member(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	admission DATETIME NOT NULL,
	current_member boolean NOT NULL,
	has_profile boolean NOT NULL,
	name VARCHAR(20),
	surname VARCHAR(30),
	fk_profile_group BIGINT(20),
	fk_user BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE front(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(500),
	image LONGBLOB,
	fk_profile_group BIGINT(20)NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE members_front(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	fk_member BIGINT(20),
	fk_front BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE member 
	ADD CONSTRAINT fk_profile_group_member FOREIGN KEY (fk_profile_group) REFERENCES profile_group(id);
	
ALTER TABLE member 
	ADD CONSTRAINT fk_user_member FOREIGN KEY (fk_user) REFERENCES profile_user (id);
	
ALTER TABLE front
	ADD CONSTRAINT fk_profile_group_front FOREIGN KEY (fk_profile_group) REFERENCES profile_group(id);
	
ALTER TABLE members_front
	ADD CONSTRAINT fk_member_members_front FOREIGN KEY (fk_member) REFERENCES member(id);
	
ALTER TABLE members_front
	ADD CONSTRAINT fk_front_members_front FOREIGN KEY (fk_front) REFERENCES front(id);
	

INSERT INTO member (admission, current_member, has_profile, fk_user, fk_profile_group ) 
		values ('2017-08-17 00:00:00', true, true, 1, 1);
		
INSERT INTO member (admission, current_member, has_profile, name, surname) 
		values ('2017-08-17 00:00:00', true, false, 'Lucas', 'Beuthel');

INSERT INTO member (admission, current_member, has_profile, fk_user, fk_profile_group ) 
		values ('2017-08-17 00:00:00', true, true, 2, 1);
		
		
INSERT INTO front (name, description, fk_profile_group)
	values ('Orquestra Camaleônica do Calango Careta', 'Esta é a maior frente musical conhecida no mundo funrarrístico do Brasil!!!',1);
	
INSERT INTO front (name, description, fk_profile_group)
	values ('Capivaretas','Se é com tambor, então vambora!' ,1);
	
INSERT INTO front (name, description, fk_profile_group)
	values ('Trupe quero-quero', 'Para alegrar nossas apresentações e trazer vida e cores para nosso grupo!',1);
	
	
INSERT INTO members_front(fk_member, fk_front) values (1,1);
INSERT INTO members_front(fk_member, fk_front) values (2,1);


		