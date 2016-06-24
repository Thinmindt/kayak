#!/bin/bash
javac main.java Database.java DbAddRiver.java
java -classpath '.:mysql-connector-java-5.1.39-bin.jar' Main
