# Spring Clinic Service Application

[![Java CI with Maven](https://github.com/spring-petclinic/spring-framework-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-petclinic/spring-framework-petclinic/actions/workflows/maven-build.yml)

Spring Petclinic is a sample application that has been designed to show how the Spring Framework can be used to build simple but powerful database-oriented applications.
The fork named Spring Framework Petclinic maintains a version both with a plain old Spring Framework configuration and a 3-layer architecture (i.e. presentation --> service --> repository).

, this repo is a fork of the [spring-projects/ClinicService](**********link to service on git hub*********).
It allows the Spring community to maintain a Petclinic version with a plain old **Spring Framework configuration**
and with a **3-layer architecture** (i.e. [controller]presentation --> service --> repository).
you can with best regards add any explanation or conversation on [aggregate-oriented domain]([ link to pull). 


## Understanding the Spring Petclinic application with a few diagrams

[See the presentation here](http://fr.slideshare.net/AntoineRey/spring-framework-petclinic-sample-application) (2017 update)

## Running petclinic locally

### With Maven command line
```
git clone https://github.com/spring-petclinic/spring-framework-petclinic.git
cd spring-framework-petclinic
./mvnw jetty:run-war
# For Windows : ./mvnw.cmd jetty:run-war
```

### With Docker
```
docker run -p 8080:8080 springcommunity/spring-framework-petclinic
```

You can then access petclinic here: [http://localhost:8080/](http://localhost:8080/)

## In case you find a bug/suggested improvement for this service

Our issue tracker is available here: *******************issues****************


## Database configuration

In its default configuration, This Service uses an in-memory database (H2) which gets populated at startup with data.

A similar setups is provided for MySQL and PostgreSQL in case a persistent database configuration is needed.
To run locally using persistent database, it is needed to run with profile defined in main pom.xml file.

For MySQL database, it is needed to run with 'MySQL' profile defined in main pom.xml file.

```
./mvnw jetty:run-war -P MySQL
```

Before do this, would be good to check properties defined in MySQL profile inside pom.xml file.

```
<properties>
    <jpa.database>MYSQL</jpa.database>
    <jdbc.driverClassName>com.mysql.cj.jdbc.Driver</jdbc.driverClassName>
    <jdbc.url>jdbc:mysql://localhost:3306/clinicService?useUnicode=true</jdbc.url>
    <jdbc.username>petclinic</jdbc.username>
    <jdbc.password>petclinic</jdbc.password>
</properties>
```      

You could start MySQL locally with whatever installer works for your OS, or with docker:

```
docker run -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```

For PostgreSQL database, it is needed to run with 'PostgreSQL' profile defined in main pom.xml file.

```
./mvnw jetty:run-war -P PostgreSQL
```

Before do this, would be good to check properties defined in PostgreSQL profile inside pom.xml file.

```
<properties>
    <jpa.database>POSTGRESQL</jpa.database>
    <jdbc.driverClassName>org.postgresql.Driver</jdbc.driverClassName>
    <jdbc.url>jdbc:postgresql://localhost:5432/petclinic</jdbc.url>
    <jdbc.username>postgres</jdbc.username>
    <jdbc.password>petclinic</jdbc.password>
</properties>
```
You could also start PostgreSQL locally with whatever installer works for your OS, or with docker:

```
docker run --name postgres-petclinic -e POSTGRES_PASSWORD=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 -d postgres:9.6.0
```

## Persistence layer choice

The persistence layer have 3 available implementations: JPA (default), JDBC and Spring Data JPA.
The default JPA implementation could be changed by using a Spring profile: `jdbc`, `spring-data-jpa` and `jpa`.  
As an example, you may use the `-Dspring.profiles.active=jdbc` VM options to start the application with the JDBC implementation.

```
./mvnw jetty:run-war -Dspring.profiles.active=jdbc
```

## Compiling the CSS

There is a `petclinic.css` in `src/main/webapp/resources/resources/css`. 
It was generated from the `petclinic.scss` source, combined with the [Bootstrap](https://getbootstrap.com/) library.
If you make changes to the `scss`, or upgrade Bootstrap, you will need to re-compile the CSS resources
using the Maven profile "css", i.e. `./mvnw generate-resources -P css`.

## Working with Petclinic in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 17 or newer (full JDK not a JRE)
* Maven 3.5+ (https://maven.apache.org/install.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Jetty 11.0+ or Tomcat 10+
* Your prefered IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in Help -> About dialog. If m2e is not there, just follow the install process here: http://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA


### Steps:

1) On the command line
```
git clone *****************************our code link from github******************
```

2) Inside intellij or your prefered IDE
```
File -> Import -> Maven -> Existing Maven project
```
Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the CSS.
Configure a Jetty or a Tomcat web container then deploy the `spring-petclinic.war` file.

3) Inside IntelliJ IDEA

In the main menu, select `File > Open` and select the  [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources` 
or right click on the `spring-clinic` project then `Maven -> Generates sources and Update Folders`.

Go to the `Run -> Edit Configuration` then configure a Tomcat or a Jetty web container. Deploy the `spring-petclinic.war` file.
Run the application by clicking on the `Run` icon.


4) Navigate to Clinic on browser

Visit [http://localhost:8080](http://localhost:8080) in your browser.


## Working with in IntelliJ IDEA

### prerequisites
The following items should be installed in your system:


## Publishing a Docker image

This application uses [Google Jib]([https://github.com/GoogleContainerTools/jib) to build an optimized Docker image
into the [Docker Hub](https://cloud.docker.com/u/springcommunity/repository/docker/springcommunity/spring-framework-petclinic/)
repository.
The [pom.xml](pom.xml) has been configured to publish the image with a the `springcommunity/spring-framework-petclinic` image name.

Jib containerizes this WAR project by using the [distroless Jetty](https://github.com/GoogleContainerTools/distroless/tree/master/java/jetty) as a base image.

Build and push the container image of Petclinic to the Docker Hub registry:
```
mvn jib:build
```

# Contributing

The [issue tracker](/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.




