# learning-microservices

This project represents a POC or _Proof Of Concept_ of _Microservices_ Architecture. Itr shows how to develop 
Microservices, package, deploy and run them. It also shows how to use some basic capabilities associasted to a 
Microservices architecture but not always implemented, like:
 
 * Self-Discovery of Services
 * Service Registration
 * Fault-tolerance (fallback)
 * Cache (pending)
 
 Additional features more devOps-related are also included, like:
  * Automatic "containerization" of each Service using _Docker_
  * Services orchestration using _Kubernetes_ over _Docker_ (pending)
 
 
 The project implements several services in a fictional "travel agency" company:
  * A Service to manage customer information (get info, register a new user, etc)
  * A service to make a booking for a specific User
  
  Each service is an independent Module within the general "learning.-microservices", and each of them has a separate
  _README-MD_ file describing its purpose and the features it implements.
  

## General Structure

In a general microservices architecture, we'll end up with: 
 * several (or dozens) of REST microservcices. Some fo them run isolated, some others need to collaborate between 
 each other. 
 * The number of instances can vary depending on the work load, so we'll also have in place a "registration Server", 
 so all the sercvices are registered there, and one service can fin the other without hardcoding the URLS.
 * Due to the previous points, we might have several instances of the same Service deployed at a time. in this case,
 when a client(liek another service) needs to consume some service, it needs to know WHICH instance to call. So here
 we'll also provide some client-load balancing capabilities: each client will connect to the "Registration" Server, 
 get a list of instances of the Service it wants to use, and pick on up based on some balacing algorith,
 * We'll also provide some fallback capabilities, in case a Service is deteriorate or failing, so the whole flow is
 not affected to some extent.
  

### Thecnology Stack
  
In order to provide the capabilities above, we'¡ll make use of the following libraries/frameworks:

* **Eureka Server**, provided by _NEtfflix_, to implement the _Registration_ and _Self-discovery_ capabilities.
* In order to develop a _Client_ for our service, we'll use (among others) a **Feign** Client, which is a very 
useful abstraction that allows us to make HTTP calls without getting our hands dirty with the HTTP communication
details.
* the **Feign client** described above, if not configured otehr way, will automatically connect to the _Eureka Server_
to get info about the instances of the Service we are trying to use, and pick one up. The decision opf choosing one
instance over another is done by the **Ribbon** library, which runs behind the scenes.
* the **Feign client** described above will also define a Fallback method, which is a reference to another class will
be invoked if something's wrong. the concept of "something's wrong" 
  
## Notes about How to build the project from Scratch.

This project has been created with the following tools:
 * Java 1.8
 * Spring Boot
 * gradle
 * Spring CLI (Command Line Tool)
 
 
 To create the main project, type the following command:
 ````
 spring init --build=gradle --java-version=1.8 learning-microservices
 ````
 
 To crete the subprojects for the 2 services, type the following commands withint the `learning-microservices` folder:
 ````
 spring init --java-version=1.8 --build=gradle --group=com.ilozanof.learning.microservices.customerService customerService
 spring init --java-version=1.8 --build=gradle --group=com.ilozanof.learning.microservices.bookingService bookingService
 ````
 
 , and then modify the `settings.gradle` file to reflect them:
 ````
 include ':customerService'
 include ':bookingService'
 ````
 