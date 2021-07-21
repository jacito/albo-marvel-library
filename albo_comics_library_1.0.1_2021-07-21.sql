# ************************************************************
# Sequel Pro SQL dump
# Versi�n 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: albo-comics-library.clo68xadu8ck.us-east-2.rds.amazonaws.com (MySQL 5.7.33-log)
# Base de datos: albo_comics_library_1.0.1
# Tiempo de Generaci�n: 2021-07-21 17:58:27 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla character_marvel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `character_marvel`;

CREATE TABLE `character_marvel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del personaje de interes',
  `character_principal_id` int(11) unsigned NOT NULL COMMENT 'Identificador único del personaje principal con el que se relaciona el registro',
  `marvel_id` int(11) unsigned NOT NULL COMMENT 'Identificar dentro del API de marvel',
  `marvel_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'Nombre del personaje identificado en el API de marvel',
  `marvel_date_modified` timestamp NULL DEFAULT NULL COMMENT 'Fecha de última modificación dentro del API de Marvel',
  `marvel_description` varchar(500) DEFAULT '' COMMENT 'Descripción del personaje dentro del API de Marvel',
  `marvel_comics_available` int(11) NOT NULL COMMENT 'Cantidad disponibles de publicaciones, en caso de tenerlas',
  PRIMARY KEY (`id`),
  KEY `character_principal_id_FK1` (`character_principal_id`),
  CONSTRAINT `character_principal_id_FK1` FOREIGN KEY (`character_principal_id`) REFERENCES `character_principal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que contiene la lista de personajes identificados con el nombre del principal, relacionados en el API de marvel';



# Volcado de tabla character_principal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `character_principal`;

CREATE TABLE `character_principal` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del personaje de interes',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT 'Nombre del personaje de interes',
  `marvel_name` varchar(20) NOT NULL COMMENT 'Nombre del personaje dentro del API de Marvel',
  `template` varchar(20) NOT NULL DEFAULT '' COMMENT 'Template de consulta del servicio',
  `system_update_date` timestamp NULL DEFAULT NULL COMMENT 'Fecha de última actualización',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla de los personajes principales de interes';

LOCK TABLES `character_principal` WRITE;
/*!40000 ALTER TABLE `character_principal` DISABLE KEYS */;

INSERT INTO `character_principal` (`id`, `name`, `marvel_name`, `template`, `system_update_date`)
VALUES
	(1,'Iron Man','Iron Man','ironman',NULL),
	(2,'Capitan America','Captain America','capamerica',NULL);

/*!40000 ALTER TABLE `character_principal` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla characters_relationship
# ------------------------------------------------------------

DROP TABLE IF EXISTS `characters_relationship`;

CREATE TABLE `characters_relationship` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la relación del personaje con otro personaje principal',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'Nombre del personaje',
  `marvel_id` int(11) NOT NULL COMMENT 'Identificar dentro del API de marvel',
  `character_principal_id` int(11) unsigned NOT NULL COMMENT 'Identificador único del personaje principal con el que se relaciona el registro',
  `comic_id` int(11) unsigned NOT NULL COMMENT 'Identificador único del comic con el que se relaciona el registro',
  PRIMARY KEY (`id`),
  KEY `character_principal_id_FK4` (`character_principal_id`),
  KEY `comic_id_FK5` (`comic_id`),
  CONSTRAINT `character_principal_id_FK4` FOREIGN KEY (`character_principal_id`) REFERENCES `character_principal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comic_id_FK5` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que enlaza a los personajes principales (character_principal), con otros personajes';



# Volcado de tabla comic
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comic`;

CREATE TABLE `comic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del comic',
  `marvel_id` int(10) unsigned NOT NULL COMMENT 'Identificar dentro del API de marvel',
  `character_principal_id` int(11) unsigned NOT NULL COMMENT 'Identificador único del personaje principal con el que se relaciona el registro',
  `marvel_title` varchar(200) NOT NULL DEFAULT '' COMMENT 'Titulo del comic deltro del API de marvel',
  `marvel_description` varchar(300) DEFAULT NULL COMMENT 'Descripción del comic dentro del API de Marvel',
  `marvel_date_modified` varchar(20) DEFAULT NULL COMMENT 'Fecha de última modificación dentro del API de Marvel',
  PRIMARY KEY (`id`),
  KEY `character_principal_id_FK2` (`character_principal_id`),
  CONSTRAINT `character_principal_id_FK2` FOREIGN KEY (`character_principal_id`) REFERENCES `character_principal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que enlaza a los personajes principales (character_principal), con sus publicaciones';



# Volcado de tabla creator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `creator`;

CREATE TABLE `creator` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del creador de interes',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'Nombre del creador',
  `role` varchar(20) NOT NULL DEFAULT '' COMMENT 'Nombre del rol que desempeño el creador',
  `comic_id` int(11) unsigned NOT NULL COMMENT 'Identificador único del comic con el que se relaciona el registro',
  PRIMARY KEY (`id`),
  KEY `comic_id_FK3` (`comic_id`),
  CONSTRAINT `comic_id_FK3` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que enlaza a los personajes principales (character_principal), con los creadores de sus comics';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
