package org.java.servlet;

/*	 		1 | Humedad                  |
|           2 | Presión Atmosférica      |
|           3 | Temperatura              |
|           4 | Dirección del Viento     |
|           5 | Velocidad del Viento     |
|           6 | Precipitación de lluvia  */

public class Weather {
	 private int deviceId;
	 private long timestamp;
	 private float humidity;
	 private int atmosphericPressure;
	 private Float temperature;
	 private Float windSpeed;
	 private int windDirection;
	 private int rainPrecipitation;
	 

	 public int getDeviceId() {
	        return deviceId;
	    }
	 
	 public void setDeviceId(int deviceId) {
	        this.deviceId = deviceId;
	    }
	 public long getTimestamp() {
	        return timestamp;
	    }
	 
	 public void setTimestamp(long timestamp) {
	        this.timestamp = timestamp;
	    }
	 		
	 public float getHumidity() {
	        return humidity;
	    }
	 
	 public void setHumidity(float humidity) {
	        this.humidity = humidity;
	    }
			
	 public int getAtmosphericPressure() {
	        return atmosphericPressure;
	    }
	 
	 public void setAtmosphericPressure(int atmosphericPressure) {
	        this.atmosphericPressure = atmosphericPressure;
	    }
	 
	 public Float getTemperature() {
	        return temperature;
	    }
	 
	 public void setTemperature(Float temperature) {
	        this.temperature = temperature;
	    }
	 public Float getWindSpeed() {
	        return windSpeed;
	    }
	 
	 public void setWindSpeed(Float windSpeed) {
	        this.windSpeed = windSpeed;
	    }
	 
	 public int getWindDirection() {
	        return windDirection;
	    }
	 
	 public void setWindDirection(int windDirection) {
	        this.windDirection = windDirection;
	    }	
	 public int getRainPrecipitation() {
	        return rainPrecipitation;
	    }
	 
	 public void setRainPrecipitation(int rainPrecipitation) {
	        this.rainPrecipitation = rainPrecipitation;
	    }
}
