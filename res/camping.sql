-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 31. Jul 2013 um 22:02
-- Server Version: 5.5.32
-- PHP-Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `camping`
--
CREATE DATABASE IF NOT EXISTS `camping` DEFAULT CHARACTER SET latin1 COLLATE latin1_german1_ci;
USE `camping`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `houseNumber` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `town_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bill`
--

CREATE TABLE IF NOT EXISTS `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `billItem_ID` int(11) NOT NULL,
  `multiplier` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `billitem`
--

CREATE TABLE IF NOT EXISTS `billitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` enum('camper pitch','parking place','tent pitch','icebox','electricity','child','adult') COLLATE latin1_german1_ci NOT NULL,
  `priceBusySeason` float NOT NULL,
  `priceLowSeason` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `billitem`
--

INSERT INTO `billitem` (`id`, `labeling`, `priceBusySeason`, `priceLowSeason`) VALUES
(1, 'tent pitch', 18.7, 12.5),
(2, 'camper pitch', 33.4, 25.3),
(3, 'parking place', 13.6, 8.5),
(4, 'icebox', 2, 1.2),
(5, 'electricity', 0.5, 0.3),
(6, 'child', 4.3, 0),
(7, 'adult', 9.9, 5.1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `responsiblePerson_ID` int(11) NOT NULL,
  `fellowTravelersList_number` int(11) NOT NULL,
  `from` date NOT NULL,
  `until` date NOT NULL,
  `equipmentList_number` int(11) NOT NULL,
  `pitchBookingList_number` int(11) NOT NULL,
  `extraBookingList_number` int(11) NOT NULL,
  `bill_number` int(11) NOT NULL,
  `chipCardList_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `chipcard`
--

CREATE TABLE IF NOT EXISTS `chipcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `validFrom` date NOT NULL,
  `validTo` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `chipcardlist`
--

CREATE TABLE IF NOT EXISTS `chipcardlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `chipCard_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `acronym` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `identificationNumber` int(11) NOT NULL,
  `employeeRole_ID` int(11) NOT NULL,
  `userName` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `password` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `blocked` tinyint(1) NOT NULL DEFAULT '1',
  `chipCard_ID` int(11) NOT NULL,
  PRIMARY KEY (`identificationNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employeerole`
--

CREATE TABLE IF NOT EXISTS `employeerole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` enum('caretaker','cleaner','conductor','groundkeeper','reception') COLLATE latin1_german1_ci NOT NULL,
  `arrangement` enum('administrator','laboratory assistant','reception staff','') COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('bike','boat','camper','car','caravan','motorbike','other','tent','','') COLLATE latin1_german1_ci NOT NULL,
  `size` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `identification` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `equipmentlist`
--

CREATE TABLE IF NOT EXISTS `equipmentlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `equipment_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `extrabooking`
--

CREATE TABLE IF NOT EXISTS `extrabooking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `labeling` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `site_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `extrabookinglist`
--

CREATE TABLE IF NOT EXISTS `extrabookinglist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `site_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guest`
--

CREATE TABLE IF NOT EXISTS `guest` (
  `identificationNumber` int(11) NOT NULL,
  `visitorsTaxClass_ID` int(11) NOT NULL,
  PRIMARY KEY (`identificationNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guestlist`
--

CREATE TABLE IF NOT EXISTS `guestlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `guest_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `identificationNumber` int(11) NOT NULL,
  `name` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `firstName` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `address_ID` int(11) NOT NULL,
  `dateOfBirth` date NOT NULL,
  PRIMARY KEY (`identificationNumber`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`identificationNumber`, `name`, `firstName`, `address_ID`, `dateOfBirth`) VALUES
(123456789, 'Giesel', 'Benedikt', 1, '1992-02-24');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pitch`
--

CREATE TABLE IF NOT EXISTS `pitch` (
  `id` int(11) NOT NULL,
  `district` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `type` enum('camper pitch','parking place','tent pitch','') COLLATE latin1_german1_ci NOT NULL,
  `length` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `natureOfSoil` enum('grass','gravel','sand','soil') COLLATE latin1_german1_ci NOT NULL,
  `deliveryPoint_ID` int(11) NOT NULL,
  `characteristics` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pitchbooking`
--

CREATE TABLE IF NOT EXISTS `pitchbooking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitch_ID` int(11) NOT NULL,
  `electricity` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pitchbookinglist`
--

CREATE TABLE IF NOT EXISTS `pitchbookinglist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `pitchBooking_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitch_ID` int(11) NOT NULL,
  `site_ID` int(11) NOT NULL,
  `emplyeeRole_ID` int(11) NOT NULL,
  `description` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `creationDate` date NOT NULL,
  `priority` int(11) NOT NULL,
  `doneDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `site`
--

CREATE TABLE IF NOT EXISTS `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `typ` enum('activity','electrical power','entrance','fireplace','other','reception','sale','sanitary facillities','service','utillity room','','','') COLLATE latin1_german1_ci NOT NULL,
  `openingHours` varchar(255) COLLATE latin1_german1_ci NOT NULL,
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
  `postalCode` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `country_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `visitorstaxclass`
--

CREATE TABLE IF NOT EXISTS `visitorstaxclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labeling` enum('busy season','busy season reduced','low season','low season reduced') COLLATE latin1_german1_ci NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci AUTO_INCREMENT=5 ;

--
-- Daten für Tabelle `visitorstaxclass`
--

INSERT INTO `visitorstaxclass` (`id`, `labeling`, `price`) VALUES
(1, 'busy season', 2),
(2, 'busy season reduced', 1),
(3, 'low season', 1.5),
(4, 'low season reduced', 0.5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
