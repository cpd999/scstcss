/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : scstc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-10-21 16:56:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for studioinformation
-- ----------------------------
DROP TABLE IF EXISTS `studioinformation`;
CREATE TABLE `studioinformation` (
  `studioId` int(11) NOT NULL AUTO_INCREMENT,
  `studioName` varchar(255) NOT NULL COMMENT '工作室名字',
  `studioQQ` int(11) NOT NULL COMMENT '工作室联系qq',
  `studioAmount` int(11) NOT NULL COMMENT '成员数量',
  `studioSummary` varchar(255) NOT NULL COMMENT '工作室简介',
  `studioLogoAddress` varchar(255) NOT NULL COMMENT 'logo图片路径',
  `studioAddress` varchar(255) NOT NULL DEFAULT '' COMMENT '工作室地址',
  PRIMARY KEY (`studioId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studioinformation
-- ----------------------------
INSERT INTO `studioinformation` VALUES ('1', '篮球社', '546492358', '3', '很棒', '123', '七教');
INSERT INTO `studioinformation` VALUES ('2', '打印机工作室', '218489489', '10', 'nice', '456', '八教');
INSERT INTO `studioinformation` VALUES ('3', '管理教学工作室', '456464898', '5', '不错', '789 ', '九教');
INSERT INTO `studioinformation` VALUES ('4', '天猫工作室', '123456789', '6', '很棒', '10123', '十教');
