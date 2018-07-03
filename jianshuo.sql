/*
Navicat MySQL Data Transfer

Source Server         : pp
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : jianshuo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-03 20:04:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `a_content` varchar(500) NOT NULL,
  `a_data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`a_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '第一个动态测试', '2018-06-17 22:15:28');
INSERT INTO `article` VALUES ('2', '1', '   把回答', '2018-06-17 23:44:06');
INSERT INTO `article` VALUES ('5', '2', '几个户开户客户', '2018-06-19 18:03:32');
INSERT INTO `article` VALUES ('6', '2', '花季雨季高合金钢我uv', '2018-06-19 18:07:46');
INSERT INTO `article` VALUES ('7', '2', '测试日期', '2018-06-20 10:03:32');
INSERT INTO `article` VALUES ('8', '2', 'jkashjkdhk uiashd', '2018-06-20 12:28:10');
INSERT INTO `article` VALUES ('9', '3', '回车键不可用11111111111111111111111111111', '2018-06-21 14:32:32');
INSERT INTO `article` VALUES ('13', '1', '简说啊', '2018-06-22 00:41:48');
INSERT INTO `article` VALUES ('14', '1', '数据测试', '2018-06-22 00:41:56');
INSERT INTO `article` VALUES ('16', '4', '数据添加+1', '2018-06-22 10:26:30');
INSERT INTO `article` VALUES ('19', '5', '他人并他 ', '2018-06-29 17:27:47');

-- ----------------------------
-- Table structure for `a_comments`
-- ----------------------------
DROP TABLE IF EXISTS `a_comments`;
CREATE TABLE `a_comments` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `c_content` varchar(100) NOT NULL,
  `c_data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_id`),
  KEY `u_id` (`u_id`),
  KEY `a_comments_ibfk_1` (`a_id`),
  CONSTRAINT `a_comments_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `article` (`a_id`),
  CONSTRAINT `a_comments_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_comments
-- ----------------------------
INSERT INTO `a_comments` VALUES ('1', '5', '1', '666666666666', '2018-06-20 21:08:48');
INSERT INTO `a_comments` VALUES ('2', '5', '1', '测试刷新', '2018-06-20 21:10:36');
INSERT INTO `a_comments` VALUES ('3', '5', '2', '回复  mzn1 :测试回复', '2018-06-21 01:57:04');
INSERT INTO `a_comments` VALUES ('4', '5', '2', '22222222', '2018-06-21 09:47:47');
INSERT INTO `a_comments` VALUES ('5', '5', '2', '111111111', '2018-06-21 09:47:56');
INSERT INTO `a_comments` VALUES ('6', '5', '2', '3333333333', '2018-06-21 09:48:04');
INSERT INTO `a_comments` VALUES ('7', '5', '2', '5555555555555', '2018-06-21 09:48:12');
INSERT INTO `a_comments` VALUES ('8', '5', '2', '44444444444', '2018-06-21 09:48:17');
INSERT INTO `a_comments` VALUES ('9', '5', '2', '锚点跳转', '2018-06-21 10:29:47');
INSERT INTO `a_comments` VALUES ('10', '5', '3', '回复  测试1 :22222朋友圈', '2018-06-21 21:17:25');
INSERT INTO `a_comments` VALUES ('11', '5', '3', '1111', '2018-06-21 21:18:37');
INSERT INTO `a_comments` VALUES ('12', '5', '3', '6666666666', '2018-06-21 21:19:38');
INSERT INTO `a_comments` VALUES ('13', '9', '3', '朋友', '2018-06-21 21:21:24');
INSERT INTO `a_comments` VALUES ('16', '14', '1', '大量数据+1', '2018-06-22 00:43:56');
INSERT INTO `a_comments` VALUES ('17', '5', '5', '评论', '2018-06-22 11:33:43');
INSERT INTO `a_comments` VALUES ('18', '9', '5', '111111', '2018-06-29 16:32:21');
INSERT INTO `a_comments` VALUES ('19', '5', '5', '回复  测试1 :4666666666', '2018-06-29 16:47:37');
INSERT INTO `a_comments` VALUES ('20', '8', '5', '66666666666', '2018-06-29 16:57:12');
INSERT INTO `a_comments` VALUES ('22', '5', '5', '带我去', '2018-06-29 17:26:12');

-- ----------------------------
-- Table structure for `friend`
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `u_id` int(11) NOT NULL,
  `f_id` int(11) NOT NULL,
  `statue` int(11) NOT NULL,
  KEY `u_id` (`u_id`),
  KEY `f_id` (`f_id`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`f_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('2', '1', '1');
INSERT INTO `friend` VALUES ('2', '2', '1');
INSERT INTO `friend` VALUES ('3', '1', '1');
INSERT INTO `friend` VALUES ('3', '2', '1');
INSERT INTO `friend` VALUES ('3', '3', '1');
INSERT INTO `friend` VALUES ('1', '1', '1');
INSERT INTO `friend` VALUES ('1', '2', '1');
INSERT INTO `friend` VALUES ('1', '3', '1');
INSERT INTO `friend` VALUES ('5', '4', '0');
INSERT INTO `friend` VALUES ('5', '2', '1');
INSERT INTO `friend` VALUES ('5', '3', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) NOT NULL,
  `u_pwd` varchar(50) NOT NULL,
  `u_tel` varchar(20) NOT NULL,
  `token` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`u_id`,`u_name`),
  UNIQUE KEY `u_tel` (`u_tel`),
  KEY `u_id` (`u_id`),
  KEY `u_name` (`u_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'mzn1', '4QrcOUm6Wau+VuBX8g+IPg==', '14796747525', '0');
INSERT INTO `user` VALUES ('2', 'ces1', '4QrcOUm6Wau+VuBX8g+IPg==', '15845452585', '0');
INSERT INTO `user` VALUES ('3', 'ces2', '4QrcOUm6Wau+VuBX8g+IPg==', '14945448555', '0');
INSERT INTO `user` VALUES ('4', 'ces3', '4QrcOUm6Wau+VuBX8g+IPg==', '15812132123', '0');
INSERT INTO `user` VALUES ('5', 'ce11', '4QrcOUm6Wau+VuBX8g+IPg==', '13554545444', 'eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzAyNjQzNDh9.wfk4CdeLCcHXaGjIsWjWYsdXj2IBqCUy6BlVQt8V8zs');

-- ----------------------------
-- Table structure for `u_info`
-- ----------------------------
DROP TABLE IF EXISTS `u_info`;
CREATE TABLE `u_info` (
  `u_id` int(11) NOT NULL,
  `u_nickname` varchar(50) NOT NULL,
  `u_age` varchar(5) DEFAULT '',
  `u_sex` varchar(5) DEFAULT '',
  `u_note` varchar(200) DEFAULT '',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_nickname` (`u_nickname`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_info
-- ----------------------------
INSERT INTO `u_info` VALUES ('1', 'mzn1', '20', '男', '');
INSERT INTO `u_info` VALUES ('2', '测试1', '21', '男', '没有。。。。');
INSERT INTO `u_info` VALUES ('3', '测试2', '', '保密', '？6666111');
INSERT INTO `u_info` VALUES ('4', '测试三', '18', '保密', '111111');
INSERT INTO `u_info` VALUES ('5', '测试1111', '', '', '');
DROP TRIGGER IF EXISTS `info_insert`;
DELIMITER ;;
CREATE TRIGGER `info_insert` AFTER INSERT ON `user` FOR EACH ROW begin
  insert into u_info(u_id,u_nickname) values(new.u_id,new.u_name);
end
;;
DELIMITER ;
