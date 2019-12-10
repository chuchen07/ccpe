/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : ccpedb

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/12/2019 15:29:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '初级', '身心健康', '欧阳颖儒');
INSERT INTO `course` VALUES (2, '中级', '心理健康', '欧阳颖儒');
INSERT INTO `course` VALUES (3, '高级', '生理知识', '欧阳颖儒');

-- ----------------------------
-- Table structure for fill_blank
-- ----------------------------
DROP TABLE IF EXISTS `fill_blank`;
CREATE TABLE `fill_blank`  (
  `fill_blank_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '填空题id',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '答案',
  `paper_id` int(8) NULL DEFAULT NULL COMMENT '来源试卷id',
  PRIMARY KEY (`fill_blank_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fill_blank
-- ----------------------------
INSERT INTO `fill_blank` VALUES (64, 'C++语言程序开发过程要经历编辑、_______、________和_______四个基本步骤。', '编译 连接 运行', 44);
INSERT INTO `fill_blank` VALUES (65, 'C++语言中的变量，按作用域的不同可分为两种，即_______和_______。', '全局变量 局部变量', 44);
INSERT INTO `fill_blank` VALUES (66, '要判别用year表示的某一年是否闰年，用逻辑表达式表示为_______。', '( year % 4= =0 && year % 100! =0 ) || year % 400 = = 0', 44);
INSERT INTO `fill_blank` VALUES (67, '变量的存储方式可分为静态存储方式和动态存储方式。自动变量是动态存储方式，其类型说明符为_______，可省略。静态变量属于静态存储方式，其类型说明符为_______。', 'auto static', 44);
INSERT INTO `fill_blank` VALUES (68, '在C++语言中，一维数组元素a[i] 用指针可表示为_______，二维数组元素a[i][j]用指针可表示为_______。', '*(a+i)  *(*(a+i)+j)', 44);

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge`  (
  `judge_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '判断题id',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '答案',
  `paper_id` int(8) NULL DEFAULT NULL COMMENT '来源试卷id',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  PRIMARY KEY (`judge_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO `judge` VALUES (25, '错', 44, '在函数调用时，实参和形参共用存储单元。');
INSERT INTO `judge` VALUES (26, '错', 44, '若a是实型变量，在执行了a=5;后，a将变答案：C为整型变量。');
INSERT INTO `judge` VALUES (27, '错', 44, '定义一个指针变量char *p; 然后执行cout<< sizeof p;语句后，sizeof p的值为1。');
INSERT INTO `judge` VALUES (28, '对', 44, '链表的数据结构，必须利用指针变量才能实现。即：一个结点中应包含一个指针变量，用它存放下一结点的地址。');

-- ----------------------------
-- Table structure for multiple_choice
-- ----------------------------
DROP TABLE IF EXISTS `multiple_choice`;
CREATE TABLE `multiple_choice`  (
  `multiple_choice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选择题id',
  `answer1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项一',
  `answer2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项二',
  `answer3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项三',
  `answer4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项四',
  `answer_true` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正确答案',
  `paper_id` int(8) NULL DEFAULT NULL COMMENT '来源试卷id',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  PRIMARY KEY (`multiple_choice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 361 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of multiple_choice
-- ----------------------------
INSERT INTO `multiple_choice` VALUES (301, '从本程序的main( )函数开始，到本程序文件的最后一个函数结束。', '从本程序文件的第一个函数开始，到本程序文件的最后一个函数结束。', '从main( )函数开始，到main( )函数结束。', '从本程序文件的第一个函数开始，到本程序main( )函数结束。', 'C', 44, '一个C++语言程序可以包括多个函数，程序总是按照如下(  )所描述的方式执行当前的程序。');
INSERT INTO `multiple_choice` VALUES (302, 'a>b      enum     #include', '_sum     Day      lotus_1_2_3', 'signed    union     D.Michal', 'if        struct     type', 'B', 44, '下面四个选项中,均是合法的标识符的选项是 (    )');
INSERT INTO `multiple_choice` VALUES (303, '5   ', '1   ', '0   ', '不确定值', 'C', 44, '以下程序的输出结果是(       )。\nmain( )\n{ int a=7,b=5; \ncout<<b=b/a<<endl;\n}');
INSERT INTO `multiple_choice` VALUES (304, 'float           ', 'char            ', 'int             ', 'double', 'D', 44, '若有类型说明语句：char w; int x; float y; double z;则表达式w*x+z-y 的结果为（      ）类型。');
INSERT INTO `multiple_choice` VALUES (305, '       ', '', '       ', '', 'D', 44, '下面是由if构成的一个程序段： \nif(a<b)\n  {    if(d= =c)\n         x=1;\n  }\n       else    x=2;\n该程序段所表示的逻辑关系对应的表达式是________。');
INSERT INTO `multiple_choice` VALUES (306, '整数1           ', 'True      　　　', '非0数  　　　　', '任意常数', 'C', 44, '在C语言中，逻辑“真”等价于下面哪一选项（         ）。');
INSERT INTO `multiple_choice` VALUES (307, '-3           ', '9           ', '-12           ', '0', 'D', 44, '若a为int类型，且其值为3，则执行完表达式a+=a-=a*a后，a的值是(      )。');
INSERT INTO `multiple_choice` VALUES (308, '   x>20||x<30 && x<-100        ', 'x>20&&x<30 || x<-100  ', '   x>20&x<30 || x<-100         ', 'x>20&&x<30 || x<100', 'B', 44, '能表达20<x<30或x<-100的C语言表达式是（       ）。');
INSERT INTO `multiple_choice` VALUES (309, '3,2           ', '3,3          ', '4,4           ', '4,5', 'B', 44, '执行下面的程序段后，a和b的值分别为（         ）。\nint a=3,b=5,c;\nc=(a>--b)?a++:b--;');
INSERT INTO `multiple_choice` VALUES (310, '语句“k=k-1;”执行1次      ', '语句“k=k-1;”执行10次', '语句“k=k-1;”执行11次     ', '语句“k=k-1;”执行12次', 'C', 44, '有程序段如下：\nint k=10;\ndo\n{  k=k-1;\n}while(k>=0);\n则下面描述中正确的是（           ）。');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试卷名称',
  PRIMARY KEY (`paper_id`, `paper_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (44, 'C++模拟题');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'deleteOrder');
INSERT INTO `permission` VALUES (2, 'submitOrder');
INSERT INTO `permission` VALUES (3, 'payFree');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '普通用户');
INSERT INTO `role` VALUES (2, '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`rid`, `pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (2, 2);
INSERT INTO `role_permission` VALUES (2, 3);

-- ----------------------------
-- Table structure for subject_record
-- ----------------------------
DROP TABLE IF EXISTS `subject_record`;
CREATE TABLE `subject_record`  (
  `subject_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NULL DEFAULT NULL,
  `fill_blank_id` int(8) NULL DEFAULT NULL,
  `judge_id` int(8) NULL DEFAULT NULL,
  `user_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `multiple_choice_id` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`subject_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_record
-- ----------------------------
INSERT INTO `subject_record` VALUES (38, 1, 64, NULL, 'a', NULL);
INSERT INTO `subject_record` VALUES (39, 1, 65, NULL, NULL, NULL);
INSERT INTO `subject_record` VALUES (40, 1, 66, NULL, NULL, NULL);
INSERT INTO `subject_record` VALUES (41, 1, 67, NULL, NULL, NULL);
INSERT INTO `subject_record` VALUES (42, 1, 68, NULL, NULL, NULL);
INSERT INTO `subject_record` VALUES (44, 1, NULL, 27, NULL, NULL);
INSERT INTO `subject_record` VALUES (45, 1, NULL, 28, NULL, NULL);
INSERT INTO `subject_record` VALUES (46, 1, NULL, NULL, NULL, 302);
INSERT INTO `subject_record` VALUES (47, 1, NULL, NULL, NULL, 303);
INSERT INTO `subject_record` VALUES (48, 1, NULL, NULL, NULL, 304);
INSERT INTO `subject_record` VALUES (49, 1, NULL, NULL, NULL, 305);
INSERT INTO `subject_record` VALUES (50, 1, NULL, NULL, NULL, 306);
INSERT INTO `subject_record` VALUES (51, 1, NULL, NULL, NULL, 307);
INSERT INTO `subject_record` VALUES (52, 1, NULL, NULL, NULL, 308);
INSERT INTO `subject_record` VALUES (53, 1, NULL, NULL, NULL, 309);
INSERT INTO `subject_record` VALUES (54, 1, NULL, NULL, NULL, 310);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名字',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userCode` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '激活码',
  `userState` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活状态',
  PRIMARY KEY (`userId`, `userCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1464596602@qq.com', '欧阳颖儒', 'c5db9b1ed4c0bd7d769e5fe42e6c8e59', '458a748cee994efea461488cc7c89a915f72e1ee3e4a488ca010727c699dccf9', '1');
INSERT INTO `user` VALUES (5, '1079734065@qq.com', '杨锦聪', 'c10823215bb56f4367efe30ae3455d08', '7afffc7922fc4980ab3d01fae42c1944c5fa8e3476ed4662a41cf642d6744af1', '1');
INSERT INTO `user` VALUES (6, '1378902792@qq.com', '谭小侠', '14e552bfeaddee37fa0af53443d7bf06', '27e92b26993949bd9f65f990d4c24c62e78ecd11c4d8432ca9657310d81ca949', '1');
INSERT INTO `user` VALUES (20, '993643197@qq.com', '林奇', 'c15d2d2a9ecf5d4049da9cf44b3dac39', '6a9e251fcc1841fba4148ecb40c2de50fd2337a2e8a649cd90c8a087706c3e06', '1');
INSERT INTO `user` VALUES (22, '993643192@qq.com', '欧弟', '87ca946b616be1cb57a93088f16a9df5', 'default', '1');
INSERT INTO `user` VALUES (23, '993643193@qq.com', '汪涵', '5142acc271276c09422dc6e534b57401', 'default', '1');
INSERT INTO `user` VALUES (24, '993643194@qq.com', '小五', '8e42dc851870a9915aff3c51ebec77a0', 'default', '1');
INSERT INTO `user` VALUES (25, '993643195@qq.com', '浩二', '315cacf075b222af7e95774e6e6f6e13', 'default', '1');
INSERT INTO `user` VALUES (26, '874747210@qq.com', '林洋', '2690a3bb4be20f749597b97de7e8a09a', 'default', '1');
INSERT INTO `user` VALUES (27, '993643198@qq.cm', '东方不败', '7a11380ff01525a128a40bbc7c5b7eba', 'default', '1');
INSERT INTO `user` VALUES (28, '874747211@qq.com', '百里', '76b923a1ee5271ea9cbd0122b48ea6f5', 'default', '1');
INSERT INTO `user` VALUES (29, '874747212@qq.com', '花花', '01d3520693e49d944adefa67b5437992', 'default', '1');
INSERT INTO `user` VALUES (30, '874747213@qq.com', '王闪火', '34b2512e1a0e066dd72ed261dc644664', 'default', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `userId` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (5, 1);
INSERT INTO `user_role` VALUES (6, 1);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (22, 1);
INSERT INTO `user_role` VALUES (23, 1);
INSERT INTO `user_role` VALUES (24, 1);
INSERT INTO `user_role` VALUES (25, 1);
INSERT INTO `user_role` VALUES (27, 1);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `video_id` int(255) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `video_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `video_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (64, 'a', '高级', '/ChickenVideo/20191209220539a.mp4', 'F:\\ChickenVideo\\20191209220539a.mp4');
INSERT INTO `video` VALUES (65, 'c', '初级', '/ChickenVideo/20191209220539c.mp4', 'F:\\ChickenVideo\\20191209220539c.mp4');
INSERT INTO `video` VALUES (66, 'b', '中级', '/ChickenVideo/20191209220539b.mp4', 'F:\\ChickenVideo\\20191209220539b.mp4');
INSERT INTO `video` VALUES (67, 'b', '中级', '/ChickenVideo/20191209232059b.mp4', 'F:\\ChickenVideo\\20191209232059b.mp4');
INSERT INTO `video` VALUES (68, 'a', '高级', '/ChickenVideo/20191209232059a.mp4', 'F:\\ChickenVideo\\20191209232059a.mp4');
INSERT INTO `video` VALUES (70, 'Neon Genesis Evangelion 01', '高级', '/ChickenVideo/20191210093014Neon Genesis Evangelion 01.rmvb', 'F:\\ChickenVideo\\20191210093014Neon Genesis Evangelion 01.rmvb');
INSERT INTO `video` VALUES (72, '呆滞大锤在线审问的亮眼表现_18-10-20_19-29-17', '高级', '/ChickenVideo/20191210094724呆滞大锤在线审问的亮眼表现_18-10-20_19-29-17.mp4', 'F:\\ChickenVideo\\20191210094724呆滞大锤在线审问的亮眼表现_18-10-20_19-29-17.mp4');
INSERT INTO `video` VALUES (73, '呆滞大锤在线审问的亮眼表现_18-09-22_21-36-55', '中级', '/ChickenVideo/20191210094724呆滞大锤在线审问的亮眼表现_18-09-22_21-36-55.mp4', 'F:\\ChickenVideo\\20191210094724呆滞大锤在线审问的亮眼表现_18-09-22_21-36-55.mp4');

SET FOREIGN_KEY_CHECKS = 1;
