/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : epidemic_material

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 04/10/2022 12:21:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anno
-- ----------------------------
DROP TABLE IF EXISTS `anno`;
CREATE TABLE `anno`  (
  `anno_id` int NOT NULL AUTO_INCREMENT,
  `anno_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anno_text` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_text` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`anno_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of anno
-- ----------------------------
INSERT INTO `anno` VALUES (8, NULL, NULL, '新闻11', '新闻内容11111');
INSERT INTO `anno` VALUES (15, '新建的公告', '新建的内容新建的内容新建的内容新建的内容新建的内容新建的内容', NULL, NULL);
INSERT INTO `anno` VALUES (17, '关于依法维护疫情防控秩序的通告', '为依法落实疫情防控“四方”责任，防范疫情传播风险，保障人民群众生命安全和身体健康，根据法律规定和国家、省市有关防疫要求，现就依法维护疫情防控秩序，查处以下违法行为通告如下：\n\n　　一、来自中高风险地区或者有发生疫情地区旅居史人员，不履行报告报备义务，瞒报、谎报涉疫病情、旅居史、接触史、行程等信息，不按要求进行核酸检测，不配合流调排查、隔离管控、健康监测等防控措施的。\n\n　　二、通过更换手机，冒用、伪造、买卖核酸检测等相关证明，借用套用他人行程码、健康码等方式隐瞒行程、活动轨迹，逃避防疫查验的。\n\n　　三、通过故意调换乘坐车辆、故意改变线路、故意绕开交通场站卡口、冲击防疫卡点等方式逃避或阻碍防疫查验，或者以提供车辆、带路等方式帮助他人逃避防疫查验的。\n\n　　四、乘坐公交车、出租车、网约车等公共交通工具，出入小区、商场超市、农贸市场、酒店宾馆、餐馆、医院、药店、景点景区等公共场所时，不按规定佩戴口罩、不配合体温监测，不按要求配合安康码、行程码查验，无码人员不如实进行信息核对登记且不听劝阻的。\n\n　　五、在集中隔离点拒不配合隔离观察等防疫措施且不听劝阻，在居家观察期间不遵守防疫要求，擅自外出、乘坐公共交通工具、出入公共场所的。\n\n　　六、在网络或者其他媒体上编造或传播涉及疫情防控谣言、虚假信息，制造社会恐慌，挑动社会情绪，扰乱公共秩序的。\n\n　　七、扰乱医疗、防疫工作秩序，侵犯医务、防疫等工作人员人身安全，故意损毁医疗、防疫财产及设施的。\n\n　　凡存在以上行为及其他违法行为，并造成严重影响的，公安机关将依法予以行政处罚，构成犯罪的，依法追究刑事责任。欢迎广大群众积极举报。举报电话：0562-110。\n\n铜陵市新冠肺炎疫情防控应急综合指挥部\n\n2022年4月16日', NULL, NULL);
INSERT INTO `anno` VALUES (18, NULL, NULL, '上海战疫：一些积极变化正在显现', '当前，上海正全力推进社会面清零攻坚行动，全市正处在疫情防控关键阶段。在社会各界不懈努力、特别是上海广大市民支持配合下，攻坚行动取得了重要阶段性成果，一些积极变化正在显现。\n\n与此同时，要深刻认识当前上海疫情形势依然严峻复杂，务必坚定信心、凝聚力量，压实“四方责任”，坚决有力推进、持续深入攻坚。\n\n清零攻坚在行动\n\n4月22日，上海新增2736例新冠肺炎本土确诊病例和20634例本土无症状感染者。同日，本土确诊病例出院1875例，无症状感染者解除集中隔离医学观察20768例。\n\n“从近阶段疫情数据可以看出，当前上海疫情形势依然严峻复杂，新增阳性感染者总量基数大、仍处于高位波动，总体在封控区、管控区、集中隔离点内发现。”上海市卫生健康委一级巡视员吴乾渝23日在上海市疫情防控工作新闻发布会上表示。\n\n当前，上海仍在不断加强资源统筹，各区深挖潜力，畅通出院流程，巩固出院人数大于入院人数的正向循环成果。\n\n22日起，上海开展社会面清零攻坚九大行动，力争早日全市实现社会面清零。\n\n根据九大行动的部署，将开展社区管控行动，最大限度减少人员流动聚集。封控区严格实施“足不出户、服务上门”要求；管控区严格实施“人不出小区（单位），错峰取物”要求；防范区严格实行“个人防护，严禁聚集”要求，人员不得流动到封控区和管控区。对流动人员加大街面巡查力度。对居家隔离对象采取上门磁等方式限制人员外出，健康码赋红码。');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total` int NULL DEFAULT NULL,
  `unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `goods`(`goods`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (1, '口罩', 5, '包', '衣物', NULL);
INSERT INTO `material` VALUES (2, '水', 10, '瓶', '食品', NULL);
INSERT INTO `material` VALUES (9, '火腿肠', 10, '箱', '食品', NULL);
INSERT INTO `material` VALUES (10, '防护服', 15, '件', '衣物', NULL);
INSERT INTO `material` VALUES (11, '香蕉', 24, '箱', '食品', NULL);
INSERT INTO `material` VALUES (12, '矿泉水', 7, '箱', '食品', NULL);
INSERT INTO `material` VALUES (13, '帐篷', 3, '个', '衣物', NULL);
INSERT INTO `material` VALUES (14, '草莓', 12, '盒', '食品', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_label` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'home', '/home', '首页', 's-home', 0);
INSERT INTO `menu` VALUES (2, 'news', '/news', '新闻公告', 'video-play', 0);
INSERT INTO `menu` VALUES (3, 'user', '/user', '个人中心', 'user', 0);
INSERT INTO `menu` VALUES (4, 'manager', '/manager', '捐赠管理', 'user', 3);
INSERT INTO `menu` VALUES (5, 'record', '/record', '捐赠记录', 'user', 3);
INSERT INTO `menu` VALUES (6, 'cangku', '/cangku', '仓库物资', 'location', 0);
INSERT INTO `menu` VALUES (7, 'material', '/material', '物资明细', 'setting', 6);
INSERT INTO `menu` VALUES (8, 'in', '/in', '全部记录', 'setting', 6);
INSERT INTO `menu` VALUES (9, 'out', '/out', '发放记录', 'setting', 6);
INSERT INTO `menu` VALUES (10, 'admin', '/admin', '管理中心', 's-tools', 0);
INSERT INTO `menu` VALUES (11, 'userManage', '/admin/user', '人员管理', 's-tools', 10);
INSERT INTO `menu` VALUES (12, 'order', '/admin/order', '捐赠审核', 's-tools', 10);
INSERT INTO `menu` VALUES (13, 'dispatch', '/admin/dispatch', '物资发放', 's-tools', 10);
INSERT INTO `menu` VALUES (14, 'newsManage', '/admin/news', '新闻管理', 's-tools', 10);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `record_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_goods` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_total` int NULL DEFAULT NULL,
  `record_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_date` timestamp NULL DEFAULT NULL,
  `record_check` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (28, '小王', '火腿肠', 10, '箱', '食品', '2022-04-19 09:16:49', '已通过', NULL, '王中王');
INSERT INTO `record` VALUES (29, '小王', '矿泉水', 10, '箱', '食品', '2022-04-19 09:16:49', '已通过', NULL, NULL);
INSERT INTO `record` VALUES (30, '小王', '防护服', 20, '件', '衣物', '2022-04-19 09:16:49', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (31, '小王', '阿莫西林', 10, '盒', '药品', '2022-04-19 09:16:49', '未通过', NULL, NULL);
INSERT INTO `record` VALUES (32, '小张', '苹果', 15, '箱', '食品', '2022-04-19 09:21:16', '未通过', NULL, NULL);
INSERT INTO `record` VALUES (33, '小张', '香蕉', 12, '箱', '食品', '2022-04-19 09:21:16', '已通过', NULL, NULL);
INSERT INTO `record` VALUES (34, '小张', '防护服', 15, '件', '衣物', '2022-04-19 09:21:16', '已通过', NULL, NULL);
INSERT INTO `record` VALUES (35, '小张', '口罩', 10, '包', '衣物', '2022-04-19 09:21:16', '未通过', NULL, NULL);
INSERT INTO `record` VALUES (36, '大黄', '菠萝', 12, '个', '食品', '2022-04-19 09:28:13', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (37, '大黄', '草莓', 12, '箱', '食品', '2022-04-19 09:28:13', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (38, '大黄', '菠菜', 15, '包', '食品', '2022-04-19 09:28:13', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (39, '大黄', '白菜', 12, '箱', '食品', '2022-04-19 09:28:13', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (40, '大黄', '帐篷', 5, '个', '衣物', '2022-04-19 09:28:13', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (41, '张三三啊', '火龙果', 12, '箱', '食品', '2022-04-19 09:35:06', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (42, '张三三啊', '菠萝', 13, '箱', '食品', '2022-04-19 09:35:06', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (43, '张三三啊', '帐篷', 3, '个', '衣物', '2022-04-19 09:35:06', '已通过', NULL, NULL);
INSERT INTO `record` VALUES (44, '张三三啊', '菠菜', 12, '箱', '食品', '2022-04-19 09:35:06', '未通过', NULL, NULL);
INSERT INTO `record` VALUES (45, '张三三啊', '火腿肠', 10, '盒', '食品', '2022-04-19 09:35:06', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (46, '张三三啊', '矿泉水', 12, '件', '食品', '2022-04-19 09:35:06', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (47, '张三', '西瓜', 12, '个', '食品', '2022-04-19 09:36:40', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (48, '张三', '草莓', 12, '盒', '食品', '2022-04-19 09:36:40', '已通过', NULL, NULL);
INSERT INTO `record` VALUES (49, '张三', '白菜', 2, '盒', '食品', '2022-04-19 09:36:40', '未通过', NULL, NULL);
INSERT INTO `record` VALUES (50, '张三', '菠菜', 5, '箱', '食品', '2022-04-19 09:36:40', '待审核', NULL, NULL);
INSERT INTO `record` VALUES (51, 'admin', '矿泉水', 3, '箱', '食品', '2022-04-19 09:42:40', NULL, '直辖市/北京市', '');
INSERT INTO `record` VALUES (52, '张三', '香蕉', 12, '箱', '食品', '2022-04-24 08:49:48', '已通过', NULL, '巍峨');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `roles_id` int NOT NULL AUTO_INCREMENT,
  `roles_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roles_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '管理员');
INSERT INTO `roles` VALUES (2, '普通用户');

-- ----------------------------
-- Table structure for roles_menu
-- ----------------------------
DROP TABLE IF EXISTS `roles_menu`;
CREATE TABLE `roles_menu`  (
  `rm_id` int NOT NULL AUTO_INCREMENT,
  `rm_rid` int NULL DEFAULT NULL,
  `rm_mid` int NULL DEFAULT NULL,
  PRIMARY KEY (`rm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles_menu
-- ----------------------------
INSERT INTO `roles_menu` VALUES (1, 1, 1);
INSERT INTO `roles_menu` VALUES (2, 1, 6);
INSERT INTO `roles_menu` VALUES (3, 1, 7);
INSERT INTO `roles_menu` VALUES (4, 1, 8);
INSERT INTO `roles_menu` VALUES (5, 1, 9);
INSERT INTO `roles_menu` VALUES (6, 1, 10);
INSERT INTO `roles_menu` VALUES (7, 1, 11);
INSERT INTO `roles_menu` VALUES (8, 1, 12);
INSERT INTO `roles_menu` VALUES (9, 1, 13);
INSERT INTO `roles_menu` VALUES (10, 1, 14);
INSERT INTO `roles_menu` VALUES (11, 2, 1);
INSERT INTO `roles_menu` VALUES (12, 2, 2);
INSERT INTO `roles_menu` VALUES (13, 2, 3);
INSERT INTO `roles_menu` VALUES (14, 2, 4);
INSERT INTO `roles_menu` VALUES (15, 2, 5);
INSERT INTO `roles_menu` VALUES (16, 2, 6);
INSERT INTO `roles_menu` VALUES (17, 2, 7);
INSERT INTO `roles_menu` VALUES (18, 2, 8);
INSERT INTO `roles_menu` VALUES (19, 2, 9);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_disable` int NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE,
  UNIQUE INDEX `user_idcard`(`user_idcard`) USING BTREE,
  UNIQUE INDEX `user_phone`(`user_phone`) USING BTREE,
  UNIQUE INDEX `user_email`(`user_email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'admin', '123456789999999999', '13088888888', '123@123.com', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `user_info` VALUES (2, '张三', '666666888888999999', '13333333333', '111@123.com', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `user_info` VALUES (20, '小王', '199999200009090009', '19999999999', 'wang@wang.com', '202cb962ac59075b964b07152d234b70', 1);
INSERT INTO `user_info` VALUES (21, '小张', '188888188808188888', '18888888888', 'zhang@zhang.com', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `user_info` VALUES (22, '大黄', '166666206606066666', '16666666666', 'huang@hang.com', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `user_info` VALUES (23, '张三三啊', '177777207707077777', '17777777777', 'san@san.com', '202cb962ac59075b964b07152d234b70', 0);

-- ----------------------------
-- Table structure for user_info_role
-- ----------------------------
DROP TABLE IF EXISTS `user_info_role`;
CREATE TABLE `user_info_role`  (
  `ur_id` int NOT NULL AUTO_INCREMENT,
  `ur_uid` int NULL DEFAULT NULL,
  `ur_rid` int NULL DEFAULT NULL,
  PRIMARY KEY (`ur_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info_role
-- ----------------------------
INSERT INTO `user_info_role` VALUES (1, 1, 1);
INSERT INTO `user_info_role` VALUES (2, 2, 2);
INSERT INTO `user_info_role` VALUES (13, 20, 2);
INSERT INTO `user_info_role` VALUES (14, 21, 2);
INSERT INTO `user_info_role` VALUES (15, 22, 2);
INSERT INTO `user_info_role` VALUES (16, 23, 2);

SET FOREIGN_KEY_CHECKS = 1;
