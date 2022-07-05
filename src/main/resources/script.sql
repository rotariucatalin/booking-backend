# ************************************************************
# Sequel Pro SQL dump
# Database: sihastriadb
# Generation Time: 2022-05-05 20:10:12 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table booking
# ------------------------------------------------------------

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
                           `booking_id` int NOT NULL AUTO_INCREMENT,
                           `start_date` date NOT NULL,
                           `end_date` date NOT NULL,
                           `guest_id` int NOT NULL,
                           `room_id` int NOT NULL,
                           `required_bed` int DEFAULT NULL,
                           `soft_delete` bit(1) NOT NULL DEFAULT b'0',
                           PRIMARY KEY (`booking_id`),
                           KEY `guest_id` (`guest_id`),
                           KEY `room_id` (`room_id`),
                           CONSTRAINT `guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`guest_id`),
                           CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;

INSERT INTO `booking` (`booking_id`, `start_date`, `end_date`, `guest_id`, `room_id`, `required_bed`, `soft_delete`)
VALUES
    (1,'2021-09-08','2021-10-16',3,2,2,b'0'),
    (2,'2021-10-08','2021-10-14',4,1,5,b'0'),
    (3,'2021-09-10','2021-09-16',5,1,5,b'0'),
    (4,'2021-09-07','2021-09-14',6,1,5,b'0'),
    (5,'2021-09-08','2021-09-17',7,3,5,b'0'),
    (6,'2021-09-10','2021-09-15',8,3,5,b'0');

/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table guest
# ------------------------------------------------------------

DROP TABLE IF EXISTS `guest`;

CREATE TABLE `guest` (
                         `guest_id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `phone_no` varchar(50) DEFAULT NULL,
                         `city` varchar(250) DEFAULT NULL,
                         `additional_details` varchar(250) DEFAULT NULL,
                         PRIMARY KEY (`guest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;

INSERT INTO `guest` (`guest_id`, `name`, `phone_no`, `city`, `additional_details`)
VALUES
    (1,'Daniel Ozac','0877936321','Bucuresti','some comment'),
    (3,'Daniel Ozac','0877936321','Bucuresti','some comment'),
    (4,'John Doe','0877936321','Dublin','some details'),
    (5,'Mick Doe','0877936321','Dublin','some details'),
    (6,'Donna Hogan','0877936321','Dublin','some details'),
    (7,'Donna Hogan','0877936321','Dublin','some details'),
    (8,'Donna Hogan','0877936321','Dublin','some details');

/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table room
# ------------------------------------------------------------

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
                        `room_id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        `total_beds` int NOT NULL,
                        `in_use` bit(1) NOT NULL DEFAULT b'1',
                        PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;

INSERT INTO `room` (`room_id`, `name`, `total_beds`, `in_use`)
VALUES
    (1,'Z64',16,b'1'),
    (2,'D2',20,b'1'),
    (3,'D3',20,b'1'),
    (4,'D4',20,b'0'),
    (5,'D5',20,b'0');

/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;






















# ========================DO NOT USE======================================
# -- -----------------------------------------------------
# -- Table `room`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `room` (
#                                       `room_id` INT NOT NULL AUTO_INCREMENT,
#                                       `name` VARCHAR(45) NOT NULL,
#                                       `total_beds` INT NOT NULL,
#                                       `in_use` BIT(1) NOT NULL DEFAULT 1,
#                                       PRIMARY KEY (`room_id`));
#
#
# -- -----------------------------------------------------
# -- Table `guest`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `guest` (
#                                        `guest_id` INT NOT NULL AUTO_INCREMENT,
#                                        `name` VARCHAR(45) NOT NULL,
#                                        `phone_no` VARCHAR(50) NULL,
#                                        `city` VARCHAR(250) NULL,
#                                        `additional_details` VARCHAR(250) NULL,
#                                        PRIMARY KEY (`guest_id`));
#
#
# -- -----------------------------------------------------
# -- Table `booking`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `booking` (
#                                          `booking_id` INT NOT NULL AUTO_INCREMENT,
#                                          `start_date` DATE NOT NULL,
#                                          `end_date` DATE NOT NULL,
#                                          `guest_id` INT NOT NULL,
#                                          `room_id` INT NOT NULL,
#                                          `required_bed` INT NULL,
#                                          PRIMARY KEY (`booking_id`),
#                                          CONSTRAINT `guest_id`
#                                              FOREIGN KEY (`guest_id`)
#                                                  REFERENCES `guest` (`guest_id`)
#                                                  ON DELETE NO ACTION
#                                                  ON UPDATE NO ACTION,
#                                          CONSTRAINT `room_id`
#                                              FOREIGN KEY (`room_id`)
#                                                  REFERENCES `room` (`room_id`));
#
#
# INSERT INTO room (name, total_beds) VALUES ('D1',20);
# INSERT INTO room (name, total_beds) VALUES ('D2',20);
# INSERT INTO room (name, total_beds) VALUES ('D3',20);
# INSERT INTO room (name, total_beds) VALUES ('D4',20);
# INSERT INTO room (name, total_beds) VALUES ('D5',20);
