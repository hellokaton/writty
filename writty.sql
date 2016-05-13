/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.40 : Database - writty
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_comment` */

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(36) NOT NULL DEFAULT '' COMMENT '文章id',
  `cid` int(11) DEFAULT NULL COMMENT '评论id',
  `uid` int(11) DEFAULT NULL COMMENT '发布评论的用户',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '评论人昵称',
  `site_url` varchar(255) DEFAULT NULL COMMENT '评论人站点',
  `email` varchar(255) DEFAULT NULL COMMENT '评论人email',
  `ip` varchar(255) DEFAULT NULL COMMENT '评论人ip',
  `created` int(11) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

/*Table structure for table `t_favorite` */

CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` varchar(64) NOT NULL,
  `uid` int(10) NOT NULL,
  `created` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_favorite` */

/*Table structure for table `t_link` */

CREATE TABLE `t_link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '链接名称',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接地址',
  `is_new` tinyint(2) NOT NULL COMMENT '是否新窗口打开',
  `display_order` int(10) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_link` */

/*Table structure for table `t_open` */

CREATE TABLE `t_open` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `created` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_open` */

insert  into `t_open`(`id`,`open_id`,`uid`,`created`) values (2,3849072,6,1462977008);

/*Table structure for table `t_options` */

CREATE TABLE `t_options` (
  `okey` varchar(100) NOT NULL DEFAULT '',
  `ovalue` text,
  PRIMARY KEY (`okey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

/*Data for the table `t_options` */

insert  into `t_options`(`okey`,`ovalue`) values ('site_description',NULL),('site_favicon',NULL),('site_keyworlds','Java,王爵,Blade'),('site_subtitle','一个javaer的日常'),('site_title','王爵的技术博客');

/*Table structure for table `t_post` */

CREATE TABLE `t_post` (
  `pid` varchar(36) NOT NULL DEFAULT '' COMMENT '文章uuid',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `slug` varchar(255) DEFAULT NULL COMMENT '自定义文章显示名',
  `uid` int(11) NOT NULL COMMENT '文章发布人',
  `sid` int(11) DEFAULT NULL COMMENT '所属栏目id',
  `cover` varchar(255) DEFAULT NULL COMMENT '文章封面图',
  `content` text COMMENT '文章内容',
  `views` int(11) DEFAULT '0' COMMENT '文章浏览量',
  `comments` int(11) DEFAULT '0' COMMENT '文章评论数',
  `is_pub` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章是否已经发布,0草稿1待审核2已发布',
  `created` int(11) NOT NULL COMMENT '文章发布时间',
  `updated` int(11) NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_post` */

insert  into `t_post`(`pid`,`title`,`slug`,`uid`,`sid`,`cover`,`content`,`views`,`comments`,`is_pub`,`created`,`updated`) values ('992cca8c84db4ef5ae4d3da4b7523cab','关于我','about',1,NULL,NULL,'关于我。。。',0,0,0,0,0);

/*Table structure for table `t_special` */

CREATE TABLE `t_special` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '分类名称',
  `slug` varchar(255) NOT NULL DEFAULT '' COMMENT '分类显示名',
  `cover` varchar(255) DEFAULT NULL COMMENT '分类封面',
  `description` varchar(1000) DEFAULT NULL COMMENT '分类介绍',
  `post_count` int(10) DEFAULT '0' COMMENT '文章数',
  `follow_count` int(10) DEFAULT '0' COMMENT '关注数',
  `created` int(11) NOT NULL COMMENT '分类创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='专栏';

/*Data for the table `t_special` */

insert  into `t_special`(`id`,`title`,`slug`,`cover`,`description`,`post_count`,`follow_count`,`created`) values (1,'IPhone应该这么玩儿','iphone_gmae','special/eTEa/4438.jpg','我来教你一些不为人知的iphone黑科技！',0,0,1463068334),(2,'从零到一学Flask','flask_study','special/eTEa/4438.jpg','我们一起学习Flask开发',0,0,1463068334),(3,'最新的Swift开发教程','swift','special/eTEa/4438.jpg','学习Swift3 开发IOS APP',0,0,1463068334),(4,'如何设计一个MVC框架','mvc','special/eTEa/4438.jpg','一起来设计一个MVC框架',0,0,1463068334),(5,'安卓大神炼成记','android','special/eTEa/4438.jpg','见证安卓大神的修炼之路',0,0,1463068334),(6,'我们都爱玩摄影','sheying','special/eTEa/4438.jpg','分享你的摄影图片',0,0,1463068334),(7,'MacBook快速入门','macbook','special/eTEa/4438.jpg','这里讲述小白如何使用mac book',0,0,1463068334),(8,'我是如何测试性能的','test','special/eTEa/4438.jpg','教你如何在服务器下测试性能',0,0,1463068334);

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '登录名',
  `pass_word` varchar(64) DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `role_id` tinyint(4) DEFAULT '4' COMMENT '1:系统管理员 2:管理员 3:编辑 4:普通会员',
  `created` int(11) NOT NULL COMMENT '创建时间',
  `updated` int(11) NOT NULL COMMENT '最后修改时间',
  `logined` int(11) DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`user_name`,`pass_word`,`nick_name`,`avatar`,`role_id`,`created`,`updated`,`logined`) values (1,'biezhi','916a042f1bde53eba1e49cd59cf4eb75','王爵',NULL,1,1462708055,1462708055,1462708055),(6,'biezhi','','王爵nice','https://avatars.githubusercontent.com/u/3849072?v=3',4,1462977008,1462977008,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
