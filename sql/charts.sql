SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `t_node_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_node_style` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `style_name` varchar(255) NOT NULL,
                          `background_color` varchar(255) NOT NULL,
                          `border_color` varchar(255) NOT NULL,
                          `border_width` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
BEGIN;
/*!40000 ALTER TABLE `t_node_style` DISABLE KEYS */;
INSERT INTO `t_node_style` VALUES (0,'classes-A','#6fb1fc','#6fb1fc','5px'),(1,'classes-B','#77c94f','#77c94f','5px');
/*!40000 ALTER TABLE `t_node_style` ENABLE KEYS */;
COMMIT;



DROP TABLE IF EXISTS `t_edge_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_edge_style` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `style_name` varchar(255) NOT NULL,
                                `curve_style` varchar(255) NOT NULL,
                                `line_color` varchar(255) NOT NULL,
                                `width` varchar(255) NOT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
BEGIN;
/*!40000 ALTER TABLE `t_edge_style` DISABLE KEYS */;
INSERT INTO `t_edge_style` VALUES (0,'relationA','bezier','#999999','1px'),(1,'relationB','bezier','#999999','1px');
/*!40000 ALTER TABLE `t_edge_style` ENABLE KEYS */;
COMMIT;


DROP TABLE IF EXISTS `t_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_node` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `node_id` varchar(255) DEFAULT NULL,
                          `chart_id` int(11) DEFAULT NULL,
                          `node_group` varchar(255) NOT NULL,
                          `node_name` varchar(255) NOT NULL,
                          `classes` varchar(255) DEFAULT NULL,
                          `position_x` int(11) DEFAULT NULL,
                          `position_y` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_edge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_edge` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `edge_id` varchar(255) DEFAULT NULL,
                          `chart_id` int(11) DEFAULT NULL,
                          `edge_group` varchar(255) NOT NULL,
                          `edge_name` varchar(255) NOT NULL,
                          `classes` varchar(255) DEFAULT NULL,
                          `source` varchar(255) DEFAULT NULL,
                          `target` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_chart` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `chart_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
