# pronostics

## Requirements

* Mysql 5.5 or MariaDB 5.5
* Maven
* Tomcat 8.5

## Installation

* Install Mysql or MariaDB
* Install Tomcat
* Update src/main/resource/spring/application-config.xml
  * Choose mysql configuration or mariadb configuration (comment/uncomment bean bloc)
  * set your mysql url, database name, user and password

## Building

1. At root of the project, run: `mvn clean install`
2. Copy and rename generated war file into webapps of your tomcat webserver: 
`mv dist/pronostics-{version}.war /path/to/apache-tomcat/webapps/pronostics.war`
3. Start Tomcat
