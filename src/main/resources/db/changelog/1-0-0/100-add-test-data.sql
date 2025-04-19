INSERT INTO users(id, email, first_name, last_name)
VALUES ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad111', 'peter.griffin@quahog.com', 'Peter', 'Griffin'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad222', 'brian.griffin@quahog.com', 'Brian', 'Griffin'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad333', 'peter.porker@luney.com', 'Peter', 'Porker'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'han.solo@starwars.com', 'Han', 'Solo'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'eric.cartman@southpark.com', 'Eric', 'Cartman'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad666', 'rick.sanchez@dimension4.com', 'Rick', 'Sanchez'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'morty.smith@dimension4.com', 'Morty', 'Smith'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'shrek@swamp.com', 'Shrek', 'Ogre'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad999', 'donkey@swamp.com', 'Donkey', 'TheDonkey'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adaaa', 'mario.bros@nintendo.com', 'Mario', 'Bros'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adbbb', 'luigi.bros@nintendo.com', 'Luigi', 'Bros'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adccc', 'bob.sponge@bikinibottom.com', 'SpongeBob', 'SquarePants'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adddd', 'patrick.star@bikinibottom.com', 'Patrick', 'Star'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adeee', 'thomas.shelby@peakyblinders.com', 'Thomas', 'Shelby'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adfff', 'gru@despicable.me', 'Felonius', 'Gru'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ae999', 'arthur.fleck@joker.com', 'Arthur', 'Fleck'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad000', 'walter.white@breakingbad.com', 'Walter', 'White'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adabc', 'jesse.pinkman@breakingbad.com', 'Jesse', 'Pinkman'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad123', 'baby.yoda@mandalorian.com', 'Grogu', 'BabyYoda'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad321', 'goku@dragonball.com', 'Son', 'Goku');

INSERT INTO housing_communities(id, name, location, owner_id)
VALUES ('4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal Oporów', 'Wroclaw', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad111'),
       ('4c2d099b-d134-48fa-b054-4814d2f1c222', 'Psie Pole', 'Wroclaw', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad222'),
       ('4c2d099b-d134-48fa-b054-4814d2f1c333', 'Klecina', 'Wroclaw', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad333');

INSERT INTO residents(user_id, housing_community_id)
VALUES ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad666', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad999', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adaaa', '4c2d099b-d134-48fa-b054-4814d2f1c111'),

       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adbbb', '4c2d099b-d134-48fa-b054-4814d2f1c222'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adccc', '4c2d099b-d134-48fa-b054-4814d2f1c222'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adddd', '4c2d099b-d134-48fa-b054-4814d2f1c222'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adeee', '4c2d099b-d134-48fa-b054-4814d2f1c222'),

       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adfff', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ae999', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad000', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923adabc', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad123', '4c2d099b-d134-48fa-b054-4814d2f1c111'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad321', '4c2d099b-d134-48fa-b054-4814d2f1c111');

INSERT INTO resolutions(id, housing_community_id, title, content, deadline, quorum, deleted)
VALUES  ('c991e197-d918-4f02-bd00-9a66c69d1555', '4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal - Uchwala numer 1', 'Tresc uchwaly numer 1', '2025-01-10 23:59:00', 3, FALSE),
        ('c991e197-d918-4f02-bd00-9a66c69d1666', '4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal - Uchwala numer 2', 'Tresc uchwaly numer 2', '2025-02-10 23:59:00', 3, FALSE),
        ('c991e197-d918-4f02-bd00-9a66c69d1777', '4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal - Uchwala numer 3', 'Tresc uchwaly numer 3', '2025-03-30 23:59:00', 3, FALSE),
        ('c991e197-d918-4f02-bd00-9a66c69d1888', '4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal - Uchwala numer 4', 'Tresc uchwaly numer 4', '2026-07-30 23:59:00', 3, TRUE),
        ('c991e197-d918-4f02-bd00-9a66c69d1444', '4c2d099b-d134-48fa-b054-4814d2f1c111', 'Atal - Uchwala numer 5', 'Tresc uchwaly numer 5', '2026-07-30 23:59:00', 3, FALSE),
        ('c991e197-d918-4f02-bd00-9a66c69d1999', '4c2d099b-d134-48fa-b054-4814d2f1c333', 'Psie Pole - Uchwala numer 1', 'Tresc uchwaly numer 1', '2025-03-10 23:59:00', 5, FALSE),
        ('c991e197-d918-4f02-bd00-9a66c69d1000', '4c2d099b-d134-48fa-b054-4814d2f1c333', 'Psie Pole - Uchwala numer 2', 'Tresc uchwaly numer 2', '2025-06-30 23:59:00', 5, FALSE);

INSERT INTO votes(resolution_id, resident_id, vote)
VALUES ('c991e197-d918-4f02-bd00-9a66c69d1555', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1555', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1555', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad666', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1555', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1555', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'AGAINST'),

       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad666', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad999', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1666', 'c708ee2a-5b19-4cf8-ab3d-bbbc923adaaa', 'AGAINST'),

       ('c991e197-d918-4f02-bd00-9a66c69d1777', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1777', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1777', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1777', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'AGAINST'),

       ('c991e197-d918-4f02-bd00-9a66c69d1888', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1888', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1888', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1888', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'IN_FAVOR'),


       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad666', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad777', 'IN_FAVOR'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad888', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923ad999', 'AGAINST'),
       ('c991e197-d918-4f02-bd00-9a66c69d1444', 'c708ee2a-5b19-4cf8-ab3d-bbbc923adaaa', 'AGAINST');