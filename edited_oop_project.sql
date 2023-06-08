CREATE DATABASE  IF NOT EXISTS `oop_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `oop_project`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: oop_project
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `auth_tbl`
--

DROP TABLE IF EXISTS `auth_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` int unsigned NOT NULL,
  `money` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_tbl`
--

LOCK TABLES `auth_tbl` WRITE;
/*!40000 ALTER TABLE `auth_tbl` DISABLE KEYS */;
INSERT INTO `auth_tbl` VALUES (1,'alies','123456',0,0),(2,'arsam','khmapa833',1,0),(3,'fahad','azizi1234',1,0);
/*!40000 ALTER TABLE `auth_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bascket_tbl`
--

DROP TABLE IF EXISTS `bascket_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bascket_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `userID` int unsigned NOT NULL,
  `foodID` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bascket_tbl`
--

LOCK TABLES `bascket_tbl` WRITE;
/*!40000 ALTER TABLE `bascket_tbl` DISABLE KEYS */;
INSERT INTO `bascket_tbl` VALUES (1,1,1);
/*!40000 ALTER TABLE `bascket_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments_tbl`
--

DROP TABLE IF EXISTS `comments_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `foodID` int unsigned NOT NULL,
  `userID` int unsigned NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments_tbl`
--

LOCK TABLES `comments_tbl` WRITE;
/*!40000 ALTER TABLE `comments_tbl` DISABLE KEYS */;
INSERT INTO `comments_tbl` VALUES (1,1,1,'kheyli khoob bood'),(2,4,1,'eftezah');
/*!40000 ALTER TABLE `comments_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_tbl`
--

DROP TABLE IF EXISTS `food_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `foodName` varchar(200) NOT NULL,
  `price` int unsigned NOT NULL,
  `discount` int unsigned NOT NULL,
  `restaurantID` int unsigned NOT NULL,
  `discountTime` int unsigned NOT NULL,
  `isActive` varchar(5) DEFAULT NULL,
  `isOrder` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_tbl`
--

LOCK TABLES `food_tbl` WRITE;
/*!40000 ALTER TABLE `food_tbl` DISABLE KEYS */;
INSERT INTO `food_tbl` VALUES (1,'kalepacheh',1200,0,1,0,'yes','no'),(4,'springroll',1500,20,2,1000,'yes','no'),(5,'sooshi',1200,0,2,0,'yes','no');
/*!40000 ALTER TABLE `food_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tbl`
--

DROP TABLE IF EXISTS `order_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `foodID` varchar(255) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `statusID` int unsigned NOT NULL,
  `restaurantID` int unsigned NOT NULL,
  `deliveryTime` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tbl`
--

LOCK TABLES `order_tbl` WRITE;
/*!40000 ALTER TABLE `order_tbl` DISABLE KEYS */;
INSERT INTO `order_tbl` VALUES (1,'1 2 3','alies',1,1,60);
/*!40000 ALTER TABLE `order_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderstatus_tbl`
--

DROP TABLE IF EXISTS `orderstatus_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderstatus_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderstatus_tbl`
--

LOCK TABLES `orderstatus_tbl` WRITE;
/*!40000 ALTER TABLE `orderstatus_tbl` DISABLE KEYS */;
INSERT INTO `orderstatus_tbl` VALUES (1,'dar hale amade saazi'),(2,'tahvil dadeh shod'),(3,'dar hale ersal');
/*!40000 ALTER TABLE `orderstatus_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response_tbl`
--

DROP TABLE IF EXISTS `response_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `commentID` int unsigned NOT NULL,
  `message` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_tbl`
--

LOCK TABLES `response_tbl` WRITE;
/*!40000 ALTER TABLE `response_tbl` DISABLE KEYS */;
INSERT INTO `response_tbl` VALUES (1,1,'ba tashakkor az message shoma'),(2,2,'hichi dastam khord');
/*!40000 ALTER TABLE `response_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_tbl`
--

DROP TABLE IF EXISTS `restaurant_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `rname` varchar(100) NOT NULL,
  `radmin` varchar(100) NOT NULL,
  `location` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_tbl`
--

LOCK TABLES `restaurant_tbl` WRITE;
/*!40000 ALTER TABLE `restaurant_tbl` DISABLE KEYS */;
INSERT INTO `restaurant_tbl` VALUES (1,'pedarekhoob','alies',3),(2,'sooshi khooneh','arsam',5),(3,'booazar\'s felafeli','fahad',8);
/*!40000 ALTER TABLE `restaurant_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restauranttype_tbl`
--

DROP TABLE IF EXISTS `restauranttype_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restauranttype_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `restaurantID` int unsigned NOT NULL,
  `typeName` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restauranttype_tbl`
--

LOCK TABLES `restauranttype_tbl` WRITE;
/*!40000 ALTER TABLE `restauranttype_tbl` DISABLE KEYS */;
INSERT INTO `restauranttype_tbl` VALUES (3,1,'fried'),(7,2,'fastfood'),(8,2,'fried'),(9,2,'italian');
/*!40000 ALTER TABLE `restauranttype_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scores_tbl`
--

DROP TABLE IF EXISTS `scores_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scores_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `foodID` int unsigned NOT NULL,
  `rating` int unsigned NOT NULL,
  `userID` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scores_tbl`
--

LOCK TABLES `scores_tbl` WRITE;
/*!40000 ALTER TABLE `scores_tbl` DISABLE KEYS */;
INSERT INTO `scores_tbl` VALUES (1,1,5,1),(2,4,5,1);
/*!40000 ALTER TABLE `scores_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-08 23:48:32
