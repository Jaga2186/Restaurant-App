#### **⚙️ Tech Stack — Restaurant Management App**

#### **🖥️ Backend Framework**



Spring Boot (Java) – for building each microservice with RESTful APIs



Spring Cloud – for service registration and discovery (Eureka Server)



#### **🗃️ Databases**



##### **MySQL – used in**



Restaurant Listing Service



Food Catalogue Service



User Info Service



##### **MongoDB – used in**



Order Service (to handle dynamic, schema-less order data efficiently)



#### **🔗 Microservice Architecture**

#### **5 Microservices:**



Restaurant Service — manages restaurant details (MySQL)



Food Catalogue Service — manages food items (MySQL)



User Info Service — handles user data (MySQL)



Order Service — processes and stores orders (MongoDB)



Eureka Server — enables service discovery and registration



#### **🧩 API Communication**



RESTful APIs – inter-service communication



Spring Web (Spring MVC) – for HTTP handling



#### **🧰 Tools \& Environment**



Maven – for dependency management and build automation



Git \& GitHub – for version control and collaboration



Postman – for API testing



MySQL Workbench \& MongoDB Compass – for DB management



VS Code / IntelliJ IDEA – as the development IDEs



#### **🛠️ Architecture Highlights**



Microservice-based architecture – independent deployment and scaling



Eureka Service Registry – for centralized service discovery



Layered Design – Controller, Service, Repository structure in each microservice



Cross-service communication – through REST endpoints



