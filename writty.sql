# ************************************************************
# Sequel Pro SQL dump
# Version 4529
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.29)
# Database: writty
# Generation Time: 2016-05-17 15:16:55 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_comment`;

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



# Dump of table t_favorite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_favorite`;

CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` varchar(64) NOT NULL,
  `uid` int(10) NOT NULL,
  `created` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_link
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '链接名称',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接地址',
  `is_new` tinyint(2) NOT NULL COMMENT '是否新窗口打开',
  `display_order` int(10) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_open
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_open`;

CREATE TABLE `t_open` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `created` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_open` WRITE;
/*!40000 ALTER TABLE `t_open` DISABLE KEYS */;

INSERT INTO `t_open` (`id`, `open_id`, `uid`, `created`)
VALUES
	(2,3849072,6,1462977008);

/*!40000 ALTER TABLE `t_open` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_options
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_options`;

CREATE TABLE `t_options` (
  `okey` varchar(100) NOT NULL DEFAULT '',
  `ovalue` text,
  PRIMARY KEY (`okey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

LOCK TABLES `t_options` WRITE;
/*!40000 ALTER TABLE `t_options` DISABLE KEYS */;

INSERT INTO `t_options` (`okey`, `ovalue`)
VALUES
	('site_description',NULL),
	('site_favicon',NULL),
	('site_keyworlds','Java,王爵,Blade'),
	('site_subtitle','一个javaer的日常'),
	('site_title','王爵的技术博客');

/*!40000 ALTER TABLE `t_options` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_post`;

CREATE TABLE `t_post` (
  `pid` varchar(36) NOT NULL DEFAULT '' COMMENT '文章uuid',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `slug` varchar(255) DEFAULT NULL COMMENT '自定义文章显示名',
  `uid` int(11) NOT NULL COMMENT '文章发布人',
  `sid` int(11) DEFAULT NULL COMMENT '所属栏目id',
  `type` tinyint(4) NOT NULL COMMENT '1:原创 2:转载 3:翻译',
  `cover` varchar(255) DEFAULT NULL COMMENT '文章封面图',
  `content` text COMMENT '文章内容',
  `comments` int(11) DEFAULT '0' COMMENT '文章评论数',
  `is_pub` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章是否已经发布,0草稿1待审核2已发布',
  `created` int(11) NOT NULL COMMENT '文章发布时间',
  `updated` int(11) NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;

INSERT INTO `t_post` (`pid`, `title`, `slug`, `uid`, `sid`, `type`, `cover`, `content`, `comments`, `is_pub`, `created`, `updated`)
VALUES
	('6851cf5df6','被子植物APG IV分类法述评',NULL,6,6,0,'post/6851cf5df6.jpg','百忙之中忽然跑上来冒个泡是因为APG (Angiosperm Phylogeny Group, 被子植物种系发生学组) IV在本月新鲜出炉了。我觉得有必要给大家介绍一下被子植物系统分类学在近六七年间的新进展。\r\n\r\n嘛虽然专栏早就被我玩坏了发到这里显得格格不入，然而我就是这样任性的宝宝惹~\r\n\r\n![](http://i4.buimg.com/799ea24f20f0cb1c.png)\r\n\r\n先扔系统树吧，系统学的研究其实看系统树就可以了。\r\n\r\nAPG IV距09年发布的APG III已经有七年之久了，这其间被子植物系统学有了很多新进展，APG IV即是对这几年这些新成果的概括和总结的成果。\r\n\r\n跟APG III相比，APG IV并没有什么博人眼球颠覆性的改变，说明近几年被子植物的系统骨架是趋于稳定的，主要进展是在很多细部上变得更明晰起来。\r\n\r\n这次的主要更新包括：\r\n\r\n\r\n1. 五个新目的成立，这五个新目都是上一版APG中地位未定的类群，这几年的系统学研究可以将它们放置到合理的位置上。这五个新目是：\r\n\r\n\r\n紫草目（Boraginales）\r\n\r\n五桠果目（Dilleniales）\r\n\r\n茶茱萸目（Icacinales）\r\n\r\n![](http://i4.buimg.com/b49c8a81626bec95.jpg)\r\n\r\n微花藤（*Iodes cirrhosa*）\r\n\r\n茶茱萸科（Icacinaceae）无疑是APG IV中的明星类群。APG IV的很多新成果都是围绕这一类群的研究展开的。该类群主产于热带，一直没有引起人们研究的重视，其单系性、属系位置和属的构成都相当成问题，是被子植物里面宛如堆满杂物无人问津的地下室一般的存在，分子系统学研究也没有给出高支持率的解决。直到2015年Stull et al.利用叶绿体基因组学系统学才比较确切地将该类群放置于唇形类分支的基部。该科的界限也进一步厘清，所包含的属从54属降到25属。金檀木科（Stemonuraceae）和心翼果科（Cardiopteridaceae）被置于冬青目，Pennantiaceae 被置于伞形目。此外该研究也揭示了下面管花木目的存在。\r\n\r\n管花木目（Metteniusiales）\r\n\r\n![](http://i4.buimg.com/c4d2fbc9a24b60bc.jpg)\r\n\r\n*Metteniusa tessman*\r\n\r\n管花木科只分布于中南美洲，只有单科单属共七种植物。Stull et al.（2015）的基因组系统学研究将茶茱萸科中的Emmotaceae和柴龙树属（Apodytes）也置于此类群中成立管花木目。\r\n\r\n二歧草目（Vahliales）\r\n\r\n![](http://i4.buimg.com/fa077509b728aa84.jpg)\r\n\r\n*Vahlia capensis*\r\n\r\n二歧草目是一个分布在印度和非洲，只有一科一属5-8种的小类群。Refulio-Rodrıguez & Olmstead (2014)将其作为茄目（Solanales）的姐妹群放置。\r\n\r\n这五个新建立的目除了五桠果目被置于菊类分支（Asterids）外侧以外，其余五个目都位于唇形类分支(Lamiids)，这也显示出唇形类植物是现在系统分类比较混乱的研究热点区域，也是这几年系统学成果最集中和令人瞩目的区域。唇形类的分类系统目前还不稳定，以前的唇形科、紫草科、马鞭草科等类群的单系性都很成问题，经常有属的位置的变动。整个唇形类植物的系统学还没有得到满意支持率的解决，亟需重建新的分类系统。\r\n\r\nAPG系统现在总共有64个目和416个科。\r\n\r\n2. 两个APG III未定目的科被合并到临近的目中：\r\n\r\n多须草科（Dasypogonaceae）(澳洲特产)被归入棕榈目（Arecales）\r\n\r\n清风藤科（Sabiaceae）被归入山龙眼目（Proteales）\r\n\r\n这也显示出APG系统在目这一分类阶元上的使用趋于简化和归并的态度。\r\n\r\n3. 两个以前属系不清的寄生类群得到了放置：\r\n\r\n![](http://i4.buimg.com/27e0a6899f5aa27c.jpg)\r\n\r\n欧洲锁阳（*Cynomorium coccineum*）\r\n\r\n锁阳科（Cynomoriaceae）这一污力滔天的类群被放在了虎耳草目（Saxifragales）。\r\n\r\n![](http://i2.buimg.com/b06b6b491fe535fc.jpg)\r\n\r\n*Pilostyles aethiopica*\r\n\r\n密恐福音离花科（Apodanthaceae）被放置在葫芦目（Cucurbitales）。\r\n\r\n但这两个类群在目中的具体位置还不清楚，有待研究。\r\n\r\n4. 一些科被合并或分拆：\r\n\r\n4.1胡椒目（Piperales）中，最恶心植物菌花科（Hydnoraceae）和短蕊花科（ Lactoridaceae）被合并到马兜铃科（Aristolochiaceae）\r\n\r\n![](http://i4.buimg.com/a388b38fe71fde1a.jpg)\r\n\r\n*Prosopanche americana*\r\n\r\n你要问马兜铃的心理阴影面积？\r\n\r\n![](http://i4.buimg.com/e70757fa864a6885.jpg)\r\n\r\n嘛其实马兜铃也很恶心的惹，真是不是一家人不进一家门。\r\n\r\n4.2 蓝果树科（Nyssaceae）因为跟山茱萸科（Cornales）不呈单系，被单独升级成科（(Xiang et al., 2011)）\r\n\r\n4.3 其它新承认的科：Maundiaceae(天南星目，从水麦冬科分出)，Peraceae（金虎尾目，从大戟科分出），Petenaeaceae（十齿花目），Kewaceae（一个以邱园命名的，特产于南部非洲和圣赫勒拿岛的单属科），Limeaceae, Macarthuriaceae ，Microteaceae，Petiveriaceae（以上石竹目），Mazaceae（唇形目）\r\n\r\n4.4 列当科（Orobanchaceae）现在包含地黄属（Rehmannia）和钟萼花属（*Lindenbergia*）。地黄的内心应该是拒绝的。。。\r\n\r\n![](http://i4.buimg.com/d435ddaab1b311e1.jpg)\r\n\r\n列当（*Orobanche coerulescens*）\r\n\r\n![](http://i4.buimg.com/2b61d726ef367c9d.jpg)\r\n\r\n地黄（*Rehmannia glutinosa*）\r\n\r\n作者：林十之\r\n链接：https://zhuanlan.zhihu.com/p/20795367\r\n来源：知乎\r\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\r\n\r\n两者其实看上去还是蛮相似的。。。\r\n\r\n4.5 一些科涉及属的变动，就不赘述了（都是国外产属）。值得一提的是白花菜科（Capparaceae）中的四个属——节蒴木属（Borthwickia），罗志藤属（Stixis），Forchhammeria，Tirania被移到木犀草科（Resedaceae）。\r\n\r\n其它值得注意的地方：\r\n\r\n近年来方法学上的进展是采用大量低拷贝核基因进行构树，Zeng et al. (2014) 和 Wickett et al. (2014)采用59和852个低拷核基因构树，但所涵盖的物种数比较有限（分别为60和37种），可见这一方法在工作量的需要上还是很大的。\r\n\r\n低拷核基因系统学所揭示的系统关系跟之前的研究在大部分位置都没有冲突。但在叶绿体基因系统学中始终位于豆类分支的卫矛目、酢浆草目和金虎尾目分支，在线粒体和核基因系统学中却处于相邻的锦葵类分支。这一冲突可能是由于叶绿体基因组中的古基因水平转移造成的（Sun et al., 2015）\r\n\r\n下面根据不同的类群在进行主要新成果和遗留问题的梳理：\r\n\r\n早期被子植物（Early angiosperm）：被子植物最早形成的几个分支。\r\n\r\n早期被子植物的系统跟以前相比保持稳定，依次为：\r\n\r\n互叶梅目（Amborellales）新喀里多尼亚特产单科属种植物，仍然置于整个被子植物的基部。\r\n\r\n睡莲目（Nymphaeales）包括排水草科（澳大利亚和印度特产，我的课题之一）、睡莲科和莼菜科。\r\n\r\n木兰藤目（Austrobaileyales）包括木兰藤科（澳大利亚西北部特有）、早落瓣科（大洋洲东南亚特产）、五味子科（包括八角茴香科）\r\n\r\n木兰类植物（Magnoliids）:单子叶植物和真双子叶植物分化之前出现的类群。系统保持稳定，分为四个目：（木兰目、樟目）、（胡椒目和白桂皮目（产于热带非洲和美洲））。\r\n\r\n菌花科（Hydnoraceae）和短蕊花科（ Lactoridaceae）被合并到马兜铃科（Aristolochiaceae）\r\n\r\n金粟兰目（Chloranthales）:作为位置不确定的类群放在早期被子植物之后。包括一科四属。Wickett et al.,（2014）作为真双子叶植物的姐妹群，Zeng et al., （2014）作为金鱼藻科的姐妹群。APG IV对于该类群的位置持审慎态度，故没有进行放置，有待进一步研究的加强。\r\n\r\n单子叶植物（Monocots）:目的水平保持稳定，从演化顺序依次为菖蒲目、泽泻目、（薯蓣目、露兜树目、）、百合目、天门冬目、棕榈目、禾本目、（鸭跖草目、姜目）。\r\n\r\n薯蓣目的科一级的系统不稳定，APG IV维持了APG III的分科系统，但提到菌异养类群水玉簪科（Burmanniaceae）的非单系问题。这也是我的课题之一，已证实水玉簪科非单系，并且情况还要复杂得多。\r\n\r\n禾本目（Poales)中刺鳞草科（Centrolepidaceae）、澳大利亚特产单属科苞穗草科(Anarthriaceae)和帚灯草科（Restionaceae）的关系一直有冲突，APG IV干脆就把它们全部归到帚灯草科（Restionaceae），还很大大落落地说酱紫不过以后的研究有什么成果，至少矛盾都被限制到科水平一类了，从外部矛盾转变成内部矛盾，还是蛮无耻惹（To stabilize the taxonomy of this order, we enlarge Restionaceae to re-include Anarthriaceae and Centrolepidaceae so that, regardless of the outcomes of future studies, the family name will remain the same.）\r\n\r\n金鱼藻目（Ceratophyllales）：单科属水生植物，作为整个真双子叶植物的姐妹群。\r\n\r\n基部真双子叶植物（Basal eudicots），即不属于蔷薇类分支和菊类分支的真双子叶演化早期类群。包括毛茛目、山龙眼目（APG IV将清风藤科归于此目）、昆栏树目、洋二仙草目和新承认的五桠果目。但五桠果目的位置不同的研究有冲突，还不明朗，现在作为菊类分支和蔷薇类分支的并系群放置。\r\n\r\n超蔷薇分支（Superrosids）。APG IV提出的新分支，即蔷薇分支加上与其成姐妹群关系的虎耳草目（Saxifragales），锁阳科被放入虎耳草目。\r\n\r\n蔷薇类分支（rosids）:目一级保持稳定。蔷薇类分支又分为两个分支：\r\n\r\n豆类分支（fabids) ：包括蒺藜目、（卫矛目、（金虎尾目、酢浆草目））、（豆目、蔷薇目（葫芦目、壳斗目）)\r\n\r\n离花科加入葫芦目(Filipowicz & Renner, 2010)。\r\n\r\n**锦葵类分支**（malvids）:包括（牻牛儿苗目、桃金娘目）、（燧体木目、美洲苦木目、无患子目、十齿花目、（十字花目、锦葵目））\r\n\r\n由于命名上的混乱和纠纷。APG IV牻牛儿苗目（Geraniales）中的几个南美特产的科Bersamaceae，Ledocarpaceae,Rhynchothecaceae， Vivianiaceae一股脑儿合并到花茎草科（Francoaceae）。再次将外部矛盾转移成为科内内部矛盾。\r\n\r\n白花菜科一直在丢属到其它科，APG IV又丢了四个属出去，白花菜科恐成最大输家。\r\n\r\n**超菊分支**（Superasterids）:APG IV提出的新概念。即菊类分支加上智利藤目、檀香目和石竹目。\r\n\r\n**檀香目**(Santalales)目以下的系统极不稳定。铁青树科（Olacaceae）被建议拆成八个科，檀香科（Santalaceae）中的七个类群被建议独立成科（Nickrent et al., 2010）。该目亟需进一步厘清。\r\n\r\n**石竹目**（Caryophyllales）仍然有三个系统分类学难题：\r\n\r\n1.紫茉莉科（Nyctaginaceae）和商陆科（Phytolaccaceae）的关系。有研究表明商陆科下的热带美洲产数珠珊瑚亚科（Rivinoideae）跟紫茉莉科形成了姐妹群关系。(Brockington et al., 2009, 2011; Bissinger et al., 2014)\r\n\r\n2.仙人掌科（Cactaceae）和马齿苋科（Portulacaceae）的关系。后者跟仙人掌科会形成并系关系。为了减少建立单属科，APG曾提议将仙人掌科的范围扩大，吞掉一些马齿苋科类群，但网络调查发现这一提议非常不受这一类群的分类学家欢迎（提议者的内心是崩溃的。。。）\r\n\r\n![](http://i4.buimg.com/48c3befda6581ad8.jpg)\r\n\r\n3. 粟米草科（Molluginaceae）的非单系问题。为解决这一问题，APG IV建立了一系列新的科。\r\n\r\n菊类分支（asterids）: 包括位于基部的山茱萸目、杜鹃花目和两个大类群——唇形分支（lamiids）和桔梗分支（campanulids）\r\n\r\n桔梗分支包括冬青目、菊目、南鼠刺目？、鳞叶树目、伞形目、（川续断目和盔瓣花目）\r\n\r\n唇形分支包括茶茱萸目、管花木目、绞木目、（紫草目？、龙胆目？、二歧草目？、茄目？、唇形目？）\r\n\r\n杜鹃花目中恶心寄生植物帽蕊草科（Mitrastemonaceae）的位置不确定。\r\n\r\n![](http://i4.buimg.com/9baa242f693bcc68.jpg)\r\n\r\n奴 草（Mitrastemon yamamotoi）。悄悄问圣僧，女儿美不美？\r\n\r\n山茱萸目中，珙桐、喜树和单室茱萸（Mastixia）构成蓝果树科（Nyssaceae），跟绣球花科、水穗草科和刺莲花科构成姐妹群关系，故不跟山茱萸科呈单系，所以独立成科。(Xiang et al., 2011)\r\n\r\n曾属于玄参科的地黄属（Rehmannia）被发现是列当科（Orobanchaceae）的姐妹群。（Xia, Wang & Smith, 2009; Refulio-Rodrıguez & Olmstead, 2014)。APG IV建议将地黄属并入列当科。\r\n\r\n唇形目跟单子叶植物中的天门冬目是新系统学研究影响最厉害的两个类群，以前的分类系统几乎被完全颠覆。经常有属从原有科变动到其它科，还有很多属的归属仍不清楚，亟需进一步研究。整个唇形目也需要重建分类系统。\r\n\r\n紫草科的位置还不清楚，APG IV索性为它建立了一个单科目。\r\n\r\n地位未定\r\n\r\nAPG III中地位未定的类群只剩下一个属——Gumillea.只有一份在18世纪晚期在秘鲁采到的标本。APG IV又增加了六个地位未定的属：Atrichodendron，Coptocheile，Hirania，Keithia，Poilanedora，Rumphia。都是一些少见或者只剩标本的植物。其中Rumphia只有插图保留。。。简直是山海经级别的神仙植物，饶了它吧。\r\n\r\n题图——《橡树》， 希斯金，1886.',0,1,1463158518,1463158518),
	('6c2279050c','aaasdasds',NULL,6,5,1,'','\r\nadadadasdsada\r\n\r\nadadadasdsada\r\n\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsada\r\nadadadasdsadadasd>\r\n\r\n> asdasd\r\n\r\n# asdsadsa',0,1,1463309455,1463309455);

/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_special
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_special`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专栏';

LOCK TABLES `t_special` WRITE;
/*!40000 ALTER TABLE `t_special` DISABLE KEYS */;

INSERT INTO `t_special` (`id`, `title`, `slug`, `cover`, `description`, `post_count`, `follow_count`, `created`)
VALUES
	(1000,'如何设计一个MVC框架','mvc-framework','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517225229029_fD1zstqDSV.png/b24M/4789.png','如何设计一个MVC框架',0,0,1463068334),
	(1001,'妹子图什么的','meizi','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517231042404_oK2WWHGTuE.jpg/NZ5R/3135.jpg','定期更新各种妹子图。',0,0,1463068334),
	(1002,'Java程序员眼中的Linux','java-linux','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517231128082_QJAczEFNQX.jpg/1xnv/5861.jpg','Java程序员眼中的Linux',0,0,1463068334),
	(1003,'7天学会NodeJs','learn-to-nodejs-seven-days','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517230821521_D0RoR8vnMl.jpg/g4HM/6741.jpg','7天学会NodeJs',0,0,1463068334),
	(1004,'缓存的理解和框架实现','cache-understand-implements','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517231305760_85sVdo06Xg.png/kBYk/3936.png','缓存的理解和框架实现',0,0,1463068334),
	(1005,'Java8的那点事儿','java8-guide','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517230721284_wG8pEiuszQ.png/cgn8/8394.png','Java8的那点事儿',0,0,1463068334),
	(1006,'Flask一步一步来','flask-learn','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517230148816_lgl2ToFnMQ.png/vxQ0/2662.png','Flask一步一步来',0,0,1463068334),
	(1007,'CentOS实战系列','centos-combat','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517225745551_O0DfWpwOpI.jpg/ka7I/5143.jpg','CentOS实战系列',0,0,1463068334),
	(1008,'设计模式跟我学','design-patterns','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517225624330_1IhcN8puUn.png/g9Bx/8858.png','设计模式跟我学',0,0,1463068334),
	(1009,'数据结构跟我学','data-structure','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517230026308_6fZFtCXqWp.jpg/K079/1566.jpg','数据结构跟我学',0,0,1463068334),
	(1010,'手动实现一个IOC','ioc-implments','special//usr/local/apache-tomcat-8.0.32/webapps/writty/static/temp/20160517231539577_RzLl7hgBda.png/0ERa/9477.png','手把手教你实现IOC容器',0,0,1463068334);

/*!40000 ALTER TABLE `t_special` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`uid`, `user_name`, `pass_word`, `nick_name`, `avatar`, `role_id`, `created`, `updated`, `logined`)
VALUES
	(1,'biezhi','916a042f1bde53eba1e49cd59cf4eb75','王爵',NULL,1,1462708055,1462708055,1462708055),
	(6,'biezhi','','王爵nice','https://avatars.githubusercontent.com/u/3849072?v=3',3,1462977008,1462977008,NULL);

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
