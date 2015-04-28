package com.datamining.datacollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.datamining.beans.CallDetailsBean;
import com.datamining.beans.CallSummaryBean;
import com.datamining.beans.LocationBean;
import com.datamining.beans.OtherNumberBean;

public class ReadDataFromHadoop {

	public static int status=0;
	
	public static HashMap<String, List<CallDetailsBean>> numlist=new HashMap<String,List<CallDetailsBean>>();
	
	public static int getStatus(){
		return status;
	}
	
	public static void setStatus(){
		status=1;
	}
	
	public static HashMap<String, List<CallDetailsBean>> getSilentNumberAnalytics(Date startdate, Date enddate, String location){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("/input/input.txt")))) {
	   writer.write(df.format(startdate)+"\n"+df.format(enddate)+"\n"+location);
	}  catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		File f=new File("/output/completed.txt");
		while(!(f.exists() && !f.isDirectory())){}
		try {
		    f.delete();
		} catch (Exception x) {
		    System.err.format("%s: no such" + " file or directory%n");
		} 
		getSilentNumbers();
		return numlist;
	}
	
	public static void getSilentNumbers(){
		
		String[] tokens;
		String[] calllist = null;
		BufferedReader in=null;
		String[] Call=null;
		List<CallDetailsBean> calls=new ArrayList<CallDetailsBean>();
		try {
			File f=new File("/output/ouput.txt");
		    in = new BufferedReader(new FileReader("/output/ouput.txt"));
		    System.out.println(f.getAbsolutePath());
		    System.out.println(f.getCanonicalPath());
		    String str;
		    while ((str = in.readLine()) != null){
		       tokens=str.split(":");
		       System.out.println(str);
		       System.out.println(tokens[2]);
		       calllist=tokens[2].split("%");
		       //System.out.println(tokens[0]);
		       for (String call: calllist){
		    	   Call=call.split(";");
		    	  System.out.println(Call.length);
		    	   calls.add(new CallDetailsBean(Call,tokens[1]));
		       }
		      
		       numlist.put(tokens[0],calls);
		    }
		} catch (IOException e) {
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static List<CallDetailsBean> getTabularAnalysis(String number, Date startDateTime, int time){
		return numlist.get(number);
	}
	
	public static CallSummaryBean getGraphicalAnalysis(String number, Date startDateTime, int time){
		List<CallDetailsBean> calls=numlist.get(number);
		Iterator it=calls.iterator();
		int vcInTotal=0;
		int vcOutTotal=0;
		int smsInTotal=0;
		int smsOutTotal=0;
		int totalDuration=0;
		while(it.hasNext()){
			CallDetailsBean calldetails=(CallDetailsBean) it.next();
			totalDuration+=calldetails.getDuration();
			if(calldetails.getCallType().equals("VC")){
				if(calldetails.getCallDirection().equals("IN")){
					vcInTotal++;
				}else{
					vcOutTotal++;
				}
			}else{
				if(calldetails.getCallDirection().equals("IN")){
					smsInTotal++;
				}else{
					smsOutTotal++;
				}
			}
		}
		return new CallSummaryBean(number,totalDuration,vcInTotal,vcOutTotal,smsInTotal,smsOutTotal);
	}
	
	public static List<OtherNumberBean> getOtherNumberGraphicalAnalysis(String number, Date startDateTime, int time){
		Iterator it=numlist.get(number).iterator();
		List<OtherNumberBean> list=new ArrayList<OtherNumberBean>();
		HashMap<String,Integer> temp=new HashMap<String,Integer>();
		int count=0;
		while(it.hasNext()){
			CallDetailsBean tempcall=(CallDetailsBean) it.next();
			if(temp.containsKey(tempcall.getOtherNumber())){
				count=temp.get(tempcall.getOtherNumber());
				count=count+1;
			}else{
				temp.put(tempcall.getOtherNumber(),1);
			}
		}
		Iterator it1 = temp.entrySet().iterator();
		while (it1.hasNext()) {
	        Map.Entry pair = (Map.Entry)it1.next();
	        list.add(new OtherNumberBean(pair.getKey().toString(),pair.getValue().toString(),number));
	       // System.out.println(pair.getKey() + " = " + pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
		return list;
	}
	
	public static List<LocationBean> getLocationAnalysis(String number, Date startDateTime, int noOfDays){
		List<CallDetailsBean> calls=numlist.get(number);
		Iterator it=calls.iterator();
		List<LocationBean> beanList=new ArrayList<LocationBean>();
		while(it.hasNext()){
			CallDetailsBean call=(CallDetailsBean) it.next();
			LocationBean bean = new LocationBean();
			bean.setSubscriberNumber(number);
			bean.setLatitude(call.getLatitude());
			bean.setLongtitude(call.getLongitude());
			bean.setAddress(call.getCellLocation());
			beanList.add(bean); 
		}
		return beanList;
	}

	public static void main(String[] args ){
		getSilentNumbers();
	}
	
}

