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
-- Table structure for table `alternativa`
--

DROP TABLE IF EXISTS `alternativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alternativa` (
  `codigoAlternativa` int(11) NOT NULL AUTO_INCREMENT,
  `tituloAlternativa` varchar(200) NOT NULL,
  `flagRespuestaCorrectaAlternativa` int(11) NOT NULL,
  `codigoPregunta` int(11) NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`codigoAlternativa`),
  KEY `fk_alternativa_pregunta_idx` (`codigoPregunta`),
  CONSTRAINT `fk_alternativa_pregunta` FOREIGN KEY (`codigoPregunta`) REFERENCES `pregunta` (`codigoPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alternativa`
--

LOCK TABLES `alternativa` WRITE;
/*!40000 ALTER TABLE `alternativa` DISABLE KEYS */;
INSERT INTO `alternativa` VALUES (1,'Una hoja de cálculo',0,1,1),(2,'Una hoja de respuestas',0,1,1),(3,'Un procesador de texto',1,1,1),(4,'Un programador',0,1,1),(5,'Cerrar el programa - Oprimir el botón del computador',0,2,1),(6,'Apagar directamente el estabilizador de corriente',0,2,1),(7,'Guardar los cambios del documento - Seleccionar apagar el computador - Apagar',1,2,1),(8,'Guardar los cambios de documento - Apagar el estabilizador',0,2,1),(9,'Verdadero',0,3,1),(10,'Falso',1,3,1),(11,'Verdadero',1,4,1),(12,'Falso',0,4,1),(13,'Indicar la cantidad de columnas y filas deseadas',1,5,1),(14,'Dibujar la tabla en paint y luego copiarla y pegarla en word',0,5,1),(15,'No se pueden insertar en Microsoft Word',0,5,1),(16,'Indicar el número de palabras y renglones deseados',0,5,1),(17,'Verdadero',0,6,1),(18,'Falso',1,6,1),(19,'Ubicarse en el escritorio- Dar clic derecho - Seleccionar nueva carpeta - Cambiar de Nombre',1,7,1),(20,'Ubicarse en el escritorio - Seleccionar una carpeta - Cambiar de nombre',0,7,1),(21,'Ubicarse en el escritorio - Dar clic izquierdo- Guardar la carpeta',0,7,1),(22,'Ubicarse en el escritorio - Buscar una carpeta- Cambiar de nombre',0,7,1),(23,'Verdadero',1,8,1),(24,'Falso',0,8,1),(25,'Verdadero',1,9,1),(26,'Falso',0,9,1),(27,'Documento',0,10,1),(28,'Libro',1,10,1),(29,'Presentación',0,10,1),(30,'Archivo',0,10,1),(31,'Un cajón',0,11,1),(32,'Un cuadro de texto',0,11,1),(33,'La Unión de una Columna, con una Fila',1,11,1),(34,'Unión de un forma vertical con una forma estatal',0,11,1),(35,'Con los números Naturales',0,12,1),(36,'Con la letra Gama',0,12,1),(37,'Con la Letra Beta',0,12,1),(38,'Todas las anteriores son falsas',1,12,1),(39,'El reloj y la fecha',0,13,1),(40,'La Negrita, la Cursiva, Subrayado',0,13,1),(41,'La barra de Herramientas, la barra de estado, encabezado de Columna, encabezado de Fila, entre otros',0,13,1),(42,'Nada que ver con la pregunta, las anteriores, A, B, y C.',1,13,1),(43,'Libro de trabajo, para digitar textos',0,14,1),(44,'Sirve para llevar los Datos, de toda una empresa',0,14,1),(45,'Aplicación utilizada para llevar el presupuesto, análisis estadísticos, y la contabilidad',1,14,1),(46,'Permite realizar presentaciones',0,14,1),(47,'Ctrl + 4',0,15,1),(48,'Ctrl + Shift :',0,15,1),(49,'Ctrl + 1',1,15,1),(50,'Ctrl + Uno',0,15,1),(51,'Porcentaje (%)',0,16,1),(52,'Asterisco (*)',1,16,1),(53,'Back Slash (\\)',0,16,1),(54,'Slash (/)',0,16,1),(55,'Salario /2 X días Laborados sobre 360',0,17,1),(56,'Salario X Cesantías sobre 360',0,17,1),(57,'Salario X Días Laborados sobre 365',1,17,1),(58,'Salario X Días Laborados sobre 360',0,17,1),(59,'La satisfacción de los clientes',0,18,1),(60,'La identificación de los clientes',0,18,1),(61,'Una actitud pro-activa',1,18,1),(62,'Verdadero',1,19,1),(63,'Falso',0,19,1),(64,'Oferta y demanda',0,20,1),(65,'Costo y beneficio',1,20,1),(66,'Precio y calidad',0,20,1),(67,'El cliente conversador',0,21,1),(68,'El cliente discutidor',0,21,1),(69,'El cliente infeliz',1,21,1),(70,'Ayúdame a ayudarte',0,22,1),(71,'Justifica porqué te debo de comprar a ti',0,22,1),(72,'Siempre dime la verdad',1,22,1),(73,'Verdadero',1,23,1),(74,'Falso',0,23,1),(75,'Empresa',0,24,1),(76,'Nadie',0,24,1),(77,'Persona',1,24,1),(78,'Intrinseco',1,25,1),(79,'Extrinseco',0,25,1),(80,'No es un valor',0,25,1),(81,'No',1,26,1),(82,'Si',0,26,1),(83,'De vez en cuando',0,26,1),(84,'\"Win-win\"',1,27,1),(85,'Esfuerzo + provecho',0,27,1),(86,'Responsabilidad',0,27,1),(87,'Actuación profesional',1,28,1),(88,'Responsabilidad',0,28,1),(89,'Actuación unida',0,28,1),(90,'Una responsabilidad',0,29,1),(91,'Una esfuerzo',0,29,1),(92,'Una riesgo',1,29,1),(93,'practica',0,30,1),(94,'exigencia',1,30,1),(95,'tolerancia',0,30,1),(96,'A guiar nuestras acciones y elecciones individuales',1,31,1),(97,'A ser mejores personas',0,31,1),(98,'A progresar',0,31,1),(99,'La ética y los valores',0,32,1),(100,'La ética y la moral',0,32,1),(101,'La ética y la conducta empresarial',1,32,1);
/*!40000 ALTER TABLE `alternativa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-09 12:55:04
