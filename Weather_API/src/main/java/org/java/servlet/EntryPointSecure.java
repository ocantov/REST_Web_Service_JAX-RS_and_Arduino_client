package org.java.servlet;
import java.util.Date;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.java.servlet.Weather;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
 
@Path("/secure")
public class EntryPointSecure{
 

	//curl -X GET http://127.0.0.1:8080/pass/test/ --digest -u user:password


    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test( ) {
    	 Date date = new Date();
    	 System.out.println("GET -- "+date.toString()+ " -- Authenticated");
	    	
    	return date.toString()+"\n";
    	}
    
    
	//curl -X POST http://127.0.0.1:8080/pass/insertvalue/algo --digest -u user:password
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("insertvalue/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@PathParam("value") String value ) {
    	Date date = new Date();
    	//String user= String.valueOf(sc.getUserPrincipal());
    	System.out.println("POST -- "+value+" -- "+date.toString() + " -- Authenticated");
    	
    	    	
    	return Response.ok("The text "+value+ " was received\n").build();
    
    	}
    //curl  -H "Content-Type: application/json"  -X POST  -d '{"deviceId":"001","timestamp":"12212000001","humidity":"12","atmosphericPressure":"996","temperature":"34","windDirection":"20","windSpeed":"20"rainPrecipitation":"20"}' http://127.0.0.1:8080/pass/insertdata --digest -u user:password
    @POST
    @Path ("/insertdata")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public Response getData (Weather weather) {
     
    		System.out.println("----------------------------------");
    		System.out.println("DeviceID: "+weather.getDeviceId());
    		System.out.println("Timestamp: "+weather.getTimestamp());
    		System.out.println("Humidity: "+weather.getHumidity());
    		System.out.println("Atmospheric Pressure: "+weather.getAtmosphericPressure());
    		System.out.println("Temperature: "+weather.getTemperature());
    		System.out.println("Wind Direction: "+weather.getWindDirection());
    		System.out.println("Wind Speed: "+weather.getWindSpeed());
    		System.out.println("Rain Precipitation: "+weather.getRainPrecipitation());
    		System.out.println("----------------------------------");
   
    	return Response.ok("ok").build();
    } 
    
/*    
curl -X POST http://127.0.0.1:8080/secure/v2/insertdata -H 'cache-control: no-cache' -H 'content-type: application/json' --digest -u user:user -d '{ "deviceId": "001",
"temperature": {
    "timestamp": "12212000001",
    "value": "25"
    },
    "humidity": {
    "timestamp": "12212000003",
    "value": "25"
    },
    "barometer": {
    "timestamp": "12212000006",
    "value": "1001"
    },
    "wind": {
    "timestamp": "12212000007",
    "speed": "20",
    "direction": "16"
    },
    "rain": {
    "timestamp": "12212000008",
    "value": "20"
    }
    }'


*/
    
    @POST
    @Path ("/v2/insertdata")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public Response getDataV2 (Weather2 weather) {
    		System.out.println("----------------------------------");
    		System.out.println("DeviceID: "+weather.getDeviceId());
    		if (weather.getTemperature() != null)
    		{
    		System.out.println("Temperature: "+weather.getTemperature());
    		}
    		if (weather.getHumidity() != null)
    		{
    		System.out.println("Humidity: "+weather.getHumidity());
    		}
    		if (weather.getBarometer() != null)
    		{
    		System.out.println("Barometer: "+weather.getBarometer());
    		}
    		if (weather.getRain() != null)
    		{
    		System.out.println("Rain: "+weather.getRain());
    		}
    		if (weather.getWindSpeed() != null)
    		{
    		System.out.println("Wind Speed "+weather.getWindSpeed());
    		}
    		if (weather.getWindDirection() != null)
    		{
    		System.out.println("Wind Direction "+weather.getWindDirection());
    		}
    		System.out.println("----------------------------------");
    		
    	return Response.ok("ok").build();
    } 
}