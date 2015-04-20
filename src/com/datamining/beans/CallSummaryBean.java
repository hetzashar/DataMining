package com.datamining.beans;

public class CallSummaryBean {

	String subscriberNumber;
	String callDirection;
	int totalDuration;
	int vcInTotal;
	int vcOutTotal;
	int smsInTotal;
	int smsOutTotal;
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getCallDirection() {
		return callDirection;
	}
	public void setCallDirection(String callDirection) {
		this.callDirection = callDirection;
	}
	public int getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}
	public int getVcInTotal() {
		return vcInTotal;
	}
	public void setVcInTotal(int vcInTotal) {
		this.vcInTotal = vcInTotal;
	}
	public int getVcOutTotal() {
		return vcOutTotal;
	}
	public void setVcOutTotal(int vcOutTotal) {
		this.vcOutTotal = vcOutTotal;
	}
	public int getSmsInTotal() {
		return smsInTotal;
	}
	public void setSmsInTotal(int smsInTotal) {
		this.smsInTotal = smsInTotal;
	}
	public int getSmsOutTotal() {
		return smsOutTotal;
	}
	public void setSmsOutTotal(int smsOutTotal) {
		this.smsOutTotal = smsOutTotal;
	}
	
	
}
