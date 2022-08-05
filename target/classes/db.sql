CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
CREATE TABLE IF NOT EXISTS 'Messages'(
	'id' int(11) NOT NULL AUTO_INCREMENT,
	'UserName' varchar(30) default NULL,
	'type' varchar(20) default NULL,
	'content' TEXT default NULL,
	'create'  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY('id')
)