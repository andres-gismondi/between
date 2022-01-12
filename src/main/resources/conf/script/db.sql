/*ALTER TABLE `prices` DROP FOREIGN KEY `brand_id`;*/
DROP TABLE IF EXISTS `brand` cascade;
DROP TABLE IF EXISTS `prices` cascade;

CREATE TABLE `brand` (
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     `description`  VARCHAR(10) NOT NULL,
     PRIMARY KEY (`id`)
) ;

CREATE TABLE `prices` (
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     `brand_id` INT(11) NOT NULL,
     `start_date` TIMESTAMP NOT NULL,
     `end_date` TIMESTAMP NOT NULL,
     `price_list` INT(11) NOT NULL,
     `product_id` INT(11) NOT NULL,
     `priority` INT(45) NOT NULL,
     `price` DECIMAL(10,2) NOT NULL,
     `curr`  VARCHAR(10) NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY(`brand_id`) REFERENCES `brand`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO `brand` (id, description) VALUES (1, 'ZARA');
INSERT INTO `prices` (id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr) VALUES
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 1, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 0, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');