# GitHubTwitterAPIMashup

## Application

- https://github.com/VenueOne/full-stack-backend-code-challenge

## Technology

- Java Spring Boot application
- Maven for build management (using maven-wrapper)
- Mockito for unit testing

## Setup Environment

1. Install Java 8. Download and follow instructions: https://java.com/en/download/ 
1. it all works if `java -version` prints out the installed java version.

The application is developed using [IntelliJ IDE](https://www.jetbrains.com/idea/download/). If you are familiar with it you can use the IDE to import the project and execute. Otherwise follow the next section to run the application.

## Compile, Configure and Execute

You find instructions in this section to compile, configure and run. 

### Compile

```
# compile and execute unit tests
./mvnw clean install
```

### Configure the application

Adapt the placeholder for the `consumer_key` / `consumer_secret` in the file `resources\application-prod`:

```
app:
  twitterConfig:
    consumerKey: "<consumer_key>"
    consumerSecret: "<consumer_secret>"
``` 

You need to build the application again to have the new configuration ready in the `prod` profile.

### Running the application

```
#  program execution with activation of correct spring profile
java -jar -Dspring.profiles.active=prod ./target/github-twitter-api-mashup-0.0.1-SNAPSHOT.jar
```
