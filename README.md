# Web site for "Southern Maritime Agency"

## Description

A web site for "Southern Maritime Agency" company.

## Requirements
### Front-end tools
  - [Bower](https://bower.io/): 1.8.0
  - [Gulp](http://gulpjs.com/): 3.9.1
  - [Node JS](http://nodejs.ru/): 7.1.0
  - [Npm](https://www.npmjs.com/): 3.10.10
### Back-end tools
  - [Maven](https://maven.apache.org/): 3.3.9
  - [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html): 8+
  - [Jetty](http://www.eclipse.org/jetty/): 9.3.14
### Database
  - [SQLite](https://www.sqlite.org/): 3.15.0
  - [MySQL](https://www.mysql.com/): 5.7.18

You can get Jetty and Maven from the **lib** directory (you can find details below).

## Installation

### Using Intellij IDEA
- Open **sma-site/pom.xml** as a project
- Go to **File->Project Structure->Project Settings->Project** and be sure that **Project SDK** and **Project language level** options are defined to Java 8 or higher
- `You can skip this option if you're using Intellij IDEA 2016 or higher` Unpack **apache-maven-\*-bin.zip** archive from **sma-site/lib** folder to any place you want (e.g. **sma-site/../Tools**)
- `You can skip this option if you're using Intellij IDEA 2016 or higher` Go to **File->Settings->Build, Execution, Deployment->Build Tools->Maven** and set **Maven home directory** to unpacked Maven folder
- Copy **sma-site/lib/runConfigurations** folder to **sma-site/.idea**
- Run **frontend-install** configuration or the command below on a main **pom.xml**
```sh
$ mvn install -P frontend-install
```
- Run **frontend-build** configuration or the command below on a main **pom.xml**
```sh
$ mvn install -P frontend-build
```

In the future you can just run **frontend-build** configuration or the command below to build the project.
```sh
$ mvn clean install -P frontend-build
```
You can just run the command below if you don't want to build frontend part (not recommended).
```sh
$ mvn clean install
```

## DB installation
Run **Setup** configuration to install DB.
> Edit **Setup** configuration and change second program argument to any path in case you want to change default deploy path.

## Run application
- There are 2 ways of running application
    - Using **IDEA Jetty Runner** plugin
        - Go to **File->Settings->Plugins** and be sure that **IDEA Jetty Runner** plugin is installed
        - Run **Jetty** configuration from Intellij IDEA.
    - Using **Maven Jetty Runner** plugin
        - Run **jetty-run** configuration from Intellij IDEA.
- Open http://127.0.0.1:8080/ in browser.
