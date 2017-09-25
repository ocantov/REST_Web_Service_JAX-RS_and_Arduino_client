package org.java.servlet;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
 
@Path("/insecure")
public class EntryPointInsecure{
 
		//curl http://127.0.0.1:8080/nopass/test

	 @GET
	    @Path("test")
	    @Produces(MediaType.TEXT_PLAIN)
	    public String test( ) {
	    	
	    	//String user= String.valueOf(sc.getUserPrincipal());
	     Date date = new Date();
		 System.out.println("GET -- "+date.toString()+" -- without authentication");
	    	
		 return date.toString()+"\n";
	    	
	    	}
	    
	 
		//curl -X POST http://127.0.0.1:8080/nopass/insertvalue/algo 
	 
	    @POST
	    @Consumes(MediaType.TEXT_PLAIN)
	    @Path("insertvalue/{value}")
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response post(@PathParam("value") String value ) {
	    	Date date = new Date();
	    	//String user= String.valueOf(sc.getUserPrincipal());
	    	System.out.println("POST -- "+value+" -- "+date.toString()+"- without authentication");
	    	
	    	
	    	return Response.ok("The text "+value+ " was received\n").build();
	    	
	    	
	    	}
//curl  -H "Content-Type: application/json"  -X POST  -d '{"deviceId":"001","humidity":"12","atmosphericPressure":"996","temperature":"34","windDirection":"20","windSpeed":"20","rainPrecipitation":"20"}' http://127.0.0.1:8080/nopass/insertdata
	    @POST
	    @Path ("/insertdata")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces({MediaType.TEXT_PLAIN})
	    public Response getData (Weather weather) {
	     
	    		System.out.println(weather.getDeviceId());
	    		System.out.println(weather.getHumidity());
	    		System.out.println(weather.getAtmosphericPressure());
	    		System.out.println(weather.getTemperature());
	    		System.out.println(weather.getWindDirection());
	    		System.out.println(weather.getWindSpeed());
	    		System.out.println(weather.getRainPrecipitation());
	    		
	   
	    	return Response.ok("ok").build();
	    }
	}