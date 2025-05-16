-- Adminer 5.2.1 MariaDB 11.7.2-MariaDB-ubu2404 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL,
  `team_details` varchar(255) DEFAULT NULL,
  `team_name` varchar(72) NOT NULL,
  `owner_account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsob22siqdnn2rfsxk6f00pgwb` (`team_name`),
  KEY `FKiyt4bctrtyhw7rj6aqbec3rrl` (`owner_account_id`),
  CONSTRAINT `FKiyt4bctrtyhw7rj6aqbec3rrl` FOREIGN KEY (`owner_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `team` (`id`, `team_details`, `team_name`, `owner_account_id`) VALUES
(102,	'New team ready for the big leagues',	'fragmentz',	152),
(103,	'sink into the pit',	'tar pit',	152);

DROP TABLE IF EXISTS `team_players`;
CREATE TABLE `team_players` (
  `team_id` bigint(20) NOT NULL,
  `players_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKj7gm7vpm2kn3c07shxqlvt4al` (`players_id`),
  KEY `FKc9igy2kys82rwa80px3q0usqa` (`team_id`),
  CONSTRAINT `FKc9igy2kys82rwa80px3q0usqa` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKmpq08auxlfjatdg8yim80xff6` FOREIGN KEY (`players_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `team_seq`;
CREATE TABLE `team_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(72) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKhl02wv5hym99ys465woijmfib` (`email`),
  UNIQUE KEY `UKcastjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `user_account` (`id`, `email`, `password`, `username`) VALUES
(152,	'example@example.com',	'{bcrypt}$2a$10$0qF94r4vB9.kuYEm.MRXSOj0WIUDcFrmgXlzEpY1bPPPfhKhk/IvW',	'steve');

DROP TABLE IF EXISTS `user_account_seq`;
CREATE TABLE `user_account_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


-- 2025-05-16 02:22:33 UTC
