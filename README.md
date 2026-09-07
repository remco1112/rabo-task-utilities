# Task Utils
This repository contains the task-utils library that provides various utilities related to the execution of tasks.

## Building the project
Requires java 17 or higher

Execute ``./mvnw clean package`` to test and build the project

## Assumptions and context
 - This library was not in use during the development of the tasks, thus backwards incompatible changes were safe to make.
 - This library will be used in an enterprise environment with many long-running projects, thus java 17 is chosen as a minimum version. It is a relatively modern LTS version yet not state-of-the-art so that it can be used in existing projects that may not be using the latest LTS version yet.
 - This library will not be used in projects that require extreme performance, thus readability and maintainability is favored over performance in this library.