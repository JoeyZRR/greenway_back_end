DROP TABLE IF EXISTS plant_collection;
DROP TABLE IF EXISTS plant;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  nick_name VARCHAR(50),
  password VARCHAR(120) NOT NULL,
  image_url VARCHAR(100) NULL,
  public_info BIT NOT NULL DEFAULT 1,
  phone_number VARCHAR(32) NULL DEFAULT NULL,
  zip_code VARCHAR(16) NULL DEFAULT NULL
);

insert into user (email, password) values ('junitTest1@gmail.com', '12345678');


CREATE TABLE IF NOT EXISTS plant (
  plant_id INT AUTO_INCREMENT PRIMARY KEY,
  botanical_name VARCHAR(50),
  common_name VARCHAR(50),
  plant_type VARCHAR(30),
  light_level VARCHAR(30),
  water_needs VARCHAR(20),
  maintenance VARCHAR(10),
  soil_type VARCHAR(30),
  image_url VARCHAR(100)
);

-- Populate plant table
INSERT INTO plant
  (botanical_name,common_name,plant_type,light_level,water_needs,maintenance,soil_type,image_url)
VALUES
  ('Actinidia deliciosa','Kiwifruit','Climbers, Fruit','Full Sun','Average','Average','Loam, Sand','https://greenwayplants.s3.amazonaws.com/Kiwifruit.jpg');

INSERT INTO plant
  (botanical_name,common_name,plant_type,light_level,water_needs,maintenance,soil_type,image_url)
VALUES
  ('Adiantum capillus-veneris','Southern Maidenhair Fern','Fern','Partial Sun, Shade','Average','Low','Chalk, Clay, Loam, Sand','https://greenwayplants.s3.amazonaws.com/SouthernMaidenhairFern.jpeg');


CREATE TABLE IF NOT EXISTS plant_collection (
  collection_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  plant_id INT NOT NULL,
  image_url VARCHAR(100),
  growth_stage VARCHAR(50),
  description VARCHAR(250),
  available BIT NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (plant_id) REFERENCES plant(plant_id)
);

insert into plant_collection(user_id, plant_id, image_url) values(1, 1, 'https://greenwayplants.s3.amazonaws.com/Kiwifruit.jpg')



