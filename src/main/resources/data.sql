DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `authority` VALUES (1,'ROLE_USER');
INSERT INTO `authority` VALUES (2,'ROLE_ADMIN')

-- password is hashed, original password is password123
INSERT INTO `user`(id,name,password,status,surname,username ) VALUES (1,'SomeName','$2y$10$f4SbLzTnDVCsJYmoa7LuBe.RQB4C3w8xiHWMLOLNfpuVIIDKUPPzC',0,'SomeSurname','testuser');
INSERT INTO `user`(id,name,password,status,surname,username ) VALUES (2,'SomeName','$2y$10$f4SbLzTnDVCsJYmoa7LuBe.RQB4C3w8xiHWMLOLNfpuVIIDKUPPzC',0,'SomeSurname','testuser2');


INSERT INTO `user_authority` VALUES (1,2);
INSERT INTO `user_authority` VALUES (1,1);
INSERT INTO `user_authority` VALUES (2,1);
