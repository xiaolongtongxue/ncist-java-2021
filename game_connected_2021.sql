/*
 Navicat Premium Data Transfer

 Source Server         : 0.本机mysql3306连接
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : game_connected_2021

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 25/03/2022 17:07:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for part
-- ----------------------------
DROP TABLE IF EXISTS `part`;
CREATE TABLE `part`  (
  `MinScore` int(11) NOT NULL,
  `MaxScore` int(11) NULL DEFAULT NULL,
  `Part_Name` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `part_Part_Name_uindex`(`Part_Name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part
-- ----------------------------
INSERT INTO `part` VALUES (1700, 2200, '不屈白银');
INSERT INTO `part` VALUES (3200, 3700, '不朽星钻');
INSERT INTO `part` VALUES (2700, 3200, '坚韧铂金');
INSERT INTO `part` VALUES (4700, NULL, '无畏大师');
INSERT INTO `part` VALUES (1200, 1700, '热血青铜');
INSERT INTO `part` VALUES (2200, 2700, '英勇黄金');
INSERT INTO `part` VALUES (3700, 4200, '荣耀皇冠');
INSERT INTO `part` VALUES (4200, 4700, '超级王牌');

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `PID` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(11) NOT NULL,
  `WinNum` int(11) NULL DEFAULT NULL,
  `DefeatNum` int(11) NULL DEFAULT NULL,
  `password` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `player_pname_uindex`(`pname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES ('000014', '123', 1400, 2, 0, '123');
INSERT INTO `player` VALUES ('000012', 'AAA', 1300, 1, 0, '741');
INSERT INTO `player` VALUES ('000007', 'Amy', 1200, 0, 0, '123456');
INSERT INTO `player` VALUES ('000013', 'asdaf', 1200, 0, 0, '123');
INSERT INTO `player` VALUES ('000001', 'aTom', 1300, 1, 0, '123');
INSERT INTO `player` VALUES ('000006', 'John', 1200, 0, 0, '741258963');
INSERT INTO `player` VALUES ('000009', 'kiee', 1200, 0, 0, '123');
INSERT INTO `player` VALUES ('000008', 'Killed', 1200, 0, 0, '741258963');
INSERT INTO `player` VALUES ('000010', 'KKKK', 1300, 1, 0, '123');
INSERT INTO `player` VALUES ('000011', 'lll', 1200, 0, 0, '1111');
INSERT INTO `player` VALUES ('000002', 'Sarah', 2100, 12, 3, '');
INSERT INTO `player` VALUES ('000003', 'TXL', 3000, 30, 12, '123456');
INSERT INTO `player` VALUES ('000004', '想不出叫啥', 1200, 0, 0, '123456');
INSERT INTO `player` VALUES ('000005', '想不出叫啥啊', 1200, 0, 0, '74125896355555');

SET FOREIGN_KEY_CHECKS = 1;
