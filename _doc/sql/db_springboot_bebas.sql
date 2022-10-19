/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Schema         : single_template

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 17/10/2022 17:19:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `base_dict_data`;
CREATE TABLE `base_dict_data`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（0 否 1 是）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_value`(`dict_value` ASC, `dict_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_dict_data
-- ----------------------------
INSERT INTO `base_dict_data` VALUES (70519612373991429, '2022-07-14 22:19:45', 'admin', '2022-07-14 22:32:37', 'admin', 0, 4, 3, '无', '2', 'sys_user_sex', NULL, 'default', '0', '0');
INSERT INTO `base_dict_data` VALUES (70519650416328709, '2022-07-14 22:19:54', 'admin', '2022-07-14 22:21:08', 'admin', 0, 0, 2, '男', '1', 'sys_user_sex', NULL, 'default', '0', '0');
INSERT INTO `base_dict_data` VALUES (70519706510950405, '2022-07-14 22:20:08', 'admin', '2022-07-16 18:22:25', 'admin', 0, 2, 1, '女', '0', 'sys_user_sex', NULL, 'default', '1', '0');
INSERT INTO `base_dict_data` VALUES (71185073868636165, '2022-07-16 18:24:04', 'admin', '2022-07-16 19:04:24', 'admin', 0, 6, 1, '正常', '0', 'sys_normal_disable', NULL, 'primary', '1', '0');
INSERT INTO `base_dict_data` VALUES (71185151308070917, '2022-07-16 18:24:22', 'admin', '2022-08-13 22:27:32', 'admin', 0, 7, 2, '停用', '1', 'sys_normal_disable', NULL, 'danger', '0', '0');
INSERT INTO `base_dict_data` VALUES (115949247513755653, '2022-08-14 16:14:37', 'admin', '2022-08-14 16:14:37', 'admin', 0, 0, 1, '显示', '0', 'sys_show_hide', NULL, 'success', 'N', '0');
INSERT INTO `base_dict_data` VALUES (115949251380379653, '2022-08-14 16:14:52', 'admin', '2022-08-14 16:14:52', 'admin', 0, 0, 2, '隐藏', '1', 'sys_show_hide', NULL, 'warning', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116268424390508549, '2022-08-28 18:27:20', 'admin', '2022-08-28 18:27:27', 'admin', 0, 1, 1, '通知', '1', 'sys_notice_type', NULL, 'primary', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116268429216579589, '2022-08-28 18:27:39', 'admin', '2022-08-28 18:27:39', 'admin', 0, 0, 2, '公告', '2', 'sys_notice_type', NULL, 'primary', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116268442149191685, '2022-08-28 18:28:28', 'admin', '2022-08-28 18:30:57', 'admin', 0, 1, 1, '正常', '0', 'sys_notice_status', NULL, 'primary', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116268483935207429, '2022-08-28 18:31:07', 'admin', '2022-08-28 18:31:07', 'admin', 0, 0, 2, '关闭', '1', 'sys_notice_status', NULL, 'warning', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116335305682321413, '2022-08-31 17:19:32', 'admin', '2022-08-31 17:19:32', 'admin', 0, 0, 1, '正常', '0', 'sys_common_status', NULL, 'success', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116335309412630533, '2022-08-31 17:19:46', 'admin', '2022-08-31 17:19:46', 'admin', 0, 0, 2, '失败', '1', 'sys_common_status', NULL, 'danger', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116494052907286533, '2022-09-07 17:32:25', 'admin', '2022-09-07 17:32:25', 'admin', 0, 0, 1, '正常', '0', 'sys_job_status', NULL, 'success', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116494056427618309, '2022-09-07 17:32:38', 'admin', '2022-09-07 17:32:53', 'admin', 0, 2, 2, '暂停', '1', 'sys_job_status', NULL, 'info', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116494076731457541, '2022-09-07 17:33:55', 'admin', '2022-09-07 17:33:55', 'admin', 0, 0, 1, '默认', 'DEFAULT', 'sys_job_group', NULL, 'primary', 'N', '0');
INSERT INTO `base_dict_data` VALUES (116494080265682949, '2022-09-07 17:34:09', 'admin', '2022-09-24 23:53:45', 'admin', 0, 1, 2, '系统', 'SYSTEM', 'sys_job_group', NULL, 'primary', 'N', '0');

-- ----------------------------
-- Table structure for base_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `base_dict_type`;
CREATE TABLE `base_dict_type`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_dict_type
-- ----------------------------
INSERT INTO `base_dict_type` VALUES (70519580258205658, '2022-07-16 18:54:59', NULL, '2022-07-25 23:35:04', 'admin', 0, 7, 'sys_normal_disable', '系统开关列表', '0', '系统开关列表');
INSERT INTO `base_dict_type` VALUES (70519580258205701, '2022-07-14 22:19:38', 'admin', '2022-07-18 22:00:33', 'admin', 0, 2, 'sys_user_sex', '性别', '0', '性别');
INSERT INTO `base_dict_type` VALUES (115949241785647109, '2022-08-14 16:14:15', 'admin', '2022-08-14 16:14:15', 'admin', 0, 0, 'sys_show_hide', '菜单状态', '0', NULL);
INSERT INTO `base_dict_type` VALUES (116268417232928773, '2022-08-28 18:26:53', 'admin', '2022-08-28 18:26:53', 'admin', 0, 0, 'sys_notice_type', '通知类型', '0', NULL);
INSERT INTO `base_dict_type` VALUES (116268437150105605, '2022-08-28 18:28:09', 'admin', '2022-08-28 18:28:09', 'admin', 0, 0, 'sys_notice_status', '通知状态', '0', '通知状态');
INSERT INTO `base_dict_type` VALUES (116335299423109125, '2022-08-31 17:19:08', 'admin', '2022-08-31 17:25:49', 'admin', 0, 1, 'sys_common_status', '公共状态', '0', '公共状态');
INSERT INTO `base_dict_type` VALUES (116494046560780293, '2022-09-07 17:32:00', 'admin', '2022-09-07 17:32:00', 'admin', 0, 0, 'sys_job_status', '任务状态', '0', '任务状态');
INSERT INTO `base_dict_type` VALUES (116494070838984709, '2022-09-07 17:33:33', 'admin', '2022-09-07 17:33:33', 'admin', 0, 0, 'sys_job_group', '任务分组', '0', NULL);

-- ----------------------------
-- Table structure for base_material_info
-- ----------------------------
DROP TABLE IF EXISTS `base_material_info`;
CREATE TABLE `base_material_info`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `material_type_id` bigint NOT NULL COMMENT '素材分类id',
  `material_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '素材名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相对路径链接url',
  `material_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '素材链接url',
  `thumbnail_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '素材缩略图url',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0正常 1停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '素材管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_material_info
-- ----------------------------

-- ----------------------------
-- Table structure for base_material_type
-- ----------------------------
DROP TABLE IF EXISTS `base_material_type`;
CREATE TABLE `base_material_type`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点id',
  `sort` int NULL DEFAULT NULL COMMENT '排序号',
  `ancestors` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0正常 1停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '素材分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_material_type
-- ----------------------------
INSERT INTO `base_material_type` VALUES (116536198821576709, '2022-09-09 14:11:58', 'admin', '2022-09-09 14:11:58', 'admin', 0, 2, '顶级分类', 0, 1, '0', '0');
INSERT INTO `base_material_type` VALUES (116536219617460229, '2022-09-09 14:13:18', 'admin', '2022-09-09 14:13:18', 'admin', 0, 1, '分类1', 116536198821576709, 1, '0,116536198821576709', '0');
INSERT INTO `base_material_type` VALUES (116536227693854725, '2022-09-09 14:13:49', 'admin', '2022-09-09 14:13:49', 'admin', 0, 0, '分类2', 116536198821576709, 2, '0,116536198821576709', '0');

-- ----------------------------
-- Table structure for base_resource_config
-- ----------------------------
DROP TABLE IF EXISTS `base_resource_config`;
CREATE TABLE `base_resource_config`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `config_key` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源键',
  `resource_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `config_key`(`config_key` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_resource_config
-- ----------------------------
INSERT INTO `base_resource_config` VALUES (102061615, NULL, NULL, '2022-10-11 16:56:04', 'admin', 0, 10, 'main', '{\"maxUserLogin\":5,\"staticWebsite\":\"http://resource-server.com\",\"website\":\"http://localhost:7001\",\"fileSavePathWin\":\"D:\\\\\\\\resourceServer\\\\\\\\\",\"fileSavePathLinux\":\"/projectResource/temp/file/\",\"registerOpen\":true,\"authCodeOpen\":false}');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, NULL, NULL, '2022-08-18 14:22:08', 'admin', 0, 4, 0, '0', '顶级部门', 0, NULL, NULL, NULL, '0');
INSERT INTO `sys_dept` VALUES (116107518461345797, '2022-08-21 15:57:13', 'admin', '2022-08-30 19:11:40', 'admin', 0, 2, 1, '0,1', '开发部', 1, NULL, NULL, NULL, '0');
INSERT INTO `sys_dept` VALUES (116197389196853253, '2022-08-25 15:11:02', 'admin', '2022-08-25 17:02:41', 'test', 0, 2, 1, '0,1', '财务部', 2, NULL, NULL, NULL, '0');
INSERT INTO `sys_dept` VALUES (116197441122861061, '2022-08-25 15:14:20', 'admin', '2022-08-31 17:21:57', 'admin', 0, 21, 116107518461345797, '0,1,116107518461345797', '网络部', 1, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint NOT NULL COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'baseDictTypeServiceImpl.test()', '0/10 * * * * ?', '3', '1', '1', '', '2022-10-13 10:36:16', NULL, '2022-09-07 21:07:02', 'admin', 0, 28);
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', '', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', '', NULL, NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `id` bigint NOT NULL COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (116516108378046469, '系统默认（无参）', 'DEFAULT', 'baseDictTypeServiceImpl.test()', '系统默认（无参） 总共耗时：36毫秒', '0', '', '2022-09-08 16:54:40', NULL, '2022-09-08 16:54:40', NULL, 0, 0);

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `status` int NULL DEFAULT 0 COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `state_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `state_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '2022-06-06 11:17:50', 'admin', '2022-10-06 18:01:51', 'admin', 0, 5, '系统管理', 0, 1, '/system', NULL, '', 0, 0, 'M', '0', '0', 'system', 1000, NULL);
INSERT INTO `sys_menu` VALUES (2, '2022-06-06 11:17:50', 'admin', '2022-09-09 13:34:18', 'admin', 0, 6, '系统监控', 116497084306948101, 2, 'monitor', NULL, '', 0, 0, 'M', '0', '0', 'monitor', 1003, NULL);
INSERT INTO `sys_menu` VALUES (100, '2022-06-06 11:17:50', 'admin', '2022-10-06 18:04:47', 'admin', 0, 3, '用户管理', 1, 1, 'user', 'system/user/index', '', 0, 0, 'C', '0', '0', 'user', 1, NULL);
INSERT INTO `sys_menu` VALUES (101, '2022-06-06 11:17:50', 'admin', '2022-09-25 16:59:53', 'admin', 0, 3, '角色管理', 116896341546500101, 2, 'role', 'system/role/index', '', 0, 0, 'C', '0', '0', 'peoples', 1, NULL);
INSERT INTO `sys_menu` VALUES (102, '2022-06-06 11:17:50', 'admin', '2022-09-25 16:59:58', 'admin', 0, 3, '菜单管理', 116896341546500101, 3, 'menu', 'system/menu/index', '', 0, 0, 'C', '0', '0', 'tree-table', 2, NULL);
INSERT INTO `sys_menu` VALUES (103, '2022-06-06 11:17:50', 'admin', '2022-09-06 15:22:50', 'admin', 0, 1, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 0, 0, 'C', '0', '0', 'tree', 4, NULL);
INSERT INTO `sys_menu` VALUES (104, '2022-06-06 11:17:50', 'admin', '2022-09-06 15:22:55', 'admin', 0, 1, '岗位管理', 1, 5, 'post', 'system/post/index', '', 0, 0, 'C', '0', '0', 'post', 5, NULL);
INSERT INTO `sys_menu` VALUES (105, '2022-06-06 11:17:50', 'admin', '2022-09-06 15:23:08', 'admin', 0, 2, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 0, 0, 'C', '0', '0', 'dict', 6, NULL);
INSERT INTO `sys_menu` VALUES (107, '2022-06-06 11:17:50', 'admin', '2022-09-06 15:23:12', 'admin', 0, 2, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 0, 0, 'C', '0', '0', 'message', 7, NULL);
INSERT INTO `sys_menu` VALUES (108, '2022-06-06 11:17:50', 'admin', '2022-10-06 18:02:07', 'admin', 0, 5, '日志管理', 0, 9, '/log', '', '', 0, 0, 'M', '0', '0', 'log', 1002, NULL);
INSERT INTO `sys_menu` VALUES (109, '2022-06-06 11:17:50', 'admin', '2022-06-06 11:17:50', 'admin', 0, 0, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 0, 0, 'C', '0', '0', 'online', 0, NULL);
INSERT INTO `sys_menu` VALUES (110, '2022-06-06 11:17:50', 'admin', '2022-06-06 11:17:50', 'admin', 0, 0, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 0, 0, 'C', '0', '0', 'job', 0, NULL);
INSERT INTO `sys_menu` VALUES (114, '2022-06-06 11:17:50', 'admin', '2022-09-07 20:45:50', 'admin', 0, 3, '表单构建', 116497084306948101, 1, 'build', 'tool/build/index', '', 0, 0, 'C', '0', '0', 'build', 1, NULL);
INSERT INTO `sys_menu` VALUES (115, '2022-06-06 11:17:50', 'admin', '2022-09-07 20:46:03', 'admin', 0, 2, '代码生成', 116497084306948101, 2, 'gen', 'tool/gen/index', '', 0, 0, 'C', '0', '0', 'code', 2, NULL);
INSERT INTO `sys_menu` VALUES (116, '2022-06-06 11:17:50', 'admin', '2022-09-07 20:46:10', 'admin', 0, 2, '接口文档', 116497084306948101, 3, 'swagger', 'tool/swagger/index', '', 0, 0, 'C', '0', '0', 'swagger', 3, NULL);
INSERT INTO `sys_menu` VALUES (500, '2022-06-06 11:17:50', 'admin', '2022-06-06 11:17:50', 'admin', 0, 0, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 0, 0, 'C', '0', '0', 'form', 0, NULL);
INSERT INTO `sys_menu` VALUES (501, '2022-06-06 11:17:50', 'admin', '2022-06-06 11:17:50', 'admin', 0, 0, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 0, 0, 'C', '0', '0', 'logininfor', 0, NULL);
INSERT INTO `sys_menu` VALUES (116497084306948101, '2022-09-07 20:45:08', 'admin', '2022-10-06 18:02:14', 'admin', 0, 3, '系统工具', 0, 0, '/tool', NULL, NULL, 0, 0, 'M', '0', '0', 'tool', 1004, NULL);
INSERT INTO `sys_menu` VALUES (116535626802659333, '2022-09-09 13:35:36', 'admin', '2022-09-09 13:35:36', 'admin', 0, 0, '素材管理', 1, 0, 'material', NULL, NULL, 0, 0, 'M', '0', '0', 'star', 8, NULL);
INSERT INTO `sys_menu` VALUES (116535653708070917, '2022-09-09 13:37:19', 'admin', '2022-09-09 13:38:39', 'admin', 0, 1, '素材分类', 116535626802659333, 0, 'materialType', 'system/material/type/index', NULL, 0, 0, 'C', '0', '0', 'star', 1, NULL);
INSERT INTO `sys_menu` VALUES (116535662704852997, '2022-09-09 13:37:53', 'admin', '2022-09-09 13:38:44', 'admin', 0, 1, '素材信息', 116535626802659333, 0, 'materialInfo', 'system/material/info/index', NULL, 0, 0, 'C', '0', '0', 'star', 2, NULL);
INSERT INTO `sys_menu` VALUES (116896341546500101, '2022-09-25 11:49:14', 'admin', '2022-09-25 11:52:39', 'admin', 0, 2, '权限管理', 0, 0, '/auth', NULL, NULL, 0, 0, 'M', '0', '0', 'anchor', 1001, NULL);
INSERT INTO `sys_menu` VALUES (116901224459272197, '2022-09-25 16:59:41', 'admin', '2022-09-25 17:00:54', 'admin', 0, 1, '接口管理', 116896341546500101, 0, 'permission', 'system/permission/index', NULL, 0, 0, 'C', '0', '0', 'component', 3, NULL);
INSERT INTO `sys_menu` VALUES (117242727687258117, '2022-10-10 18:51:52', 'admin', '2022-10-11 09:26:05', 'admin', 0, 14, '网站配置', 117256171332567045, 0, 'webset', 'system/webset/index', NULL, 0, 0, 'C', '0', '0', 'international', 1, NULL);
INSERT INTO `sys_menu` VALUES (117256171332567045, '2022-10-11 09:06:36', 'admin', '2022-10-11 09:07:25', 'admin', 0, 1, '网站设置', 0, 0, '/webset', NULL, NULL, 0, 0, 'M', '0', '0', 'flower', 99, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (116579025553457157, '2022-09-11 11:34:50', 'admin', '2022-09-11 11:34:50', 'admin', 1, 0, 'test2', '1', '<p><img src=\"/back-system/profile/upload/2022/09/11/474a4dad-b233-4b8a-90f9-1d2ec368182f_20220911113427A028.jpg\"></p><p>奥术大师多无</p>', '0');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` bigint NOT NULL COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父id',
  `module_controller` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块控制器对象',
  `module_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `original_route_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原始路由',
  `route_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口路由',
  `permission_tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口标识',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式 GET POST...',
  `if_route` tinyint NULL DEFAULT 0 COMMENT '是否路由 0 否 1 是',
  `route_visit_rule` tinyint NULL DEFAULT NULL COMMENT '接口访问规则\r\n1 授权访问\r\n2 匿名访问（不携带token）\r\n3 完全放行\r\n4 不可访问',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (116944499437731845, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986177233879045, 'sysProfileController', '个人业务', 'avatar', '/module/base/profile/avatar', '/module/base/profile/avatar', 'module:base:profile:avatar', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437731846, '2022-09-27 14:51:02', NULL, '2022-10-05 13:56:54', 'admin', 0, 4, 116986378177478661, 'sysRoleController', '角色编辑', 'baseEdit', '/module/base/sysrole/baseEdit', '/module/base/sysrole/baseEdit', 'module:base:sysrole:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437731847, '2022-09-27 14:51:02', NULL, '2022-10-05 13:51:50', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户状态改变', 'changeStatus', '/module/base/sysuser/changeStatus', '/module/base/sysuser/changeStatus', 'module:base:sysuser:changeStatus', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437731848, '2022-09-27 14:51:02', NULL, '2022-10-03 14:09:10', 'admin', 0, 6, 116986328175607813, 'sysPostController', '岗位编辑', 'baseEdit', '/module/base/syspost/baseEdit', '/module/base/syspost/baseEdit', 'module:base:syspost:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437731849, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseQueryByParam', '/module/base/sysmenu/baseQueryByParam', '/module/base/sysmenu/baseQueryByParam', 'module:base:sysmenu:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437731850, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseQueryByParam', '/module/quartz/sysjob/baseQueryByParam', '/module/quartz/sysjob/baseQueryByParam', 'module:quartz:sysjob:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993989, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'excludeChild', '/module/base/sysdept/list/exclude/{deptId}', '/module/base/sysdept/list/exclude/*', 'module:base:sysdept:list:exclude:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993990, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'selectPermissionRoute', '/module/base/sysmenu/selectPermissionRoute', '/module/base/sysmenu/selectPermissionRoute', 'module:base:sysmenu:selectPermissionRoute', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993991, '2022-09-27 14:51:02', NULL, '2022-10-03 14:28:04', 'admin', 0, 2, 116986403964583941, 'sysUserController', '重置密码', 'resetPwd', '/module/base/sysuser/resetPwd', '/module/base/sysuser/resetPwd', 'module:base:sysuser:resetPwd', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993992, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseQueryById', '/module/base/sysmenu/baseQueryById/{id}', '/module/base/sysmenu/baseQueryById/*', 'module:base:sysmenu:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993993, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'treeselect', '/module/base/basematerialtype/treeselect', '/module/base/basematerialtype/treeselect', 'module:base:basematerialtype:treeselect', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499437993994, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986370697461765, 'resourceConfigController', '系统参数配置', 'editByConfigKey', '/module/base/baseresourceconfig/editByConfigKey', '/module/base/baseresourceconfig/editByConfigKey', 'module:base:baseresourceconfig:editByConfigKey', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438256133, '2022-09-27 14:51:02', NULL, '2022-10-10 18:42:32', 'admin', 0, 2, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseDeleteByIds', '/module/base/baseresourceconfig/baseDeleteByIds/{ids}', '/module/base/baseresourceconfig/baseDeleteByIds/*', 'module:base:baseresourceconfig:baseDeleteByIds:*', 'DELETE', 1, 4);
INSERT INTO `sys_permission` VALUES (116944499438256134, '2022-09-27 14:51:02', NULL, '2022-10-05 13:54:05', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色查询', 'baseQueryByParam', '/module/base/sysrole/baseQueryByParam', '/module/base/sysrole/baseQueryByParam', 'module:base:sysrole:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438256135, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986177233879045, 'sysProfileController', '个人业务', 'resetPassword', '/module/base/profile/updatePwd', '/module/base/profile/updatePwd', 'module:base:profile:updatePwd', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438256136, '2022-09-27 14:51:02', NULL, '2022-10-05 13:54:40', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色修改保存数据权限', 'dataScope', '/module/base/sysrole/dataScope', '/module/base/sysrole/dataScope', 'module:base:sysrole:dataScope', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438256137, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'remove', '/module/base/syslogininfor/{infoIds}', '/module/base/syslogininfor/*', 'module:base:syslogininfor:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438256138, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseAdd', '/module/quartz/sysjoblog/baseAdd', '/module/quartz/sysjoblog/baseAdd', 'module:quartz:sysjoblog:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518277, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseQueryPageByParam', '/module/base/sysoperlog/baseQueryPageByParam', '/module/base/sysoperlog/baseQueryPageByParam', 'module:base:sysoperlog:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518278, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseAdd', '/module/base/syslogininfor/baseAdd', '/module/base/syslogininfor/baseAdd', 'module:base:syslogininfor:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518279, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseDeleteByIds', '/module/base/basedicttype/baseDeleteByIds/{ids}', '/module/base/basedicttype/baseDeleteByIds/*', 'module:base:basedicttype:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518280, '2022-09-27 14:51:02', NULL, '2022-10-05 13:55:43', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色下拉列表', 'optionselect', '/module/base/sysrole/optionselect', '/module/base/sysrole/optionselect', 'module:base:sysrole:optionselect', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518281, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'selectPermissionTreeModel', '/module/base/sysmenu/selectPermissionTreeModel', '/module/base/sysmenu/selectPermissionTreeModel', 'module:base:sysmenu:selectPermissionTreeModel', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438518282, '2022-09-27 14:51:02', NULL, '2022-10-06 00:43:34', 'admin', 0, 3, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseQueryByParam', '/module/base/monitor/online/baseQueryByParam', '/module/base/monitor/online/baseQueryByParam', 'module:base:monitor:online:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438780421, '2022-09-27 14:51:02', NULL, '2022-10-17 16:25:15', 'admin', 0, 7, 116986099043139589, 'loginPcController', '获取登陆用户信息', 'info', '/auth/system/info', '/auth/system/info', 'auth:system:info', 'GET', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499438780422, '2022-09-27 14:51:02', NULL, '2022-10-05 13:51:57', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户编辑', 'baseEdit', '/module/base/sysuser/baseEdit', '/module/base/sysuser/baseEdit', 'module:base:sysuser:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438780423, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseDeleteByIds', '/module/base/sysmenu/baseDeleteByIds/{ids}', '/module/base/sysmenu/baseDeleteByIds/*', 'module:base:sysmenu:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438780424, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseDeleteByIds', '/module/quartz/sysjoblog/baseDeleteByIds/{ids}', '/module/quartz/sysjoblog/baseDeleteByIds/*', 'module:quartz:sysjoblog:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438780425, '2022-09-27 14:51:02', NULL, '2022-10-03 14:48:22', 'admin', 0, 19, 116986328175607813, 'sysPostController', '岗位信息查询单个信息', 'baseQueryById', '/module/base/syspost/baseQueryById/{id}', '/module/base/syspost/baseQueryById/*', 'module:base:syspost:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499438780426, '2022-09-27 14:51:02', NULL, '2022-10-05 13:56:17', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色批量取消授权用户', 'cancelAuthUserAll', '/module/base/sysrole/authUser/cancelAll', '/module/base/sysrole/authUser/cancelAll', 'module:base:sysrole:authUser:cancelAll', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042565, '2022-09-27 14:51:02', NULL, '2022-10-12 18:36:13', 'admin', 0, 4, 116986396732817413, 'sysDeptController', '部门管理', 'baseQueryById', '/module/base/sysdept/baseQueryById/{id}', '/module/base/sysdept/baseQueryById/*', 'module:base:sysdept:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042566, '2022-09-27 14:51:02', NULL, '2022-10-05 13:52:05', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户详情', 'baseQueryById', '/module/base/sysuser/baseQueryById/{id}', '/module/base/sysuser/baseQueryById/*', 'module:base:sysuser:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042567, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986118718881797, 'commonController', '公共common 控制器', 'fileDownload', '/common/file/download', '/common/file/download', 'common:file:download', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042568, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseAdd', '/module/base/monitor/online/baseAdd', '/module/base/monitor/online/baseAdd', 'module:base:monitor:online:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042569, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'baseDeleteByIds', '/module/base/sysdept/baseDeleteByIds/{ids}', '/module/base/sysdept/baseDeleteByIds/*', 'module:base:sysdept:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439042570, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986177233879045, 'sysProfileController', '个人业务', 'profile', '/module/base/profile', '/module/base/profile', 'module:base:profile', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304709, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseEdit', '/module/base/basematerialtype/baseEdit', '/module/base/basematerialtype/baseEdit', 'module:base:basematerialtype:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304710, '2022-09-27 14:51:02', NULL, '2022-10-06 00:59:54', 'admin', 0, 9, 116986191970041861, 'sysNoticeController', '通知公告', 'baseQueryByParam', '/module/base/sysnotice/baseQueryByParam', '/module/base/sysnotice/baseQueryByParam', 'module:base:sysnotice:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304711, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseDeleteByIds', '/module/base/syslogininfor/baseDeleteByIds/{ids}', '/module/base/syslogininfor/baseDeleteByIds/*', 'module:base:syslogininfor:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304712, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseAdd', '/module/base/syspermission/baseAdd', '/module/base/syspermission/baseAdd', 'module:base:syspermission:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304713, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'flushCache', '/module/base/basedicttype/flushCache', '/module/base/basedicttype/flushCache', 'module:base:basedicttype:flushCache', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439304714, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseAdd', '/module/base/basedictdata/baseAdd', '/module/base/basedictdata/baseAdd', 'module:base:basedictdata:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439566853, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'selectPermissionTree', '/module/base/sysmenu/selectPermissionTree', '/module/base/sysmenu/selectPermissionTree', 'module:base:sysmenu:selectPermissionTree', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439566854, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseQueryPageByParam', '/module/base/basematerialtype/baseQueryPageByParam', '/module/base/basematerialtype/baseQueryPageByParam', 'module:base:basematerialtype:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439566855, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseQueryById', '/module/quartz/sysjob/baseQueryById/{id}', '/module/quartz/sysjob/baseQueryById/*', 'module:quartz:sysjob:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439566856, '2022-09-27 14:51:02', NULL, '2022-10-10 18:42:31', 'admin', 0, 2, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseEdit', '/module/base/baseresourceconfig/baseEdit', '/module/base/baseresourceconfig/baseEdit', 'module:base:baseresourceconfig:baseEdit', 'PUT', 1, 4);
INSERT INTO `sys_permission` VALUES (116944499439566857, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseQueryPageByParam', '/module/quartz/sysjoblog/baseQueryPageByParam', '/module/quartz/sysjoblog/baseQueryPageByParam', 'module:quartz:sysjoblog:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439566858, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseQueryById', '/module/base/syspermission/baseQueryById/{id}', '/module/base/syspermission/baseQueryById/*', 'module:base:syspermission:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439828997, '2022-09-27 14:51:02', NULL, '2022-10-05 13:56:34', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '查询未分配用户角色列表', 'unallocatedList', '/module/base/sysrole/authUser/unallocatedList', '/module/base/sysrole/authUser/unallocatedList', 'module:base:sysrole:authUser:unallocatedList', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439828998, '2022-09-27 14:51:02', NULL, '2022-10-10 15:22:39', 'admin', 0, 13, 116986191970041861, 'sysNoticeController', '通知公告', 'baseQueryPageByParam', '/module/base/sysnotice/baseQueryPageByParam', '/module/base/sysnotice/baseQueryPageByParam', 'module:base:sysnotice:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439828999, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'apiResourceController', '', 'securityConfiguration', '/swagger-resources/configuration/security', '/swagger-resources/configuration/security', 'swagger-resources:configuration:security', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439829000, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseEdit', '/module/quartz/sysjoblog/baseEdit', '/module/quartz/sysjoblog/baseEdit', 'module:quartz:sysjoblog:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439829001, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986191970041861, 'sysNoticeController', '通知公告', 'baseEdit', '/module/base/sysnotice/baseEdit', '/module/base/sysnotice/baseEdit', 'module:base:sysnotice:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499439829002, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseQueryById', '/module/base/basedictdata/baseQueryById/{id}', '/module/base/basedictdata/baseQueryById/*', 'module:base:basedictdata:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091141, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986177233879045, 'sysProfileController', '个人业务', 'update', '/module/base/profile/update', '/module/base/profile/update', 'module:base:profile:update', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091142, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseQueryPageByParam', '/module/quartz/sysjob/baseQueryPageByParam', '/module/quartz/sysjob/baseQueryPageByParam', 'module:quartz:sysjob:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091143, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986118718881797, 'commonController', '公共common 控制器', 'uploadFile', '/common/file/upload', '/common/file/upload', 'common:file:upload', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091144, '2022-09-27 14:51:02', NULL, '2022-10-05 13:56:43', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色编辑', 'editRole', '/module/base/sysrole/editRole', '/module/base/sysrole/editRole', 'module:base:sysrole:editRole', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091145, '2022-09-27 14:51:02', NULL, '2022-10-05 13:56:49', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色新增', 'baseAdd', '/module/base/sysrole/baseAdd', '/module/base/sysrole/baseAdd', 'module:base:sysrole:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440091146, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseQueryByParam', '/module/base/syspermission/baseQueryByParam', '/module/base/syspermission/baseQueryByParam', 'module:base:syspermission:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353285, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'baseEdit', '/module/base/sysdept/baseEdit', '/module/base/sysdept/baseEdit', 'module:base:sysdept:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353286, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseQueryById', '/module/base/basematerialtype/baseQueryById/{id}', '/module/base/basematerialtype/baseQueryById/*', 'module:base:basematerialtype:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353287, '2022-09-27 14:51:02', NULL, '2022-10-05 13:52:12', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户新增', 'baseAdd', '/module/base/sysuser/baseAdd', '/module/base/sysuser/baseAdd', 'module:base:sysuser:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353288, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseQueryByParam', '/module/base/syslogininfor/baseQueryByParam', '/module/base/syslogininfor/baseQueryByParam', 'module:base:syslogininfor:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353289, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'baseQueryPageByParam', '/module/base/sysdept/baseQueryPageByParam', '/module/base/sysdept/baseQueryPageByParam', 'module:base:sysdept:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440353290, '2022-09-27 14:51:02', NULL, '2022-10-03 14:10:55', 'admin', 0, 2, 116986328175607813, 'sysPostController', '岗位新增', 'baseAdd', '/module/base/syspost/baseAdd', '/module/base/syspost/baseAdd', 'module:base:syspost:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440615429, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'optionSelect', '/module/base/basedicttype/optionSelect', '/module/base/basedicttype/optionSelect', 'module:base:basedicttype:optionSelect', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440615430, '2022-09-27 14:51:02', NULL, '2022-10-08 15:46:22', 'admin', 0, 4, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseQueryPageByParam', '/module/base/basematerialinfo/baseQueryPageByParam', '/module/base/basematerialinfo/baseQueryPageByParam', 'module:base:basematerialinfo:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440615431, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseDeleteByIds', '/module/base/monitor/online/baseDeleteByIds/{ids}', '/module/base/monitor/online/baseDeleteByIds/*', 'module:base:monitor:online:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440615432, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'apiResourceController', '', 'swaggerResources', '/swagger-resources', '/swagger-resources', 'swagger-resources', 'GET', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499440615433, '2022-09-27 14:51:02', NULL, '2022-10-05 13:57:05', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色查询 分页', 'baseQueryPageByParam', '/module/base/sysrole/baseQueryPageByParam', '/module/base/sysrole/baseQueryPageByParam', 'module:base:sysrole:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440615434, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'baseQueryByParam', '/module/base/sysdept/baseQueryByParam', '/module/base/sysdept/baseQueryByParam', 'module:base:sysdept:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877573, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986191970041861, 'sysNoticeController', '通知公告', 'baseAdd', '/module/base/sysnotice/baseAdd', '/module/base/sysnotice/baseAdd', 'module:base:sysnotice:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877574, '2022-09-27 14:51:02', NULL, '2022-10-05 13:57:11', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色删除', 'baseDeleteByIds', '/module/base/sysrole/baseDeleteByIds/{ids}', '/module/base/sysrole/baseDeleteByIds/*', 'module:base:sysrole:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877575, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseEdit', '/module/quartz/sysjob/baseEdit', '/module/quartz/sysjob/baseEdit', 'module:quartz:sysjob:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877576, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseDeleteByIds', '/module/base/basedictdata/baseDeleteByIds/{ids}', '/module/base/basedictdata/baseDeleteByIds/*', 'module:base:basedictdata:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877577, '2022-09-27 14:51:02', NULL, '2022-10-05 13:52:57', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户查询', 'baseQueryByParam', '/module/base/sysuser/baseQueryByParam', '/module/base/sysuser/baseQueryByParam', 'module:base:sysuser:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499440877578, '2022-09-27 14:51:02', NULL, '2022-10-05 13:57:17', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色详情', 'baseQueryById', '/module/base/sysrole/baseQueryById/{id}', '/module/base/sysrole/baseQueryById/*', 'module:base:sysrole:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441139717, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'basicErrorController', '', 'error', '/error', '/error', 'error', '', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499441139718, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseQueryPageByParam', '/module/base/basedictdata/baseQueryPageByParam', '/module/base/basedictdata/baseQueryPageByParam', 'module:base:basedictdata:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441139719, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'baseAdd', '/module/base/sysdept/baseAdd', '/module/base/sysdept/baseAdd', 'module:base:sysdept:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441139720, '2022-09-27 14:51:02', NULL, '2022-10-05 13:57:40', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '取消授权用户', 'cancelAuthUser', '/module/base/sysrole/authUser/cancel', '/module/base/sysrole/authUser/cancel', 'module:base:sysrole:authUser:cancel', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441139721, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'swagger2ControllerWebMvc', '', 'getDocumentation', '/v2/api-docs', '/v2/api-docs', 'v2:api-docs', 'GET', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499441139722, '2022-09-27 14:51:02', NULL, '2022-10-10 16:51:29', 'admin', 0, 34, 116986390162702341, 'generateController', '代码生成 - 控制器', 'code', '/module/generate/generateStart', '/module/generate/generateStart', 'module:generate:generateStart', 'POST', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499441401861, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseQueryPageByParam', '/module/base/sysmenu/baseQueryPageByParam', '/module/base/sysmenu/baseQueryPageByParam', 'module:base:sysmenu:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441401862, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseEdit', '/module/base/syspermission/baseEdit', '/module/base/syspermission/baseEdit', 'module:base:syspermission:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441401863, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseQueryByParam', '/module/base/basedicttype/baseQueryByParam', '/module/base/basedicttype/baseQueryByParam', 'module:base:basedicttype:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441401864, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseQueryById', '/module/base/sysoperlog/baseQueryById/{id}', '/module/base/sysoperlog/baseQueryById/*', 'module:base:sysoperlog:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441401865, '2022-09-27 14:51:02', NULL, '2022-10-10 18:42:29', 'admin', 0, 2, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseQueryPageByParam', '/module/base/baseresourceconfig/baseQueryPageByParam', '/module/base/baseresourceconfig/baseQueryPageByParam', 'module:base:baseresourceconfig:baseQueryPageByParam', 'POST', 1, 4);
INSERT INTO `sys_permission` VALUES (116944499441401866, '2022-09-27 14:51:02', NULL, '2022-10-10 18:42:28', 'admin', 0, 2, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseAdd', '/module/base/baseresourceconfig/baseAdd', '/module/base/baseresourceconfig/baseAdd', 'module:base:baseresourceconfig:baseAdd', 'POST', 1, 4);
INSERT INTO `sys_permission` VALUES (116944499441664005, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseEdit', '/module/base/syslogininfor/baseEdit', '/module/base/syslogininfor/baseEdit', 'module:base:syslogininfor:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441664006, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseQueryById', '/module/base/basematerialinfo/baseQueryById/{id}', '/module/base/basematerialinfo/baseQueryById/*', 'module:base:basematerialinfo:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441664007, '2022-09-27 14:51:02', NULL, '2022-10-03 14:49:17', 'admin', 0, 3, 116986099043139589, 'loginPcController', '注册', 'register', '/auth/system/register', '/auth/system/register', 'auth:system:register', 'POST', 1, 2);
INSERT INTO `sys_permission` VALUES (116944499441664008, '2022-09-27 14:51:02', NULL, '2022-10-03 14:48:26', 'admin', 0, 4, 116986328175607813, 'sysPostController', '岗位删除根据id', 'baseDeleteByIds', '/module/base/syspost/baseDeleteByIds/{ids}', '/module/base/syspost/baseDeleteByIds/*', 'module:base:syspost:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441664009, '2022-09-27 14:51:02', NULL, '2022-10-05 13:57:55', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '查询未分配用户角色列表', 'allocatedList', '/module/base/sysrole/authUser/allocatedList', '/module/base/sysrole/authUser/allocatedList', 'module:base:sysrole:authUser:allocatedList', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441664010, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseDeleteByIds', '/module/quartz/sysjob/baseDeleteByIds/{ids}', '/module/quartz/sysjob/baseDeleteByIds/*', 'module:quartz:sysjob:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441926149, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'roleMenuTreeselect', '/module/base/sysmenu/roleMenuTreeselect/{roleId}', '/module/base/sysmenu/roleMenuTreeselect/*', 'module:base:sysmenu:roleMenuTreeselect:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441926150, '2022-09-27 14:51:02', NULL, '2022-10-03 14:49:27', 'admin', 0, 3, 116986099043139589, 'loginPcController', '后台管理登陆', 'login', '/auth/system/login', '/auth/system/login', 'auth:system:login', 'POST', 1, 2);
INSERT INTO `sys_permission` VALUES (116944499441926151, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseAdd', '/module/base/sysmenu/baseAdd', '/module/base/sysmenu/baseAdd', 'module:base:sysmenu:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441926152, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseQueryPageByParam', '/module/base/syslogininfor/baseQueryPageByParam', '/module/base/syslogininfor/baseQueryPageByParam', 'module:base:syslogininfor:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441926153, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseQueryPageByParam', '/module/base/syspermission/baseQueryPageByParam', '/module/base/syspermission/baseQueryPageByParam', 'module:base:syspermission:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499441926154, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'getListByDictType', '/module/base/basedictdata/getListByDictType/{dictType}', '/module/base/basedictdata/getListByDictType/*', 'module:base:basedictdata:getListByDictType:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188293, '2022-09-27 14:51:02', NULL, '2022-10-15 19:10:14', 'admin', 0, 4, 116986378177478661, 'sysRoleController', '角色批量选择用户授权', 'selectAuthUserAll', '/module/base/sysrole/authUser/selectAll', '/module/base/sysrole/authUser/selectAll', 'module:base:sysrole:authUser:selectAll', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188294, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986144712818693, 'sysPermissionController', '权限管理', 'baseDeleteByIds', '/module/base/syspermission/baseDeleteByIds/{ids}', '/module/base/syspermission/baseDeleteByIds/*', 'module:base:syspermission:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188295, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseQueryByParam', '/module/base/basematerialinfo/baseQueryByParam', '/module/base/basematerialinfo/baseQueryByParam', 'module:base:basematerialinfo:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188296, '2022-09-27 14:51:02', NULL, '2022-10-03 17:17:03', 'admin', 0, 8, 116986328175607813, 'sysPostController', '岗位列表查询（分页）', 'baseQueryPageByParam', '/module/base/syspost/baseQueryPageByParam', '/module/base/syspost/baseQueryPageByParam', 'module:base:syspost:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188297, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseEdit', '/module/base/basedictdata/baseEdit', '/module/base/basedictdata/baseEdit', 'module:base:basedictdata:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442188298, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseDeleteByIds', '/module/base/basematerialinfo/baseDeleteByIds/{ids}', '/module/base/basematerialinfo/baseDeleteByIds/*', 'module:base:basematerialinfo:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442450437, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseAdd', '/module/base/basematerialtype/baseAdd', '/module/base/basematerialtype/baseAdd', 'module:base:basematerialtype:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442450438, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986390162702341, 'generateController', '代码生成 - 控制器', 'queryList', '/module/generate/queryList', '/module/generate/queryList', 'module:generate:queryList', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442450439, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseDeleteByIds', '/module/base/sysoperlog/baseDeleteByIds/{ids}', '/module/base/sysoperlog/baseDeleteByIds/*', 'module:base:sysoperlog:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442450440, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'basicErrorController', '', 'errorHtml', '/error', '/error', 'error', '', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499442450441, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986113957560325, 'apiResourceController', '', 'uiConfiguration', '/swagger-resources/configuration/ui', '/swagger-resources/configuration/ui', 'swagger-resources:configuration:ui', 'GET', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499442450442, '2022-09-27 14:51:02', NULL, '2022-10-10 18:42:27', 'admin', 0, 2, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseQueryById', '/module/base/baseresourceconfig/baseQueryById/{id}', '/module/base/baseresourceconfig/baseQueryById/*', 'module:base:baseresourceconfig:baseQueryById:*', 'GET', 1, 4);
INSERT INTO `sys_permission` VALUES (116944499442712581, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseQueryByParam', '/module/base/sysoperlog/baseQueryByParam', '/module/base/sysoperlog/baseQueryByParam', 'module:base:sysoperlog:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442712582, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986191970041861, 'sysNoticeController', '通知公告', 'baseDeleteByIds', '/module/base/sysnotice/baseDeleteByIds/{ids}', '/module/base/sysnotice/baseDeleteByIds/*', 'module:base:sysnotice:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442712583, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986341494095877, 'sysMenuController', '菜单权限', 'baseEdit', '/module/base/sysmenu/baseEdit', '/module/base/sysmenu/baseEdit', 'module:base:sysmenu:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442712584, '2022-09-27 14:51:02', NULL, '2022-10-05 13:53:12', 'admin', 0, 2, 116986403964583941, 'sysUserController', '根据用户编号获取授权角色', 'authRole', '/module/base/sysuser/authRole/{id}', '/module/base/sysuser/authRole/*', 'module:base:sysuser:authRole:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442712585, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986370697461765, 'resourceConfigController', '系统参数配置', 'baseQueryByParam', '/module/base/baseresourceconfig/baseQueryByParam', '/module/base/baseresourceconfig/baseQueryByParam', 'module:base:baseresourceconfig:baseQueryByParam', 'POST', 1, 3);
INSERT INTO `sys_permission` VALUES (116944499442712586, '2022-09-27 14:51:02', NULL, '2022-10-05 13:53:22', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户查询 分页', 'baseQueryPageByParam', '/module/base/sysuser/baseQueryPageByParam', '/module/base/sysuser/baseQueryPageByParam', 'module:base:sysuser:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974725, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseQueryById', '/module/quartz/sysjoblog/baseQueryById/{id}', '/module/quartz/sysjoblog/baseQueryById/*', 'module:quartz:sysjoblog:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974726, '2022-09-27 14:51:02', NULL, '2022-10-03 17:06:26', 'admin', 0, 2, 116986144712818693, 'sysPermissionController', '路由同步', 'mappingSync', '/module/base/syspermission/mappingSync', '/module/base/syspermission/mappingSync', 'module:base:syspermission:mappingSync', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974727, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseEdit', '/module/base/basematerialinfo/baseEdit', '/module/base/basematerialinfo/baseEdit', 'module:base:basematerialinfo:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974728, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseQueryByParam', '/module/base/basematerialtype/baseQueryByParam', '/module/base/basematerialtype/baseQueryByParam', 'module:base:basematerialtype:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974729, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseAdd', '/module/base/basedicttype/baseAdd', '/module/base/basedicttype/baseAdd', 'module:base:basedicttype:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499442974730, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'clean', '/module/base/syslogininfor/clean', '/module/base/syslogininfor/clean', 'module:base:syslogininfor:clean', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236869, '2022-09-27 14:51:02', NULL, '2022-10-05 13:53:33', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户授权角色', 'insertAuthRole', '/module/base/sysuser/insertAuthRole', '/module/base/sysuser/insertAuthRole', 'module:base:sysuser:insertAuthRole', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236870, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'cleanOperlog', '/module/base/sysoperlog/clean', '/module/base/sysoperlog/clean', 'module:base:sysoperlog:clean', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236871, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986177233879045, 'sysProfileController', '个人业务', 'updateUser', '/module/base/profile/updateUser', '/module/base/profile/updateUser', 'module:base:profile:updateUser', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236872, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseEdit', '/module/base/monitor/online/baseEdit', '/module/base/monitor/online/baseEdit', 'module:base:monitor:online:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236873, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'baseDeleteByIds', '/module/base/basematerialtype/baseDeleteByIds/{ids}', '/module/base/basematerialtype/baseDeleteByIds/*', 'module:base:basematerialtype:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443236874, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'clean', '/module/quartz/sysjoblog/clean', '/module/quartz/sysjoblog/clean', 'module:quartz:sysjoblog:clean', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499013, '2022-09-27 14:51:02', NULL, '2022-10-05 13:53:41', 'admin', 0, 2, 116986403964583941, 'sysUserController', '用户删除', 'baseDeleteByIds', '/module/base/sysuser/baseDeleteByIds/{ids}', '/module/base/sysuser/baseDeleteByIds/*', 'module:base:sysuser:baseDeleteByIds:*', 'DELETE', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499014, '2022-09-27 14:51:02', NULL, '2022-10-03 14:48:27', 'admin', 0, 9, 116986328175607813, 'sysPostController', '岗位列表查询', 'baseQueryByParam', '/module/base/syspost/baseQueryByParam', '/module/base/syspost/baseQueryByParam', 'module:base:syspost:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499015, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseEdit', '/module/base/basedicttype/baseEdit', '/module/base/basedicttype/baseEdit', 'module:base:basedicttype:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499016, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 3, 117080384228294661, 'baseMaterialTypeController', '素材分类', 'excludeChild', '/module/base/basematerialtype/list/exclude/{id}', '/module/base/basematerialtype/list/exclude/*', 'module:base:basematerialtype:list:exclude:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499017, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'optionSelect', '/module/base/basedictdata/optionSelect/{dictType}', '/module/base/basedictdata/optionSelect/*', 'module:base:basedictdata:optionSelect:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443499018, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117080524952698885, 'baseMaterialInfoController', '素材管理', 'baseAdd', '/module/base/basematerialinfo/baseAdd', '/module/base/basematerialinfo/baseAdd', 'module:base:basematerialinfo:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761157, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'changeStatus', '/module/quartz/sysjob/changeStatus', '/module/quartz/sysjob/changeStatus', 'module:quartz:sysjob:changeStatus', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761158, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'run', '/module/quartz/sysjob/run', '/module/quartz/sysjob/run', 'module:quartz:sysjob:run', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761159, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseQueryPageByParam', '/module/base/monitor/online/baseQueryPageByParam', '/module/base/monitor/online/baseQueryPageByParam', 'module:base:monitor:online:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761160, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseAdd', '/module/base/sysoperlog/baseAdd', '/module/base/sysoperlog/baseAdd', 'module:base:sysoperlog:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761161, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986118718881797, 'commonController', '公共common 控制器', 'uploadFiles', '/common/file/uploads', '/common/file/uploads', 'common:file:uploads', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499443761162, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986191970041861, 'sysNoticeController', '通知公告', 'baseQueryById', '/module/base/sysnotice/baseQueryById/{id}', '/module/base/sysnotice/baseQueryById/*', 'module:base:sysnotice:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023301, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116985804997525509, 'sysUserOnlineController', '在线用户监控', 'baseQueryById', '/module/base/monitor/online/baseQueryById/{id}', '/module/base/monitor/online/baseQueryById/*', 'module:base:monitor:online:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023302, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'roleDeptTreeselect', '/module/base/sysdept/roleDeptTreeselect/{roleId}', '/module/base/sysdept/roleDeptTreeselect/*', 'module:base:sysdept:roleDeptTreeselect:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023303, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseQueryById', '/module/base/basedicttype/baseQueryById/{id}', '/module/base/basedicttype/baseQueryById/*', 'module:base:basedicttype:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023304, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309884968206341, 'baseDictTypeController', '字典类型', 'baseQueryPageByParam', '/module/base/basedicttype/baseQueryPageByParam', '/module/base/basedicttype/baseQueryPageByParam', 'module:base:basedicttype:baseQueryPageByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023305, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082562753986565, 'sysLogininforController', '系统访问记录- 控制器', 'baseQueryById', '/module/base/syslogininfor/baseQueryById/{id}', '/module/base/syslogininfor/baseQueryById/*', 'module:base:syslogininfor:baseQueryById:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444023306, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986396732817413, 'sysDeptController', '部门管理', 'treeselect', '/module/base/sysdept/treeselect', '/module/base/sysdept/treeselect', 'module:base:sysdept:treeselect', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444285445, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117082554640105477, 'sysOperLogController', '操作日志记录- 控制器', 'baseEdit', '/module/base/sysoperlog/baseEdit', '/module/base/sysoperlog/baseEdit', 'module:base:sysoperlog:baseEdit', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444285446, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobController', '定时任务调度表', 'baseAdd', '/module/quartz/sysjob/baseAdd', '/module/quartz/sysjob/baseAdd', 'module:quartz:sysjob:baseAdd', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444285447, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 2, 117309893460623365, 'baseDictDataController', '字典数据', 'baseQueryByParam', '/module/base/basedictdata/baseQueryByParam', '/module/base/basedictdata/baseQueryByParam', 'module:base:basedictdata:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444285448, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986122956963845, 'sysJobLogController', '定时任务调度日志表', 'baseQueryByParam', '/module/quartz/sysjoblog/baseQueryByParam', '/module/quartz/sysjoblog/baseQueryByParam', 'module:quartz:sysjoblog:baseQueryByParam', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (116944499444285449, '2022-09-27 14:51:02', NULL, '2022-09-27 14:51:02', 'admin', 0, 1, 116986370697461765, 'resourceConfigController', '系统参数配置', 'queryByConfigKey', '/module/base/baseresourceconfig/queryByConfigKey/{configKey}', '/module/base/baseresourceconfig/queryByConfigKey/*', 'module:base:baseresourceconfig:queryByConfigKey:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (116985804997525509, '2022-09-29 10:37:10', 'admin', '2022-10-03 17:07:40', 'admin', 0, 2, 117124725460434949, '在线用户监控模块', '在线用户监控模块', '在线用户监控模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986099043139589, '2022-09-29 10:55:52', 'admin', '2022-09-29 10:55:52', 'admin', 0, 0, 0, '登陆模块', '登陆模块', '登陆模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986113957560325, '2022-09-29 10:56:49', 'admin', '2022-09-29 10:56:49', 'admin', 0, 0, 0, '其他模块', '其他模块', '其他模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986118718881797, '2022-09-29 10:57:07', 'admin', '2022-09-29 10:57:07', 'admin', 0, 0, 0, '公共模块', '公共模块', '公共模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986122956963845, '2022-09-29 10:57:23', 'admin', '2022-09-29 10:57:23', 'admin', 0, 1, 117124725460434949, '定时任务模块', '定时任务模块', '定时任务模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986144712818693, '2022-09-29 10:58:46', 'admin', '2022-09-29 10:58:46', 'admin', 0, 1, 116986422871982085, '权限管理模块', '权限管理模块', '权限管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986177233879045, '2022-09-29 11:00:50', 'admin', '2022-09-29 11:00:50', 'admin', 0, 1, 117124717295173637, '个人业务模块', '个人业务模块', '个人业务模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986191970041861, '2022-09-29 11:01:46', 'admin', '2022-09-29 11:01:46', 'admin', 0, 0, 0, '通知公告模块', '通知公告模块', '通知公告模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986202220658693, '2022-09-29 11:02:25', 'admin', '2022-10-03 14:58:58', 'admin', 0, 6, 0, '素材管理模型', '素材管理模型', '素材管理模型', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986328175607813, '2022-09-29 11:10:26', 'admin', '2022-09-29 11:10:26', 'admin', 0, 0, 0, '岗位管理模块', '岗位管理模块', '岗位管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986341494095877, '2022-09-29 11:11:17', 'admin', '2022-09-29 11:11:17', 'admin', 0, 1, 116986422871982085, '菜单管理模块', '菜单管理模块', '菜单管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986354265751557, '2022-09-29 11:12:05', 'admin', '2022-09-29 11:12:05', 'admin', 0, 1, 117124725460434949, '字典管理模块', '字典管理模块', '字典管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986370697461765, '2022-09-29 11:13:08', 'admin', '2022-09-29 11:13:08', 'admin', 0, 1, 117124725460434949, '系统参数配置模块', '系统参数配置模块', '系统参数配置模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986378177478661, '2022-09-29 11:13:36', 'admin', '2022-09-29 11:13:36', 'admin', 0, 1, 116986422871982085, '角色管理模块', '角色管理模块', '角色管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986390162702341, '2022-09-29 11:14:22', 'admin', '2022-09-29 11:14:22', 'admin', 0, 1, 117124725460434949, '代码生成模块', '代码生成模块', '代码生成模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986396732817413, '2022-09-29 11:14:47', 'admin', '2022-09-29 11:14:47', 'admin', 0, 0, 0, '部门管理模块', '部门管理模块', '部门管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986403964583941, '2022-09-29 11:15:15', 'admin', '2022-09-29 11:15:15', 'admin', 0, 1, 117124717295173637, '用户管理模块', '用户管理模块', '用户管理模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (116986422871982085, '2022-09-29 11:16:27', 'admin', '2022-09-29 11:16:27', 'admin', 0, 0, 0, '权限管理模型', '权限管理模型', '权限管理模型', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117080384228294661, '2022-10-03 14:50:21', 'admin', '2022-10-03 14:54:51', 'admin', 0, 4, 116986202220658693, '素材分类模块', '素材分类模块', '素材分类模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117080524952698885, '2022-10-03 14:59:18', 'admin', '2022-10-03 14:59:18', 'admin', 0, 1, 116986202220658693, '素材信息模块', '素材信息模块', '素材信息模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117082462076796933, '2022-10-03 17:02:27', NULL, '2022-10-03 17:06:44', 'admin', 0, 5, 116986144712818693, 'sysPermissionController', '改变访问规则', 'changeRouteVisitRule', '/module/base/syspermission/changeRouteVisitRule', '/module/base/syspermission/changeRouteVisitRule', 'module:base:syspermission:changeRouteVisitRule', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (117082462076796934, '2022-10-03 17:02:27', NULL, '2022-10-03 17:07:13', 'admin', 0, 3, 116986144712818693, 'sysPermissionController', '分配上级目录', 'allocationRouteModule', '/module/base/syspermission/allocationRouteModule', '/module/base/syspermission/allocationRouteModule', 'module:base:syspermission:allocationRouteModule', 'POST', 1, 1);
INSERT INTO `sys_permission` VALUES (117082547799457797, '2022-10-03 17:07:54', 'admin', '2022-10-03 17:07:54', 'admin', 0, 0, 0, '日志模型', '日志模型', '日志模型', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117082554640105477, '2022-10-03 17:08:21', 'admin', '2022-10-03 17:08:21', 'admin', 0, 1, 117082547799457797, '操作日志', '操作日志', '操作日志', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117082562753986565, '2022-10-03 17:08:51', 'admin', '2022-10-03 17:08:51', 'admin', 0, 1, 117082547799457797, '访问日志', '访问日志', '访问日志', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117124679163445253, '2022-10-05 13:46:33', NULL, '2022-10-05 13:47:28', 'admin', 0, 2, 116986378177478661, 'sysPermissionController', '获取角色拥有的路由id', 'rolePermissionByRoleId', '/module/base/syspermission/rolePermissionByRoleId/{roleId}', '/module/base/syspermission/rolePermissionByRoleId/*', 'module:base:syspermission:rolePermissionByRoleId:*', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (117124679163445254, '2022-10-05 13:46:33', NULL, '2022-10-05 13:48:04', 'admin', 0, 2, 116986378177478661, 'sysRoleController', '角色分配路由权限', 'roleAllocationRoute', '/module/base/sysrole/roleAllocationRoute', '/module/base/sysrole/roleAllocationRoute', 'module:base:sysrole:roleAllocationRoute', 'PUT', 1, 1);
INSERT INTO `sys_permission` VALUES (117124679163445255, '2022-10-05 13:46:33', NULL, '2022-10-15 19:10:59', 'admin', 0, 16, 116986378177478661, 'sysPermissionController', '获取路由树列表（根据角色）', 'rolePermissionTreeList', '/module/base/syspermission/rolePermissionTreeList', '/module/base/syspermission/rolePermissionTreeList', 'module:base:syspermission:rolePermissionTreeList', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (117124717295173637, '2022-10-05 13:48:58', 'admin', '2022-10-05 13:48:58', 'admin', 0, 0, 0, '用户模型', '用户模型', '用户模型', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117124725460434949, '2022-10-05 13:49:29', 'admin', '2022-10-05 13:49:29', 'admin', 0, 0, 0, '系统配置模型', '系统配置模型', '系统配置模型', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117133365360984069, '2022-10-05 22:58:48', NULL, '2022-10-05 22:59:42', 'admin', 0, 4, 116986113957560325, 'errorController', '异常信息打印', 'printError', '/error/print', '/error/print', 'error:print', 'GET', 1, 3);
INSERT INTO `sys_permission` VALUES (117133443824615429, '2022-10-05 23:03:47', NULL, '2022-10-06 00:31:44', 'test', 0, 4, 116986144712818693, 'sysPermissionController', '刷新路由动态权限', 'flushPermissionConfig', '/module/base/syspermission/flushPermissionConfig', '/module/base/syspermission/flushPermissionConfig', 'module:base:syspermission:flushPermissionConfig', 'GET', 1, 1);
INSERT INTO `sys_permission` VALUES (117309884968206341, '2022-10-13 18:01:37', 'admin', '2022-10-13 18:01:37', 'admin', 0, 1, 116986354265751557, '字典类型模块', '字典类型模块', '字典类型模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117309893460623365, '2022-10-13 18:02:09', 'admin', '2022-10-13 18:02:09', 'admin', 0, 1, 116986354265751557, '字典数据模块', '字典数据模块', '字典数据模块', '0', NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_permission` VALUES (117329132609601541, '2022-10-14 14:25:21', NULL, '2022-10-14 15:31:43', 'admin', 0, 4, 116986403964583941, 'sysUserController', '用户信息导出', 'executeExcelExport', '/module/base/sysuser/excelExport', '/module/base/sysuser/excelExport', 'module:base:sysuser:excelExport', 'PUT', 1, 1);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'ALL' COMMENT '数据范围\r\n    ALL,        // 所有权限\r\n    CUSTOM,     // 自定义\r\n    DEPT_MY,    // 本部门\r\n    DEPT,       // 本部门以及以下\r\n    MY;         // 本人',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_key`(`role_key` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2022-10-05 00:49:02', 'admin', '2022-10-06 00:38:15', 'admin', 0, 1, '系统人员', 'admin', 2, 'ALL', 1, 1, '0');
INSERT INTO `sys_role` VALUES (1000000000, '2022-05-29 19:58:03', NULL, '2022-08-15 18:29:19', 'admin', 0, 23, '系统管理员', 'system', 1, 'ALL', 1, 0, '0');
INSERT INTO `sys_role` VALUES (53814717356965893, '2022-05-29 20:00:28', NULL, '2022-10-06 01:14:02', 'admin', 0, 42, '普通用户', 'normal', 1, 'CUSTOM', 1, 0, '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (1, NULL, NULL, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_role_dept` VALUES (2, NULL, NULL, NULL, NULL, 0, 0, 1, 2);
INSERT INTO `sys_role_dept` VALUES (3, NULL, NULL, NULL, NULL, 0, 0, 1, 3);
INSERT INTO `sys_role_dept` VALUES (4, NULL, NULL, NULL, NULL, 0, 0, 1, 4);
INSERT INTO `sys_role_dept` VALUES (116309618796265477, '2022-08-30 14:06:24', NULL, '2022-08-30 14:06:24', NULL, 0, 0, 53814717356965893, 116107518461345797);
INSERT INTO `sys_role_dept` VALUES (116309618796265478, '2022-08-30 14:06:24', 'admin', '2022-08-30 14:06:24', 'admin', 0, 0, 53814717356965893, 116197441122861061);
INSERT INTO `sys_role_dept` VALUES (116309618796265479, '2022-08-30 14:06:24', NULL, '2022-08-30 14:06:24', NULL, 0, 0, 53814717356965893, 116197389196853253);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (117135492388487173, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 107);
INSERT INTO `sys_role_menu` VALUES (117135492388487174, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 103);
INSERT INTO `sys_role_menu` VALUES (117135492388487175, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 102);
INSERT INTO `sys_role_menu` VALUES (117135492388487176, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 105);
INSERT INTO `sys_role_menu` VALUES (117135492388749317, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 116535653708070917);
INSERT INTO `sys_role_menu` VALUES (117135492388749318, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 1);
INSERT INTO `sys_role_menu` VALUES (117135492388749319, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 108);
INSERT INTO `sys_role_menu` VALUES (117135492388749320, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 104);
INSERT INTO `sys_role_menu` VALUES (117135492388749321, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 500);
INSERT INTO `sys_role_menu` VALUES (117135492388749322, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 116535662704852997);
INSERT INTO `sys_role_menu` VALUES (117135492389011461, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 501);
INSERT INTO `sys_role_menu` VALUES (117135492389011462, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 100);
INSERT INTO `sys_role_menu` VALUES (117135492389011463, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 101);
INSERT INTO `sys_role_menu` VALUES (117135492389011464, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 116901224459272197);
INSERT INTO `sys_role_menu` VALUES (117135492389011465, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 116896341546500101);
INSERT INTO `sys_role_menu` VALUES (117135492389011466, '2022-10-06 01:14:02', NULL, '2022-10-06 01:14:02', 'admin', 0, 0, 53814717356965893, 116535626802659333);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (117238955646124037, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117082462076796933, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646124038, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441401861, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646124039, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499442712583, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646124040, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499439304712, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646124041, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441926149, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646124042, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499439566853, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386181, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499437731849, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386182, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116986341494095877, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386183, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117124679163445254, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386184, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499437993990, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386185, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438256134, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646386186, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117124679163445255, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648325, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441139720, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648326, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440615433, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648327, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117124679163445253, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648328, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499437993992, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648329, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438256136, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646648330, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440877578, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910469, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440091144, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910470, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499437731846, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910471, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440877574, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910472, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440091145, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910473, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117133443824615429, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955646910474, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499440091146, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172613, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499439828997, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172614, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441926151, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172615, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438518281, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172616, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441664009, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172617, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499442974726, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647172618, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499442188294, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434757, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 117082462076796934, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434758, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441926153, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434759, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116986144712818693, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434760, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438780423, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434761, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441401862, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647434762, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499442188293, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696901, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438780426, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696902, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116986378177478661, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696903, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438518280, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696904, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499439566858, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696905, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441664007, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647696906, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116986099043139589, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647959045, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116986422871982085, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647959046, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499438780421, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117238955647959047, '2022-10-10 14:52:03', NULL, '2022-10-10 14:52:03', 'admin', 0, 0, 116944499441926150, 53814717356965893);
INSERT INTO `sys_role_permission` VALUES (117399019620663301, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440091146, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620663302, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441926151, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620663303, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441139720, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620663304, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499437731846, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620663305, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442712583, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620663306, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441401862, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925445, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986378177478661, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925446, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440091145, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925447, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117133365360984069, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925448, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442974726, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925449, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441926149, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019620925450, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986422871982085, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187589, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438256134, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187590, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441664009, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187591, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117133443824615429, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187592, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442450441, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187593, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440091144, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621187594, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442450440, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449733, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441926153, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449734, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440615432, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449735, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438256136, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449736, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117124679163445255, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449737, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442188294, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621449738, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117082462076796934, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711877, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440877578, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711878, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440877574, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711879, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438780426, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711880, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441664007, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711881, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499439566858, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621711882, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499440615433, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974021, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438518280, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974022, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499439828997, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974023, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499442188293, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974024, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499437731849, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974025, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986341494095877, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019621974026, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441139721, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236165, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499439304712, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236166, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117082462076796933, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236167, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499437993992, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236168, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499439566853, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236169, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438518281, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622236170, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117124679163445253, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498309, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986113957560325, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498310, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499439828999, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498311, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441401861, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498312, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499437993990, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498313, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441926150, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622498314, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986099043139589, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622760453, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438780421, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622760454, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116986144712818693, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622760455, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499438780423, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622760456, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 117124679163445254, 1000000000);
INSERT INTO `sys_role_permission` VALUES (117399019622760457, '2022-10-17 16:28:39', NULL, '2022-10-17 16:28:39', 'admin', 0, 0, 116944499441139717, 1000000000);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `login_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1000000000, '2022-05-22 19:34:41', NULL, '2022-10-03 15:00:57', 'admin', 0, 36, 1, 'admin', '吴昊', '00', '156156432@qq.com', '15642346516', '0', '/upload/2022/10/03/44338c3aa354488999a0452f928d9ded.jpeg', '$2a$10$.eQAjqPOuo73CoG/4khMH.Re1m9N2GCokaTegDK8TFyzAhEOT6QMe', '0', '', NULL);
INSERT INTO `sys_user` VALUES (117398913374486533, '2022-10-17 16:21:53', NULL, '2022-10-17 16:21:53', NULL, 0, 0, 1, 'test', 'test', '00', '', '', '0', '', '$2a$10$gajKXwvSxZqAi5OSxElOzu1P9f.2muAI1lkZ7AeleRdX1HPGiHv8u', '0', '', NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, NULL, NULL, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, NULL, NULL, NULL, NULL, 0, 0, 2, 1);
INSERT INTO `sys_user_role` VALUES (3, NULL, NULL, NULL, NULL, 0, 0, 3, 1);
INSERT INTO `sys_user_role` VALUES (5, NULL, NULL, NULL, NULL, 0, 0, 4, 1);
INSERT INTO `sys_user_role` VALUES (116448379170455557, '2022-09-05 17:08:33', NULL, '2022-09-05 17:08:33', 'admin', 0, 0, 1000000000, 53814717356965893);
INSERT INTO `sys_user_role` VALUES (117398913388642309, '2022-10-17 16:21:53', NULL, '2022-10-17 16:21:53', NULL, 0, 0, 117398913374486533, 1000000000);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `id` bigint NOT NULL COMMENT '主键',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_oper` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `update_oper` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1 已删除',
  `version` int NULL DEFAULT 0 COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '登录人主键',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token key',
  `last_time` timestamp NOT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录人token信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (117396367000993797, '2022-10-17 13:40:00', NULL, '2022-10-17 13:40:00', NULL, 0, 0, 1000000000, '7decf738-2e0b-42e8-9402-84fbdd43d6f4', '2022-10-17 13:40:00');
INSERT INTO `sys_user_token` VALUES (117398190752530437, '2022-10-17 15:35:57', NULL, '2022-10-17 15:35:57', NULL, 0, 0, 1000000000, 'a4d932f4-c403-4c36-88f3-a0a2fccdf14a', '2022-10-17 15:35:57');
INSERT INTO `sys_user_token` VALUES (117398968208195589, '2022-10-17 16:25:23', NULL, '2022-10-17 16:25:23', NULL, 0, 0, 117398913374486533, '95d43e1b-4e2c-493e-9c94-cb9c90591101', '2022-10-17 16:25:23');
INSERT INTO `sys_user_token` VALUES (117399027073941509, '2022-10-17 16:29:07', NULL, '2022-10-17 16:29:07', NULL, 0, 0, 1000000000, 'ebc4df7f-5f58-49c3-bfb5-fee6774cb40c', '2022-10-17 16:29:07');

SET FOREIGN_KEY_CHECKS = 1;
