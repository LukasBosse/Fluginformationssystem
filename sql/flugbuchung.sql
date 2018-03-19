-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 19. Mrz 2018 um 16:54
-- Server-Version: 10.1.25-MariaDB
-- PHP-Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `flugbuchung`
--
CREATE DATABASE IF NOT EXISTS `flugbuchung` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `flugbuchung`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abflug`
--

CREATE TABLE `abflug` (
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

CREATE TABLE `buchung` (
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

CREATE TABLE `flug` (
  `flugnr` varchar(12) NOT NULL,
  `flugzeug` varchar(255) NOT NULL,
  `start` varchar(30) DEFAULT NULL,
  `ziel` varchar(30) DEFAULT NULL,
  `flugzeit` decimal(10,0) DEFAULT NULL,
  `km` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `flug`
--

INSERT INTO `flug` (`flugnr`, `flugzeug`, `start`, `ziel`, `flugzeit`, `km`) VALUES
('AF123', 'Boeing - A380', 'Paris', 'Frankfurt', '2', '500'),
('DE344', 'Lockheed - L-1011 TriStar', 'Bremen', 'München', '2', '800'),
('DE454', 'Boeing - 777', 'Hannover', 'Berlin', '1', '250'),
('KM343', 'Boeing - 777', 'Berlin', 'Hannover', '2', '300'),
('LH222', 'Messerschmitt - BF 109', 'Frankfurt', 'New York', '8', '6000'),
('LH412', 'Messerschmitt - BF 109', 'München', 'Los Angeles', '13', '110000'),
('ME932', 'Boeing - A380', 'Bremen', 'München', '8', '700'),
('NA454', 'Messerschmitt - BF 109', 'Hannover', 'Braunschweig', '1', '100'),
('TA129', 'Lockheed - L-1011 TriStar', 'Paris', 'Frankfurt', '2', '650');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeuge`
--

CREATE TABLE `flugzeuge` (
  `id` int(11) NOT NULL,
  `hersteller` enum('Boeing','Airbus','Lockheed','Messerschmitt') NOT NULL,
  `type` varchar(20) NOT NULL,
  `sitze` int(11) NOT NULL,
  `fluglinie` varchar(12) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `flugzeuge`
--

INSERT INTO `flugzeuge` (`id`, `hersteller`, `type`, `sitze`, `fluglinie`) VALUES
(1, 'Boeing', 'A380', 500, 'AF123'),
(2, 'Messerschmitt', 'BF 109', 1, 'AF123'),
(3, 'Lockheed', 'L-1011 TriStar', 150, 'AF123'),
(4, 'Boeing', '777', 777, 'AF123');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `passagier`
--

CREATE TABLE `passagier` (
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
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD KEY `fk_abflug_tag` (`flugnr`,`tag`),
  ADD KEY `fk_passagier_name_ort` (`name`,`ort`);

--
-- Indizes für die Tabelle `flug`
--
ALTER TABLE `flug`
  ADD PRIMARY KEY (`flugnr`);

--
-- Indizes für die Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fluglinie` (`fluglinie`);

--
-- Indizes für die Tabelle `passagier`
--
ALTER TABLE `passagier`
  ADD PRIMARY KEY (`name`,`ort`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
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
  ADD CONSTRAINT `fk_abflug_tag` FOREIGN KEY (`flugnr`,`tag`) REFERENCES `abflug` (`flugnr`, `tag`),
  ADD CONSTRAINT `fk_passagier_name_ort` FOREIGN KEY (`name`,`ort`) REFERENCES `passagier` (`name`, `ort`);

--
-- Constraints der Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  ADD CONSTRAINT `flugzeug_fluglinie` FOREIGN KEY (`fluglinie`) REFERENCES `flug` (`flugnr`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
