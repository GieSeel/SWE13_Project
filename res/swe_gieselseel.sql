-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Dez 2013 um 09:05
-- Server Version: 5.6.11
-- PHP-Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `swe_gieselseel`
--
CREATE DATABASE IF NOT EXISTS `swe_gieselseel` DEFAULT CHARACTER SET latin1 COLLATE latin1_german1_ci;
USE `swe_gieselseel`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `house_number` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `address`
--

INSERT INTO `address` (`id`, `street`, `house_number`) VALUES
(1, 'Tenneseeallee', 'X86'),
(2, 'Schillerstraße', '5');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bill_item`
--

CREATE TABLE IF NOT EXISTS `bill_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` int(11) NOT NULL DEFAULT '0',
  `price_busy_season` int(11) NOT NULL DEFAULT '0',
  `price_low_season` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=10 ;

--
-- Daten für Tabelle `bill_item`
--

INSERT INTO `bill_item` (`id`, `labeling`, `price_busy_season`, `price_low_season`) VALUES
(1, 0, 3340, 2530),
(2, 1, 1360, 850),
(3, 2, 1870, 1250),
(4, 3, 200, 120),
(5, 4, 50, 30),
(6, 5, 430, 0),
(7, 6, 990, 510),
(8, 7, 200, 150),
(9, 8, 100, 50);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duration_from` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `duration_until` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `guest_id` int(11) NOT NULL,
  `checked_in` bit(1) NOT NULL,
  `pitch_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `booking`
--

INSERT INTO `booking` (`id`, `duration_from`, `duration_until`, `guest_id`, `checked_in`, `pitch_id`) VALUES
(1, '2013-12-09 08:04:29', '2013-12-30 23:00:00', 1, b'1', 12);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_bill_item`
--

CREATE TABLE IF NOT EXISTS `booking_bill_item` (
  `booking_id` int(11) NOT NULL,
  `bill_item_id` int(11) NOT NULL,
  `multiplier` int(11) NOT NULL DEFAULT '1',
  `current_price` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`bill_item_id`),
  KEY `FKbooking_bi899764` (`booking_id`),
  KEY `FKbooking_bi791590` (`bill_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `booking_bill_item`
--

INSERT INTO `booking_bill_item` (`booking_id`, `bill_item_id`, `multiplier`, `current_price`) VALUES
(1, 1, 1, 1360);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_chipcard`
--

CREATE TABLE IF NOT EXISTS `booking_chipcard` (
  `booking_id` int(11) NOT NULL,
  `chipcard_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`chipcard_id`),
  KEY `FKbooking_ch400243` (`booking_id`),
  KEY `FKbooking_ch394097` (`chipcard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `booking_chipcard`
--

INSERT INTO `booking_chipcard` (`booking_id`, `chipcard_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_equipment`
--

CREATE TABLE IF NOT EXISTS `booking_equipment` (
  `booking_id` int(11) NOT NULL,
  `equipment_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`equipment_id`),
  KEY `FKbooking_eq199832` (`booking_id`),
  KEY `FKbooking_eq608432` (`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `booking_equipment`
--

INSERT INTO `booking_equipment` (`booking_id`, `equipment_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_extra_booking`
--

CREATE TABLE IF NOT EXISTS `booking_extra_booking` (
  `booking_id` int(11) NOT NULL,
  `extra_booking_id` int(11) NOT NULL,
  `duration_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`extra_booking_id`),
  KEY `FKbooking_ex845195` (`booking_id`),
  KEY `FKbooking_ex669936` (`extra_booking_id`),
  KEY `FKbooking_ex50100` (`duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_fellow_guest`
--

CREATE TABLE IF NOT EXISTS `booking_fellow_guest` (
  `booking_id` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  `duration_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`guest_id`),
  KEY `FKbooking_fe102855` (`booking_id`),
  KEY `FKbooking_fe745335` (`guest_id`),
  KEY `FKbooking_fe663831` (`duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_pitch_booking`
--

CREATE TABLE IF NOT EXISTS `booking_pitch_booking` (
  `booking_id` int(11) NOT NULL,
  `pitch_booking_id` int(11) NOT NULL,
  `duration_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`,`pitch_booking_id`),
  KEY `FKbooking_pi97819` (`booking_id`),
  KEY `FKbooking_pi442367` (`pitch_booking_id`),
  KEY `FKbooking_pi668867` (`duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `booking_pitch_booking`
--

INSERT INTO `booking_pitch_booking` (`booking_id`, `pitch_booking_id`, `duration_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `chipcard`
--

CREATE TABLE IF NOT EXISTS `chipcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duration_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKchipcard900597` (`duration_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `chipcard`
--

INSERT INTO `chipcard` (`id`, `duration_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `acronym` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `country`
--

INSERT INTO `country` (`id`, `name`, `acronym`) VALUES
(1, 'Deutschland', 'DE'),
(2, 'Wunderland', 'WL');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `deliverypoint`
--

CREATE TABLE IF NOT EXISTS `deliverypoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `deliverypoint`
--

INSERT INTO `deliverypoint` (`id`, `description`) VALUES
(1, 'Electricity and Water');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `duration`
--

CREATE TABLE IF NOT EXISTS `duration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `until` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `duration`
--

INSERT INTO `duration` (`id`, `from`, `until`) VALUES
(1, '2013-11-24 09:00:00', '2013-11-30 09:00:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `employee_role_id` int(11) NOT NULL,
  `user_name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `password` varchar(255) COLLATE latin1_german1_ci NOT NULL DEFAULT '0000',
  `blocked` bit(1) NOT NULL DEFAULT b'1',
  `chipcard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKemployee944641` (`person_id`),
  KEY `FKemployee339298` (`employee_role_id`),
  KEY `FKemployee407699` (`chipcard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employee_role`
--

CREATE TABLE IF NOT EXISTS `employee_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` int(11) NOT NULL DEFAULT '0',
  `arrangement` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `employee_role`
--

INSERT INTO `employee_role` (`id`, `labeling`, `arrangement`) VALUES
(1, 0, 1),
(2, 1, 1),
(3, 2, 0),
(4, 3, 1),
(5, 4, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0',
  `size` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `identification` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `equipment`
--

INSERT INTO `equipment` (`id`, `type`, `size`, `identification`) VALUES
(1, 0, 'Size of equipment', 'Number of vehicle');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `extra_booking`
--

CREATE TABLE IF NOT EXISTS `extra_booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `labeling` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKextra_book516693` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guest`
--

CREATE TABLE IF NOT EXISTS `guest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_identification_number` varchar(255) NOT NULL,
  `person_name` varchar(255) NOT NULL,
  `person_first_name` varchar(255) NOT NULL,
  `person_date_of_birth` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_street` varchar(255) NOT NULL,
  `address_house_number` varchar(255) NOT NULL,
  `town_name` varchar(255) NOT NULL,
  `town_postal_code` varchar(255) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  `country_acronym` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `guest`
--

INSERT INTO `guest` (`id`, `person_identification_number`, `person_name`, `person_first_name`, `person_date_of_birth`, `address_street`, `address_house_number`, `town_name`, `town_postal_code`, `country_name`, `country_acronym`) VALUES
(1, '0123456789D', 'Silie', 'Peter', '1990-12-23 23:00:00', 'Schillerstrasse', '23', 'Karlsruhe', '12345', 'Deutschland', 'DE');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identification_number` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `first_name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `date_of_birth` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKperson798046` (`town_id`),
  KEY `FKperson335089` (`country_id`),
  KEY `FKperson350708` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`id`, `identification_number`, `name`, `first_name`, `date_of_birth`, `address_id`, `town_id`, `country_id`) VALUES
(1, '1234567890D', 'Mustermann', 'Max', '1990-12-23 22:00:00', 2, 2, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pitch`
--

CREATE TABLE IF NOT EXISTS `pitch` (
  `id` int(11) NOT NULL,
  `area` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `nature_of_soil` int(11) NOT NULL,
  `characteristics` varchar(255) NOT NULL,
  `x_coords` text NOT NULL,
  `y_coords` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `pitch`
--

INSERT INTO `pitch` (`id`, `area`, `type`, `height`, `width`, `nature_of_soil`, `characteristics`, `x_coords`, `y_coords`) VALUES
(12, 'B', 0, 50, 50, 0, 'Platz mit Strom und Wasseranschluss', '15, 150, 20, 30', '30, 150, 15, 30');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pitch_booking`
--

CREATE TABLE IF NOT EXISTS `pitch_booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitch_id` int(11) NOT NULL,
  `electricity` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FKpitch_book849756` (`pitch_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `pitch_booking`
--

INSERT INTO `pitch_booking` (`id`, `pitch_id`, `electricity`) VALUES
(1, 1, b'1');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_role_id` int(11) NOT NULL,
  `duration_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `priority` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FKservice345147` (`employee_role_id`),
  KEY `FKservice922011` (`duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service_deliverypoint`
--

CREATE TABLE IF NOT EXISTS `service_deliverypoint` (
  `service_id` int(11) NOT NULL,
  `deliverypoint_id` int(11) NOT NULL,
  PRIMARY KEY (`service_id`,`deliverypoint_id`),
  KEY `FKservice_de37859` (`service_id`),
  KEY `FKservice_de715513` (`deliverypoint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service_pitch`
--

CREATE TABLE IF NOT EXISTS `service_pitch` (
  `service_id` int(11) NOT NULL,
  `pitch_id` int(11) NOT NULL,
  PRIMARY KEY (`service_id`),
  KEY `FKservice_pi375692` (`pitch_id`),
  KEY `FKservice_pi133392` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service_site`
--

CREATE TABLE IF NOT EXISTS `service_site` (
  `service_id` int(11) NOT NULL,
  `site_id` int(11) NOT NULL,
  PRIMARY KEY (`service_id`),
  KEY `FKservice_si430833` (`service_id`),
  KEY `FKservice_si397153` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `site`
--

CREATE TABLE IF NOT EXISTS `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `opening_hours` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `description` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `town`
--

CREATE TABLE IF NOT EXISTS `town` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `postal_code` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `town`
--

INSERT INTO `town` (`id`, `name`, `postal_code`) VALUES
(1, 'Karlsruhe', '76131'),
(2, 'Mustercity', '76123');

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `booking_bill_item`
--
ALTER TABLE `booking_bill_item`
  ADD CONSTRAINT `FKbooking_bi791590` FOREIGN KEY (`bill_item_id`) REFERENCES `bill_item` (`id`),
  ADD CONSTRAINT `FKbooking_bi899764` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`);

--
-- Constraints der Tabelle `booking_chipcard`
--
ALTER TABLE `booking_chipcard`
  ADD CONSTRAINT `FKbooking_ch394097` FOREIGN KEY (`chipcard_id`) REFERENCES `chipcard` (`id`),
  ADD CONSTRAINT `FKbooking_ch400243` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`);

--
-- Constraints der Tabelle `booking_equipment`
--
ALTER TABLE `booking_equipment`
  ADD CONSTRAINT `FKbooking_eq199832` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
  ADD CONSTRAINT `FKbooking_eq608432` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`);

--
-- Constraints der Tabelle `booking_extra_booking`
--
ALTER TABLE `booking_extra_booking`
  ADD CONSTRAINT `FKbooking_ex50100` FOREIGN KEY (`duration_id`) REFERENCES `duration` (`id`),
  ADD CONSTRAINT `FKbooking_ex669936` FOREIGN KEY (`extra_booking_id`) REFERENCES `extra_booking` (`id`),
  ADD CONSTRAINT `FKbooking_ex845195` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
