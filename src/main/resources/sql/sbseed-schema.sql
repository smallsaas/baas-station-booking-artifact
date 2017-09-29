/*
Navicat MySQL Data Transfer

Source Server         : jacob
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : embbooking

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-29 18:14:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for emb_appointment
-- ----------------------------
DROP TABLE IF EXISTS `emb_appointment`;
CREATE TABLE `emb_appointment` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `studio_id` bigint(20) NOT NULL COMMENT '宸ヤ綔瀹D',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '鎶�甯圛D',
  `customer_id` bigint(20) NOT NULL COMMENT '瀹㈡埛濮撳悕',
  `service_id` bigint(20) NOT NULL COMMENT '绫诲瀷ID',
  `status` varchar(20) NOT NULL COMMENT ' 璁㈠崟鐘舵��',
  `fee` decimal(10,2) NOT NULL COMMENT '棰勭害璐圭敤',
  `create_time` date NOT NULL COMMENT ' 鍒涘缓鏃堕棿',
  `appointment_time` date NOT NULL COMMENT ' 棰勭害鏃堕棿',
  `close_time` date DEFAULT NULL COMMENT ' 瀹屾垚(鍏抽棴)鏃堕棿',
  `phone` varchar(255) DEFAULT NULL COMMENT '鐢ㄦ埛鎵嬫満鍙风爜',
  `field_b` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_c` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  PRIMARY KEY (`id`),
  KEY `studio_id` (`studio_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `emb_appointment_ibfk_1` FOREIGN KEY (`studio_id`) REFERENCES `emb_studio` (`id`),
  CONSTRAINT `emb_appointment_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `emb_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_appointment
-- ----------------------------

-- ----------------------------
-- Table structure for emb_customer
-- ----------------------------
DROP TABLE IF EXISTS `emb_customer`;
CREATE TABLE `emb_customer` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `name` varchar(50) DEFAULT NULL COMMENT '鑱旂郴浜哄鍚嶏紙鍗冲鎴峰鍚嶏級',
  `phone` varchar(30) DEFAULT NULL COMMENT '鑱旂郴鐢佃瘽',
  `birthday` date DEFAULT NULL COMMENT '鐢熸棩',
  `verified` int(11) DEFAULT NULL COMMENT '鏄惁鎵嬫満璁よ瘉',
  `sex` varchar(20) DEFAULT NULL COMMENT ' 鎬у埆',
  `wechat` varchar(20) DEFAULT NULL COMMENT '寰俊鍙�',
  `description` varchar(255) DEFAULT NULL COMMENT '鑷垜鎻忚堪',
  `cover` varchar(255) DEFAULT NULL COMMENT '澶村儚',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '缁忓害',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '绾害',
  `field_a` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_b` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_c` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_customer
-- ----------------------------

-- ----------------------------
-- Table structure for emb_doctor
-- ----------------------------
DROP TABLE IF EXISTS `emb_doctor`;
CREATE TABLE `emb_doctor` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `name` varchar(50) DEFAULT NULL COMMENT '鎶�甯堝鍚�',
  `sex` varchar(20) DEFAULT NULL COMMENT ' 鎬у埆',
  `studio_id` bigint(20) DEFAULT NULL COMMENT '宸ヤ綔瀹D',
  `description` varchar(255) DEFAULT NULL COMMENT '鑷垜鎻忚堪',
  `work_time` date DEFAULT NULL COMMENT ' 宸ヤ綔鏃堕棿',
  `cover` varchar(255) DEFAULT NULL COMMENT '澶村儚',
  `field_a` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_b` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_c` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_doctor
-- ----------------------------

-- ----------------------------
-- Table structure for emb_products_photos
-- ----------------------------
DROP TABLE IF EXISTS `emb_products_photos`;
CREATE TABLE `emb_products_photos` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `product_id` bigint(20) NOT NULL COMMENT '鍏徃鍚嶅瓧',
  `photo` varchar(255) NOT NULL COMMENT '鍥剧墖',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `emb_products_photos_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `emb_studio_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_products_photos
-- ----------------------------

-- ----------------------------
-- Table structure for emb_service_type
-- ----------------------------
DROP TABLE IF EXISTS `emb_service_type`;
CREATE TABLE `emb_service_type` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `name` varchar(20) NOT NULL COMMENT '绫诲瀷鍚嶇О',
  `pid` bigint(20) DEFAULT NULL COMMENT '澶栭敭',
  `cover` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `emb_service_type_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `emb_service_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_service_type
-- ----------------------------
INSERT INTO `emb_service_type` VALUES ('1', 'dabaojian', '1', '1');
INSERT INTO `emb_service_type` VALUES ('2', 'dabao', '2', '2');

-- ----------------------------
-- Table structure for emb_studio
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio`;
CREATE TABLE `emb_studio` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `name` varchar(50) NOT NULL COMMENT '宸ヤ綔瀹ゅ悕绉�',
  `description` varchar(255) NOT NULL COMMENT '宸ヤ綔瀹ゆ弿杩�',
  `work_time` date DEFAULT NULL COMMENT '宸ヤ綔鏃堕棿',
  `fee` decimal(10,2) NOT NULL COMMENT '璐圭敤',
  `studio_site` varchar(20) NOT NULL COMMENT '璇︾粏鍦板潃',
  `cover` varchar(255) DEFAULT NULL COMMENT '澶村儚',
  `province` varchar(255) DEFAULT NULL COMMENT '鐪�',
  `city` varchar(255) DEFAULT NULL COMMENT '甯�',
  `district` varchar(255) DEFAULT NULL COMMENT '鍖�',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '缁忓害',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '绾害',
  `contact` varchar(255) DEFAULT NULL COMMENT '鑱旂郴浜�',
  `phone` varchar(255) DEFAULT NULL COMMENT '鐢佃瘽',
  `studio_phone` varchar(255) NOT NULL COMMENT '搴楅摵鐢佃瘽',
  `is_stick` varchar(26) NOT NULL COMMENT '淇濈暀瀛楁',
  `field_b` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_c` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studio
-- ----------------------------
INSERT INTO `emb_studio` VALUES ('1', '1', '1', '2017-09-28', '12.00', '321', '123', '132', '123', '123', null, null, null, null, '2315', 'RAISE', null, null);

-- ----------------------------
-- Table structure for emb_studios_photos
-- ----------------------------
DROP TABLE IF EXISTS `emb_studios_photos`;
CREATE TABLE `emb_studios_photos` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `studio_id` bigint(20) NOT NULL COMMENT '鍏徃鍚嶅瓧',
  `photo` varchar(255) NOT NULL COMMENT '鍥剧墖',
  PRIMARY KEY (`id`),
  KEY `studio_id` (`studio_id`),
  CONSTRAINT `emb_studios_photos_ibfk_1` FOREIGN KEY (`studio_id`) REFERENCES `emb_studio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studios_photos
-- ----------------------------

-- ----------------------------
-- Table structure for emb_studio_collect
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_collect`;
CREATE TABLE `emb_studio_collect` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `studio_id` bigint(20) NOT NULL COMMENT '鍏徃鍚嶅瓧',
  `customer_id` bigint(20) NOT NULL COMMENT '瀹㈡埛鍚嶅瓧',
  PRIMARY KEY (`id`),
  KEY `studio_id` (`studio_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `emb_studio_collect_ibfk_1` FOREIGN KEY (`studio_id`) REFERENCES `emb_studio` (`id`),
  CONSTRAINT `emb_studio_collect_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `emb_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studio_collect
-- ----------------------------

-- ----------------------------
-- Table structure for emb_studio_product
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_product`;
CREATE TABLE `emb_studio_product` (
  `id` bigint(20) NOT NULL COMMENT '涓婚敭',
  `studio_id` bigint(20) NOT NULL COMMENT '宸ヤ綔瀹D',
  `feature` varchar(26) DEFAULT NULL COMMENT '绫诲瀷ID',
  `fee` decimal(10,2) NOT NULL COMMENT '椤圭洰璐圭敤',
  `name` varchar(255) NOT NULL COMMENT ' 椤圭洰鍚嶇О',
  `description` varchar(255) NOT NULL COMMENT ' 椤圭洰鎻忚堪',
  `cover` varchar(255) DEFAULT NULL COMMENT ' 灏侀潰',
  `field_a` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_b` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  `field_c` varchar(255) DEFAULT NULL COMMENT '淇濈暀瀛楁',
  PRIMARY KEY (`id`),
  KEY `studio_id` (`studio_id`),
  CONSTRAINT `emb_studio_product_ibfk_1` FOREIGN KEY (`studio_id`) REFERENCES `emb_studio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studio_product
-- ----------------------------

-- ----------------------------
-- Table structure for emb_studio_service
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_service`;
CREATE TABLE `emb_studio_service` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type_id` bigint(20) NOT NULL COMMENT '服务类型',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '技师ID',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `studio_id` (`studio_id`),
  CONSTRAINT `emb_studio_service_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `emb_service_type` (`id`),
  CONSTRAINT `emb_studio_service_ibfk_2` FOREIGN KEY (`studio_id`) REFERENCES `emb_studio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studio_service
-- ----------------------------
