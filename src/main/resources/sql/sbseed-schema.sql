/*
Navicat MySQL Data Transfer

Source Server         : jacob
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : embbooking

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-15 10:11:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for emb_customer
-- ----------------------------
DROP TABLE IF EXISTS `emb_customer`;
CREATE TABLE `emb_customer` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '联系人姓名（即客户姓名）',
  `phone` varchar(30) NOT NULL COMMENT '联系电话',
  `birthday` date NOT NULL COMMENT '生日',
  `verified` int(11) NOT NULL COMMENT '是否手机认证',
  `sex` varchar(20) NOT NULL COMMENT ' 性别',
  `wechat` varchar(20) NOT NULL COMMENT '微信号',
  `description` varchar(255) NOT NULL COMMENT '自我描述',
  `cover` varchar(255) not null comment '头像',
  `field_a` varchar(255)  null comment '保留字段',
  `field_b` varchar(255)  null comment '保留字段',
  `field_c` varchar(255)  null comment '保留字段',
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
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '技师姓名',
  `sex` varchar(20) NOT NULL COMMENT ' 性别',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  `description` varchar(255) NOT NULL COMMENT '自我描述',
  `work_time` date NOT NULL COMMENT ' 工作时间',
  `cover` varchar(255) not null comment '头像',
  `field_a` varchar(255)  null comment '保留字段',
  `field_b` varchar(255)  null comment '保留字段',
  `field_c` varchar(255)  null comment '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_doctor
-- ----------------------------

-- ----------------------------
-- Table structure for emb_service
-- ----------------------------
DROP TABLE IF EXISTS `emb_service`;
CREATE TABLE `emb_service` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '服务类型',
  `doctor_id` bigint(20) NOT NULL COMMENT '技师ID',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_service
-- ----------------------------

-- ----------------------------
-- Table structure for emb_service_type
-- ----------------------------
DROP TABLE IF EXISTS `emb_service_type`;
CREATE TABLE `emb_service_type` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '类型名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_service_type
-- ----------------------------

-- ----------------------------
-- Table structure for emb_studio
-- ----------------------------
DROP TABLE IF EXISTS `emb_studio`;
CREATE TABLE `emb_studio` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '工作室名称',
  `description` varchar(255) NOT NULL COMMENT '工作室描述',
  `work_time` date NOT NULL COMMENT '工作时间',
  `fee` decimal(10,2) NOT NULL COMMENT '费用',
  `studio_site` varchar(20) NOT NULL COMMENT '地址',
    `covers` varchar(255) not null comment '头像',
	`field_a` varchar(255)  null comment '保留字段',
  `field_b` varchar(255)  null comment '保留字段',
  `field_c` varchar(255)  null comment '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emb_studio
-- ----------------------------
-- ----------------------------
-- Table structure for emb_appointment
-- ----------------------------
DROP TABLE IF EXISTS `emb_appointment`;
CREATE TABLE `emb_appointment` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '技师ID',
  `user_id` bigint(20) NOT NULL COMMENT '技师姓名',
  `service_id` bigint(20) NOT NULL COMMENT '类型ID',
  `status` varchar(20) NOT NULL COMMENT ' 订单状态',
  `fee` decimal(10,2) NOT NULL COMMENT '预约费用',
  `create_time` date NOT NULL COMMENT ' 创建时间',
  `appointment_time` date NOT NULL COMMENT ' 预约时间',
  `close_time` date NOT NULL COMMENT ' 完成(关闭)时间',
  `field_a` varchar(255)  null comment '保留字段',
  `field_b` varchar(255)  null comment '保留字段',
  `field_c` varchar(255)  null comment '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for emb_appointment
-- ----------------------------

DROP TABLE IF EXISTS `emb_studio_product`;
CREATE TABLE `emb_studio_product` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `studio_id` bigint(20) NOT NULL COMMENT '工作室ID',
  `service_type` bigint(20) NOT NULL COMMENT '类型ID',
  `fee` decimal(10,2) NOT NULL COMMENT '项目费用',
  `name` varchar(255) NOT NULL COMMENT ' 项目名称',
  `description` varchar(255) NOT NULL COMMENT ' 项目描述',
  `covers` varchar(255) NOT NULL COMMENT ' 封面',
  `field_a` varchar(255)  null comment '保留字段',
  `field_b` varchar(255)  null comment '保留字段',
  `field_c` varchar(255)  null comment '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;