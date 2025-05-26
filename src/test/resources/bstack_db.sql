-- Adminer 5.2.1 MariaDB 11.7.2-MariaDB-ubu2404 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

INSERT INTO `team` (`id`, `team_details`, `team_name`, `owner_account_id`) VALUES
(102,	'New team ready for the big leagues',	'fragmentz',	152),
(103,	'sink into the pit',	'tar pit',	152);


INSERT INTO `user_account` (`id`, `email`, `password`, `username`) VALUES
(152,	'example@example.com',	'{bcrypt}$2a$10$0qF94r4vB9.kuYEm.MRXSOj0WIUDcFrmgXlzEpY1bPPPfhKhk/IvW',	'steve');

-- 2025-05-26 19:38:22 UTC
