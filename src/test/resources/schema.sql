
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

DROP TABLE if EXISTS bdpn;
CREATE TABLE bdpn (
  number varchar(10) NOT NULL DEFAULT '',
  ownerid varchar(50) NOT NULL DEFAULT '',
  mnc int(2) NOT NULL,
  route varchar(5) NOT NULL DEFAULT '',
  regioncode int(2) NOT NULL,
  portdate varchar(25) NOT NULL DEFAULT '',
  rowcount int(6) DEFAULT NULL
) ENGINE=MyISAM;

INSERT INTO bdpn (number, ownerid, mnc, route, regioncode, portdate, rowcount)
VALUES
	('9000000000', 'red', 2, 'D2502', 25, '2016-09-07T12:00:17+03:00', 4450418),
	('9000000001', 'red', 2, 'D2502', 25, '2016-07-26T17:00:07+03:00', 0),
	('9000000003', 'red', 2, 'D2502', 25, '2016-09-20T10:00:09+03:00', 0),
	('9000000004', 'yellow', 99, 'D2599', 25, '2016-11-07T00:08:57+03:00', 0),
	('9000000005', 'yellow', 1, 'D2501', 25, '2015-08-10T18:01:16+03:00', 0),
	('9000000006', 'yellow', 1, 'D2501', 25, '2014-07-10T20:13:04+03:00', 0),
	('9000000007', 'green', 1, 'D2501', 25, '2015-02-21T14:06:03+03:00', 0),
	('9000000008', 'green', 2, 'D2502', 25, '2016-07-14T08:00:11+03:00', 0);
