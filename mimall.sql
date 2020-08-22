-- 创建小米商城数据库
create database mimall default character set utf8 collate utf8_bin;
-- 使用库
use mimall;

-- 表一：创建用户信息表
create table if not exists userInfo(
	uid int primary key auto_increment, -- 主键自增
	uname varchar(100) not null unique, -- 昵称
	pwd varchar(100) not null, -- 密码
	sex varchar(10), -- 性别
	tel varchar(15) unique, -- 手机号
	email varchar(30) unique, -- 邮箱
	hobby varchar(100), -- 兴趣爱好
	status int  -- 状态  1 正常  0 冻结
)engine = InnoDB auto_increment = 101  default charset=utf8  collate=utf8_bin;

-- 表二：创建管理员信息表
create table if not exists adminInfo(
	aid int primary key auto_increment, -- 主键自增
	aname varchar(100) not null unique, -- 管理员昵称
	pwd varchar(100) not null, -- 密码
	sex varchar(10) not null, -- 性别
	tel varchar(15) unique, -- 联系方式
	status int  -- 状态  1 正常  0 冻结
)engine = InnoDB auto_increment = 101  default charset=utf8  collate=utf8_bin;

-- 表三：创建商品类型表
create table if not exists types(
	tno int primary key auto_increment,-- 主键自增
	tname varchar(30) not null unique,
	status int default "1" -- 类型状态  状态为1时：可用  为2时暂时不使用
)ENGINE = InnoDB auto_increment=1 default charset=utf8 collate=utf8_bin;

-- 表四：创建商品信息表
create table if not exists productInfo(
	pid int primary key auto_increment,-- 主键自增
	pname varchar(100) not null, -- 商品名称
	tno int,-- 所属类型 外键 类型表
	size varchar(50),-- 尺寸
	version varchar(50),-- 产品版本
	color varchar(100),-- 颜色
	price varchar(100),-- 价格
	intro varchar(500),-- 商品介绍
	type varchar(100),-- 型号
	balance varchar(100),-- 库存
	pics varchar(500),-- 图片
	status int,-- 商品状态 1 上架 0 下架
	constraint FK_productInfo_tno foreign key(tno) references types(tno)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;

-- 表五：创建收货地址表
create table if not exists addrInfo(
	ano varchar(100) primary key, -- 时间戳
	uid int, -- 用户编号 外键  用户表
	name varchar(100) not null, -- 收货人姓名
	postcode varchar(10), -- 邮政编码
	province varchar(100) not null, -- 省份
	city varchar(100) not null, -- 城市
	area varchar(100) not null, -- 地区
	addr varchar(100) not null, -- 详细地址
	tel varchar(15) not null, -- 手机号码
	flag int, -- 是否为默认收货地址  1 默认  0 地址
	status int, -- 状态   1 有效  0 无效  是否使用 
	constraint FK_addrInfo_uid foreign key(uid) references userInfo(uid)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;

-- 表六：创建购物车表
create table if not exists cartInfo(
	cno varchar(100) primary key,-- UUID
	uid int, -- 用户编号   外键  用户表
	pid int, -- 商品信息编号   外键  商品信息表
	num int, -- 数量
	constraint FK_cartInfo_uid foreign key(uid) references userInfo(uid),
	constraint FK_cartInfo_pid foreign key(pid) references productInfo(pid)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;

-- 表七：创建订单表
create table if not exists orderInfo(
	ono varchar(100) primary key, -- 时间戳
	odate datetime, -- 下单日期
	ano varchar(100), -- 收货地址 外键   收货地址表
	sdate datetime, -- 发货日期
	rdate datetime, -- 收货日期
	status int, -- 订单状态   0  已取消 1 去支付 2  已支付 3 已发货 4 已收货
	price decimal(10,2), -- 订单总额
	invoice int, -- 是否已开发票  1 开票  0未开票
	constraint FK_orderInfo_ano foreign key(ano) references addrInfo(ano)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;

-- 表八：订单详情表
create table if not exists orderItemInfo(
	ino int primary key auto_increment, -- 主键自增
	ono varchar(100), -- 所属订单   外键  订单表
	pid int, -- 商品信息编号   外键  商品信息表
	nums int, -- 商品数量
	price decimal(8,2), -- 购买价 防止价格折扣波动  冗余的必须要性
	constraint FK_orderItemInfo_ono foreign key(ono) references orderInfo(ono),
	constraint FK_orderItemInfo_pid foreign key(pid) references productInfo(pid)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;


-- 表九：评价表
create table if not exists  discuss(
	dno int primary key auto_increment, -- 主键自增
	uid int, -- 所属用户   外键  用户表
	ino int, -- 所属订单  订单详情表
	stat int, -- 1-5 表示星数
	date varchar(100), -- 评价时间
	content varchar(100),-- 评论内容
	constraint FK_discuss_uid foreign key(uid) references userinfo(uid),
	constraint FK_discuss_ino foreign key(ino) references orderItemInfo(ino)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;

-- 表十：收藏表
create table if not exists likeInfo(
	cno int primary key auto_increment, -- 主键自增
	uid int, -- 所属用户   外键  用户表表
	pid int, -- 商品信息编号   外键  商品信息表
	constraint FK_likeInfo_uid foreign key(uid) references userinfo(uid),
	constraint FK_likeInfo_pid foreign key(pid) references productInfo(pid)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;
