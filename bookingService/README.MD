# bookingService

> NOTE: The entry-point of this project, if you want to learn how all the libraries or frameworks are being 
applied, is the _customerService_ module. the rest of modules, including this one, relay on that one, and
some explanations are not repeated (for example, instruction about hot to install the distribuitable in the 
local Maven repo, or how to generate a Docker image are opnly explain in the _customerService_ module), but
they are applied on different modules through the project.

This another of the services of the fictional Travel Agency Company...

This Service is completely similar to the _CustomerService_ , so we are **NOT** including here the same guidelines
or instructions again, since the technology stack is the same. the only difference is that this Service _uses_ the
previous _Customer Service_, that means that we need to define the dependency properly:

If we have access to the source code of both services, we can define both as _gradle modules_, but the best option,
if we are to follow a pure _microservice architec ture way-of-thinking_, is to assuem that we might _not_ have the
soruce code of all the services. In that case, we should _import_ the _client library (Jar)_ defined for each one of
the services we need to use.

In this case, we'll import the Jar library previously generated for the _Customer Service_, with the following change
in the gradle build file:


````
dependencies {
...
compile('com.ilozanof.learning.microservices:customerServiceClient:1.0-SNAPSHOT')
...
}
````

Also, in our Man Class we need to make sure that all the _Beans_ are properly injected. The package hierarchy for
both projects (_Booking Service_ and _Customer Service_) are not exactly the same: both share the same initial packages, 
but the last package name is different. So we make it work, swe use the _@ComponentScan_ annotation in our main
class:

````
...
@ComponentScan("com.ilozanof.learning.microservices")
````
That way, all the Beans under that package hierarchy (which both Services share) will nbe injected when the Spring
context is initialized.