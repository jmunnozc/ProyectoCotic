-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cotic
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `usuariodispositivo`
--

DROP TABLE IF EXISTS `usuariodispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariodispositivo` (
  `codigoUsuarioDispositivo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoDispositivo` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL,
  `usuarioCreacion` varchar(45) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `usuarioModificacion` varchar(45) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `codigoUsuario` int(11) NOT NULL,
  PRIMARY KEY (`codigoUsuarioDispositivo`),
  UNIQUE KEY `codigoDispositivo_UNIQUE` (`codigoDispositivo`),
  KEY `fk_usuarioDispositivo_usuario_idx` (`codigoUsuario`),
  CONSTRAINT `fk_usuarioDispositivo_usuario` FOREIGN KEY (`codigoUsuario`) REFERENCES `usuario` (`codigoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariodispositivo`
--

LOCK TABLES `usuariodispositivo` WRITE;
/*!40000 ALTER TABLE `usuariodispositivo` DISABLE KEYS */;
INSERT INTO `usuariodispositivo` VALUES (1,'XXXXXX',1,'JAMBROCIO','2017-05-31 00:00:00',NULL,NULL,1),(2,'AAAAAA',1,'JAMBROCIO','2017-05-31 00:00:00',NULL,NULL,1),(3,'7876F0DF601967C4',1,'JAMBROCIO','2017-08-21 16:31:49',NULL,NULL,2);
/*!40000 ALTER TABLE `usuariodispositivo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-09 12:55:03
