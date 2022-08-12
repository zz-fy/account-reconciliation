/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : behalf

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-08-12 16:40:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for issuing_company
-- ----------------------------
DROP TABLE IF EXISTS `issuing_company`;
CREATE TABLE `issuing_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issuing_platform_id` bigint(20) DEFAULT NULL COMMENT '代发平台',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unidx_platform_name` (`issuing_platform_id`,`name`) COMMENT '代发平台下面的公司应该唯一的'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='代发公司';

-- ----------------------------
-- Records of issuing_company
-- ----------------------------

-- ----------------------------
-- Table structure for issuing_platform
-- ----------------------------
DROP TABLE IF EXISTS `issuing_platform`;
CREATE TABLE `issuing_platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '代发平台名',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unidx_name` (`name`) COMMENT '代发平台名称唯一'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='代发平台';

-- ----------------------------
-- Records of issuing_platform
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `product_id` bigint(20) NOT NULL,
  `sold_account_id` bigint(20) DEFAULT NULL COMMENT '售出账户id',
  `sold_platform_id` bigint(20) DEFAULT NULL COMMENT '售出平台名',
  `mark_price` decimal(10,3) DEFAULT NULL COMMENT '标价',
  `sold_price` decimal(10,3) DEFAULT NULL COMMENT '售出价格',
  `cost_price` decimal(10,3) DEFAULT NULL COMMENT '成本',
  `sold_quantity` int(11) DEFAULT NULL COMMENT '售出数量',
  `status` tinyint(2) DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_customer
-- ----------------------------
DROP TABLE IF EXISTS `order_customer`;
CREATE TABLE `order_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称(客户在平台名)',
  `real_name` varchar(255) DEFAULT NULL COMMENT '客户真名',
  `phone` varchar(255) DEFAULT NULL COMMENT '客户电话',
  `address` varchar(255) DEFAULT NULL COMMENT '客户地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_customer
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '产品名称(规格参数拼在括号)',
  `issuing_company_id` bigint(20) DEFAULT NULL COMMENT '代发公司id',
  `issuing_url` varchar(255) DEFAULT NULL COMMENT '代发链接',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品表(由于代发平台价格是变动的，所以没有价格字段)';

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for sold_account
-- ----------------------------
DROP TABLE IF EXISTS `sold_account`;
CREATE TABLE `sold_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `name` varchar(255) DEFAULT NULL COMMENT '账户名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unidx_phone` (`phone`) USING BTREE COMMENT '电话唯一键'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sold_account
-- ----------------------------

-- ----------------------------
-- Table structure for sold_platform
-- ----------------------------
DROP TABLE IF EXISTS `sold_platform`;
CREATE TABLE `sold_platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '售出平台名',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unidx_name` (`name`) COMMENT '平台名称唯一'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='售出平台  闲鱼/转转。。';

-- ----------------------------
-- Records of sold_platform
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `username` varchar(255) NOT NULL COMMENT '登录名',
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表 登录可用';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'admin', '15198065677', '管理员', '$2a$10$yZtFVbMpO1YI9ZPsLqDrA.w8/qGFZ5bxYpyIbc0GcDNl/fOL6GGrS', '2022-07-11 10:58:04', '2022-07-11 11:44:50');
