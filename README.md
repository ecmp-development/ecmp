[![ECMP API Pipeline](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-api-pipeline.yml/badge.svg?branch=main)](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-api-pipeline.yml) [![ECMP UI Pipeline](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-ui-pipeline.yml/badge.svg?branch=main)](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-ui-pipeline.yml)

![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)

# ECMP | E-Commerce Max Platform
ECMP is a lightweight, but also modern Online-Shop, created by @SkyZ18 and @maxthnd. ECMP offers Customers, to create their own shop and sell their things with little to no effort.
Customers have a nice experience buying their Products, because of the simple but modern layout and design. 

## Setup | DEV-Environment
To Setup ECMP on your local Maschine, clone this Repository into a Folder of your Choice via ```git clone https://github.com/ecmp-development/ecmp.git```

Then you need to create the Docker-Containers. Before that, you need to run ```mvn clean && install``` to create the package.

## Docker-Deployment

To now Deploy the application in Docker, you need to run ```docker-compose up``` in the root folder of ECMP. Now go to **http://localhost:4200/** to see the Frontend of ECMP.
