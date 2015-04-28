package com.datamining.beans;

import java.io.Serializable;
import java.util.Date;

public class CallDetailsBean implements Serializable{
	int count;
	Date startDateTime;
	Date endDateTime;
	String subscriberNumber;
	String callType;
	String callDirection;
	int duration;
	String otherNumber;
	String imei;
	String imsi;
	String cellLocation;
	String subscriberOperator;
	float latitude;
	float longitude;
	String otherNumberServiceProvider;
	int nationalInternational=0;
	String circle;
	
	public CallDetailsBean(){
		
	}
	
	public CallDetailsBean(String[] details,String cnt){
		System.out.println("Initialising...");
		count=Integer.parseInt(cnt);
		callType=details[3];
		callDirection=details[4];
		duration=Integer.parseInt(details[1]);
		otherNumber=details[0];
		imei=details[2];
		imsi=details[8];
		cellLocation=details[5];
		latitude=Float.parseFloat(details[9]);
		longitude=Float.parseFloat(details[10]);
		otherNumberServiceProvider=details[7];
		if(!details[11].equals(""))
		nationalInternational=Integer.parseInt(details[11]);			
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