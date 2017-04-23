drop table if exists dianying.test;
CREATE TABLE test(
  id                   bigint not null auto_increment COMMENT '主键',
  test                 varchar(32) comment '测试数据',
  createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
ENGINE = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.test COMMENT '测试数据';
create index IDX_TEST on dianying.test
(
   id,test
);

drop table if exists dianying.user;
create table user(
  id                bigint not null auto_increment comment '主键',
  username          varchar(32) comment '用户名',
  password          varchar(128) comment '密码',
  nickname          varchar(32) comment '昵称',
  name              varchar(32) comment '真实姓名',
  gender               enum('male','female') comment '性别',
  phonenumber       varchar(128) comment '联系电话',
   createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.user comment '用户表';
create index IDX_USER on dianying.user(
    id
);

drop table if exists dianying.movie;
create table movie(
  id                bigint not null auto_increment comment '主键',
  name              varchar(32) comment '电影名',
  pic               varchar(128) comment '图片地址',
  director          varchar(32) comment '导演名',
  stars             varchar(32) comment '主演',
  showtime            datetime comment '上映日期',
  createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.movie comment '电影表';
create index IDX_MOVIE on dianying.movie(
    id
);

drop table if exists dianying.cinema;
create table cinema(
 id                bigint not null auto_increment comment '主键',
 name              varchar(32) comment '影院名称',
 summary           varchar(128) comment '影院简介',
 pic               varchar(128) comment '图片地址',
  createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.cinema comment '影院';

drop table if exists dianying.show;
create table play(
  id                bigint not null auto_increment comment '主键',
  movieId           bigint not null comment '电影id',
  cinemaId          bigint not null comment '影院id',
  videoHall         varchar(32) comment '放映厅',
  showtime           datetime comment '放映时间',
  seatNum           varchar(32) comment '剩余座位数',
   createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.play comment '放映';

drop table if exists dianying.pingjia;
create table pingjia(
   id                bigint not null auto_increment comment '主键',
   userId            bigint not null comment '用户id',
   movieId           bigint not null comment '电影id',
   content           varchar(128) comment '评价内容',
    createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
  PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.pingjia comment '用户评价';

drop table if exists dianying.order;
create table ord(
 id                bigint not null auto_increment comment '主键',
  userId           bigint not null comment '用户id',
  playId           bigint not null comment '放映id',
  ticketNum        varchar(128) comment '取票码',
  payState         int not null comment '支付状态',
   createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
   PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.ord comment '订单';

drop table if exists dianying.message;
create table message(
 id                bigint not null auto_increment comment '主键',
 userId             bigint not null comment '用户id',
 content            varchar(128) comment '消息内容',
 state              int  not null comment '消息状态',
  createDate           datetime COMMENT '创建时间',
  modifyDate          datetime COMMENT '更新时间',
   PRIMARY KEY (id)
)
engine = InnoDB
COLLATE = utf8_general_ci;
alter table dianying.message comment '消息';