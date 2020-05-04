# Warehouse System

This Java EE application is an API with a embebbed web interface to access the database. It help to control the output and input of products in a warehouse.

## Installation/Running

To run the applicaton is necessary to build it with maven:
```
mvn install
```
Then you need to deploy the .war package in an application server which supports Java EE 8.0.1. The server needs a datasource with JNDI named "java:/MySQLDS".

## Notes

This is an application for training and showing the use of Java EE so there is no caution for security and most likely It has bugs.