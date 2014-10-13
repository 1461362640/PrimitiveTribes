/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : primitivetribes

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2014-10-13 21:08:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` varchar(32) NOT NULL,
  `editDate` datetime DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `uploadDate` datetime DEFAULT NULL,
  `videoUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_banner
-- ----------------------------

-- ----------------------------
-- Table structure for t_filmtype
-- ----------------------------
DROP TABLE IF EXISTS `t_filmtype`;
CREATE TABLE `t_filmtype` (
  `id` varchar(32) NOT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `selected` bit(1) DEFAULT NULL,
  `text` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_filmtype
-- ----------------------------

-- ----------------------------
-- Table structure for t_links
-- ----------------------------
DROP TABLE IF EXISTS `t_links`;
CREATE TABLE `t_links` (
  `id` varchar(32) NOT NULL,
  `bold` bit(1) DEFAULT NULL,
  `href` varchar(32) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_links
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(32) NOT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `operation` varchar(32) DEFAULT NULL,
  `role` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('000ee3703dd843aaaa0e64289cfcd9e9', '127.0.0.1', '2014-10-13 20:31:25', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('11efe2638a9d41828c6ad488ba502d0a', '127.0.0.1', '2014-10-13 20:30:59', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('28762b8b6cc944918a2a672749da82a3', '127.0.0.1', '2014-10-13 20:31:59', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('3b0b7a1db542484a88e70edc273b93ec', '127.0.0.1', '2014-10-13 20:28:25', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('76295a40ad364b0392f953810743910a', '127.0.0.1', '2014-10-13 20:27:02', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('85959346a8ed4c02902a76371d9483dc', '127.0.0.1', '2014-10-13 20:28:27', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('8c30507d8e214aab87377846514d649a', '127.0.0.1', '2014-10-13 20:32:00', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('a7003de2b640495cbee85963d592ffea', '127.0.0.1', '2014-10-13 20:30:58', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('ce657f16124c4d8196a5e8f2ddd89d3f', '127.0.0.1', '2014-10-13 20:31:23', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('dcd8d2c2528247d0a8cb463ac6e24fc3', '127.0.0.1', '2014-10-13 20:27:03', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('df41cf6f5d0844f8bc0d8da0748ad728', '127.0.0.1', '2014-10-13 20:33:43', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('f265a7a629174b3ba86804e10c9d792e', '0:0:0:0:0:0:0:1', '2014-10-13 20:22:33', '登录成功', null, '陈亚洲');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(32) NOT NULL,
  `commitTime` datetime DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `menuIndex` smallint(6) DEFAULT NULL,
  `menuid` varchar(32) DEFAULT NULL,
  `menuname` varchar(32) DEFAULT NULL,
  `subId` varchar(32) DEFAULT NULL,
  `url` varchar(40) DEFAULT NULL,
  `isCheck` smallint(6) DEFAULT NULL,
  `isOpen` smallint(6) DEFAULT NULL,
  `recordIndex` int(11) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('05df7f4f623b4a55837ba9b7fccc914f', '2014-10-13 20:26:51', 'icon-metro-users', '990', '21', '人事管理', null, '', '0', '0', '7', '1', null);
INSERT INTO `t_menu` VALUES ('137e868c94ae47f0a00e3ce8bb373c74', '2014-09-23 19:51:34', 'icon-munich-customers', '969', '7', '员工管理', null, './user.do?list', '0', '0', '11', '1', null);
INSERT INTO `t_menu` VALUES ('1a2681e5796a4cd08887597ef3af140d', '2014-09-23 19:46:18', 'icon-woocons-32-window-move', '997', '3', '栏目管理', null, './menu.do?list', '0', '0', '2', '1', null);
INSERT INTO `t_menu` VALUES ('291de3ec8cd642618707338a44df3110', '2014-09-23 19:47:48', 'icon-berlin-link', '995', '3', '链接管理', null, './links.do?list', '0', '0', '13', '1', null);
INSERT INTO `t_menu` VALUES ('38700e4ff7fc4e4bbc4e5d076c92f6b6', '2014-10-05 16:13:36', 'icon-sys', '1000', '0', '后台管理', null, '', '0', '0', '21', '1', null);
INSERT INTO `t_menu` VALUES ('42bb927bcf1743b9b1c499088c914bbd', '2014-09-23 19:48:35', 'icon-hamburg-bug', '994', '3', '系统日志', null, './log.do?list', '0', '0', '5', '1', null);
INSERT INTO `t_menu` VALUES ('abe20a8533b941b8a0f86f4b337d45bd', '2014-09-23 19:47:16', 'icon-woocons-32-magic-wand', '996', '3', '权限设置', null, './permission.do?list', '0', '0', '4', '1', null);
INSERT INTO `t_menu` VALUES ('abe20a8533b941b8a0f86f4b337d45cc', '2014-10-05 16:16:39', 'icon-woocons-32-system-activity-monitor', '998', '21', '系统设置', null, '#', '0', '0', '3', '1', null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `userIds` varchar(32) DEFAULT NULL,
  `menuIds` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('33e33a2a464b459db8311c3d625ea62f', '管理员', '1', '21,12,14,20,6,7,11,15,19,17,16,3,2,13,5,4', null);
INSERT INTO `t_permission` VALUES ('efb7e3c5b6eb4bff98afafaa88898f35', '测试组', '16', '18,6,7,11,15,19,17,16', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `image` varchar(32) DEFAULT NULL,
  `mobilephone` varchar(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `recordIndex` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('123321', '123@qq.com', null, '13047617173', '陈亚洲', 'admin', 'admin', '1', '1', '0', 'admin', '2014-08-20 23:14:50', null, '1');
INSERT INTO `t_user` VALUES ('1c609bb7663a44d2a8147db2df2a9d82', '123@qq.com', null, '13047617173', '周杰伦', null, null, '6', '1', '0', '的的', null, '2014-08-23 14:44:39', '1');
INSERT INTO `t_user` VALUES ('2a27290a7f894232804c55b21448b7c0', '123@qq.com', null, '13047617173', '德萨', null, null, '8', '1', '0', '请问', null, '2014-08-23 14:45:11', '1');
INSERT INTO `t_user` VALUES ('2b74bcfc47b1481880e74456297b5db2', '123@qq.com', null, '13047617173', '朱建峰', null, null, '2', '1', '0', '1', null, '2014-08-23 14:43:35', '1');
INSERT INTO `t_user` VALUES ('35cd86736fde47ca98b18b16e273ec88', '123@qq.com', null, '13047617173', '刘德华', 'ldh', '000000', '13', '1', '0', 'ldh', '2014-08-25 23:53:00', '2014-08-23 15:03:57', '1');
INSERT INTO `t_user` VALUES ('4124d805e4fd4857a32262a541ed4717', '123@qq.com', null, '13047617173', '李亚鹏', null, '000000', '5', '1', '0', '韩国', null, '2014-08-23 14:44:15', '1');
INSERT INTO `t_user` VALUES ('6d98557ad0e34652b67fb7a23015f3e4', '123@qq.com', null, '13047617173', '嘉康利', null, null, '9', '1', '0', 'ing发', null, '2014-08-23 14:45:28', '1');
INSERT INTO `t_user` VALUES ('735db24c76074c078b86fea6fe562db0', '123@qq.com', null, '13047617173', '姚家园', null, null, '14', '1', '0', 'yjy', null, '2014-08-23 15:04:14', '1');
INSERT INTO `t_user` VALUES ('a392fafae8c94f9fa75e3d73d4dae56b', '123@qq.com', null, '13047617173', '阿赫', null, null, '10', '1', '0', '发给', null, '2014-08-23 14:45:54', '1');
INSERT INTO `t_user` VALUES ('dada30b0c4b94905ab76adf615303b38', '120@qq.com', null, '13047617173', '张娜拉', null, null, '15', '1', '0', 'znl', null, '2014-08-23 15:04:36', '1');
INSERT INTO `t_user` VALUES ('dfbda34c82284a0093310a945a255d0c', 'test@qq.com', null, '13047617173', '测试用户', null, '000000', '16', '1', '0', 'test', null, '2014-08-27 19:54:52', '1');
INSERT INTO `t_user` VALUES ('e17fb2e586084f5189b4e30a6cea9ed5', '123@qq.com', null, '13047617173', '清道夫', null, null, '11', '1', '0', 'iyg', null, '2014-08-23 14:46:17', '1');
INSERT INTO `t_user` VALUES ('effc35e83983488791b4dab4b6bed635', '123@qq.com', null, '13047617173', '桑干河', null, null, '7', '1', '0', '那就好', null, '2014-08-23 14:44:57', '1');

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` varchar(32) NOT NULL,
  `describes` varchar(255) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `relName` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `uploadDate` datetime DEFAULT NULL,
  `videoUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_video
-- ----------------------------

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` varchar(32) NOT NULL,
  `describes` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `uploadDate` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version
-- ----------------------------
