-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mercadinhoestoque
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `ID_Produto` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) NOT NULL,
  `Descricao` text,
  `Preco` decimal(10,2) NOT NULL,
  `Quantidade` int NOT NULL DEFAULT '0',
  `Tipo_Produto` varchar(50) DEFAULT NULL,
  `ID_Fornecedor` int DEFAULT NULL,
  `ID_Setor` int DEFAULT NULL,
  PRIMARY KEY (`ID_Produto`),
  KEY `ID_Fornecedor` (`ID_Fornecedor`),
  KEY `ID_Setor` (`ID_Setor`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedor` (`ID_Fornecedor`),
  CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`ID_Setor`) REFERENCES `setor` (`ID_Setor`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Maçã Fuji','Maçã vermelha importada',5.99,150,'Fruta',3,1),(2,'Leite Integral','Leite pasteurizado 1L',4.49,80,'Laticínio',4,2),(3,'Coca-Cola 2L','Refrigerante de cola',8.99,120,'Refrigerante',2,4),(4,'Arroz Branco 5kg','Arroz tipo 1',22.90,60,'Grão',1,5),(5,'Filé Mignon','Carne bovina premium',89.90,25,'Carne bovina',5,3),(6,'Sabão em Pó','Sabão para roupas 1kg',12.50,45,'Produto de limpeza',1,6),(7,'Pão Francês','Pão fresco do dia',0.99,200,'Pão',1,7),(8,'Queijo Mussarela','Queijo fatiado 500g',18.75,40,'Laticínio',4,2),(9,'Água Mineral 500ml','Água sem gás',2.25,180,'Água',2,4),(10,'Banana Prata','Banana nacional',3.99,200,'Fruta',3,1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27  9:52:38
