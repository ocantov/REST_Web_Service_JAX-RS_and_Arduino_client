# REST Web Service JAX-RS and Arduino client
This is just an practical example of a client-server weather app. Arduino captures the temperature and humidity, sends it along with the timestamp to a server(cloud service) using ESP8266 module.

The cloud service is a Java REST API created with Jersey and embedded Jetty. It uses Security Constraints and Authorization Constraint to drive the authentication and authorization operations of web container security. The web container authenticate the Arduino web client/user using HTTP DIGEST.

The client application that runs in Arduino Mega (we need mega because of memory consumption of Digest algorithm) needs 3 modules: 

DS3231(Real-Time Clock)  
wiring: SCL > 21, SDA  > 20

AM2302 (temperature-humidity sensor)
wiring: Data out > 2

ESP8266 (Wi-Fi)
wiring: tx1,rx1


Web Service
There is a pre-builded WAR in Weather_API/target called Weather_API-0.0.1-SNAPSHOT.war, just run it with java -jar Weather_API-0.0.1-SNAPSHOT.war. The server container is Jetty Embedded so you just need to run it.
I will listen to port 8080, Arduino will send data in Json format and the sever will print it formated in the console.

You can also build it from the code with: maven clean install 

