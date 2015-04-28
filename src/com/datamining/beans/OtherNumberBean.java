package com.datamining.beans;

import java.io.Serializable;

public class OtherNumberBean implements Serializable{

	String otherNumber;
	int noOfCalls;
	String subscriberNumber;
	
	public OtherNumberBean(){
		
	}
	
	public OtherNumberBean(String num,String calls,String number){
		otherNumber=num;
		noOfCalls=Integer.parseInt(calls);
		subscriberNumber=number;
	}
	
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	public int getNoOfCalls() {
		return noOfCalls;
	}
	public void setNoOfCalls(int noOfCalls) {
		this.noOfCalls = noOfCalls;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	
	
}