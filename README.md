# Microservice for authentication with Domain-Driven Design
Full Spring Boot authentication microservice with Domain-Driven Design approach.

### 1. Introduction

#### 1.1. Purpose of this Repository

This is a list of the main goals of this repository:

* Showing how you can implement a Domain-Drive design
* Showing how you can implement a CQRS
* Presentation of the full implementation of an application
  * This is not another proof of concept (PoC)
  * The goal is to present the implementation of an application that would be ready to run in production
* Showing the application of best practices and object-oriented programming principles
* Presentation of the use of design patterns. When, how and why they can be used
* Presentation of the implementation using Domain-Driven Design approach (tactical patterns)
* Presentation of the implementation of Unit Tests for Domain Model (Testable Design in mind)
* Presentation of the implementation of Integration Tests
* Presentation of the implementation of testing Web-Layer only

#### 1.2 Your contribution
* Give it a star
* Share it
* Become a contributor

### 2. Domain
#### 2.1. Business logic of the repository
The main idea of this repository is to create small microservice for authentication that provides next functionalities:
* Form Registration
* Form Login, Google Login, Facebook Login
* Password recovery
* Email notifications
* Session identification and authentication
#### 2.2. How to run
* Run database docker container first: `docker-compose up -d`
* Run application locally with `local` profile
* Plan is to dockerize the application in order to run it easy 
#### 2.3. How to run tests
* Run database docker container first: `docker-compose up -d`. Plan is to add testdb for testing purposes.
* Run Integration test separately
* Run Unit test separately
* Run Web-Layer (tests that are testing Spring Boot implementation and Rest Controller) tests separately


### 3. Desired TODO List to complete the application:
* Clean the code from inconsistencies
* Add more session details
* Implement a tool like ELK
* Caching if/when needed
* More tests
* CI
* Pull out 'ddd' folder and create a library
* Pull out 'CQRS' folders (contracts and configuration) and create a library
* Finish SpringBoot security and CSFR token
* Dockerize

### 4. The main idea is to create a reusable microservice network that consists of the next microservices:
* Service discovery (probably with K8s).
* Create a microservice for authentication (current repository).
* Create a microservice for authorization - simple implementation of RBAC.
* Create a microservice for sending emails.
* Create a microservice for localization - the idea is to provide UI for translating the application to various languages as a common part of most applications.
* Create a microservice for asynchronous communication (AC) - the idea is to create a microservice that distributes the messages between microservices. The microservice should work over DB (e.g. Redis) and RMQ to provide asynchrony. The microservice should provide the REST API for accessing it. In this way, we should have RMQ in only one place and communication with this microservice should go over REST API.
  The microservice should provide these routes:
  * route for registering messages by other microservices. E.g. Email-Microservice could register message **send-email** with _required properties_, _endpoint_, and _version_. That configuration should be saved into a DB.
  * sending messages - E.g. Authentication microservice should send a message after registering a user with the name **send-email** and _required properties_. The AC microservice will receive that message, validate the required properties, enrich the message body with an endpoint (which is saved in DB) and publish a message to RMQ. The RMQ consumer will consume the message and distribute it to the endpoint.

### 5. License
The project is under [MIT license](https://opensource.org/licenses/MIT).

### 6. Inspiration
#### 6.1. Domain-Driven Design
- ["Domain-Driven Design: Tackling Complexity in the Heart of Software"](https://www.amazon.com/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215) book, Eric Evans
- ["Implementing Domain-Driven Design"](https://www.amazon.com/Implementing-Domain-Driven-Design-Vaughn-Vernon/dp/0321834577) book, Vaughn Vernon
- ["Domain-Driven Design in PHP"](https://www.amazon.com/dp/1787284948) book, by Carlos Buenosvinos, Christian Soronellas, Keyvan Akbary
- ["IDDD_Samples"](https://github.com/VaughnVernon/IDDD_Samples) GH repository, Vaughn Vernon
- ["Blog CQRS"](https://github.com/dddshelf/blog-cqrs) GH repository, by Carlos Buenosvinos, Christian Soronellas, Keyvan Akbary
- ["DDD example - Last Wishes"](https://github.com/dddshelf/last-wishes) GH repository, by Carlos Buenosvinos, Christian Soronellas, Keyvan Akbary
- ["Modular Monolith with DDD"](https://github.com/kgrzybek/modular-monolith-with-ddd) GH repository, by Kamil Grzybek
- ["Mediator implementation"](https://github.com/jbogard/MediatR) GH repository, by Jimmy Bogard

#### 6.2 Application Architecture
- ["Patterns of Enterprise Application Architecture"](https://martinfowler.com/books/eaa.html) book, Martin Fowler
- ["Clean Architecture: A Craftsman's Guide to Software Structure and Design (Robert C. Martin Series"](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164) book, Robert C. Martin
- ["The Clean Architecture"](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) article, Robert C. Martin
- ["DDD, Hexagonal, Onion, Clean, CQRS, … How I put it all together"](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/) article, Herberto Graca

#### 6.2. System Architecture
- ["Building Microservices: Designing Fine-Grained Systems"](https://www.amazon.com/Building-Microservices-Designing-Fine-Grained-Systems/dp/1491950358) book, Sam Newman

#### 6.3. Design
- ["Clean Code: A Handbook of Agile Software Craftsmanship"](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882) book, Robert C. Martin
- ["Design Patterns: Elements of Reusable Object-Oriented Software"](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612) book, Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides

