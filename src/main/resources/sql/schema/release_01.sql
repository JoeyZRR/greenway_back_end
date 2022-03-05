-- DDL to create initial tables
DROP TABLE IF EXISTS plant;

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

DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  nick_name VARCHAR(50),
  password VARCHAR(120) NOT NULL,
  phone_number VARCHAR(32),
  zip_code VARCHAR(16),
  image_url VARCHAR(100),
  public_info BIT NOT NULL DEFAULT 1
);

DROP TABLE IF EXISTS plant_collection;

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

DROP TABLE IF EXISTS wish_list;

CREATE TABLE IF NOT EXISTS wish_list (
  wish_list_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  plant_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (plant_id) REFERENCES plant(plant_id)
);

-- Populate plant table
INSERT INTO plant
  (botanical_name,common_name,plant_type,light_level,water_needs,maintenance,soil_type,image_url)
VALUES
  ("Actinidia deliciosa","Kiwifruit","Climbers, Fruit","Full Sun","Average","Average","Loam, Sand","https://greenwayplants.s3.amazonaws.com/Kiwifruit.jpg"),
  ("Adiantum capillus-veneris","Southern Maidenhair Fern","Fern","Partial Sun, Shade","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/SouthernMaidenhairFern.jpeg"),
  ("Aeonium","Sunburst","Cactus - Succulents","Full Sun, Partial Sun","Low, Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/Sunburst.jpeg"),
  ("Agave victoriae-reginae","Queen Victoria Century Plant","Cactus - Succulents","Full Sun","Low","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/QueenVictoriaCenturyPlant.jpeg"),
  ("Alcea rosea","Hollyhock","Annuals","Full Sun","Average","High","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Hollyhock.jpeg"),
  ("Allium schoenoprasum","Chives","Bulbs, Perennials","Full Sun, Partial Sun","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Chives.jpeg"),
  ("Anemone sylvestris","Madonna Snowdrop Anemone","Perennials","Partial Sun, Shade","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/MadonnaSnowdropAnemone.jpeg"),
  ("Asplenium nidus","Bird's Nest Fern","Fern","Partial Sun, Shade","Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/BirdsNestFern.jpeg"),
  ("Asplenium trichomanes","Maidenhair Spleenwort","Fern","Partial Sun, Shade","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/MaidenhairSpleenwort.jpeg"),
  ("Aster aellus","King George","Perennials","Full Sun","Average","Low","Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/KingGeorge.jpeg"),
  ("Astilbe x Arendsii","Amethyst","Perennials","Full Sun, Partial Sun, Shade","Average, High","Low","Loam","https://greenwayplants.s3.amazonaws.com/Amethyst.jpeg"),
  ("Athyrium niponicum var. pictum","Painted Lady Fern","Fern","Partial Sun, Shade","Average","Low","Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/PaintedLadyFern.jpeg"),
  ("Baptisia australis","False Indigo","Perennials","Full Sun","Low, Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/FalseIndigo.jpeg"),
  ("Begonia","Dragon Wing Red Begonia","Bulbs, Perennials","Partial Sun, Shade","Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/DragonWingRedBegonia.jpeg"),
  ("Bougainvillea","California Gold","Climbers, Shrubs ","Full Sun","Low","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/CaliforniaGold.jpeg"),
  ("Calibrachoa","Superbells","Annuals, Perennials","Full Sun","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Superbells.jpeg"),
  ("Cardiocrinum giganteum","Giant Himalayan Lily","Bulbs, Perennials","Partial Sun","Average","High","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/GiantHimalayanLily.jpeg"),
  ("Citrus × aurantiifolia","Key Lime","Fruit, Shrubs, Trees","Full Sun","Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/KeyLime.jpeg"),
  ("Citrus clementina","Clementine","Fruit, Trees","Full Sun","Average","Average","Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Clementine.jpeg"),
  ("Dahlia","Arabian Night","Bulbs, Perennials","Full Sun","Average","Average","Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/ArabianNight.jpeg"),
  ("Epiphyllum oxypetalum","Queen of the Night","Cactus - Succulents","Full Sun, Partial Sun","Average","Average","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/QueenoftheNight.jpeg"),
  ("Eschscholzia californica","California Poppy","Annuals, Perennials","Full Sun","Low, Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/CaliforniaPoppy.jpeg"),
  ("Helichrysum italicum","Curry Plant","Herbs, Perennials, Shrubs","Full Sun","Low","Low","Chalk, Loam","https://greenwayplants.s3.amazonaws.com/CurryPlant.jpeg"),
  ("Hylocereus undatus","Dragon Fruit","Cactus - Succulents, Fruit","Full Sun, Partial Sun","Average","Average","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/DragonFruit.jpeg"),
  ("Impatiens","Divine Violet","Annuals, Perennials","Full Sun, Partial Sun, Shade","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/DivineViolet.jpeg"),
  ("Jasminum polyanthum","Pink Jasmine","Climbers, Shrubs","Full Sun, Partial Sun","Low, Average","Low","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/PinkJasmine.jpeg"),
  ("Lathyrus odoratus 'Promise'","Sweet Pea","Annuals, Climbers","Full Sun, Partial Sun","Low, Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/SweetPea.jpeg"),
  ("Linum usitatissimum","Common Flax","Annuals, Herbs","Full Sun","Average","Average","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/CommonFlax.jpg"),
  ("Lonicera sempervirens","Trumpet Honeysuckle","Climbers","Full Sun, Partial Sun","Low, Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/TrumpetHoneysuckle.jpeg"),
  ("Opuntia robusta","Silver Dollar Prickly Pear","Cactus - Succulents","Full Sun, Partial Sun","Low","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/SilverDollarPricklyPear.jpeg"),
  ("Prunus dulcis","Almond","Fruit, Shrubs, Trees","Full Sun","Low, Average","Low","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Almond.jpeg"),
  ("Rosa English Rose","Charlotte","Rose","Full Sun, Partial Sun","Average","Average","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/Charlotte.jpeg"),
  ("Rosa Ground Cover Rose","Flower Carpet Scarlet","Rose","Full Sun, Partial Sun","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/FlowerCarpetScarlet.jpeg"),
  ("Rosa Hybrid Tea Rose","Apricot Candy","Rose","Full Sun","Average","Average","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/ApricotCandy.jpeg"),
  ("Rosa Hybrid Tea Rose","Blue Moon","Rose","Full Sun","Average","Average","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/BlueMoon.jpeg"),
  ("Rosa Hybrid Tea Rose","Mister Lincoln","Rose","Full Sun","Average","Average","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/MisterLincoln.jpeg"),
  ("Rubus deliciosus","Delicious Raspberry","Climbers, Shrubs","Full Sun, Partial Sun","Low, Average","Low","Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/DeliciousRaspberry.jpeg"),
  ("Rubus occidentalis","Black Raspberry","Fruit, Shrubs","Full Sun, Partial Sun","Average","Average","Loam, Sand","https://greenwayplants.s3.amazonaws.com/BlackRaspberry.jpeg"),
  ("Rudbeckia fulgida","Black-Eyed Susan","Perennials","Full Sun, Partial Sun","Average","Low","Chalk, Clay, Loam","https://greenwayplants.s3.amazonaws.com/BlackEyedSusan.jpeg"),
  ("Satureja hortensis","Summer Savory","Annuals, Herbs","Full Sun","Low, Average","Low","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/SummerSavory.jpeg"),
  ("Thelypteris noveboracensis","New York Fern","Fern","Partial Sun, Shade","Average","Low","Chalk, Clay, Loam, Sand","https://greenwayplants.s3.amazonaws.com/NewYorkFern.jpeg"),
  ("Thymus vulgaris","Common Thyme","Herbs, Shrubs","Full Sun","Low","Low","Chalk, Loam, Sand","https://greenwayplants.s3.amazonaws.com/CommonThyme.jpeg"),
  ("Tulbaghia violacea","Society Garlic","Herbs, Perennials","Full Sun","Average","Low","Loam, Sand","https://greenwayplants.s3.amazonaws.com/SocietyGarlic.jpeg"),
  ("Zantedeschia","Garnet Glow Calla Lily","Bulbs","Full Sun, Partial Sun","Average, High","Low","Clay, Loam","https://greenwayplants.s3.amazonaws.com/GarnetGlowCallaLily.jpeg")
