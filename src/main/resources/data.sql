INSERT INTO OAUTH_CLIENT_DETAILS (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES 
	('web', '{bcrypt}$2y$10$X0IqSKiqknGxsWZYdOFaNe6AwUZMTQZFkxjFnxyoFN7eiFju19u4m', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO PERMISSION (name) VALUES
	('create_profile'),
	('read_profile'),
	('update_profile'),
	('delete_profile');
 
INSERT INTO role (name) VALUES
	('ROLE_admin'),('ROLE_operator');

INSERT INTO PERMISSION_ROLE (permission_id, role_id) VALUES
	(1,1),  --create admin 
    (2,1),  --read admin 
	(3,1),  --update admin 
    (4,1),  --delete admin 
    (2,2),  --read operator 
    (3,2);  --update operator 
     
INSERT INTO USER (username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES 
	('dave', '{bcrypt}$2a$10$ol3mRCC14nRVUUZr4iK2leEKVA0B4WBFBpuMzm7.jITF99LV/aGRK', 'k@krishantha.com', '1', '1', '1', '1'),
	('pepe', '{bcrypt}$2a$10$B7FJcPCUyk7iCetPVMCZweHcR5sy6KydHULiDTSo478aarb8Mtkg.', 'k@krishantha.com', '1', '1', '1', '1');

INSERT INTO ROLE_USER (role_id, user_id) VALUES
	(1, 1), --dave admin
    (2, 2); --pepe operator