/* DO Use Statement to tell SQL which database to add these tables to */

CREATE TABLE IF NOT EXISTS Account (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    `type` TINYINT(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC)
) ENGINE=INNODB;

DELIMITER $$
CREATE TRIGGER chk_UserType BEFORE INSERT ON Account
FOR EACH ROW
BEGIN
    IF NEW.type NOT IN (1,2) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid User Type Given';
    END IF;
END$$

CREATE TABLE if not exists CommissionEmployee(
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(200) NOT NULL ,
    `lastName` VARCHAR(200) NOT NULL ,
    `position` VARCHAR(200) NOT NULL ,
    `department` VARCHAR(200) NOT NULL ,
    `commissionRate` DOUBLE(3,2) NOT NULL ,
    `sales` DECIMAL(9,2) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=INNODB;

CREATE TABLE if not exists BasePlusCommissionEmployee(
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(200) NOT NULL ,
    `lastName` VARCHAR(200) NOT NULL ,
    `position` VARCHAR(200) NOT NULL ,
    `department` VARCHAR(200) NOT NULL ,
    `commissionRate` DOUBLE(3,2) NOT NULL ,
    `sales` DECIMAL(9,2) NOT NULL,
    `salary` DECIMAL (15,2) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=INNODB;

CREATE TABLE if not exists HourlyEmployee(
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(200) NOT NULL ,
    `lastName` VARCHAR(200) NOT NULL ,
    `position` VARCHAR(200) NOT NULL ,
    `department` VARCHAR(200) NOT NULL ,
    `hoursPerWeek` DECIMAL(3,2) NOT NULL,
    `hourlyRate` DECIMAL(3,2) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=INNODB;

CREATE TABLE if not exists SalaryEmployee(
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(200) NOT NULL ,
    `lastName` VARCHAR(200) NOT NULL ,
    `position` VARCHAR(200) NOT NULL ,
    `department` VARCHAR(200) NOT NULL ,
    `salary` DOUBLE(3,2) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=INNODB;

CREATE TABLE if not exists Manufacturers(
    `id` int NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200),
    PRIMARY KEY(id)
) ENGINE=INNODB;

CREATE TABLE if not exists Products(
    `id` int NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200),
    `modelNumber`VARCHAR(200),
    `manufacturer_ID` INT,
    PRIMARY KEY(id),
    FOREIGN KEY(manufacturer_ID) references Manufacturers(id)
) ENGINE=INNODB;

CREATE TABLE if not exists CommissionEmployeeSales(
    `id` int NOT NULL AUTO_INCREMENT,
    `salePrice` DECIMAL(15,2) NOT NULL,
    `product_ID` INT,
    `employee_ID` INT,
	PRIMARY KEY(id),
	FOREIGN KEY(product_ID) references Products(id),
    FOREIGN KEY(employee_ID) references CommissionEmployee(id)
) ENGINE=INNODB;

CREATE TABLE if not exists BasePlusCommissionEmployeeSales(
    `id` int NOT NULL AUTO_INCREMENT,
    `salePrice` DECIMAL(15,2) NOT NULL,
    `product_ID` INT,
    `employee_ID` INT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(product_ID) references Products(id),
    FOREIGN KEY(employee_ID) references BasePlusCommissionEmployee(id)
) ENGINE=INNODB;

CREATE TABLE if not exists HourlyEmployeeSales(
    `id` int NOT NULL AUTO_INCREMENT,
    `salePrice` DECIMAL(15,2) NOT NULL,
    `product_ID` INT,
    `employee_ID` INT,
    PRIMARY KEY(id),
    FOREIGN KEY(product_ID) references Products(id),
    FOREIGN KEY(employee_ID) references HourlyEmployee(id)
) ENGINE=INNODB;

CREATE TABLE if not exists SalaryEmployeeSales(
    `id` int NOT NULL AUTO_INCREMENT,
    `salePrice` DECIMAL(15,2) NOT NULL,
    `product_ID` INT,
    `employee_ID` INT,
    PRIMARY KEY(id),
    FOREIGN KEY(product_ID) references Products(id),
    FOREIGN KEY(employee_ID) references SalaryEmployee(id)
) ENGINE=INNODB;

# Insert accounts into table

INSERT INTO account (username, password, type) VALUES ("Regular", "regular", 1);
INSERT INTO account (username, password, type) VALUES ("Admin", "admin", 2);

# Create some Dummy Data

INSERT INTO BasePlusCommissionEmployee (`firstName`,`lastName`,`position`,`department`,`commissionRate`,`sales`,`salary`)
VALUES ("Bob", "Smith", "Programmer", "IT", 10.00, 20000.00, 60000.00);




