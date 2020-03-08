# MAN MONITOR TRUCKS
[![Build Status](https://travis-ci.org/alexmarqs/man-monitor-trucks.svg?branch=master)](https://travis-ci.org/alexmarqs/man-monitor-trucks)

Man Monitor Trucks Challenge Web App (React + Spring Boot)


This is a code challenge where I will use the following technologies:

- [x] Spring Boot (thanks [Lombok](https://projectlombok.org/) to turn the development so much easier!) 
- [x] MongoDB 
- [x] Reactjs (using react hooks!) 


### System requirements
- JDK 11
- Maven
- Node
- Docker

### API documentation 
This application uses Swagger 2. Access https://monitor-truck-api.herokuapp.com/api/swagger-ui.html to check the documentation.

### Local Development
For development purposes, I decided to containerize three services, the backend, database and the frontend. Let them communicate with each other ([docker-compose-dev](./docker-compose-dev.yml))! The [Dockerfile-dev](./monitor-truck-client/Dockerfile-dev) of frontend is responsible to run the server in dev mode, exposing the port 3000 - that’s the reason for the volume mapping presented in our docker-compose file for this service - we can have hot reload during our development. The [Dockerfile-dev](./monitor-truck-api/Dockerfile-dev) of backend is responsible to run the generated artifact (.jar) from our spring boot project, exposing the debug port 5005 with DEV spring profile enabled. If you want to have hot reload here I suggest to include [spring devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html).

For the database, I use the original mongodb docker image. All the mongo database information that will be (re)used is being mapped in our local “data” folder. For the database initialisation (when “data” folder does not exist) I mapped my folder “scripts” to “docker-entrypoint-initdb.d” in order to run the init scripts (user dev creation + dummy data insertion).

**Instructions** to start the development and run the web app locally:
  - (prepare frontend) Install all the package dependencies for the react app using the command “npm install“;
  - (prepare backend) Generate the artifact (.jar) from the spring boot app using the command “mvn clean package”;
  - (ready to go) Run docker compose command `docker-compose -f docker-compose-dev.yml up --build` in order to (re)build and start the services!

Note: *If you prefer not to use docker, you can manually build and generate the executable file with `mvn clean package` and then run the maven spring boot plugin (`mvn spring-boot:run`) or directly the java command `java -jar <jar file location>`. Do not forget to set the required environment variables. You must be running an instance of MongoDB server.*

### CI/CD + Live Demo
For continuous integration / deployment, I use the tool Travis CI. Since I am using only one git repository for frontend and backend, I configured two stages, one to run frontend tests and deploy the build on Netlify and the other to run the backend tests and deploy the artifact on Heroku. For the database, I use MongoDB Atlas. For real world applications, I suggest a separate git repository for each project.

Check the live demo at https://monitor-truck-client.netlify.com/ 

Note: *For test purposes I added to my database some data related with the license plate "LI-2" - If you want more please let me know.*






