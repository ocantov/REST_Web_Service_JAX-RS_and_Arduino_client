package org.java.servlet;

public class WindSpeed {

	 private long timestamp;
	 private Float value;
	 
	 public Float getValue()
	 {
		 return value;
	 }
	 public void setValue(Float value)
	 {
		 this.value=value;
	 }
	 public long getTimestamp() {
	        return timestamp;
	    }
	 public void setTimestamp(long timestamp) {
	        this.timestamp=timestamp;
	    }
	 @Override
	 public String toString() {
		 return "[ timestamp=" + timestamp +", value=" + value +  " ]";
	 }
	
}
