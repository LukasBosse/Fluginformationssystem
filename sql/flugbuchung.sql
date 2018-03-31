-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 31. Mrz 2018 um 19:20
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
('LH412', '2012-10-17');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchung`
--

CREATE TABLE `buchung` (
  `ID` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `geschlecht` int(11) NOT NULL,
  `ort` varchar(30) DEFAULT NULL,
  `flugnr` varchar(12) DEFAULT NULL,
  `tag` date DEFAULT NULL,
  `preis` decimal(10,0) DEFAULT NULL,
  `bestaetigt` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `buchung`
--

INSERT INTO `buchung` (`ID`, `name`, `geschlecht`, `ort`, `flugnr`, `tag`, `preis`, `bestaetigt`) VALUES
(2, 'Maier', 0, 'München', 'LH222', '2012-10-01', '500', 1),
(3, 'Maier', 0, 'München', 'LH222', '2012-10-15', '250', 0),
(4, 'Weber', 0, 'Darmstadt', 'LH222', '2012-10-08', '1100', 1),
(5, 'Weber', 0, 'Darmstadt', 'LH222', '2012-10-15', '600', 1),
(6, 'Lux', 0, 'Frankfurt', 'LH222', '2012-10-08', '950', 0),
(7, 'Lux', 0, 'Frankfurt', 'AF123', '2012-10-13', '300', 1),
(8, 'Lux', 0, 'Frankfurt', 'AF123', '2012-10-06', '300', 1),
(9, 'Weber', 0, 'Darmstadt', 'AF123', '2012-10-06', '1100', 0),
(10, 'Weber', 0, 'Darmstadt', 'AF123', '2012-10-13', '80', 1),
(11, 'Lux', 0, 'Frankfurt', 'AF123', '2012-10-15', '200', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flug`
--

CREATE TABLE `flug` (
  `flugnr` varchar(12) NOT NULL,
  `flugzeug` int(11) NOT NULL,
  `Startzeit` time NOT NULL,
  `Landezeit` time NOT NULL,
  `flugzeit` decimal(10,2) DEFAULT NULL,
  `km` int(11) DEFAULT NULL,
  `timeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `inklusiveMahlzeit` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `flug`
--

INSERT INTO `flug` (`flugnr`, `flugzeug`, `Startzeit`, `Landezeit`, `flugzeit`, `km`, `timeStamp`, `inklusiveMahlzeit`) VALUES
('AF123', 5, '12:43:00', '14:43:00', '2.00', 500, '2018-03-19 18:29:48', 1),
('LH222', 9, '10:00:00', '18:00:00', '8.00', 6000, '2018-03-19 18:29:48', 1),
('LH412', 11, '10:00:00', '23:00:00', '13.00', 110000, '2018-03-19 18:29:48', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fluggesellschaften`
--

CREATE TABLE `fluggesellschaften` (
  `ID` int(11) NOT NULL,
  `Bezeichnung` varchar(255) NOT NULL,
  `Gesellschaft` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `fluggesellschaften`
--

INSERT INTO `fluggesellschaften` (`ID`, `Bezeichnung`, `Gesellschaft`) VALUES
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
-- Tabellenstruktur für Tabelle `flughäfen`
--

CREATE TABLE `flughäfen` (
  `ID` int(11) NOT NULL,
  `Bezeichnung` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `flughäfen`
--

INSERT INTO `flughäfen` (`ID`, `Bezeichnung`) VALUES
(2, 'Berlin'),
(12, 'Bonn'),
(10, 'Bremen'),
(1, 'Hannover'),
(3, 'München'),
(4, 'Stuttgart');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fluglinien`
--

CREATE TABLE `fluglinien` (
  `Fluglinie` varchar(255) NOT NULL,
  `Startort` int(11) NOT NULL,
  `Zielort` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `fluglinien`
--

INSERT INTO `fluglinien` (`Fluglinie`, `Startort`, `Zielort`) VALUES
('AF123', 2, 4),
('LH222', 3, 2),
('LH412', 1, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeuge`
--

CREATE TABLE `flugzeuge` (
  `id` int(11) NOT NULL,
  `hersteller` enum('Boeing','Airbus','Lockheed','Messerschmitt') NOT NULL,
  `type` varchar(20) NOT NULL,
  `sitze` int(11) NOT NULL,
  `fluglinie` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `fluggesellschaft` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `flugzeuge`
--

INSERT INTO `flugzeuge` (`id`, `hersteller`, `type`, `sitze`, `fluglinie`, `fluggesellschaft`) VALUES
(5, 'Boeing', '747', 450, 'LH412', 8),
(9, 'Boeing', '797', 300, 'LH412', 4),
(11, 'Boeing', '747', 300, 'LH412', 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `passagier`
--

CREATE TABLE `passagier` (
  `name` varchar(30) NOT NULL,
  `geschlecht` tinyint(1) NOT NULL,
  `ort` varchar(30) NOT NULL,
  `geburtsdatum` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `passagier`
--

INSERT INTO `passagier` (`name`, `geschlecht`, `ort`, `geburtsdatum`) VALUES
('Bosse', 1, 'Hannover', '05.11.1995'),
('Doe', 1, 'Doehausen', '27.12.1960'),
('Lux', 1, 'Frankfurt', '09.05.1989'),
('Maier', 1, 'München', '27.03.1970'),
('Mustermann', 0, 'Musterstadt', '11.02.2000'),
('Weber', 0, 'Darmstadt', '15.09.1969'),
('Willi', 0, 'DA', '01.01.2000');

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
(1, 'Admin', '098F6BCD4621D373CADE4E832627B4F6', 'Manager'),
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
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_abflug_tag` (`flugnr`,`tag`),
  ADD KEY `fk_passagier_name_ort` (`name`,`ort`);

--
-- Indizes für die Tabelle `flug`
--
ALTER TABLE `flug`
  ADD PRIMARY KEY (`flugnr`);

--
-- Indizes für die Tabelle `fluggesellschaften`
--
ALTER TABLE `fluggesellschaften`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Bezeichnung` (`Bezeichnung`),
  ADD UNIQUE KEY `Gesellschaft` (`Gesellschaft`);

--
-- Indizes für die Tabelle `flughäfen`
--
ALTER TABLE `flughäfen`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Bezeichnung` (`Bezeichnung`);

--
-- Indizes für die Tabelle `fluglinien`
--
ALTER TABLE `fluglinien`
  ADD PRIMARY KEY (`Fluglinie`),
  ADD UNIQUE KEY `Fluglinie` (`Fluglinie`);

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
-- AUTO_INCREMENT für Tabelle `buchung`
--
ALTER TABLE `buchung`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT für Tabelle `fluggesellschaften`
--
ALTER TABLE `fluggesellschaften`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT für Tabelle `flughäfen`
--
ALTER TABLE `flughäfen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT für Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
