CREATE TABLE authority(
	id INT(3) PRIMARY KEY,
	name VARCHAR(15)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE authority_user(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	fk_user BIGINT(20),
	fk_authority INT(8),
	CONSTRAINT fk_user_authority_user FOREIGN KEY (fk_user) REFERENCES user(id),
	CONSTRAINT fk_autority_authority_authority_user FOREIGN KEY (fk_authority) REFERENCES authority (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO authority(id, name) values(1, 'USER');
INSERT INTO authority(id, name) values(2, 'ADMIN_GROUP');
INSERT INTO authority(id, name) values(3, 'ADMIN_SYSTEM');


INSERT INTO authority_user(fk_user, fk_authority) VALUES(1,1);
INSERT INTO authority_user(fk_user, fk_authority) VALUES(1,2);
INSERT INTO authority_user(fk_user, fk_authority) VALUES(1,3);

INSERT INTO authority_user(fk_user, fk_authority) VALUES(1,1);

		