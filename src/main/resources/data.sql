INSERT INTO franchise (name, description) VALUES ('Lord of the Rings', 'The Lord of the Rings is the saga of a group
of sometimes reluctant heroes who set forth to save their world from consummate evil.');

INSERT INTO franchise (name, description) VALUES ('Harry Potter', 'Harry Potter is a series following the life of Harry Potter,
a boy who discovers he is the son of wizards and will attend Hogwarts.');

INSERT INTO franchise (name, description) VALUES ('The Twilight Saga', 'The Twilight Saga is a young adult adventure-romance
about a teenage girl who falls in love with a vampire.');


INSERT INTO movie (title, genre, release_year, director, franchise_id, picture, trailer)
VALUES ('The Two Towers', 'Fantasy', 2002, 'Peter Jackson', 1, 'https://artofthemovies.co.uk/products/lord-of-the-rings-the-two-towers-2002-ds', 'https://www.youtube.com/watch?v=LbfMDwc4azU');

INSERT INTO movie (title, genre, release_year, director, franchise_id, picture, trailer)
VALUES ('The Prisoner of Azkaban', 'Fantasy', 2004, 'Alfonso Cuarón', 2, 'https://artofthemovies.co.uk/products/harry-potter-and-the-prisoner-of-azkaban-2004-ds-os-highgloss-01', 'https://www.youtube.com/results?search_query=prizoner+of+azkaban+trailer');

INSERT INTO movie (title, genre, release_year, director, franchise_id, picture, trailer)
VALUES ('New Moon', 'Romance', 2009, 'Chris Weitz', 3, 'https://www.joblo.com/tag/new-moon/posters/#posters-2', 'https://www.youtube.com/watch?v=q58iQSHhZGg');


INSERT INTO character (name, alias, gender, picture_url) VALUES ('Aragorn', 'Strider', 'Male', 'https://static.wikia.nocookie.net/lotr/images/b/b6/Aragorn_profile.jpg/revision/latest?cb=20170121121423');
INSERT INTO character (name, alias, gender, picture_url) VALUES ('Gandalf', 'Olórin', 'Male', 'https://static.wikia.nocookie.net/lotr/images/e/e7/Gandalf_the_Grey.jpg/revision/latest?cb=20121110131754');

INSERT INTO character (name, alias, gender, picture_url) VALUES ('Sirius Black', 'Padfoot', 'Male', 'https://static.wikia.nocookie.net/harrypotter/images/b/bc/OOTP_promo_front_closeup_Sirius_Black.jpg/revision/latest?cb=20150918055024');
INSERT INTO character (name, alias, gender, picture_url) VALUES ('Minerva McGonagall', null, 'Female', 'https://static.wikia.nocookie.net/harrypotter/images/6/65/ProfessorMcGonagall-HBP.jpg/revision/latest?cb=20100612114856');

INSERT INTO character (name, alias, gender, picture_url) VALUES ('Isabella Swan', 'Bella', 'Female', 'https://static.wikia.nocookie.net/twilightsaga/images/a/a4/Bella-306318_429619423747956_93621998_n.jpg/revision/latest?cb=20130824013830');
INSERT INTO character (name, alias, gender, picture_url) VALUES ('Edward Cullen', 'Edward Masen', 'Male', 'https://static.wikia.nocookie.net/twilightsaga/images/8/8a/Edward-376194_429619737081258_1836140990_n.jpg/revision/latest?cb=20120728050624');


INSERT INTO movie_character (movie_id, character_id) VALUES (1, 1);
INSERT INTO movie_character (movie_id, character_id) VALUES (1, 2);
INSERT INTO movie_character (movie_id, character_id) VALUES (2, 3);
INSERT INTO movie_character (movie_id, character_id) VALUES (2, 4);
INSERT INTO movie_character (movie_id, character_id) VALUES (3, 5);
INSERT INTO movie_character (movie_id, character_id) VALUES (3, 6);