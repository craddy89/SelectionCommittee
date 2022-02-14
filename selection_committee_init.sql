CREATE DATABASE  IF NOT EXISTS `admissions_committee`;
USE `admissions_committee`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `applicant` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) NOT NULL,
                             `faculty_id` int(11) NOT NULL,
                             `applicant_state_id` int(11) NOT NULL,
                             `enrollment_id` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `id_UNIQUE` (`id`),
                             KEY `FK_applicants_faculty_idx` (`faculty_id`),
                             KEY `FK_applicant_applicant_state_idx` (`applicant_state_id`),
                             KEY `FK_applicant_enrollment_idx` (`enrollment_id`),
                             KEY `FK_applicant_user_id_idx` (`user_id`),
                             CONSTRAINT `FK_applicant_applicant_state` FOREIGN KEY (`applicant_state_id`) REFERENCES `applicant_state` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                             CONSTRAINT `FK_applicant_enrollment` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                             CONSTRAINT `FK_applicant_faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                             CONSTRAINT `FK_applicant_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicant_state`
--

DROP TABLE IF EXISTS `applicant_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `applicant_state` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `state_type` varchar(45) NOT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `id_UNIQUE` (`id`),
                                   UNIQUE KEY `state_type_UNIQUE` (`state_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_state`
--

LOCK TABLES `applicant_state` WRITE;
/*!40000 ALTER TABLE `applicant_state` DISABLE KEYS */;
INSERT INTO `applicant_state` VALUES (1,'applied'),(2,'enrolled'),(3,'not_enrolled');
/*!40000 ALTER TABLE `applicant_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `enrollment` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `enrollment_state_id` int(11) NOT NULL,
                              `start_date` datetime NOT NULL,
                              `end_date` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `id_UNIQUE` (`id`),
                              KEY `FK_enrollment_enrollment_state_idx` (`enrollment_state_id`),
                              CONSTRAINT `FK_enrollment_enrollment_state` FOREIGN KEY (`enrollment_state_id`) REFERENCES `enrollment_state` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment_state`
--

DROP TABLE IF EXISTS `enrollment_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `enrollment_state` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `enrollment_type` varchar(45) NOT NULL,
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `id_UNIQUE` (`id`),
                                    UNIQUE KEY `enrollment_type_UNIQUE` (`enrollment_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment_state`
--

LOCK TABLES `enrollment_state` WRITE;
/*!40000 ALTER TABLE `enrollment_state` DISABLE KEYS */;
INSERT INTO `enrollment_state` VALUES (2,'closed'),(1,'opened');
/*!40000 ALTER TABLE `enrollment_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `faculty` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `capacity` int(11) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Faculty of Mathematics',3),(2,'Faculty of Radioengineering',3),(3,'Faculty of Linguistics',3),(4,'Faculty of Arts',3),(5,'Faculty of Biology',4);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `required_subject`
--

DROP TABLE IF EXISTS `required_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `required_subject` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `faculty_id` int(11) NOT NULL,
                                    `subject_id` int(11) NOT NULL,
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `id_UNIQUE` (`id`),
                                    KEY `FK_required_subject_faculty_idx` (`faculty_id`),
                                    KEY `FK_required_subject_subject_idx` (`subject_id`),
                                    CONSTRAINT `FK_required_subject_faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                    CONSTRAINT `FK_required_subject_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `required_subject`
--

LOCK TABLES `required_subject` WRITE;
/*!40000 ALTER TABLE `required_subject` DISABLE KEYS */;
INSERT INTO `required_subject` VALUES (1,1,1),(2,1,6),(3,1,9),(4,1,10),(5,2,1),(6,2,5),(7,2,9),(8,2,10),(9,3,1),(10,3,6),(11,3,7),(12,3,8),(13,4,1),(14,4,2),(15,4,6),(16,4,7),(17,5,1),(18,5,3),(19,5,4),(20,5,6);
/*!40000 ALTER TABLE `required_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `subject` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `id_UNIQUE` (`id`),
                           UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (2,'Art'),(3,'Biology'),(4,'Chemistry'),(5,'Computer Science'),(6,'English'),(1,'GPA'),(7,'History'),(8,'Latin'),(9,'Maths'),(10,'Physics');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_grades`
--

DROP TABLE IF EXISTS `subject_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `subject_grades` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `applicant_id` int(11) NOT NULL,
                                  `subject_id` int(11) NOT NULL,
                                  `grade` int(11) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `id_UNIQUE` (`id`),
                                  KEY `FK_subject_raiting_applicant_idx` (`applicant_id`),
                                  KEY `FK_subject_raiting_subject_idx` (`subject_id`),
                                  CONSTRAINT `FK_subject_raiting_applicant` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                  CONSTRAINT `FK_subject_raiting_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_grades`
--

LOCK TABLES `subject_grades` WRITE;
/*!40000 ALTER TABLE `subject_grades` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `user_role_id` int(11) NOT NULL,
                        `login` varchar(45) NOT NULL,
                        `first_name` varchar(45) NOT NULL,
                        `last_name` varchar(45) NOT NULL,
                        `patronymic` varchar(45) NOT NULL,
                        `password` bigint(64) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `id_UNIQUE` (`id`),
                        UNIQUE KEY `login_UNIQUE` (`login`),
                        KEY `FK_user_user_role_idx` (`user_role_id`),
                        KEY `FK_user_applicant_idx` (`id`),
                        CONSTRAINT `FK_user_user_role` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'admin','Admin','Admin','Admin',63116079);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `role_type` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `id_UNIQUE` (`id`),
                             UNIQUE KEY `role_type_UNIQUE` (`role_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'admin'),(2,'applicant');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'admissions_committee'
--

--
-- Dumping routines for database 'admissions_committee'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-11 20:58:41
