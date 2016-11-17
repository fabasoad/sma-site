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

You can get Jetty and Maven from the **lib** directory. Other libs are installed running command below on the **sma-site-webapp** module.
```sh
$ mvn install
```

## Installation

### Using Intellij IDEA
- Open **sma-site-root/pom.xml** as a project
- Run the command below on a main **pom.xml**
```sh
$ mvn install -P frontend-install
$ mvn install -P frontend-build
```
Instead of commands above alternatively you can do the following steps:
- Copy **sma-site-root/lib/runConfigurations** folder to **sma-site-root/.idea**
- Run **frontend-install** configuration
- Run **frontend-build** configuration

In the future you can just run the command below to build the project.
```sh
$ mvn clean install -P frontend-build
```
You can just run the command below if you don't want to build frontend part (not recommended).
```sh
$ mvn clean install
```
- Unpack **apache-maven-\*-bin.zip** and **jetty-distribution-\*.zip** archives to any place you want (e.g. **sma-site-root/../Tools**)
- Copy **sma-site-root/lib/runConfigurations** folder to **sma-site-root/.idea** if you still don't copy it
- Open **Jetty** configuration for edit
- Set **Application server** to unpacked Jetty folder
- Save and close configuration

## Run application

Run **Jetty** configuration from Intellij IDEA.