/*!40101 SET NAMES utf8 */;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `weixin_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `avatar` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `helping_count` int(11) DEFAULT NULL COMMENT '成功助力次数',
  `black_count` int(11) DEFAULT NULL COMMENT '被拉黑次数',
  `credit_rate` decimal(10,2) DEFAULT NULL COMMENT '信用度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `helping_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1：互助 2:积分 3:赏金',
  `end_flg` int(11) DEFAULT NULL COMMENT '是否已结束',
  `owner_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `end_time` int(11) DEFAULT NULL COMMENT '有效期截止时间（默认当天24:00）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `t_helping`;
CREATE TABLE `t_helping` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `confirm_flg` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `idx_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;