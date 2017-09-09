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
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `codigoPregunta` int(11) NOT NULL AUTO_INCREMENT,
  `tituloPregunta` varchar(300) NOT NULL,
  `codigoCurso` int(11) NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`codigoPregunta`),
  KEY `fk_curso_pregunta_idx` (`codigoCurso`),
  CONSTRAINT `fk_pregunta_curso` FOREIGN KEY (`codigoCurso`) REFERENCES `curso` (`codigoCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` VALUES (1,'1. ¿Qué es Microsoft Word?',1,1),(2,'2. Los pasos para apagar el computador después de trabajar en un documento son:',1,1),(3,'3. En un documento de WORD, no se pueden insertar imágenes ni realizar tablas de datos',1,1),(4,'4. Cuando un equipo se queda sin fuente de energía el programa Microsoft Word automáticamente guarda los cambios, aunque en ocasiones puede ser que no guarde los cambios más recientes.',1,1),(5,'5. Para insertar una tabla en Microsoft Word, es necesario :',1,1),(6,'6. Después de elegir el tipo de letra, el color y el tamaño de la letra deseada, no se puede volver a cambiar, sino hasta que se realice otro documento.',1,1),(7,'7. Para crear una carpeta en el escritorio los pasos son:',1,1),(8,'8. Una norma específica de la sala de sistemas es cuidar los equipo, encendiéndolos y apagándolos adecuadamente.',1,1),(9,'9. La clase de tecnología, puede ser fundamental para el desarrollo de otras asignaturas.',1,1),(10,'1. Un archivo que contiene una o más hojas de cálculo :',2,1),(11,'2. Una celda en una hoja de cálculo, es:',2,1),(12,'3. Las columnas en una hoja de cálculo, se distinguen de la siguiente manera:',2,1),(13,'4. Elementos de una hoja Electrónica o llamada hoja o libro de trabajo son:',2,1),(14,'5. La definición más completa de Excel - Xls. Es:',2,1),(15,'6. Para ingresar a Formato Celda, con las Teclas, se realiza con el siguiente comando.',2,1),(16,'7. El operador de la División',2,1),(17,'8. La fórmula, en una Liquidación Laboral, para saber el valor de las Cesantías, es la siguiente:',2,1),(18,'1. ¿Para satisfacer a los clientes no basta con eliminar los motivos de insatisfacción o de quejas, es necesario asumir?',3,1),(19,'2. ¿La identificación de los clientes de una organización debe iniciarse averiguando donde se encuentran los clientes externos y clientes internos y cuáles son sus necesidades?',3,1),(20,'3. ¿Los clientes deben percibir que en los productos y servicios que adquieren hay una relación de?',3,1),(21,'4. ¿Son el tipo de clientes que cuando entran a un establecimiento comercial tienen expresiones  cómo estoy seguro de que no tiene lo que busco?',3,1),(22,'5. ¿A qué tipo de regla nos referimos cuándo el cliente nos indica \"dime con precisión tus tiempos de entrega, la hora en la que me entregarás la cotización o si mi negocio verdaderamente te interesa\"?',3,1),(23,'6.¿La voz del cliente se escucha en el contexto; en sus expresiones verbales, pero principalmente en las no verbales gestos, actitud, entusiasmo, grado de confianza?',3,1),(24,'1. La ética empresarial es una exigencia de la _____________',4,1),(25,'2. ¿La ética que tipo de valor es?',4,1),(26,'3. ¿La ética individual y la ética organizacional se pueden separar?',4,1),(27,'4. ¿Cómo debe ser la fórmula de éxito?',4,1),(28,'5. ¿Una buena actuación ética es simultáneamente a una buena?',4,1),(29,'6. Toda decisión conlleva implícito__________',4,1),(30,'7. La ética es una ______________ que se hace más significativa mientras mayor es la complejidad social',4,1),(31,'8. ¿Los valores éticos en que nos ayudan?',4,1),(32,'9. Son temas de interes y candentes en la actualidad',4,1);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
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
