# customerService

This is one of the Service of the Fictional Travel Agency. It only provides very basic operations, and it's been 
implemented following the patterns described in the parent project (see _README.MD_ file in the `learning-microservices`
 main project).
 
## Notes about how to build this Module.

The first step is create the project structure, here we'll use `Spring CLI`:
````
spring init --java-version=1.8 --build=gradle --group=com.ilozanof.learning.microservices.customerService --dependencies=web customerService
````

We'll go now through the structure of this project quickly, highlighting the most important points:

### General structure:

 * The code is broken down into several layers: Controller (view), logic, etc.
 * The `controller` package implements the _View_ layer
 * The `logic` package implements the business logic.
 * There is no `db` or `persistence` layher in this simple example, but there's bound to be one in more advanced projects.
 * The `model` package implements the business entities, and ptjher fclasses shared betwen different layers.
 * the `client` packate stores the classes that implement a *Client* of this Service. More on this later on.
 
 
### Packaging the application:

The application is distributed in 2 different ways:
* As a _SpringBoot fat-jar_, containing the whole source code plus the classes implementing the Web Server 
(tomcat in this case). This JAr is intended to be deployed in isolation, or embedded in a Container (_Docker_)
* As a small _Client_, which implements only a client of this service. This _JAR_ is the part that will be imported
by other clients that want to use this serice, like a jUnit case or another Service.

In order to generate both artifacts (fat jar and the client Jar), we need to make some adjustments to our `build.gradle`
file:

The FAT Jar is already created by the `build` task in the build lifecyhcle, since we are already using 
the _springBoot_ plugin. So what we need now is to create an additional artifact. In this case, we are creating a 
new gradle Task, to get the compiled classes and generate a JAR file with them:

````
task createClientJar(type: Zip) {
    dependsOn compileJava
    destinationDir = file("$rootDir/build")
    archiveName = rootProject.name + 'Client' + '-' + rootProject.version + '.jar'
    from sourceSets.main.output
    include 'com/ilozanof/learning/microservices/customerService/model/**/*'
    include 'com/ilozanof/learning/microservices/customerService/logic/**/*'
    include 'com/ilozanof/learning/microservices/customerService/client/**/*'
}
````

#### Publishing the Project in the Maven Local

If we want other project ot use this Service, we'll have to provide them with our JAR containing the _Client_ classes.
since we are using _dependency injection_, it's a good practice to publish this jar in our maven local repo, so other
project can take it from there. In order to publish, it, we need to make some changes to our `build.gradle` file:

````
apply plugin: 'maven-publish'
...
publishing {
	publications {
		serviceApp(MavenPublication) {
			from components.java
			groupId group
			artifactId project.name + 'App'
		}

		serviceClient(MavenPublication) {
			artifact 'build/' + rootProject.name + 'Client' + '-' + rootProject.version + '.jar'
			groupId group
			artifactId project.name + 'Client'
		}
	}
}
````

After applying the _maven-publish_ plugin, additional gradle tasks will be available to us. One of them is 
`publishtoMavenLocal`, which will publish both artifacts to our local repo. This publishing is configured by the
`publishing` intruction above; in this case we are declaring 2 artifacts to publish, one with the whole application
(the fat jar), and another one with the client Jar.


### Implementing the Client of this Service

It's our responsability to provide a client for the Service we are implementing. So taht's the goal of the classes
defined in the `client` package. The whole idea is to provide an interface, and 2 implementations for it:

* A default implementation, which will make a HTTP call to our service. This is the "normal" or "regular" way, as it 
will be used by other services in the architecture.
* A "local" implementation, which will NOT make any HTTP call. Instead, this sercice will invoke directly the 
business logic. Obviously, this will ONLY work in the same environment where the business logic works. In other
works: if the business logic need to access a DB or any other resource, and if we want to use the "local"
implementation, we'll need to execute our code in an environment with access to those resources, too. 
this implementation is intended to be used by other Services _in the same enviropment_, which want a more direct access
to the Sercice, without the delay caused by HTTP communications. _NOTE: the "local" implementation does NOT provide
fallback capabilities_

* the `CustomerService` is the general interface
* the `CustomerServiceLocalImpl` is the "local" implementation
* the `CustomerServiceHttpImpl` is the "normal" implementation. It makes use of the following classes:
   * the `CustomerServiceFeignClient`, which allows us to make calls to RESt endpoints easily.
   * the `CustomerServiceFallback` class, which implements fallback for all the methods declared in the interface.

### Registering our App in the Eureka Server

First, we need our _Eureka Server_ up and running, so pur Applicaiton can register on it on starting. These are the 
changes needed in our code:

In the `gradle.build` file:
````
dependencies {
    ...
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:2.0.1.RELEASE')
    ...
    compile('org.springframework.cloud:spring-cloud-starter-openfeign:2.0.1.RELEASE')
    compile('org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.0.1.RELEASE')
     compile('org.springframework.cloud:spring-cloud-starter-hystrix:1.4.5.RELEASE')
}
````
We are using FeignClients in this project to implement a Client of our won service, and our Feign client automatically
connects to the Eureka Server, using `ribbon` as client-side load balancing library. also, we are including fallback
capabilities, implemented by the `hystrix`library behind the scenes.

In the `application.properties` file:
````
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

logging.level.com.netflix.eureka=OFF
logging.level.com.netflix.discovery=OFF
````

In the `CustomerServiceFeignClient` class: This client will automatically connect to the Eureka Server to look for
instances of the Service, so we need to specify the _id_:
````
import org.springframework.cloud.openfeign.FeignClient;
...
@FeignClient(value = "customerService", fallback = CustomerServiceFallback.class)
```` 

_(the 'fallback' property is used for the fallback scenario)_

