
drop table if exists user;
create table user(
id int primary key auto_increment comment '自增主键id',
username varchar(200) comment '用户名',
user_password varchar(200) comment '密码',
user_phone varchar(20) comment '手机号',
admin_flg char(1) comment '是否是管理员标记位 0否  1是',
create_time timestamp not null default current_timestamp comment '创建时间',
update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
UNIQUE KEY `username_unique` (`username`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';

insert into user(username,user_password,user_phone,admin_flg) values('admin','$2a$10$6LSs8wcExq5x2rZHBszD5uYWF0B.0AkuGvQUKo/rBTa/q7Givl6iO','18368539295','1');
insert into user(username,user_password,user_phone,admin_flg) values('zhangsan','$2a$10$6LSs8wcExq5x2rZHBszD5uYWF0B.0AkuGvQUKo/rBTa/q7Givl6iO','18368539295','1');


drop table if exists book_category;
CREATE TABLE `book_category` (
`category_id` int(11) NOT NULL AUTO_INCREMENT comment '书的分类id',
`category_name` varchar(20) DEFAULT NULL comment '书的分类name',
create_time timestamp not null default current_timestamp comment '创建时间',
update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '书分类信息';

insert into book_category(category_name) values('文学');


drop table if exists book;
CREATE TABLE `book` (
`id` int(11) NOT NULL AUTO_INCREMENT comment '主键自增id',
`book_no` varchar(200) comment '书的编号',
`book_name` varchar(200) NOT NULL comment '书名',
`book_author` varchar(200) DEFAULT NULL comment '书的作者',
`book_publish` varchar(200) DEFAULT NULL comment '书的出版社',
`book_category` int(11) DEFAULT NULL comment '书的分类',
`book_price` varchar(20) DEFAULT NULL comment '书的价格',
`book_introduction` text DEFAULT NULL comment '书的介绍',
create_time timestamp not null default current_timestamp comment '创建时间',
update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`id`),
KEY `book_category` (`book_category`) USING BTREE,
CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_category`) REFERENCES `book_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '书的信息';

insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('1111','巨人的陨落','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('2222','巨人的陨落2','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('3333','巨人的陨落3','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('4444','巨人的陨落4','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('5555','巨人的陨落5','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('6666','巨人的陨落6','肯.福莱特','江苏凤凰文艺出版社',1,'129','一本文学巨作');
insert into book(book_no,book_name,book_author,book_publish,book_category,book_price,book_introduction) values('7777','巨人的陨落7','张三','江苏凤凰文艺出版社',1,'129','一本文学巨作');



create table borrow_info(
id int primary key auto_increment comment '自增主键id',
user_id int comment '用户id',
book_id int comment '书籍id',
borrow_time datetime comment '借书时间 yyyy-MM-dd HH:mm:ss',
return_time datetime comment '需要还书的时间 yyyy-MM-dd HH:mm:ss',
real_return_time datetime comment '实际还书的时间 yyyy-MM-dd HH:mm:ss',
create_time timestamp not null default current_timestamp comment '创建时间',
update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
KEY `user_id` (`user_id`) USING BTREE,
KEY `book_id` (`book_id`) USING BTREE,
CONSTRAINT `borrow_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
CONSTRAINT `borrow_book_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '借阅信息表';