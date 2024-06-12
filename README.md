[![ECMP API Pipeline](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-api-pipeline.yml/badge.svg?branch=main)](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-api-pipeline.yml) [![ECMP UI Pipeline](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-ui-pipeline.yml/badge.svg?branch=main)](https://github.com/ecmp-development/ecmp/actions/workflows/ecmp-ui-pipeline.yml)

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

# ECMP | E-Commerce Max Platform
ECMP is a lightweight, but also modern Online-Shop, created by @SkyZ18 and @maxthnd. ECMP offers Customers, to create their own shop and sell their things with little to no effort.
Customers have a nice experience buying their Products, because of the simple but modern layout and design. 

## Setup | DEV-Environment
To Setup ECMP on your local Maschine, clone this Repository into a Folder of your Choice via ```git clone https://github.com/ecmp-development/ecmp.git```

Then you need to create the Docker-Containers. Before that, you need to run ```mvn clean && install``` to create the package.

## Docker-Deployment

To now Deploy the application in Docker, you need to run ```docker-compose up``` in the root folder of ECMP. Now go to **http://localhost:4200/** to see the Frontend of ECMP.
