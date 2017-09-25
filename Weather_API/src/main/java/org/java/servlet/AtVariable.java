package org.java.servlet;

public class AtVariable {

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
		 String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(getTimestamp()*1000));
		 return "[ timestamp=" + timestamp + " ("+date+"), value=" + value + " ]";
	 }
	
}
