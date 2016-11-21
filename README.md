# Web site for "Southern Maritime Agency"

## Description

A web site for "Southern Maritime Agency" company.

## Requirements

  - [Maven](https://maven.apache.org/): 3.3.9
  - [Bower](https://bower.io/): 1.8.0
  - [Gulp](http://gulpjs.com/): 3.9.1
  - [Node JS](http://nodejs.ru/): 7.1.0
  - [Npm](https://www.npmjs.com/): 3.10.10
  - [Jetty](http://www.eclipse.org/jetty/): 9.3.14

You can get Jetty and Maven from the **lib** directory (you can find details below).

## Installation

### Using Intellij IDEA
- Open **sma-site/pom.xml** as a project
- Go to **File->Settings->Plugins** and be sure that **IDEA Jetty Runner** plugin is installed
- Unpack **apache-maven-\*-bin.zip** archive from **sma-site/lib** folder to any place you want (e.g. **sma-site/../Tools**)
- Copy **sma-site/lib/runConfigurations** folder to **sma-site/.idea**
- Go to **File->Settings->Build, Execution, Deployment->Build Tools->Maven** and set **Maven home directory** to unpacked Maven folder
- Run **frontend-install** configuration or the command below on a main **pom.xml**
```sh
$ mvn install -P frontend-install
```
- Open **Terminal** and run the commands below
```sh
$ cd sma-site-webapp
$ npm install --save gulp-install
$ npm install --save gulp
$ npm install -g bower
$ npm install --save-dev gulp-uglify
$ npm install --save-dev gulp-babel
$ npm install --save-dev merge-stream
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

## Run application

Run **Jetty** configuration from Intellij IDEA.
