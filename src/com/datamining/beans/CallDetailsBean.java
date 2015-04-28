package com.datamining.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallDetailsBean implements Serializable{
	public int count;
	public Date startDateTime;
	public Date endDateTime;
	public String subscriberNumber;
	public String callType;
	public String callDirection;
	public int duration;
	public String otherNumber;
	public String imei;
	public String imsi;
	public String cellLocation;
	public String subscriberOperator;
	public float latitude;
	public float longitude;
	public String otherNumberServiceProvider;
	public int nationalInternational=0;
	public String circle;
	
	public CallDetailsBean(){
		
	}
	
	public CallDetailsBean(String[] details,String cnt){
		System.out.println("Initialising...");
		count=Integer.parseInt(cnt.trim());
		callType=details[3].trim();
		callDirection=details[4].trim();
		duration=Integer.parseInt(details[1].trim());
		otherNumber=details[0];
		imei=details[2];
		imsi=details[8];
		cellLocation=details[5];
		latitude=Float.parseFloat(details[9].trim());
		longitude=Float.parseFloat(details[10].trim());
		otherNumberServiceProvider=details[7];
		DateFormat df=new SimpleDateFormat("dd-MMM-yy");
        try {
			startDateTime=df.parse(details[13].trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!details[11].equals(""))
		nationalInternational=Integer.parseInt(details[11].trim());			
		circle=details[12];
		subscriberOperator=details[6];
	}
	
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCallDirection() {
		return callDirection;
	}
	public void setCallDirection(String callDirection) {
		this.callDirection = callDirection;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getCellLocation() {
		return cellLocation;
	}
	public void setCellLocation(String cellLocation) {
		this.cellLocation = cellLocation;
	}
	public String getSubscriberOperator() {
		return subscriberOperator;
	}
	public void setSubscriberOperator(String subscriberOperator) {
		this.subscriberOperator = subscriberOperator;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getOtherNumberServiceProvider() {
		return otherNumberServiceProvider;
	}
	public void setOtherNumberServiceProvider(String otherNumberServiceProvider) {
		this.otherNumberServiceProvider = otherNumberServiceProvider;
	}
	public int getNationalInternational() {
		return nationalInternational;
	}
	public void setNationalInternational(int nationalInternational) {
		this.nationalInternational = nationalInternational;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	
	
}