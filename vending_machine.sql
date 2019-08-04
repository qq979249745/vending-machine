/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 127.0.0.1:3306
 Source Schema         : vending_machine

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 04/08/2019 17:24:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_buy_machine
-- ----------------------------
DROP TABLE IF EXISTS `tb_buy_machine`;
CREATE TABLE `tb_buy_machine`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `vm_id` int(11) NOT NULL COMMENT '售货机id',
  `location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '售货机经营的位置',
  `state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '售货机的状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_bm_vm`(`vm_id`) USING BTREE,
  CONSTRAINT `frk_bm_vm` FOREIGN KEY (`vm_id`) REFERENCES `tb_vending_machine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '售货机表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_buy_machine
-- ----------------------------
INSERT INTO `tb_buy_machine` VALUES (1, 3, '南昌航空大学20栋楼下', '经营中');
INSERT INTO `tb_buy_machine` VALUES (2, 1, '南昌航空大学19栋楼下', '经营中');
INSERT INTO `tb_buy_machine` VALUES (3, 2, '南昌航空大学17栋楼下', '已下线');
INSERT INTO `tb_buy_machine` VALUES (4, 8, '南昌航空大学教学楼D栋', '经营中');
INSERT INTO `tb_buy_machine` VALUES (5, 6, '南昌航空大学教学楼A栋', '经营中');
INSERT INTO `tb_buy_machine` VALUES (6, 8, '南昌航空大学教学楼B栋', '经营中');
INSERT INTO `tb_buy_machine` VALUES (7, 4, '南昌航空大学教学楼C栋', '待运输');
INSERT INTO `tb_buy_machine` VALUES (8, 2, '南昌航空大学20栋楼下', '经营中');
INSERT INTO `tb_buy_machine` VALUES (9, 1, '南昌航空大学20栋楼下', '待运输');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `sale_product_id` int(11) NOT NULL COMMENT '销售产品id',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售出时的价格',
  `order_time` datetime(0) NOT NULL COMMENT '订单时间',
  `pay_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '订单流水号',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_o_sp`(`sale_product_id`) USING BTREE,
  CONSTRAINT `frk_o_sp` FOREIGN KEY (`sale_product_id`) REFERENCES `tb_sale_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (1, 1, 3.50, '2019-06-27 22:54:52', '2019062806545201', '未付款');
INSERT INTO `tb_order` VALUES (2, 1, 3.50, '2019-06-27 22:57:06', '2019062806570601', '已支付');
INSERT INTO `tb_order` VALUES (4, 3, 2.60, '2019-06-27 23:45:07', '2019062807450601', '已支付');
INSERT INTO `tb_order` VALUES (5, 30, 50.00, '2019-06-28 00:08:44', '2019062808084304', '已支付');
INSERT INTO `tb_order` VALUES (6, 14, 3.50, '2019-06-28 02:18:10', '2019062810181005', '已支付');
INSERT INTO `tb_order` VALUES (7, 31, 3.50, '2019-06-28 14:30:48', '2019062822304802', '已支付');
INSERT INTO `tb_order` VALUES (8, 1, 3.50, '2019-06-30 06:23:01', '2019063014230001', '已支付');

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `inventory` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, '肥宅快乐水 500ml', 3.50, 515, 'ab5e23d7c733472c922ebe34b8ac18e4.jpg');
INSERT INTO `tb_product` VALUES (2, '雪碧 250ml', 2.60, 50, '89b775eba7d841bf90ee3195237836d7.jpg');
INSERT INTO `tb_product` VALUES (3, '芬达汽水 330ml', 2.50, 404, '2f87bb271a5147bf85d56049608d2b1b.jpg');
INSERT INTO `tb_product` VALUES (4, '百岁山矿泉水570ml 纯净水饮用水 天然健康', 3.00, 344, 'cce0b59d481840a799d40f08dfd7e0ee.jpg');
INSERT INTO `tb_product` VALUES (5, '统一小茗同学青柠红茶冷泡茶480ml', 4.00, 260, 'b23fbc3b7cff473a9ec4b70812943f61.jpg');
INSERT INTO `tb_product` VALUES (6, '康师傅 绿茶蜂蜜茉莉味500ml', 4.50, 260, '450b1638603640a69b47fe288902f397.jpg');
INSERT INTO `tb_product` VALUES (7, '康师傅冰红茶瓶装柠檬口味经典茶饮料500ml', 3.80, 300, '3c42a2f5390045dd88e22cd49d1c226f.jpg');
INSERT INTO `tb_product` VALUES (8, '青岛啤酒经典啤酒500ml', 4.80, 393, '1fc4daf58b5148e1b8ca51b304a984eb.jpg');
INSERT INTO `tb_product` VALUES (9, '澳洲奔富进口洛神西拉赤霞珠干红酒葡萄酒750ml', 50.00, 175, 'bec1d3115cd94425810c6e28b0514b17.jpg');

-- ----------------------------
-- Table structure for tb_sale_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_sale_product`;
CREATE TABLE `tb_sale_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `sale_num` int(11) NOT NULL DEFAULT 0 COMMENT '上架数量',
  `buy_machine_id` int(11) NOT NULL COMMENT '上架的售货机id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_sp_p`(`product_id`) USING BTREE,
  INDEX `frk_sp_bm`(`buy_machine_id`) USING BTREE,
  CONSTRAINT `frk_sp_bm` FOREIGN KEY (`buy_machine_id`) REFERENCES `tb_buy_machine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `frk_sp_p` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sale_product
-- ----------------------------
INSERT INTO `tb_sale_product` VALUES (1, 1, 30, 1);
INSERT INTO `tb_sale_product` VALUES (3, 2, 12, 1);
INSERT INTO `tb_sale_product` VALUES (4, 4, 16, 1);
INSERT INTO `tb_sale_product` VALUES (5, 1, 10, 6);
INSERT INTO `tb_sale_product` VALUES (6, 2, 10, 6);
INSERT INTO `tb_sale_product` VALUES (7, 3, 10, 6);
INSERT INTO `tb_sale_product` VALUES (8, 4, 10, 6);
INSERT INTO `tb_sale_product` VALUES (9, 5, 10, 6);
INSERT INTO `tb_sale_product` VALUES (10, 6, 10, 6);
INSERT INTO `tb_sale_product` VALUES (11, 7, 10, 6);
INSERT INTO `tb_sale_product` VALUES (12, 8, 10, 6);
INSERT INTO `tb_sale_product` VALUES (13, 9, 5, 6);
INSERT INTO `tb_sale_product` VALUES (14, 1, 10, 5);
INSERT INTO `tb_sale_product` VALUES (15, 2, 10, 5);
INSERT INTO `tb_sale_product` VALUES (16, 3, 10, 5);
INSERT INTO `tb_sale_product` VALUES (17, 4, 10, 5);
INSERT INTO `tb_sale_product` VALUES (18, 5, 10, 5);
INSERT INTO `tb_sale_product` VALUES (19, 6, 10, 5);
INSERT INTO `tb_sale_product` VALUES (20, 7, 10, 5);
INSERT INTO `tb_sale_product` VALUES (21, 8, 10, 5);
INSERT INTO `tb_sale_product` VALUES (22, 1, 10, 4);
INSERT INTO `tb_sale_product` VALUES (23, 2, 10, 4);
INSERT INTO `tb_sale_product` VALUES (24, 3, 10, 4);
INSERT INTO `tb_sale_product` VALUES (25, 4, 10, 4);
INSERT INTO `tb_sale_product` VALUES (26, 5, 10, 4);
INSERT INTO `tb_sale_product` VALUES (27, 6, 10, 4);
INSERT INTO `tb_sale_product` VALUES (28, 7, 10, 4);
INSERT INTO `tb_sale_product` VALUES (29, 8, 10, 4);
INSERT INTO `tb_sale_product` VALUES (30, 9, 10, 4);
INSERT INTO `tb_sale_product` VALUES (31, 1, 10, 2);
INSERT INTO `tb_sale_product` VALUES (32, 2, 10, 2);
INSERT INTO `tb_sale_product` VALUES (33, 3, 10, 2);
INSERT INTO `tb_sale_product` VALUES (34, 4, 10, 2);
INSERT INTO `tb_sale_product` VALUES (35, 5, 10, 2);
INSERT INTO `tb_sale_product` VALUES (36, 6, 10, 2);
INSERT INTO `tb_sale_product` VALUES (37, 7, 10, 2);
INSERT INTO `tb_sale_product` VALUES (38, 8, 10, 2);
INSERT INTO `tb_sale_product` VALUES (39, 9, 10, 2);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密密码',
  `email` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `role` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色:factory,business_people,operation_people',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '123456', 'x16YjoF1LNE=', '111@qq.com', '南昌航空大学20栋自动售货机业务人员集合地', 'business');
INSERT INTO `tb_user` VALUES (2, '111111', 'RaFutWeh0ww=', '111@qq.com', '南昌航空大学20栋自动售货机厂商集合地', 'factory');
INSERT INTO `tb_user` VALUES (3, '222222', 'auhoepvxIEQ=', '222@qq.com', '南昌航空大学20栋自动售货机运维人员集合地', 'operation');

-- ----------------------------
-- Table structure for tb_vending_machine
-- ----------------------------
DROP TABLE IF EXISTS `tb_vending_machine`;
CREATE TABLE `tb_vending_machine`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '售货机名称',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售货机价格',
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '售货机图片路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '售货机描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_vending_machine
-- ----------------------------
INSERT INTO `tb_vending_machine` VALUES (1, '红酒自动售货机|售酒机', 2699.00, '581c55deebdc4688a929376b1a4792fd.jpg', '产品型号：升降型红酒自动售货机（自动售酒机），适宜售卖红酒、生鲜、牛奶、鸡蛋等易碎的商品，出货平稳快捷。');
INSERT INTO `tb_vending_machine` VALUES (2, '自动售货机零食饮料机器', 2389.00, 'd17f7f3af10642ad8de616e42db8563c.png', '自动售货机零食饮料机器无人贩卖机24小时自动售卖机商用');
INSERT INTO `tb_vending_machine` VALUES (3, 'T4-21.5寸触摸屏自动售货机', 4999.00, '585ab6a7197e4fab95c6c0e406135d61.png', '外观尺寸:高1955×宽1250×深874.8(mm)设备净重:约400kg额定电压:AC220/50Hz\r\n额定功率:制冷600W/常温150W货道数量:标准6行9列(可调、可定制)\r\n远程后台:电脑端、手机端实时查看设备 销售数据及运营状态\r\n商品容量:标准可容纳270瓶饮料选单操作:21.5寸电容触摸屏选单购买 (安卓系统)支付方式:微信/支付宝支付工作环境:-20℃到40℃出货速度:<5秒\r\n温控系统:最低2℃');
INSERT INTO `tb_vending_machine` VALUES (4, '零食自动售货机-扫码一拖40格', 2699.00, 'af85700483d24e7381bb7e17c06b0f87.png', '产品名称:  盛马纯扫码饮料机   产品型号话ZL-SM\r\n产品尺寸：1951*930*811* (高宽*厚mm )  额定电压：25W/370W制冷方式  风冷无霜\r\n总容量 ：300- 450   制冷温度：4*-15*\r\n加热温度：15*-20* (选装)\r\n重量 ：222KG  支付方式：支付宝');
INSERT INTO `tb_vending_machine` VALUES (5, 'LE303V 投币式咖啡饮料自动售卖机', 1349.00, 'e4f0909aaa814bc5b0eed02d26af836d.jpg', '本机均采用工业级金属、电子零配件，有力保证运行过程中\r\n机器的高稳定性,率先使用国际先进的电脑温控技术，可保证\r\n销售高峰期间连续不间断供应热饮');
INSERT INTO `tb_vending_machine` VALUES (6, '兴元 XD8A 饮料自动售货机', 2359.00, '583e54aac6074a019fac60b529ba6b76.jpg', '产品名称：兴元 XD8A 饮料自动售货机\r\n产品类别： 饮料自动售货机\r\n主要群体： 白领，上班族，学生，老师，医生，护士，居民，家庭主妇，工人\r\n贩卖产品：奶茶，咖啡，红牛，罐装、瓶装、盒装、袋装等各类型包装饮料，矿泉水，烟酒（需授权）等。商品货道可针对商品大小进行宽窄和厚薄的灵活调整根据季节的不同，灵活调整饮料和食品的销售。\r\n适合场地：公寓，写字楼，医院，学校，宿舍楼，食堂，网吧，社区，地铁，图书馆，酒店，宾馆，健身房，娱乐场所等');
INSERT INTO `tb_vending_machine` VALUES (7, '艾丰-扫码支付智能饮料', 3499.00, '691ed9c3a0364d719c4c8e54b614752f.jpg', '外观尺寸：宽:882高1 940厚790 (mm)\r\n机器重量：230KG  工作电源：AC220V/50~60HZ\r\n温控类型：4°C~25*C/常温型  标准货道：60货道\r\n商品容量：300- 800个商品(按体积) 支付方式：支付宝');
INSERT INTO `tb_vending_machine` VALUES (8, '艾丰-超大容量饮料零售', 3980.00, '318e0b988fcb4e6ebff6bbb42bca53da.jpg', '产品型号：AF- 48T(5HP)\r\n外形尺寸：高:1940mm宽:726.5mm 厚:790mm\r\n标准货道：48货道\r\n货舱容量：288- -600个商品(按体积)\r\n重  量：190kg (机器自重)\r\n电  源：AC100- -240V/50~60Hz\r\n支付方式：支付宝\r\n温控模式：冷藏型\r\n温度设定：4°C~25°C');

SET FOREIGN_KEY_CHECKS = 1;
