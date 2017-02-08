--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `app_content_type`
--

DROP TABLE IF EXISTS `app_content_type`;
CREATE TABLE `app_content_type` (
  `app_id` int(11) NOT NULL,
  `content_type` varchar(50) NOT NULL,
  PRIMARY KEY (`app_id`,`content_type`),
  CONSTRAINT `app_content_type` FOREIGN KEY (`app_id`) REFERENCES `applications` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
CREATE TABLE `applications` (
  `app_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `app_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;