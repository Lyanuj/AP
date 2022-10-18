-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 18 oct. 2022 à 13:00
-- Version du serveur :  10.5.6-MariaDB-1:10.5.6+maria~stretch
-- Version de PHP : 7.3.19-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `Ap_canin`
--

-- --------------------------------------------------------

--
-- Structure de la table `Chien`
--

CREATE TABLE `Chien` (
  `FAPAC` int(11) NOT NULL,
  `id_club` int(11) DEFAULT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `race` varchar(15) DEFAULT NULL,
  `dateN` date DEFAULT NULL,
  `sexe` varchar(15) DEFAULT NULL,
  `ct` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Classe`
--

CREATE TABLE `Classe` (
  `id` int(11) NOT NULL,
  `libelle` varchar(150) DEFAULT NULL,
  `Nbexo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Clubs`
--

CREATE TABLE `Clubs` (
  `id` int(11) NOT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `adresse` varchar(15) DEFAULT NULL,
  `cp` varchar(15) DEFAULT NULL,
  `ville` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Concours`
--

CREATE TABLE `Concours` (
  `id` int(11) NOT NULL,
  `id_club` int(11) DEFAULT NULL,
  `id_juge` int(11) DEFAULT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `dateD` date DEFAULT NULL,
  `dateF` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Conducteur`
--

CREATE TABLE `Conducteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `prenom` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `evaluer`
--

CREATE TABLE `evaluer` (
  `id_incription` int(11) NOT NULL,
  `id_exercices` int(11) NOT NULL,
  `notes` float DEFAULT NULL,
  `commentaires` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='2clé primaire 1 et 2';

-- --------------------------------------------------------

--
-- Structure de la table `Exercice`
--

CREATE TABLE `Exercice` (
  `id` int(11) NOT NULL,
  `libelle` varchar(150) DEFAULT NULL,
  `coef` int(2) DEFAULT NULL,
  `id_classe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `id` int(11) NOT NULL,
  `id_concours` int(11) DEFAULT NULL,
  `id_chien` int(11) DEFAULT NULL,
  `id_classe` int(11) DEFAULT NULL,
  `id_conducteur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Juge`
--

CREATE TABLE `Juge` (
  `id` int(15) NOT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `prenom` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Chien`
--
ALTER TABLE `Chien`
  ADD PRIMARY KEY (`FAPAC`),
  ADD KEY `FK_Chien_Clubs` (`id_club`);

--
-- Index pour la table `Classe`
--
ALTER TABLE `Classe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Clubs`
--
ALTER TABLE `Clubs`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Concours`
--
ALTER TABLE `Concours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Concours_Clubs` (`id_club`),
  ADD KEY `FK_Concours_Juge` (`id_juge`);

--
-- Index pour la table `Conducteur`
--
ALTER TABLE `Conducteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evaluer`
--
ALTER TABLE `evaluer`
  ADD PRIMARY KEY (`id_incription`,`id_exercices`),
  ADD KEY `FK_evaluer_Exercice` (`id_exercices`,`id_incription`) USING BTREE;

--
-- Index pour la table `Exercice`
--
ALTER TABLE `Exercice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Exercice_Classe` (`id_classe`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Index 2` (`id_concours`),
  ADD KEY `Index 3` (`id_chien`),
  ADD KEY `Index 4` (`id_classe`),
  ADD KEY `Index 5` (`id_conducteur`);

--
-- Index pour la table `Juge`
--
ALTER TABLE `Juge`
  ADD PRIMARY KEY (`id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Chien`
--
ALTER TABLE `Chien`
  ADD CONSTRAINT `FK_Chien_Clubs` FOREIGN KEY (`id_club`) REFERENCES `Clubs` (`id`);

--
-- Contraintes pour la table `Concours`
--
ALTER TABLE `Concours`
  ADD CONSTRAINT `FK_Concours_Clubs` FOREIGN KEY (`id_club`) REFERENCES `Clubs` (`id`),
  ADD CONSTRAINT `FK_Concours_Juge` FOREIGN KEY (`id_juge`) REFERENCES `Juge` (`id`);

--
-- Contraintes pour la table `evaluer`
--
ALTER TABLE `evaluer`
  ADD CONSTRAINT `FK_evaluer_Exercice` FOREIGN KEY (`id_exercices`) REFERENCES `Exercice` (`id`),
  ADD CONSTRAINT `FK_evaluer_inscription` FOREIGN KEY (`id_incription`) REFERENCES `inscription` (`id`);

--
-- Contraintes pour la table `Exercice`
--
ALTER TABLE `Exercice`
  ADD CONSTRAINT `FK_Exercice_Classe` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK_inscription_Chien` FOREIGN KEY (`id_chien`) REFERENCES `Chien` (`FAPAC`),
  ADD CONSTRAINT `FK_inscription_Classe` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id`),
  ADD CONSTRAINT `FK_inscription_Concours` FOREIGN KEY (`id_concours`) REFERENCES `Concours` (`id`),
  ADD CONSTRAINT `FK_inscription_Conducteur` FOREIGN KEY (`id_conducteur`) REFERENCES `Conducteur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
