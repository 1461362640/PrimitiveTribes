/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : primitivetribes

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2014-10-13 22:13:59
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
INSERT INTO `t_log` VALUES ('055d5950bf6340028d2410579a893204', '127.0.0.1', '2014-10-13 21:48:48', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('0d34c39b25d34bff8c94a3bba67df764', '127.0.0.1', '2014-10-13 22:03:32', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('11efe2638a9d41828c6ad488ba502d0a', '127.0.0.1', '2014-10-13 20:30:59', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('12c5c1a68cba49188fc721dc10210682', '127.0.0.1', '2014-10-13 21:49:16', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('23d4056f86734452a1ce03320be89877', '127.0.0.1', '2014-10-13 21:47:35', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('2629e070eb824ede8fa80d96063c4f9b', '127.0.0.1', '2014-10-13 21:40:27', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('28762b8b6cc944918a2a672749da82a3', '127.0.0.1', '2014-10-13 20:31:59', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('29e23e911f58413c9c08ac0680ae1ce4', '127.0.0.1', '2014-10-13 21:54:59', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('301a939a4ad24be7937227e781a8abf4', '127.0.0.1', '2014-10-13 21:47:36', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('343511057f73473b8bd270e79a35faa5', '127.0.0.1', '2014-10-13 21:49:15', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('3b0b7a1db542484a88e70edc273b93ec', '127.0.0.1', '2014-10-13 20:28:25', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('4d7fe8ed44974a1a9affd004cd51ad30', '127.0.0.1', '2014-10-13 21:48:19', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('60200c28e9724e5c93eb7d6d8b555695', '127.0.0.1', '2014-10-13 21:39:44', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('610abe55d4164cda9526c0fc5a680fdb', '127.0.0.1', '2014-10-13 21:42:55', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('6ffacb33603c4bef87dbdd7b05725a3a', '127.0.0.1', '2014-10-13 22:12:39', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('76295a40ad364b0392f953810743910a', '127.0.0.1', '2014-10-13 20:27:02', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('85959346a8ed4c02902a76371d9483dc', '127.0.0.1', '2014-10-13 20:28:27', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('89115ecf8d4a4854bbfc7276a69dc4c8', '127.0.0.1', '2014-10-13 21:48:47', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('89e3dce1e2be464299a59b166b5d81c1', '127.0.0.1', '2014-10-13 21:47:12', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('8a5e132b5d6747dd98c2133e37d62231', '127.0.0.1', '2014-10-13 21:40:57', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('8c30507d8e214aab87377846514d649a', '127.0.0.1', '2014-10-13 20:32:00', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('95b5029a613142f49dffde9cf42e84b9', '127.0.0.1', '2014-10-13 21:54:58', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('9b8ee19404f849ebb219c61b8325ebe5', '127.0.0.1', '2014-10-13 22:12:40', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('a7003de2b640495cbee85963d592ffea', '127.0.0.1', '2014-10-13 20:30:58', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('a8e992698c0c42838cc5bc5d80c5c2ad', '127.0.0.1', '2014-10-13 21:38:10', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('a9fb212c37b947b8b598237cc8901743', '127.0.0.1', '2014-10-13 22:03:31', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('ab4a4e27ef3f43c6996140297baf1301', '127.0.0.1', '2014-10-13 21:47:10', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('adfa16b76fe448028b8015ef1e872a9f', '127.0.0.1', '2014-10-13 21:39:45', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('b2bc0e4fdb804e2993db52aa7ab1c80b', '127.0.0.1', '2014-10-13 21:40:26', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('cdc59afa21304755a4eb9be6c68ea341', '127.0.0.1', '2014-10-13 21:40:56', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('ce657f16124c4d8196a5e8f2ddd89d3f', '127.0.0.1', '2014-10-13 20:31:23', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('dcd8d2c2528247d0a8cb463ac6e24fc3', '127.0.0.1', '2014-10-13 20:27:03', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('df41cf6f5d0844f8bc0d8da0748ad728', '127.0.0.1', '2014-10-13 20:33:43', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('e3d01a5f7a20492c9a42d22c0031aa88', '127.0.0.1', '2014-10-13 21:44:55', '退出系统', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('ec3a25e4e9d14fc3b6f836d8fe877c27', '127.0.0.1', '2014-10-13 21:44:56', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('f0e7ec9617714d3abbdfcd7046b4a853', '127.0.0.1', '2014-10-13 21:42:57', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('f265a7a629174b3ba86804e10c9d792e', '0:0:0:0:0:0:0:1', '2014-10-13 20:22:33', '登录成功', null, '陈亚洲');
INSERT INTO `t_log` VALUES ('f7f31abb75bd4ecea4c153442125172e', '127.0.0.1', '2014-10-13 21:48:18', '退出系统', null, '陈亚洲');

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
INSERT INTO `t_menu` VALUES ('017192029c66424d9b6ea106546a18d4', '2014-10-13 21:52:11', 'icon-sys', '990', '27', '商品管理', null, '', '0', '0', '28', '1', null);
INSERT INTO `t_menu` VALUES ('059d4260faa546e4bd2b803b8db61331', '2014-10-13 22:12:17', 'icon-sys', '2999', '63', '收货地址', null, '', '0', '0', '68', '1', null);
INSERT INTO `t_menu` VALUES ('05df7f4f623b4a55837ba9b7fccc914f', '2014-10-13 21:47:32', 'icon-metro-users', '990', '3', '用户管理', null, '', '0', '0', '7', '1', null);
INSERT INTO `t_menu` VALUES ('091ea82b86074c2986737bacab339b53', '2014-10-13 22:02:05', 'icon-sys', '2999', '56', '试用参与', null, '', '0', '0', '57', '1', null);
INSERT INTO `t_menu` VALUES ('137e868c94ae47f0a00e3ce8bb373c74', '2014-10-13 21:41:20', 'icon-munich-customers', '969', '7', '系统用户', null, './user.do?list', '0', '0', '11', '1', null);
INSERT INTO `t_menu` VALUES ('1a2681e5796a4cd08887597ef3af140d', '2014-09-23 19:46:18', 'icon-woocons-32-window-move', '997', '3', '栏目管理', null, './menu.do?list', '0', '0', '2', '1', null);
INSERT INTO `t_menu` VALUES ('1ba7130961444c9fac33f215d0ad650c', '2014-10-13 21:57:14', 'icon-sys', '1999', '40', '发布试用活动', null, '', '0', '0', '42', '1', null);
INSERT INTO `t_menu` VALUES ('1bb3e5fcbcdb46d8b8d3b3ad935cb95e', '2014-10-13 22:09:37', 'icon-sys', '2999', '59', '修改资料', null, '', '0', '0', '60', '1', null);
INSERT INTO `t_menu` VALUES ('291de3ec8cd642618707338a44df3110', '2014-10-13 21:54:43', 'icon-berlin-link', '995', '3', '友情链接管理', null, './links.do?list', '0', '0', '13', '1', null);
INSERT INTO `t_menu` VALUES ('2c88d874c73d4c168e43cceee43fdb9c', '2014-10-13 22:11:46', 'icon-sys', '2999', '63', '资金提现', null, '', '0', '0', '66', '1', null);
INSERT INTO `t_menu` VALUES ('2ef107e17ad345758993c0fd2aecefa4', '2014-10-13 22:11:13', 'icon-sys', '2999', '63', '资金流水', null, '', '0', '0', '64', '1', null);
INSERT INTO `t_menu` VALUES ('31ab9c0c05b8419191fd3b897a8fb4a5', '2014-10-13 22:11:34', 'icon-sys', '2999', '63', '充值记录', null, '', '0', '0', '65', '1', null);
INSERT INTO `t_menu` VALUES ('3718b43a55034fd0b72d3825c6911734', '2014-10-13 21:54:21', 'icon-sys', '990', '3', '站内信管理', null, '', '0', '0', '37', '1', null);
INSERT INTO `t_menu` VALUES ('371adcc52e8f48efac0fe8e574f67aa5', '2014-10-13 21:56:25', 'icon-sys', '1999', '39', '试用管理', null, '', '0', '0', '40', '1', null);
INSERT INTO `t_menu` VALUES ('38700e4ff7fc4e4bbc4e5d076c92f6b6', '2014-10-13 21:39:42', 'icon-sys', '1000', '0', '试客系统', null, '', '0', '0', '21', '1', null);
INSERT INTO `t_menu` VALUES ('397c722190a242b5809af3054fb92285', '2014-10-13 21:50:29', 'icon-sys', '990', '3', '商品栏目管理', null, '', '0', '0', '26', '1', null);
INSERT INTO `t_menu` VALUES ('42bb927bcf1743b9b1c499088c914bbd', '2014-09-23 19:48:35', 'icon-hamburg-bug', '994', '3', '系统日志', null, './log.do?list', '0', '0', '5', '1', null);
INSERT INTO `t_menu` VALUES ('436cea6c2ff14a56acd6d578a8e16f64', '2014-10-13 21:42:52', 'icon-munich-customers', '966', '7', '用户审核', null, '#', '0', '0', '24', '1', null);
INSERT INTO `t_menu` VALUES ('47baaca35545461ebabe9a4715dc8b52', '2014-10-13 21:53:44', 'icon-sys', '990', '3', '积分管理', null, '', '0', '0', '34', '1', null);
INSERT INTO `t_menu` VALUES ('4d3df336b7ed4b21959c242bb347c59f', '2014-10-13 21:58:48', 'icon-sys', '1999', '43', '客服中心', null, '', '0', '0', '47', '1', null);
INSERT INTO `t_menu` VALUES ('51a5eb83ac2745bf98adeba9980f3ad9', '2014-10-13 21:42:33', 'icon-munich-customers', '967', '7', '试客用户', null, '#', '0', '0', '23', '1', null);
INSERT INTO `t_menu` VALUES ('530d2e9f22fa4af8916699a2eb588079', '2014-10-13 22:01:39', 'icon-sys', '2999', '55', '试用管理', null, '', '0', '0', '56', '1', null);
INSERT INTO `t_menu` VALUES ('75ff5685e67749d1ad245163c55a3484', '2014-10-13 21:52:43', 'icon-sys', '990', '27', '推荐管理', null, '', '0', '0', '30', '1', null);
INSERT INTO `t_menu` VALUES ('7f0aafdf73fd404e81c3b21e7628f2de', '2014-10-13 21:56:02', 'icon-sys', '2000', '21', '商家后台管理', null, '', '0', '0', '39', '1', null);
INSERT INTO `t_menu` VALUES ('83b37c045be147029d307d717e8e1b14', '2014-10-13 21:54:09', 'icon-sys', '990', '3', '帮助信息管理', null, '', '0', '0', '36', '1', null);
INSERT INTO `t_menu` VALUES ('871766dc90ab4ee08e9cac0994ac5f1e', '2014-10-13 22:00:11', 'icon-sys', '1999', '43', '收货地址', null, '', '0', '0', '54', '1', null);
INSERT INTO `t_menu` VALUES ('9197c34dce0a49c0a1414355a7803f76', '2014-10-13 21:57:37', 'icon-sys', '1999', '39', '会员信息', null, '', '0', '0', '43', '1', null);
INSERT INTO `t_menu` VALUES ('95c1ed1cfc344e3eba1ef68634eabe88', '2014-10-13 22:02:46', 'icon-sys', '2999', '55', '会员信息', null, '', '0', '0', '59', '1', null);
INSERT INTO `t_menu` VALUES ('96d9f678b27c46fcbe7033ec5ddcb3bf', '2014-10-13 21:59:22', 'icon-sys', '1999', '43', '资金流水管理', null, '', '0', '0', '50', '1', null);
INSERT INTO `t_menu` VALUES ('976abce72bfe4e239f80bb732f3e9623', '2014-10-13 22:00:00', 'icon-sys', '1999', '43', '积分管理', null, '', '0', '0', '53', '1', null);
INSERT INTO `t_menu` VALUES ('a393cd8e61f145cdb41692b51f4eb077', '2014-10-13 21:52:54', 'icon-sys', '990', '27', '评价管理', null, '', '0', '0', '31', '1', null);
INSERT INTO `t_menu` VALUES ('a3a3c2153d8c423d81b35c08797f766f', '2014-10-13 21:51:42', 'icon-sys', '990', '3', '试用商品管理', null, '', '0', '0', '27', '1', null);
INSERT INTO `t_menu` VALUES ('abe20a8533b941b8a0f86f4b337d45bd', '2014-10-13 21:48:14', 'icon-woocons-32-magic-wand', '996', '3', '权限管理', null, './permission.do?list', '0', '0', '4', '1', null);
INSERT INTO `t_menu` VALUES ('abe20a8533b941b8a0f86f4b337d45cc', '2014-10-13 21:47:06', 'icon-woocons-32-system-activity-monitor', '998', '21', '系统后台管理', null, '#', '0', '0', '3', '1', null);
INSERT INTO `t_menu` VALUES ('aee0432490cd48e1ac6070b7bc784510', '2014-10-13 21:53:58', 'icon-sys', '990', '3', '资金流水管理', null, '', '0', '0', '35', '1', null);
INSERT INTO `t_menu` VALUES ('b040e06620fb4ac4b16cbc8b996fad52', '2014-10-13 21:58:23', 'icon-sys', '1999', '43', '广告预定', null, '', '0', '0', '45', '1', null);
INSERT INTO `t_menu` VALUES ('bb552dc8e50640ee8716430bfffa01b9', '2014-10-13 21:59:48', 'icon-sys', '1999', '43', '资金提现', null, '', '0', '0', '52', '1', null);
INSERT INTO `t_menu` VALUES ('bd2bee3cea664e62859e72dbec079d88', '2014-10-13 21:58:09', 'icon-sys', '1999', '43', '手机短信', null, '', '0', '0', '44', '1', null);
INSERT INTO `t_menu` VALUES ('be3b6264328545e4add5513c6cc2caf6', '2014-10-13 21:53:29', 'icon-sys', '990', '3', '试用报告管理', null, '#', '0', '0', '33', '1', null);
INSERT INTO `t_menu` VALUES ('c1d3954c63a44a7bb5d98aae8d1f7625', '2014-10-13 21:50:14', 'icon-sys', '990', '3', '信息栏目管理', null, '#', '0', '0', '25', '1', null);
INSERT INTO `t_menu` VALUES ('c33faa213bcf432e9484a1b03aa74692', '2014-10-13 22:00:58', 'icon-sys', '3000', '21', '试客后台管理', null, '', '0', '0', '55', '1', null);
INSERT INTO `t_menu` VALUES ('cbc4cb120612494d9c2bfcf0cab35a46', '2014-10-13 21:53:09', 'icon-sys', '990', '3', '试用审核管理', null, '', '0', '0', '32', '1', null);
INSERT INTO `t_menu` VALUES ('cc657f86200142be8f0fde85e9f87aa9', '2014-10-13 22:09:54', 'icon-sys', '2999', '59', '客服中心', null, '', '0', '0', '61', '1', null);
INSERT INTO `t_menu` VALUES ('cecd6b583b3f4739b59af4e4c63c383d', '2014-10-13 21:54:30', 'icon-sys', '990', '3', '投诉建议管理', null, '', '0', '0', '38', '1', null);
INSERT INTO `t_menu` VALUES ('cfdf5751e74b4948a83b2ee7eb45a29b', '2014-10-13 22:10:10', 'icon-sys', '2999', '59', '站内信', null, '', '0', '0', '62', '1', null);
INSERT INTO `t_menu` VALUES ('d005c36ffb51472581ca58f998d0cb6e', '2014-10-13 21:58:36', 'icon-sys', '1999', '43', '修改资料', null, '', '0', '0', '46', '1', null);
INSERT INTO `t_menu` VALUES ('d44c5fac9d354078aa5526c6f8f53e70', '2014-10-13 22:10:35', 'icon-sys', '2999', '55', '交易管理', null, '', '0', '0', '63', '1', null);
INSERT INTO `t_menu` VALUES ('d8383f46528f41e7895b062d48fa89db', '2014-10-13 21:59:35', 'icon-sys', '1999', '43', '充值记录', null, '', '0', '0', '51', '1', null);
INSERT INTO `t_menu` VALUES ('db09ba9c1f3e46aaaf180a01dbc377d1', '2014-10-13 21:59:01', 'icon-sys', '1999', '43', '站内信', null, '', '0', '0', '48', '1', null);
INSERT INTO `t_menu` VALUES ('de17a3a678a748d8a75e64903311df69', '2014-10-13 21:52:30', 'icon-sys', '990', '27', '推广管理', null, '', '0', '0', '29', '1', null);
INSERT INTO `t_menu` VALUES ('e0067a809ff947a9ac4fb99a5df5391f', '2014-10-13 21:42:14', 'icon-munich-customers', '968', '7', '系统用户', null, '#', '0', '0', '22', '1', null);
INSERT INTO `t_menu` VALUES ('e76ca4543519416eaa200e985b2290a6', '2014-10-13 21:56:56', 'icon-sys', '1999', '40', '试用活动管理', null, '', '0', '0', '41', '1', null);
INSERT INTO `t_menu` VALUES ('f66e16d0c00343908722e44952b659bc', '2014-10-13 21:59:11', 'icon-sys', '1999', '43', '交易管理', null, '', '0', '0', '49', '1', null);
INSERT INTO `t_menu` VALUES ('f7d98eb3b43c421f9610f4823070ce35', '2014-10-13 22:02:24', 'icon-sys', '2999', '56', '试用报告', null, '', '0', '0', '58', '1', null);
INSERT INTO `t_menu` VALUES ('f883542b644a4f4890db071e8d38b2e1', '2014-10-13 22:12:01', 'icon-sys', '1999', '63', '积分管理', null, '', '0', '0', '67', '1', null);

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
INSERT INTO `t_permission` VALUES ('33e33a2a464b459db8311c3d625ea62f', '管理员', '1', '21,39,40,42,41,43,47,54,50,53,45,52,44,46,51,48,49,3,7,11,24,23,22,2,13,37,26,5,34,36,27,28,30,31,29,4,35,33,25,32,38,55,56,57,58,59,60,61,62,63,68,66,64,65,67', null);
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
