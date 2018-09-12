# eurekaServer

This module does NOT contain any logic. It's a Web Server App whcih only purpose is to work as a _Eureka Server_, so
other Services can connect, register into it, and also fetch information from it so they can use another services 
registered.

In most tutorials on the Internet, the main Web Application also works as a Eureka Server, in this case we are 
defining a separate module for it, since this is the way forward in a production environment.

So, in order to enable_Eureka_ capacities, we need to make the following changes:

in the `gradle.build` file:
````
compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:2.0.1.RELEASE')
````

in the `application.properties` file:
````
spring.application.name=eureka-server
server.port = 8761

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

logging.level.com.netflix.eureka=OFF
logging.level.com.netflix.discovery=OFF
````

And that's it. After starting the application, the _Eureka Server_ will be listening to the address and port specified.
Other Services will have to make a reference to that URL in their configuration files, for them to register on it and
fetch information about other services, in order to use them.