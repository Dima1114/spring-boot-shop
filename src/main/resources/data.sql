

INSERT INTO categories(name, description) VALUES ('Snowboards', '')
INSERT INTO categories(name, description) VALUES ('Bindings', '')
INSERT INTO categories(name, description) VALUES ('Boots', '')
INSERT INTO categories(name, description) VALUES ('Jackets', '')
INSERT INTO categories(name, description) VALUES ('Pants', '')
INSERT INTO categories(name, description) VALUES ('Masks', '')

/*Roles*/
INSERT INTO roles (name) VALUES('admin')
INSERT INTO roles (name) VALUES('user')

/*Users*/
INSERT INTO users(username, password, first_name, last_name, email, address) VALUES ('user', '123', 'Homer', 'Simpson', 'homer.jay.simpson@gmail.com', 'Springfield')
INSERT INTO users(username, password, first_name, last_name) VALUES ('', '','Big','Bro')



/*size*/
/*Masks*/
INSERT INTO size(size, type) VALUES ('UniSize', 'UniSize')
/*Clothes*/
INSERT INTO size(size, type) VALUES ('S', 'Clothes')
INSERT INTO size(size, type) VALUES ('M', 'Clothes')
INSERT INTO size(size, type) VALUES ('L', 'Clothes')
INSERT INTO size(size, type) VALUES ('XL', 'Clothes')
INSERT INTO size(size, type) VALUES ('XXL', 'Clothes')
/*Bindings*/
INSERT INTO size(size, type) VALUES ('S/M', 'Bindings')
INSERT INTO size(size, type) VALUES ('M/L', 'Bindings')
INSERT INTO size(size, type) VALUES ('L/XL', 'Bindings')
/*Boots*/
INSERT INTO size(size, type) VALUES ('7.0', 'Boots')
INSERT INTO size(size, type) VALUES ('7.5', 'Boots')
INSERT INTO size(size, type) VALUES ('8.0', 'Boots')
INSERT INTO size(size, type) VALUES ('8.5', 'Boots')
INSERT INTO size(size, type) VALUES ('9.0', 'Boots')
INSERT INTO size(size, type) VALUES ('9.5', 'Boots')
INSERT INTO size(size, type) VALUES ('10.0', 'Boots')
INSERT INTO size(size, type) VALUES ('10.5', 'Boots')
INSERT INTO size(size, type) VALUES ('11.0', 'Boots')
INSERT INTO size(size, type) VALUES ('11.5', 'Boots')
INSERT INTO size(size, type) VALUES ('12.0', 'Boots')
/*Boards*/
INSERT INTO size(size, type) VALUES ('149', 'Snowboards')
INSERT INTO size(size, type) VALUES ('150', 'Snowboards')
INSERT INTO size(size, type) VALUES ('151', 'Snowboards')
INSERT INTO size(size, type) VALUES ('152', 'Snowboards')
INSERT INTO size(size, type) VALUES ('153', 'Snowboards')
INSERT INTO size(size, type) VALUES ('154', 'Snowboards')
INSERT INTO size(size, type) VALUES ('154W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('155', 'Snowboards')
INSERT INTO size(size, type) VALUES ('155W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('156', 'Snowboards')
INSERT INTO size(size, type) VALUES ('156W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('157', 'Snowboards')
INSERT INTO size(size, type) VALUES ('157W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('158', 'Snowboards')
INSERT INTO size(size, type) VALUES ('159', 'Snowboards')
INSERT INTO size(size, type) VALUES ('159W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('160', 'Snowboards')
INSERT INTO size(size, type) VALUES ('160W', 'Snowboards')
INSERT INTO size(size, type) VALUES ('162', 'Snowboards')

/*brands*/
INSERT INTO brands(name) VALUES ('Capita')
INSERT INTO brands(name) VALUES ('DC')
INSERT INTO brands(name) VALUES ('Flow')
INSERT INTO brands(name) VALUES ('LIB-TECH')
INSERT INTO brands(name) VALUES ('GNU')
INSERT INTO brands(name) VALUES ('Roxy')
INSERT INTO brands(name) VALUES ('UNION')
INSERT INTO brands(name) VALUES ('Deeluxe')
INSERT INTO brands(name) VALUES ('Quiksilver')

/*bindings*/
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow fuse gt hybrid black-red', 'description',250,2,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow fuse hybrid mustard', 'description',250,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow nexus hybrid black', 'description',240,2,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('GNU b real white', 'description',230,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('GNU cheeter green', 'description',250,2,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('GNU psych blue', 'description',220,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy classic white', 'description',220,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy rock it dash white', 'description',210,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy team white', 'description',240,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION contact camo', 'description',250,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION contact pro volt red', 'description',240,2,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION flite pro black', 'description',260,2,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION flite pro red', 'description',260,2,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION str white', 'description',270,2,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('UNION st white', 'description',250,2,5,'2018-01-01')

/*boots*/
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC karma white', 'description',290,3,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC mutiny black', 'description',260,3,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC phase black', 'description',250,3,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC scout black', 'description',270,3,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC scout black white', 'description',270,3,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC search silver', 'description',230,3,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC travis rice black-yellow', 'description',250,3,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Deeluxe empire tf-black', 'description',290,3,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Deeluxe ID 61 cf-black', 'description',290,3,3,'2018-01-01')

/*jackets*/
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC defy insignia blue', 'description',240,4,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC defy nautical blue', 'description',240,4,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC merchant chili pepper', 'description',250,4,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC servo insignia blue', 'description',230,4,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver tr ambition iguana', 'description',260,4,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy jetty neon cloud', 'description',220,4,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy wildlife pop snow', 'description',230,4,3,'2018-01-01')

/*masks*/
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Electric EG35 light blue jet-black', 'description',160,6,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver fenom pack vallarta blue', 'description',140,6,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver hubble tr marine iguana', 'description',130,6,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver QS-R grey camokazi', 'description',150,6,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver QS-R vallarta blue', 'description',150,6,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy hubble women white', 'description',120,6,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy isis magenta purple', 'description',130,6,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy popscreen neon grapefruit', 'description',140,6,5,'2018-01-01')

/*pants*/
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC ace burnt henna', 'description',220,5,2,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC asylum black', 'description',230,5,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC banshee tender shots', 'description',290,5,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC collective bib black', 'description',220,5,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC nomad black', 'description',250,5,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver estate kelly green', 'description',260,5,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver forest oak cub', 'description',260,5,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Quiksilver porter black', 'description',270,5,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Roxy backyard neon grapefruit', 'description',220,5,3,'2018-01-01')

/*boards*/
INSERT INTO items(name, description, price, category_id, rating, date, brand_id) VALUES ('Capita birds of a feather', 'description',400,1,5,'2018-01-01',1)
INSERT INTO items(name, description, price, category_id, rating, date, brand_id) VALUES ('Capita horrorscope', 'description',400,1,4,'2018-01-01',1)
INSERT INTO items(name, description, price, category_id, rating, date, brand_id) VALUES ('Capita space metal fantasy', 'description',450,1,5,'2018-01-01',1)
INSERT INTO items(name, description, price, category_id, rating, date, brand_id) VALUES ('Capita thunderstick', 'description',370,1,4,'2018-01-01',1)
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC biddy', 'description',370,1,2,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC focus', 'description',420,1,2,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC space-echo', 'description',340,1,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('DC telegraph', 'description',390,1,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow blackout', 'description',390,1,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow chill', 'description',390,1,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('Flow era', 'description',390,1,4,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('LIB-TECH attack banana hp-c2e', 'description',590,1,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('LIB-TECH skate-banana-blue', 'description',560,1,5,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('LIB-TECH skate banana stealth', 'description',560,1,3,'2018-01-01')
INSERT INTO items(name, description, price, category_id, rating, date) VALUES ('LIB-TECH t-rice hp-C2', 'description',590,1,3,'2018-01-01')


