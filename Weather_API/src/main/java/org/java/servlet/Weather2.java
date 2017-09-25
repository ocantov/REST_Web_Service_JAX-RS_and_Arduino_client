package org.java.servlet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*	 		1 | Humedad                  |
|           2 | Presión Atmosférica      |
|           3 | Temperatura              |
|           4 | Dirección del Viento     |
|           5 | Velocidad del Viento     |
|           6 | Precipitación de lluvia  */

public class Weather2 {
	 private int deviceId;
	 private AtVariable temperature;
	 private AtVariable humidity;
	 private AtVariable barometer;
	 private AtVariable rain;
	 private AtVariable windDirection;
	 private AtVariable windSpeed;
	 
	 @JsonIgnoreProperties
	 @JsonProperty("deviceId")
	 public int getDeviceId() {
	        return deviceId;
	    }
	 
	 public void setDeviceId(int deviceId) {
	        this.deviceId = deviceId;
	    }
	 
	 @JsonIgnoreProperties
	 @JsonProperty("temperature")
	 public AtVariable getTemperature() {
	        return temperature;
	    }
	 
	 public void setTemperature(AtVariable temperature) {
	        this.temperature = temperature;
	    }
	 @JsonIgnoreProperties
	 @JsonProperty("humidity")
	 public AtVariable getHumidity() {
	        return humidity;
	    }
	 
	 public void setHumidity(AtVariable humidity) {
	        this.humidity = humidity;
	    }
	 
	 @JsonIgnoreProperties
	 @JsonProperty("barometer")
	 public AtVariable getBarometer() {
	        return barometer;
	    }
	 
	 public void setbarometer(AtVariable barometer) {
	        this.barometer = barometer;
	    }

	 @JsonIgnoreProperties
	 @JsonProperty("rain")
	 public AtVariable getRain() {
	        return rain;
	    }
	 
	 public void setRain(AtVariable rain) {
	        this.rain = rain;
	    }
	 
	 @JsonIgnoreProperties
	 @JsonProperty("windDirection")
	 public AtVariable getWindDirection() {
	        return windDirection;
	    }
	 
	 public void setWindDirection(AtVariable windDirection) {
	        this.windDirection = windDirection;
	    }

	 
	 @JsonIgnoreProperties
	 @JsonProperty("windSpeed")
	 public AtVariable getWindSpeed() {
	        return windSpeed;
	    }
	 
	 public void setWindSpeed(AtVariable windSpeed) {
	        this.windSpeed = windSpeed;
	    }
	 }

