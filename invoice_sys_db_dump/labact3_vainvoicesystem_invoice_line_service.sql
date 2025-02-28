-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: labact3_vainvoicesystem
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `invoice_line_service`
--

DROP TABLE IF EXISTS `invoice_line_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_line_service` (
  `Invoice_No` int NOT NULL,
  `Service_No` int NOT NULL,
  `Total_Hours_Rendered` int DEFAULT NULL,
  `Service_Subtotal` int DEFAULT NULL,
  PRIMARY KEY (`Invoice_No`,`Service_No`),
  KEY `invoice_line_service_ibfk_2` (`Service_No`),
  CONSTRAINT `invoice_line_service_ibfk_1` FOREIGN KEY (`Invoice_No`) REFERENCES `invoice` (`Invoice_No`),
  CONSTRAINT `invoice_line_service_ibfk_2` FOREIGN KEY (`Service_No`) REFERENCES `service` (`Service_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_line_service`
--

LOCK TABLES `invoice_line_service` WRITE;
/*!40000 ALTER TABLE `invoice_line_service` DISABLE KEYS */;
INSERT INTO `invoice_line_service` VALUES (1,1,15,NULL),(1,2,20,NULL),(1840,1,20,500),(1840,2,12,360),(4299,1,12,300),(4299,2,12,360),(4299,5,20,400),(4299,7,20,200),(4331,1,1,25),(4623,1,12,300),(5005,1,12,300),(5005,5,12,240),(5243,1,35,875),(5243,4,12,120),(5243,5,20,400),(6045,1,3,75),(6045,2,6,180),(6045,4,10,100),(7037,2,20,600),(7037,6,40,1000),(7037,7,20,200),(7107,1,25,625),(7107,2,16,480);
/*!40000 ALTER TABLE `invoice_line_service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-28 23:40:14
