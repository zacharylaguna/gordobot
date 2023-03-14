///api///
unzip folder
install intellij
select pom.xml file when opening project in intellij
INSTALL DATABASE AND DOCKER FROM INSTRUCTIONS BELOW where it says ///database and docker containter///
run GordoAPIApplication.java from src/main/java/com.example.gordoapi (use intellij)
output is on http://localhost:8080 use the following commands in terminal for usage:
* curl -i -X GET http://localhost:8080/Recipe/createTest
* curl -i -X POST -H "Content-Type: application/json" -d '{"id":"6266c7fd3bca9b6083d8f10c","name":"test2","instructions":["step 1","step 2","step 3"],"dispenseList":[{"amount":1,"units":"mL"},{"amount":2,"units":"g"},{"amount":3,"units":"mL"}]}' http://localhost:8080/Recipe/create
* 

///database and docker container///
install docker from docker website (whichever version you want)
execute this command in terminal (/usr/local/bin/docker-compose location may be different for OS, i am using mac)
    * /usr/local/bin/docker-compose -f /Users/zachlaguna/Documents/College/csce483/gordo-api/docker-compose.yaml up -d
the docker instance is now running
navigate to http://localhost:8081 to examine database


///user interface///
unzip folder
To clear angular/cli
    * npm uninstall -g @angular/cli
    * npm cache clean (may have to use —force)
    * npm cache verify (if npm > 5)
npm install -g @angular/cli@9.1.5
navigate inside gordo-ui folder using cd
ng serve
output is on http://localhost:4200

(might need to use npm install --save-dev @angular-devkit/build-angular)