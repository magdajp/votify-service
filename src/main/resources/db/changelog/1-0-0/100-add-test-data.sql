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