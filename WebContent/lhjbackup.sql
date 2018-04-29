-- MySQL dump 10.13  Distrib 5.5.39, for Win64 (x86)
--
-- Host: localhost    Database: lhjbishe
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Table structure for table `t_borrow_record`
--

DROP TABLE IF EXISTS `t_borrow_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_borrow_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vehicleid` int(10) unsigned NOT NULL,
  `userid` int(10) unsigned NOT NULL,
  `businessid` varchar(45) CHARACTER SET latin1 NOT NULL,
  `createtime` varchar(45) CHARACTER SET latin1 NOT NULL,
  `createtimestmp` longtext CHARACTER SET latin1 NOT NULL,
  `starttime` varchar(255) CHARACTER SET latin1 NOT NULL,
  `finishtime` varchar(255) CHARACTER SET latin1 NOT NULL,
  `message` varchar(255) NOT NULL COMMENT '用途',
  `state` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`,`vehicleid`,`userid`,`businessid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk COMMENT='借车记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_borrow_record`
--

LOCK TABLES `t_borrow_record` WRITE;
/*!40000 ALTER TABLE `t_borrow_record` DISABLE KEYS */;
INSERT INTO `t_borrow_record` VALUES (7,10,4,'397JB78LGU5','2018-04-29 21:01:22','1525006882298','2018-5-1','2018-5-3','用于接送员工春游旅程。','1'),(8,14,5,'397JB78LGU5','2018-04-29 21:28:07','1525008487397','2018-5-1','2018-5-11','用于个人去上海出差。','3'),(9,18,5,'397JB78LGU5','2018-04-29 21:54:39','1525010079549','2018-04-29','2018-5-29','自驾旅游','1');
/*!40000 ALTER TABLE `t_borrow_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_business`
--

DROP TABLE IF EXISTS `t_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_business` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `businessid` varchar(255) NOT NULL COMMENT '企业号8位',
  `password` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '密码',
  `companyname` varchar(255) NOT NULL COMMENT '企业名称',
  `createtime` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '创建时间',
  `updatetime` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '修改时间',
  `logo` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT '“”' COMMENT 'logo',
  `address` varchar(255) DEFAULT NULL COMMENT '企业地址',
  `email` varchar(233) NOT NULL COMMENT '企业邮箱',
  PRIMARY KEY (`id`,`businessid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_business`
--

LOCK TABLES `t_business` WRITE;
/*!40000 ALTER TABLE `t_business` DISABLE KEYS */;
INSERT INTO `t_business` VALUES (1,'397JB78LGU5','123456','江苏万达','2018-03-25 17:22:46','1525004845566','1525004844741.png','江苏南京','123@qq.com');
/*!40000 ALTER TABLE `t_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_status`
--

DROP TABLE IF EXISTS `t_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vehicleid` int(10) unsigned NOT NULL COMMENT '车辆id',
  `statusid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '状态0,1,2,3,4,5',
  `updatetime` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '更新时间',
  `statustype` varchar(255) NOT NULL COMMENT '类型描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk COMMENT='状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_status`
--

LOCK TABLES `t_status` WRITE;
/*!40000 ALTER TABLE `t_status` DISABLE KEYS */;
INSERT INTO `t_status` VALUES (7,7,0,'2018-04-29 20:30:39','可借'),(8,8,0,'2018-04-29 20:36:12','可借'),(9,9,0,'2018-04-29 20:40:29','可借'),(10,10,0,'2018-04-29 20:46:10','可借'),(11,11,0,'2018-04-29 20:52:31','可借'),(12,12,0,'2018-04-29 21:10:36','可借'),(13,13,0,'2018-04-29 21:12:21','可借'),(14,14,0,'2018-04-29 21:15:31','可借'),(15,15,0,'2018-04-29 21:16:55','可借'),(16,16,0,'2018-04-29 21:18:33','可借'),(17,17,0,'2018-04-29 21:20:23','可借'),(18,18,0,'2018-04-29 21:22:14','可借');
/*!40000 ALTER TABLE `t_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_token`
--

DROP TABLE IF EXISTS `t_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_token` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userphone` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `createtime` mediumtext NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_token`
--

LOCK TABLES `t_token` WRITE;
/*!40000 ALTER TABLE `t_token` DISABLE KEYS */;
INSERT INTO `t_token` VALUES (5,'18251986780','0ML8E9955OYR0W68','1525004548605'),(7,'18251981111','60:e7:01:5d:8b:0e35M3R29W8V3I25BG','1525006821719'),(9,'18251982222','60:e7:01:5d:8b:0e29G8WEID7223XYMM','1525008836807');
/*!40000 ALTER TABLE `t_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_type` (
  `typeid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL COMMENT '类型名',
  `order` int(10) unsigned NOT NULL COMMENT '顺序',
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'全部',0),(2,'私人轿车',1),(3,'商务',2),(4,'货车',3),(5,'大巴客车',4),(6,'皮卡',5),(7,'跑车',6);
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userphone` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '用户名',
  `userpass` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '密码',
  `createtime` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '创建时间',
  `age` varchar(255) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `headurl` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT '‘’' COMMENT '头像地址',
  `lastupdatetime` longtext CHARACTER SET latin1 COMMENT '更新时间',
  `businessid` varchar(255) NOT NULL COMMENT '企业id',
  `truename` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `licence` varchar(45) DEFAULT NULL COMMENT '驾驶证',
  `type` varchar(45) DEFAULT NULL COMMENT '证件类型',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (3,'18251986780','12345678','2018-04-29 20:22:28','1994年04月22日','女','‘’','1525004604593','397JB78LGU5','candy','32072219940422052X','C1'),(4,'18251981111','12345678','2018-04-29 20:56:33','1994年04月22日','女','1525006740387.png','1525006740424','397JB78LGU5','candy','320722199404223111','A2'),(5,'18251982222','12345678','2018-04-29 21:23:19','1994年04月22日','男','1525008421545.png','1525008421598','397JB78LGU5','kevin','320481199501221231','A1');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_vehicle`
--

DROP TABLE IF EXISTS `t_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vehicle` (
  `vehicleid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `businessid` varchar(255) NOT NULL COMMENT '企业id',
  `name` varchar(255) NOT NULL COMMENT '车名',
  `typeid` int(10) NOT NULL COMMENT '类型id',
  `biref` varchar(255) NOT NULL COMMENT '简介',
  `indexpicurl` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '索引图url',
  `createtime` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '创建时间',
  `price` double NOT NULL DEFAULT '0' COMMENT '汽车价值',
  `typetitle` varchar(45) NOT NULL COMMENT '类型名',
  `level` varchar(45) NOT NULL COMMENT '准驾等级',
  `identity` varchar(45) NOT NULL COMMENT '车牌号',
  PRIMARY KEY (`vehicleid`,`identity`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC COMMENT='车辆表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_vehicle`
--

LOCK TABLES `t_vehicle` WRITE;
/*!40000 ALTER TABLE `t_vehicle` DISABLE KEYS */;
INSERT INTO `t_vehicle` VALUES (7,'397JB78LGU5','商务用车',3,'此车可用于出差、商务旅行。最大荷载人数：7人。','1525005039775.png','2018-04-29 20:30:39',35,'商务','B2','苏A270V31'),(8,'397JB78LGU5','小型轿车',2,'可用于出差，商务出行。最大荷载人数：4人。','1525005371944.png','2018-04-29 20:36:12',20,'私人轿车','C1','苏A771998'),(9,'397JB78LGU5','大型货车',4,'用于运输货物，最多荷载3人，重量8吨。','1525005629734.png','2018-04-29 20:40:29',35,'货车','A2','苏A66H750'),(10,'397JB78LGU5','大巴客车',5,'用于接送员工上下班或集体活动。最大荷载：55人。','1525005970548.png','2018-04-29 20:46:10',45,'大巴客车','A1','苏A550122'),(11,'397JB78LGU5','皮卡车',6,'用于运输少量货物，最大荷载2人，重量1吨。','1525006351630.png','2018-04-29 20:52:31',20,'皮卡','B1','苏A007221'),(12,'397JB78LGU5','皮卡车',6,'用于运输少量货物，最大荷载2人，重量1吨。','1525007436780.png','2018-04-29 21:10:36',15,'皮卡','B1','苏A990000'),(13,'397JB78LGU5','跑车',7,'最大荷载2人。','1525007540939.png','2018-04-29 21:12:20',150,'跑车','C1','苏A000111'),(14,'397JB78LGU5','跑车_玛莎拉蒂',7,'最大荷载2人','1525007731220.png','2018-04-29 21:15:31',300,'跑车','C1','苏A7788811'),(15,'397JB78LGU5','大巴车',5,'用于接送员工。最大荷载：55人。','1525007815258.png','2018-04-29 21:16:55',75,'大巴客车','C1','苏A211933'),(16,'397JB78LGU5','商务车',3,'用于出差，商务出行。最大荷载：7人。','1525007913692.png','2018-04-29 21:18:33',60,'商务','B2','苏A200118'),(17,'397JB78LGU5','小型货车',4,'小型货车，用于短途运输。最大荷载：2吨。','1525008023190.png','2018-04-29 21:20:23',35,'货车','A2','苏A665444'),(18,'397JB78LGU5','小轿车',2,'用于个人短途出行，最大荷载人数：4人。','1525008134066.png','2018-04-29 21:22:14',15,'私人轿车','C1','苏A776111');
/*!40000 ALTER TABLE `t_vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-29 22:15:59
