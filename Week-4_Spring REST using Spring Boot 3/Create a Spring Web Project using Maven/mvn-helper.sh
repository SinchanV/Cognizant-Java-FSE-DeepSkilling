#!/bin/bash

# Maven Wrapper Script for Spring Learn Project
# This script provides easy access to common Maven commands

echo "=========================================="
echo "Spring Learn - Maven Command Helper"
echo "=========================================="

case "$1" in
    "clean")
        echo "Cleaning the project..."
        mvn clean
        ;;
    "compile")
        echo "Compiling the project..."
        mvn clean compile
        ;;
    "test")
        echo "Running tests..."
        mvn test
        ;;
    "package")
        echo "Packaging the application..."
        mvn clean package
        ;;
    "run")
        echo "Running the Spring Boot application..."
        mvn spring-boot:run
        ;;
    "run-jar")
        echo "Running the packaged JAR..."
        java -jar target/spring-learn-0.0.1-SNAPSHOT.jar
        ;;
    "dependencies")
        echo "Showing dependency tree..."
        mvn dependency:tree
        ;;
    "help")
        echo "Available commands:"
        echo "  clean     - Clean the project"
        echo "  compile   - Compile the project"
        echo "  test      - Run tests"
        echo "  package   - Package the application"
        echo "  run       - Run the Spring Boot application"
        echo "  run-jar   - Run the packaged JAR"
        echo "  dependencies - Show dependency tree"
        echo "  help      - Show this help message"
        ;;
    *)
        echo "Usage: ./mvn-helper.sh [command]"
        echo "Run './mvn-helper.sh help' for available commands"
        ;;
esac
