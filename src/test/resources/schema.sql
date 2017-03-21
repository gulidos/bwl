
DROP TABLE if EXISTS whitelist;
CREATE TABLE whitelist (
  number varchar(11) NOT NULL DEFAULT '',
  date datetime NOT NULL,
  tag varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (number)
) ENGINE=MyISAM;

INSERT INTO whitelist (number, date, tag)
VALUES
	('01234567890', '2016-03-02 23:24:51', 'test1'),
	('01234567891', '2016-03-02 23:24:51', 'test1'),
	('01234567892', '2016-03-02 23:24:51', 'test1'),
	('01234567893', '2016-03-02 23:24:51', 'test1'),
	('01234567894', '2016-03-02 23:24:51', 'test2'),
	('01234567895', '2016-03-02 23:24:51', 'test2'),
	('01234567896', '2016-03-02 23:24:51', 'test2'),
	('01234567897', '2016-03-02 23:24:51', 'test2'),
	('01234567898', '2016-03-02 23:24:51', 'test2');

