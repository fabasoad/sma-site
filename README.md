# DEPRECATED

This project is no longer supported and has been archived.

# Web site for "Southern Maritime Agency"

![GitHub release](https://img.shields.io/github/v/release/fabasoad/sma-site?include_prereleases) ![Security Tests](https://github.com/fabasoad/sma-site/workflows/Security%20Tests/badge.svg) ![YAML Lint](https://github.com/fabasoad/sma-site/workflows/YAML%20Lint/badge.svg) [![Known Vulnerabilities](https://snyk.io/test/github/fabasoad/sma-site/badge.svg)](https://snyk.io/test/github/fabasoad/sma-site) [![Total alerts](https://img.shields.io/lgtm/alerts/g/fabasoad/sma-site.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/fabasoad/sma-site/alerts/) [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/fabasoad/sma-site.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/fabasoad/sma-site/context:java) [![Language grade: JavaScript](https://img.shields.io/lgtm/grade/javascript/g/fabasoad/sma-site.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/fabasoad/sma-site/context:javascript) [![Maintainability](https://api.codeclimate.com/v1/badges/35955772b835d953d79d/maintainability)](https://codeclimate.com/github/fabasoad/sma-site/maintainability)

## Description

A web site for "Southern Maritime Agency" company.

## Requirements
### Front-end tools
  - [Gulp](http://gulpjs.com/)
  - [Node JS](http://nodejs.ru/)
  - [Npm](https://www.npmjs.com/)
### Back-end tools
  - [Maven](https://maven.apache.org/)
  - [Java](https://jdk.java.net/15/)
  - [Jetty](http://www.eclipse.org/jetty/)
### Database
  - [SQLite](https://www.sqlite.org/)
  - [MySQL](https://www.mysql.com/)

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
- There are two ways of running application
    - Using **IDEA Jetty Runner** plugin
        - Go to **File->Settings->Plugins** and be sure that **IDEA Jetty Runner** plugin is installed
        - Run **Jetty** configuration from Intellij IDEA.
    - Using **Maven Jetty Runner** plugin
        - Run **jetty-run** configuration from Intellij IDEA.
- Open http://127.0.0.1:8080/ in browser.
