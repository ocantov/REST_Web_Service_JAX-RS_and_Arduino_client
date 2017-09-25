package org.java.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class EntryPointIndex {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String index()
	{
		String endpoints = "GET /insecure/test\n"
				+"curl http://127.0.0.1:8080/insecure/test/\n\n"
				
				+ "POST /insecure/test/algo\n"
				+ "curl -X POST http://127.0.0.1:8080/insecure/test/algo\n\n"
				
				+ "GET /secure/test/ --digest -u user:password\n"
				+"curl -X GET http://127.0.0.1:8080/secure/test/ --digest -u user:password\n\n"
				
				+ "POST /secure/insertvalue/algo --digest -u user:password\n"
				+ "curl -X POST http://127.0.0.1:8080/secure/insertvalue/algo --digest -u user:password\n\n"
				
				+"POST /secure/insertdata\n"
				+"curl -H \"Content-Type: application/json\" -X POST -d '{\"deviceId\":\"001\",\"humidity\":\"12\",\"atmosphericPressure\":\"996\",\"temperature\":\"34\",\"windDirection\":\"20\",\"windSpeed\":\"20\"rainPrecipitation\":\"20\"}' http://127.0.0.1:8080/secure/insertdata  --digest -u user:password\n\n"
				
				+"POST /secure/v2/insertdata\n"
				+"curl -X POST http://127.0.0.1:8080/secure/v2/insertdata -H 'cache-control: no-cache' -H 'content-type: application/json' --digest -u user:password -d '{\"deviceId\": \"001\","
				+"\"temperature\": {"
				+"\"timestamp\": \"12212000001\","
				+"\"value\": \"25\""
				+ "},"
				+ "\"humidity\": {"
			    +"\"timestamp\": \"12212000003\","
			    +"\"value\": \"25\""
			    +"},"
			    +"\"atmosphericPressure\": {"
			    +"\"timestamp\": \"12212000006\","
			    +"\"value\": \"1001\""
			    +"},"
			    +"\"wind\": {"
			    +"\"timestamp\": \"12212000007\","
			    +"\"speed\": \"20\","
			    +"\"direction\": \"16\""
			    +"},"
			    +"\"rain\": {"
			    +"\"timestamp\": \"12212000008\","
			    +"\"value\": \"20\""
			    +"}"
			    +"}'\n\n"
			    
				+"POST /secure/v3/insertdata\n"
				+"curl -H \"Content-Type: application/json\" -X POST -d '{\"deviceId\":\"001\",\"value\":\"12\",\"type\":\"temperature\",\"timestamp\":\"1502576077\"}' http://127.0.0.1:8080/secure/v3/insertdata --digest -u user:password\n\n";
		
			
		return endpoints;
	}
	
}
