USE gc200260847;

CREATE TABLE if not exists CommissionEmployee(
id int NOT NULL,
`firstName` VARCHAR(200) NOT NULL , 
`lastName` VARCHAR(200) NOT NULL , 
`position` VARCHAR(200) NOT NULL , 
`department` VARCHAR(200) NOT NULL , 
`commissionRate` DOUBLE(3,2) NOT NULL , 
`sales` DECIMAL(9,2) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE if not exists BasePlusCommissionEmployee(
id int NOT NULL,
`firstName` VARCHAR(200) NOT NULL , 
`lastName` VARCHAR(200) NOT NULL , 
`position` VARCHAR(200) NOT NULL , 
`department` VARCHAR(200) NOT NULL , 
`commissionRate` DOUBLE(3,2) NOT NULL , 
`sales` DECIMAL(9,2) NOT NULL,
`salary` DECIMAL (15,2) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE if not exists HourlyEmployee(
id int NOT NULL,
`firstName` VARCHAR(200) NOT NULL , 
`lastName` VARCHAR(200) NOT NULL , 
`position` VARCHAR(200) NOT NULL , 
`department` VARCHAR(200) NOT NULL ,  
`sales` DECIMAL(9,2) NOT NULL,
`hoursPerWeek` DECIMAL(3,2) NOT NULL,
`hourlyRate` DECIMAL(3,2) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE if not exists SalaryEmployee(
id int NOT NULL,
`firstName` VARCHAR(200) NOT NULL , 
`lastName` VARCHAR(200) NOT NULL , 
`position` VARCHAR(200) NOT NULL , 
`department` VARCHAR(200) NOT NULL , 
`salary` DOUBLE(3,2) NOT NULL,
PRIMARY KEY(id) 
);

CREATE TABLE if not exists Manufacturers(
id int NOT NULL,
`name` VARCHAR(200),
PRIMARY KEY(id)
);

CREATE TABLE if not exists Products(
id int NOT NULL,
`name` VARCHAR(200),
`modelNumber`VARCHAR(200),
`manufacturer_ID` INT,
FOREIGN KEY(manufacturer_ID) references Manufacturers(id)
);

CREATE TABLE if not exists Sale(
id int NOT NULL,
`salePrice` DECIMAL(15,2)NOT NULL,
`product_ID` INT,
`employee_ID` INT,
FOREIGN KEY(product_ID) references Products(id),
FOREIGN KEY(employee_ID) references Employee(id)
);





