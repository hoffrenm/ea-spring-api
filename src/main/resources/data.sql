INSERT INTO movie (title, genre, releaseYear, director)
VALUES ('The Two Towers', 'Fantasy', 2002, 'Peter Jackson');

INSERT INTO movie (title, genre, releaseYear, director)
VALUES ('The Prisoner of Azkaban', 'Fantasy', 2004, 'Alfonso Cuarón');

INSERT INTO movie (title, genre, releaseYear, director)
VALUES ('New Moon', 'Romance', 2009, 'Chris Weitz');


INSERT INTO character (name, alias, gender) VALUES ('Aragorn', 'Strider', 'Male');
INSERT INTO character (name, alias, gender) VALUES ('Gandalf', 'Olórin', 'Male');

INSERT INTO character (name, alias, gender) VALUES ('Sirius Black', 'Padfoot', 'Male');
INSERT INTO character (name, alias, gender) VALUES ('Minerva McGonagall', null, 'Female');

INSERT INTO character (name, alias, gender) VALUES ('Isabella Swan', 'Bella', 'Male');
INSERT INTO character (name, alias, gender) VALUES ('Edward Cullen', 'Edward Masen', 'Female');


INSERT INTO franchise (name, description) VALUES ('Lord of the Rings', 'The Lord of the Rings is the saga of a group
of sometimes reluctant heroes who set forth to save their world from consummate evil.');

INSERT INTO franchise (name, description) VALUES ('Harry Potter', 'Harry Potter is a series following the life of Harry Potter,
a boy who discovers he is the son of wizards and will attend Hogwarts'.);

INSERT INTO franchise (name, description) VALUES ('The Twilight Saga', 'The Twilight Saga is a young adult adventure-romance
about a teenage girl who falls in love with a vampire.');


INSERT INTO movie_character (movie_id, character_id) VALUES (1, 1);
INSERT INTO movie_character (movie_id, character_id) VALUES (1, 2);
INSERT INTO movie_character (movie_id, character_id) VALUES (2, 3);
INSERT INTO movie_character (movie_id, character_id) VALUES (2, 4);
INSERT INTO movie_character (movie_id, character_id) VALUES (3, 5);
INSERT INTO movie_character (movie_id, character_id) VALUES (3, 6);