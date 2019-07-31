# Facebook-api-micro-service

Micro Service intended to provide a user name and profile photo by facebook ID.

## Requirements

* Java 8 or greater
* Gradle

## Configuration

Edit the file `...\src\main\resources\application.properties` and change 
* **api.accessToken**: Any APP Access Token
* **api.facebookApiURL**: Facebook API URL

## Running the project

- Console command: `gradle bootRun`.
- Open:
 * [http://localhost:8080/api/name/4](http://localhost:8080/api/name/4) (where 4 - is a facebookId)
 * [http://localhost:8080/api/photo/4](http://localhost:8080/api/photo/4) (where 4 - is a facebookId)
