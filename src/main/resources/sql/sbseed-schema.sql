/*
Navicat MySQL Data Transfer

Source Server         : jacob
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : embbooking

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-30 09:36:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for emb_appointment
-- ----------------------------
DROP TABLE IF EXISTS `emb_appointment`;
CREATE TABLE `emb_appointment` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '技师ID',
  `customer_id` bigint(20) NOT NULL COMMENT '用户ID',
  `status` varchar(20) NOT NULL COMMENT ' 状态',
  `fee` decimal(10,2) NOT NULL COMMENT '费用',
  `create_time` datetime NOT NULL COMMENT ' 创建时间',
  `appointment_time` datetime NOT NULL COMMENT ' 预约时间',
  `close_time` datetime DEFAULT NULL COMMENT ' 结束时间',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field_c` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_customer
-- ----------------------------
DROP TABLE IF EXISTS `emb_customer`;
CREATE TABLE `emb_customer` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `verified` int(11) DEFAULT NULL COMMENT '认证状态',
  `sex` varchar(20) DEFAULT NULL COMMENT ' 性别',
  `wechat` varchar(20) DEFAULT NULL COMMENT '微信',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '头像',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `create_time` datetime DEFAULT  NULL COMMENT '注册时间',
  `openid` varchar(255) NOT NULL COMMENT '微信提供openid',
  `user_id` bigint(20) NOT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_doctor
-- ----------------------------
DROP TABLE IF EXISTS `emb_doctor`;
CREATE TABLE `emb_doctor` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT ' 性别',
  `studio_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `work_time` datetime DEFAULT NULL COMMENT ' 工作时间',
  `cover` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field_a` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field_b` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field_c` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_products_photos
-- ----------------------------
DROP TABLE IF EXISTS `emb_products_photos`;
CREATE TABLE `emb_products_photos` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `photo` varchar(255) NOT NULL COMMENT '产品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_service_type
-- ----------------------------
DROP TABLE IF EXISTS `emb_service_type`;
CREATE TABLE `emb_service_type` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `name` varchar(20) NOT NULL unique COMMENT '名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点',
  `cover` varchar(255) NOT NULL comment '封面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_studio
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio`;
CREATE TABLE `emb_studio` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `work_time` datetime DEFAULT NULL COMMENT '工作时间',
  `fee` decimal(10,2) NOT NULL COMMENT '费用',
  `studio_site` varchar(255) NOT NULL COMMENT '地址',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '区',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `studio_phone` varchar(255) NOT NULL COMMENT '店铺电话',
  `is_stick` varchar(26) NOT NULL COMMENT '精选',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `field_c` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_studios_photos
-- ----------------------------
DROP TABLE IF EXISTS `emb_studios_photos`;
CREATE TABLE `emb_studios_photos` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `studio_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `photo` varchar(255) NOT NULL COMMENT '封面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_studio_collect
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_collect`;
CREATE TABLE `emb_studio_collect` (
  `id` bigint(20) NOT NULL UNIQUE  COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `studio_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `customer_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT  NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for emb_studio_product
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_product`;
CREATE TABLE `emb_studio_product` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `studio_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `feature` varchar(26) DEFAULT NULL COMMENT '特性',
  `fee` decimal(10,2) NOT NULL COMMENT '费用',
  `name` varchar(255) NOT NULL COMMENT ' 名称',
  `description` varchar(255) NOT NULL COMMENT ' 描述',
  `cover` varchar(255) DEFAULT NULL COMMENT ' 封面',
  `stick` varchar(255) DEFAULT NULL COMMENT '精选/SIFT/NORMAL',
  `attribute` varchar(255) DEFAULT NULL COMMENT '精选/次卡/团购/',
  `field_c` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emb_studio_service
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio_service`;
CREATE TABLE `emb_studio_service` (
  `id` bigint(20) NOT NULL UNIQUE COMMENT '主键',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属组织ID',
  `org_tag` varchar(100) NULL DEFAULT NULL COMMENT '组织标志',
  `type_id` bigint(20) NOT NULL COMMENT '服务类型',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '技师ID',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

