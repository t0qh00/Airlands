-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: bd_airlands
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `tb_administrativo`
--

DROP TABLE IF EXISTS `tb_administrativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_administrativo` (
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_administrativo`
--

LOCK TABLES `tb_administrativo` WRITE;
/*!40000 ALTER TABLE `tb_administrativo` DISABLE KEYS */;
INSERT INTO `tb_administrativo` VALUES ('admin','admin');
/*!40000 ALTER TABLE `tb_administrativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_consulta`
--

DROP TABLE IF EXISTS `tb_consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_consulta` (
  `nombre` varchar(40) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `tipoConsulta` varchar(45) DEFAULT NULL,
  `comentario` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_consulta`
--

LOCK TABLES `tb_consulta` WRITE;
/*!40000 ALTER TABLE `tb_consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_factura`
--

DROP TABLE IF EXISTS `tb_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_factura` (
  `usuario` varchar(45) DEFAULT NULL,
  `nombreA` varchar(45) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `monto` varchar(45) DEFAULT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_factura`
--

LOCK TABLES `tb_factura` WRITE;
/*!40000 ALTER TABLE `tb_factura` DISABLE KEYS */;
INSERT INTO `tb_factura` VALUES ('as','Avion de combate','2','6780000.0',1),('as','Avion de la primera guerra mundial','5','3.955E7',2),('grey','Avioneta','2','678000.0',3),('as','Avion todoala','1','2825000.0',4),('jurgen','Zeppelin','10000000000','5.65E15',5),('qwe','Avion de la primera guerra mundial','6','4.746E7',6);
/*!40000 ALTER TABLE `tb_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_productos`
--

DROP TABLE IF EXISTS `tb_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_productos` (
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_productos`
--

LOCK TABLES `tb_productos` WRITE;
/*!40000 ALTER TABLE `tb_productos` DISABLE KEYS */;
INSERT INTO `tb_productos` VALUES ('Avion de carga','Para que quieres casa si puedes vivir en un avion mientras me pagas','air-force-2178863_640.jpg',150000),('Avion de combate','Perfecto para dar una vuelta y destruir a tus enemigos','fighter-jet-63028_640.jpg',3000001),('Avion de la primera guerra mundial','Pelea la batalla de tus antepasados ','primera guerra.jpeg',7000000),('Avion todoala','Aviones todoala, el nuevo diseño que ahorra combustible','todoala.jpg',2500000),('Avioneta','Mira los atardeceres mas cerca junto a tu familia','aircraft-547105_640.jpg',300000),('Boeing 777','Lleva a tus amigos de viaje a Paris y disfruta de 440 espacios para ti','boeing777.jpeg',480000),('Concorde','Disfruta de la velocidad y la adrenalina en el cielo','Concorde.jpeg',7508000),('Estrella de la muerte','Saca el dark vader que llevas dentro','estrella de la muerte.jpeg',9999999),('Globo aeroestatico','Relajate mientras vas en la direccion del viento','playlist-vuelo-globo-aerostatico.jpg',40000),('mh-53','No es un avion pero vuela, aparte puedes destruir ciudades','mh53.jpeg',400000),('Millenium Falcon','Para que mirar las estrellas de lejos si puedes estar junto a ellas','millenium-falcon-1627322_640.jpg',1260000),('Zeppelin','Haz la mejor publicidad desde las alturas','zepelin.jpeg',500000);
/*!40000 ALTER TABLE `tb_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usuarios` (
  `nombre` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `validacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES ('as','asd','asd','as','as','as'),('grey','gre@gmail.com','2233','grey','123','123'),('nel','nel','nel@gmail.com','nel','123','123'),('qwe','qwe','qwe','qwe','qwe','qwe');
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-09  9:16:43
