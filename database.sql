CREATE TABLE RESTAURANT
(
		resto_id int NOT NULL UNIQUE AUTO_INCREMENT,
		genre VARCHAR(255),
		price_range VARCHAR(255),
		rating VARCHAR(255),
		notes VARCHAR(255),
		user_id int,
		longitude int,
		latitude int
		//Do the foreing keys here
);