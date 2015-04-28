package com.datamining.beans;


public class LocationBean {
	
	String subscriberNumber;
	SubscriberDetailsBean subscriber;
	float latitude;
	float longtitude;
	String address;
	String otherNumber;
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public SubscriberDetailsBean getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(SubscriberDetailsBean subscriber) {
		this.subscriber = subscriber;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	
	

}
