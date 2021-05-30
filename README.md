# Wake Me App Backend
## About
Backend for [Stupid Hack 2021 Project](https://app.hackjunction.com/projects/stupid-hack-2021/view/60966cfb9d693e00448f98f7).
The idea is to allow users to set up and manager alarm clocks for other users.

Technologies: Java, Spring Boot, Spring MVC, Spring Data JPA,
Lombok

## Project Structure
Code is located in `com.junction.stupidhack.alarm` package:
* `api` - Rest API Controllers for authentication and alarm managment
* `model` - classes representing a domain model
* `repository` - Spring Data JPA reprositories intended to manage persisted model entities
* `service` - business logic layer
* `security` - basic authentication implementation
* `configuration` - web related configurations