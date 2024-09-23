INSERT INTO `auditorium` (`name`) VALUES ('Jupiter');
SET @auditorium_id = LAST_INSERT_ID();
INSERT INTO `auditorium_size` (auditorium_id, `row`, seats) VALUES
(@auditorium_id, 1, 6),
(@auditorium_id, 2, 6),
(@auditorium_id, 3, 10);