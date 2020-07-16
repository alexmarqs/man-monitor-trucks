# MAN MONITOR TRUCKS
[![Build Status](https://travis-ci.org/alexmarqs/man-monitor-trucks.svg?branch=master)](https://travis-ci.org/alexmarqs/man-monitor-trucks)

Man Monitor Trucks Web App (React + Spring Boot)

In this project the following technologies are used:

- [x] Spring Boot (thanks [Lombok](https://projectlombok.org/) to turn the development so much easier!) 
- [x] MongoDB 
- [x] Reactjs (using react hooks!) 

**System requirements**
- JDK 11
- Maven
- Node
- Docker

## API documentation 
This application uses Swagger 2. Access https://monitor-truck-api.herokuapp.com/api/swagger-ui.html to check the documentation.

## Local Development
For development purposes, I decided to containerize three services: backend, database and frontend. Let them communicate with each other ([docker-compose-dev](./docker-compose-dev.yml))! The [Dockerfile-dev](./monitor-truck-client/Dockerfile-dev) of frontend is responsible to run the app in the development mode, exposing the port 3000. The [Dockerfile-dev](./monitor-truck-api/Dockerfile-dev) of backend is responsible to run the generated artifact (.jar) from the spring boot project, exposing the ports 8080 and 5005 (debug port) with DEV spring profile enabled - if you want to have hot reload here I suggest to include [spring devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html).

For the database, I used the original mongodb docker image (the port 27017 is exposed). All the information from the mongo database is being mapped into a local "data" folder. For the database initialization, the folder “scripts” is mapped to “docker-entrypoint-initdb.d” in order to run the init scripts (user creation + dummy data insertion).

**Instructions** to start the development and run the web app locally:
  - (prepare frontend) Install all the package dependencies for the react app using the command “npm install“;
  - (prepare backend) Generate the artifact (.jar) from the spring boot app using the command “mvn clean package”;
  - (ready to go) Run docker compose command `docker-compose -f docker-compose-dev.yml up --build` in order to (re)build and start the services!

***Note:** If you prefer not to use docker for the backend during development, you can manually build and generate the executable file with `mvn clean package` and then run the maven spring boot plugin (`mvn spring-boot:run`) or directly the java command `java -jar <jar file location>`. Do not forget to set the required environment variables. You must be running an instance of MongoDB server.*

## CI/CD + Live Demo
For continuous integration / deployment, I decided to use [Travis CI](https://travis-ci.org). Since I am using only one git repository for frontend and backend, I configured two stages, one to run frontend tests and deploy the build on [Netlify](https://www.netlify.com/) and the other to run the backend tests (an embedded mongo database is used) and deploy the artifact on [Heroku](https://www.heroku.com/). [MongoDB Atlas](https://www.mongodb.com/cloud/atlas), a database as a service, is used to store all the data. For real world applications, I suggest a separate git repository for each project.

Check the live demo at https://monitor-truck-client.netlify.com/ !

***Note:** For test purposes I added to my database some data related with the license plate "LI-2" - If you want more please let me know.*

## Screenshots
**Desktop:**

<img src="/screenshots/screenshot_desktop.png" width="60%" height="60%">

**Tablet/Mobile:**

<img src="/screenshots/screenshot_tablet_mobile.png" width="50%" height="50%">




