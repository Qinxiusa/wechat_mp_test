CREATE TABLE  IF NOT EXISTS `Messages` (

`id` int(11) NOT NULL default '0',

`user_name` varchar(30) default NULL,

`msg_type` varchar(20) default NULL,

`content` text default NULL,

`create_time` varchar(20) default NULL,

 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;