-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 20. Mrz 2018 um 12:03
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `flugbuchung`
--
CREATE DATABASE IF NOT EXISTS `flugbuchung` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `flugbuchung`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abflug`
--

CREATE TABLE IF NOT EXISTS `abflug` (
  `flugnr` varchar(12) NOT NULL DEFAULT '',
  `tag` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `abflug`
--

INSERT INTO `abflug` (`flugnr`, `tag`) VALUES
('AF123', '2012-10-06'),
('AF123', '2012-10-13'),
('AF123', '2012-10-15'),
('LH222', '2012-10-01'),
('LH222', '2012-10-08'),
('LH222', '2012-10-15'),
('LH412', '2012-10-03'),
('LH412', '2012-10-08'),
('LH412', '2012-10-17'),
('TA129', '2015-01-01');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchung`
--

CREATE TABLE IF NOT EXISTS `buchung` (
  `name` varchar(30) DEFAULT NULL,
  `ort` varchar(30) DEFAULT NULL,
  `flugnr` varchar(12) DEFAULT NULL,
  `tag` date DEFAULT NULL,
  `preis` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `buchung`
--

INSERT INTO `buchung` (`name`, `ort`, `flugnr`, `tag`, `preis`) VALUES
('Willi', 'DA', 'TA129', '2015-01-01', '10'),
('Maier', 'München', 'LH222', '2012-10-01', '500'),
('Maier', 'München', 'LH222', '2012-10-15', '250'),
('Weber', 'Darmstadt', 'LH222', '2012-10-08', '1100'),
('Weber', 'Darmstadt', 'LH222', '2012-10-15', '600'),
('Lux', 'Frankfurt', 'LH222', '2012-10-08', '950'),
('Lux', 'Frankfurt', 'AF123', '2012-10-13', '300'),
('Lux', 'Frankfurt', 'AF123', '2012-10-06', '300'),
('Weber', 'Darmstadt', 'AF123', '2012-10-06', '1100'),
('Weber', 'Darmstadt', 'AF123', '2012-10-13', '80'),
('Lux', 'Frankfurt', 'AF123', '2012-10-15', '200');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flug`
--

CREATE TABLE IF NOT EXISTS `flug` (
  `flugnr` varchar(12) NOT NULL,
  `flugzeug` int(11) NOT NULL,
  `start` int(11) DEFAULT NULL,
  `ziel` int(11) DEFAULT NULL,
  `flugzeit` decimal(10,0) DEFAULT NULL,
  `km` decimal(10,0) DEFAULT NULL,
  `timeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `flug`
--

INSERT INTO `flug` (`flugnr`, `flugzeug`, `start`, `ziel`, `flugzeit`, `km`, `timeStamp`) VALUES
('AF123', 2, 2, 4, '2', '500', '2018-03-19 18:29:48'),
('DE344', 1, 4, 2, '2', '800', '2018-03-19 18:29:48'),
('DE394', 2, 2, 10, '2', '200', '2018-03-20 08:11:15'),
('DE454', 3, 3, 1, '1', '250', '2018-03-19 18:29:48'),
('GE444', 1, 1, 2, '2', '200', '2018-03-19 18:29:48'),
('GE453', 2, 1, 3, '2', '250', '2018-03-19 18:29:48'),
('KM343', 4, 2, 3, '2', '300', '2018-03-19 18:29:48'),
('LH222', 1, 3, 2, '8', '6000', '2018-03-19 18:29:48'),
('LH412', 2, 1, 4, '13', '110000', '2018-03-19 18:29:48'),
('ME932', 1, 4, 1, '8', '700', '2018-03-19 18:29:48'),
('MG445', 4, 1, 2, '2', '200', '2018-03-19 18:31:28'),
('NA454', 1, 4, 3, '1', '100', '2018-03-19 18:29:48'),
('TA129', 4, 2, 1, '2', '650', '2018-03-19 18:29:48');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flughäfen`
--

CREATE TABLE IF NOT EXISTS `flughäfen` (
`ID` int(11) NOT NULL,
  `Bezeichnung` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `flughäfen`
--

INSERT INTO `flughäfen` (`ID`, `Bezeichnung`) VALUES
(2, 'Berlin'),
(10, 'Bremen'),
(1, 'Hannover'),
(3, 'München'),
(4, 'Stuttgart');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fluglinien`
--

CREATE TABLE IF NOT EXISTS `fluglinien` (
`ID` int(11) NOT NULL,
  `Bezeichnung` varchar(255) NOT NULL,
  `Gesellschaft` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `fluglinien`
--

INSERT INTO `fluglinien` (`ID`, `Bezeichnung`, `Gesellschaft`) VALUES
(1, '1I', 'Deutsche Rettungsflugwacht'),
(2, '2A', 'Deutsche Bahn'),
(3, '3D', 'Dokasch Air Cargo Equipment'),
(4, '3S', 'Aerologic'),
(5, '4U', 'Germanwings'),
(6, '5P', 'ACM Air Charter'),
(7, '6U', 'Air Cargo Germany'),
(8, 'AB', 'Air Berlin'),
(9, 'DE', 'Condor Flugdienst'),
(10, 'EW', 'Eurowings'),
(11, 'LH', 'Lufthansa'),
(12, 'X3', 'TUIfly');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeuge`
--

CREATE TABLE IF NOT EXISTS `flugzeuge` (
`id` int(11) NOT NULL,
  `hersteller` enum('Boeing','Airbus','Lockheed','Messerschmitt') NOT NULL,
  `type` varchar(20) NOT NULL,
  `sitze` int(11) NOT NULL,
  `fluglinie` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `fluggesellschaft` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `flugzeuge`
--

INSERT INTO `flugzeuge` (`id`, `hersteller`, `type`, `sitze`, `fluglinie`, `fluggesellschaft`) VALUES
(1, 'Boeing', 'A380', 500, 'AF123', 2),
(2, 'Messerschmitt', 'BF 109', 1, 'AF123', 1),
(3, 'Lockheed', 'L-1011 TriStar', 150, 'AF123', 4),
(4, 'Boeing', '777', 777, 'AF123', 5),
(5, 'Boeing', '747', 450, 'LH412', 8),
(6, 'Boeing', '747', 450, NULL, 8),
(7, 'Boeing', '747', 450, NULL, 8),
(8, 'Boeing', '747', 450, 'NA454', 8),
(9, 'Boeing', '747', 450, NULL, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `passagier`
--

CREATE TABLE IF NOT EXISTS `passagier` (
  `name` varchar(30) NOT NULL,
  `ort` varchar(30) NOT NULL,
  `geburtsdatum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `passagier`
--

INSERT INTO `passagier` (`name`, `ort`, `geburtsdatum`) VALUES
('Lux', 'Frankfurt', '1985-05-09'),
('Maier', 'München', '1980-03-27'),
('Weber', 'Darmstadt', '1968-09-15'),
('Willi', 'DA', '2015-01-01');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `relationen`
--

CREATE TABLE IF NOT EXISTS `relationen` (
`ID` int(11) NOT NULL,
  `Startort` int(11) NOT NULL,
  `Zielort` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `relationen`
--

INSERT INTO `relationen` (`ID`, `Startort`, `Zielort`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 10, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`ID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`ID`, `username`, `passwort`, `type`) VALUES
(1, 'Admin', '098f6bcd4621d373cade4e832627b4f6', 'Manager'),
(2, 'Lukas', '098F6BCD4621D373CADE4E832627B4F6', 'Mitarbeiter');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `abflug`
--
ALTER TABLE `abflug`
 ADD PRIMARY KEY (`flugnr`,`tag`);

--
-- Indizes für die Tabelle `buchung`
--
ALTER TABLE `buchung`
 ADD KEY `fk_abflug_tag` (`flugnr`,`tag`), ADD KEY `fk_passagier_name_ort` (`name`,`ort`);

--
-- Indizes für die Tabelle `flug`
--
ALTER TABLE `flug`
 ADD PRIMARY KEY (`flugnr`);

--
-- Indizes für die Tabelle `flughäfen`
--
ALTER TABLE `flughäfen`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `Bezeichnung` (`Bezeichnung`);

--
-- Indizes für die Tabelle `fluglinien`
--
ALTER TABLE `fluglinien`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `Bezeichnung` (`Bezeichnung`), ADD UNIQUE KEY `Gesellschaft` (`Gesellschaft`);

--
-- Indizes für die Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
 ADD PRIMARY KEY (`id`), ADD KEY `fluglinie` (`fluglinie`);

--
-- Indizes für die Tabelle `passagier`
--
ALTER TABLE `passagier`
 ADD PRIMARY KEY (`name`,`ort`);

--
-- Indizes für die Tabelle `relationen`
--
ALTER TABLE `relationen`
 ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `flughäfen`
--
ALTER TABLE `flughäfen`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT für Tabelle `fluglinien`
--
ALTER TABLE `fluglinien`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT für Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `relationen`
--
ALTER TABLE `relationen`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `abflug`
--
ALTER TABLE `abflug`
ADD CONSTRAINT `fk_flug_flugnr` FOREIGN KEY (`flugnr`) REFERENCES `flug` (`flugnr`);

--
-- Constraints der Tabelle `buchung`
--
ALTER TABLE `buchung`
ADD CONSTRAINT `fk_abflug_tag` FOREIGN KEY (`flugnr`, `tag`) REFERENCES `abflug` (`flugnr`, `tag`),
ADD CONSTRAINT `fk_passagier_name_ort` FOREIGN KEY (`name`, `ort`) REFERENCES `passagier` (`name`, `ort`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
