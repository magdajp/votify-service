<!-- TOC -->
* [Deployment](#deployment)
* [Test data](#test-data)
  * [Communities](#communities)
  * [Users](#users)
<!-- TOC -->

# Deployment

1. Build the package: `mvn clean package -DskipTests`
2. Build Docker image `docker build -t votify-service .`

# Test data

The application has stubbed data:

## Communities

| Community Id                         | Name        | Location | Admin                    |
|--------------------------------------|-------------|----------|--------------------------|
| 4c2d099b-d134-48fa-b054-4814d2f1c111 | Atal Oporów | Wroclaw  | peter.griffin@quahog.com |
| 4c2d099b-d134-48fa-b054-4814d2f1c222 | Psie Pole   | Wroclaw  | brian.griffin@quahog.com |
| 4c2d099b-d134-48fa-b054-4814d2f1c333 | Klecina     | Wroclaw  | peter.porker@luney.com   |

## Users
| User id                              | Role     | Email                           | First name | Last name   | Community   |
|--------------------------------------|----------|---------------------------------|------------|-------------|-------------|
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad111 | ADMIN    | peter.griffin@quahog.com        | Peter      | Griffin     | Atal Oporów |     
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad222 | ADMIN    | brian.griffin@quahog.com        | Brian      | Griffin     | Psie Pole   |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad333 | ADMIN    | peter.porker@luney.com          | Peter      | Porker      | Klecina     |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad444 | RESIDENT | han.solo@starwars.com           | Han        | Solo        | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad555 | RESIDENT | eric.cartman@southpark.com      | Eric       | Cartman     | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad666 | RESIDENT | rick.sanchez@dimension4.com     | Rick       | Sanchez     | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad777 | RESIDENT | morty.smith@dimension4.com      | Morty      | Smith       | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad888 | RESIDENT | shrek.x@swamp.com               | Shrek      | Ogre        | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad999 | RESIDENT | donkey.x@swamp.com              | Donkey     | TheDonkey   | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adaaa | RESIDENT | mario.bros@nintendo.com         | Mario      | Bros        | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adbbb | RESIDENT | luigi.bros@nintendo.com         | Luigi      | Bros        | Psie Pole   |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adccc | RESIDENT | bob.sponge@bikinibottom.com     | SpongeBob  | SquarePants | Psie Pole   |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adddd | RESIDENT | patrick.star@bikinibottom.com   | Patrick    | Star        | Psie Pole   |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adeee | RESIDENT | thomas.shelby@peakyblinders.com | Thomas     | Shelby      | Psie Pole   |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adfff | RESIDENT | gru.x@despicable.me             | Felonius   | Gru         | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ae999 | RESIDENT | arthur.fleck@joker.com          | Arthur     | Fleck       | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad000 | RESIDENT | walter.white@breakingbad.com    | Walter     | White       | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923adabc | RESIDENT | jesse.pinkman@breakingbad.com   | Jesse      | Pinkman     | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad123 | RESIDENT | baby.yoda@mandalorian.com       | Grogu      | BabyYoda    | Atal Oporów |    
| c708ee2a-5b19-4cf8-ab3d-bbbc923ad321 | RESIDENT | goku.x@dragonball.com           | Son        | Goku        | Atal Oporów |    

Every user shares the same password. 