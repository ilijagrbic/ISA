/* Insert za korisnike */
INSERT INTO korisnici VALUES (0,'true', 0, 'Novi Sad', 'andjelovskiolja@gmail.com', 'true', 'Olja', 'olja', '0611626739', 'REG_USER', 'Andjelovski', '111');
INSERT INTO korisnici VALUES (1,'true', 0, 'Futog', 'schhhhhtef@gmail.com', 'true', 'Stefan', 'stefan', '0611626739', 'REG_USER', 'Tulac', '222');
INSERT INTO korisnici VALUES (2, 'true', 0, 'Novi Sad', 'ilijagrb.ns@gmail.com', 'true', 'Ilija', 'ilija', '06116267389', 'CINEMA_THEATRE_ADMIN', 'Grbic', '333');
INSERT INTO korisnici VALUES (3, 'true', 0, 'Beograd', 'milos@gmail.com', 'true',  'Milos', 'milos', '0637896536', 'REG_USER', 'Milosevic', '444');
INSERT INTO korisnici VALUES (4, 'true', 0, 'Kragujevac', 'tanja@gmail.com', 'true',  'Tanja', 'tanja', '0638526536', 'REG_USER', 'Milic', '555');
INSERT INTO korisnici VALUES (5, 'true', 0, 'Nis', 'milan@gmail.com', 'true',  'Milan', 'milan', '0637855536', 'REG_USER', 'Matic', '666');
INSERT INTO korisnici VALUES (6, 'true', 0, 'Pancevo', 'matija@gmail.com', 'true',  'Matija', 'matija', '0657896666', 'REG_USER', 'Matija', '777');
INSERT INTO korisnici VALUES (7, 'true', 0, 'Beograd', 'uros@gmail.com', 'true',  'Uros', 'uros', '0637896536', 'REG_USER', 'Urosevic', '888');
INSERT INTO korisnici VALUES (8, 'true', 0, 'Beograd', 'marko@gmail.com', 'true',  'Marko', 'marko', '0652896536', 'REG_USER', 'Mitic', '999');
INSERT INTO korisnici VALUES (9, 'true', 0, 'Novi Sad', 'sima@gmail.com', 'true',  'Sima', 'sima', '0666696536', 'REG_USER', 'Simic', '101010');
INSERT INTO korisnici VALUES (10, 'true', 0, 'Subotica', 'nikola@gmail.com', 'true',  'Nikola', 'nikola', '0637896536', 'FAN_ZONE_ADMIN', 'Nikolic', '111111');
INSERT INTO korisnici VALUES (11, 'true', 0, 'Novi Sad', 'dragana@gmail.com', 'true',  'Dragana', 'dragana', '0637896536', 'CINEMA_THEATRE_ADMIN', 'Dragic', '121212');
INSERT INTO korisnici VALUES (12, 'true', 0, 'Novi Sad', 'kosta@gmail.com', 'true',  'Kosta', 'kosta', '0637896536', 'SYSTEM_ADMIN', 'Kostic', '131313');

/* Insert za invite */
INSERT INTO invite VALUES (0, 'true', 1, 0);
INSERT INTO invite VALUES (1, 'true', 2, 0);
INSERT INTO invite VALUES (2, 'true', 2, 1);
INSERT INTO invite VALUES (3, 'false', 3, 0);
INSERT INTO invite VALUES (4, 'false', 4, 0);
INSERT INTO invite VALUES (5, 'false', 5, 0);

/* Dodavanje bioskopa */
INSERT INTO repertoire (id, bioskop_id) values (1, null);
INSERT INTO bioskop_pozoriste (id, address, bronze_sale, bronze_treshold, description, gmaps_url, gold_sale, gold_treshold, name, silver_sale, silver_treshold, type, repertoar) VALUES 
							  (1, 'Bulevar neki', 50, 10, 'Ovo je test bioskop. Opis nije bitan al samo kucam nesto da ima', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2809.1092174864743!2d19.83506441555019!3d45.245584179099005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1015b93d3229%3A0xe7b7f1b2bf36ec57!2z0JvQsNGB0LvQsCDQk9Cw0LvQsCAyMywg0J3QvtCy0Lgg0KHQsNC0IDIxMDAw!5e0!3m2!1ssr!2srs!4v1523445804304', 
							  100, 20, 'Arena cineplex', 150, 30, 'CINNEMA', 1);
update repertoire set bioskop_id=1 where id=1;

INSERT INTO repertoire (id, bioskop_id) values (2, null);
INSERT INTO bioskop_pozoriste (id, address, bronze_sale, bronze_treshold, description, gmaps_url, gold_sale, gold_treshold, name, silver_sale, silver_treshold, type, repertoar) VALUES 
							  (2, 'Bulevar neki', 50, 10, 'Ovo je test bioskop. Opis nije bitan al samo kucam nesto da ima', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2809.1092174864743!2d19.83506441555019!3d45.245584179099005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1015b93d3229%3A0xe7b7f1b2bf36ec57!2z0JvQsNGB0LvQsCDQk9Cw0LvQsCAyMywg0J3QvtCy0Lgg0KHQsNC0IDIxMDAw!5e0!3m2!1ssr!2srs!4v1523445804304', 
							  100, 20, 'Arena cineplex', 150, 30, 'CINNEMA', 1);
update repertoire set bioskop_id=2 where id=2;

INSERT INTO repertoire (id, bioskop_id) values (3, null);
INSERT INTO bioskop_pozoriste (id, address, bronze_sale, bronze_treshold, description, gmaps_url, gold_sale, gold_treshold, name, silver_sale, silver_treshold, type, repertoar) VALUES 
							  (3, 'Bulevar neki', 50, 10, 'Ovo je test bioskop. Opis nije bitan al samo kucam nesto da ima', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2809.1092174864743!2d19.83506441555019!3d45.245584179099005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1015b93d3229%3A0xe7b7f1b2bf36ec57!2z0JvQsNGB0LvQsCDQk9Cw0LvQsCAyMywg0J3QvtCy0Lgg0KHQsNC0IDIxMDAw!5e0!3m2!1ssr!2srs!4v1523445804304', 
							  100, 20, 'Arena cineplex', 150, 30, 'CINNEMA', 1);
update repertoire set bioskop_id=3 where id=3;

/* Setovanje adminu bioskopa */
INSERT INTO korisnici_bioskopi(user_id, bioskopi_id) values(2,1);

/* Dodavanje filmova*/
INSERT INTO movie_show (id, avg_review, description, director, duration, genre, image, name, price, repertoar, type) values 
(1, 0, 'film neki', 'reziser neki', 150, 'Akcioni', '7ce6ee93-3636-4e07-b08b-722c706a1e7a', 'Film', 400, 1, 'MOVIE');

INSERT INTO movie_show (id, avg_review, description, director, duration, genre, image, name, price, repertoar, type) values 
(2, 0, 'film neki', 'reziser neki', 150, 'Akcioni', '7ce6ee93-3636-4e07-b08b-722c706a1e7a', 'Film', 400, 1, 'MOVIE');

INSERT INTO movie_show (id, avg_review, description, director, duration, genre, image, name, price, repertoar, type) values 
(3, 0, 'film neki', 'reziser neki', 150, 'Akcioni', '7ce6ee93-3636-4e07-b08b-722c706a1e7a', 'Film', 400, 1, 'MOVIE');




INSERT INTO sala values(1,15151,'bROJ NAZIV', 15151,1);

INSERT INTO projekcija VALUES (1, 1200, '2038-01-19 03:14:07', 1, 1);



