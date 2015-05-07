package com.datamining.datacollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.datamining.beans.CallDetailsBean;
import com.datamining.beans.CallSummaryBean;
import com.datamining.beans.LocationBean;
import com.datamining.beans.OtherNumberBean;
import com.datamining.beans.SubscriberDetailsBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SilentNumberAnalysis {
public static int status=0;
	
	public static HashMap<String, List<CallDetailsBean>> numlist=new HashMap<String,List<CallDetailsBean>>();

	public static HashMap<String, List<CallDetailsBean>> getSilentNumberAnalytics(Date startdate, Date enddate,
			String location) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		AmazonS3 s3client = new AmazonS3Client();
		String bucketName = "cmpe239jjugglers";
		String keyName = "input1.txt";

		BufferedWriter writer=null;
		try {
//			File file = new File("/input1.txt");
//			writer = new BufferedWriter(new FileWriter(file,true));
//			writer.write(df.format(startdate) + "\n" + df.format(enddate) + "\n" + location);
			java.sql.Connection conn;
			try {
				conn = ConnectionPool.getConnectionFromPool();
			
			PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into input values(?,?,?)");
            
       	 	pst.setString(1,df.format(startdate));  
            pst.setString(2,df.format(enddate));        
            pst.setString(3,location);
            
            
            pst.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
			

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

//		try {
//			keyName = "completed.txt";
//			File file = new File("completed.txt");
//			S3Object object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
//			InputStream objectData = object.getObjectContent();
//			objectData.close();
//		} catch (IOException e) {
//			System.out.println("In catch IO Exception... " + e.getMessage());
//		} catch (AmazonServiceException ase) {
//			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
//					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
//			System.out.println("Error Message:    " + ase.getMessage());
//			System.out.println("HTTP Status Code: " + ase.getStatusCode());
//			System.out.println("AWS Error Code:   " + ase.getErrorCode());
//			System.out.println("Error Type:       " + ase.getErrorType());
//			System.out.println("Request ID:       " + ase.getRequestId());
//		} catch (AmazonClientException ace) {
//			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
//					+ "an internal error while trying to " + "communicate with S3, "
//					+ "such as not being able to access the network.");
//			System.out.println("Error Message: " + ace.getMessage());
//		}

//		AmazonS3 s3client = new AmazonS3Client();
        try {
            System.out.println("Downloading a new object to S3 from a file\n");
           // File file = new File(uploadFileName);
            S3Object s3object;
            keyName = "completed.txt";
            while(true){
            try{
            s3object=s3client.getObject(new GetObjectRequest(bucketName, keyName));
            break;
            }catch(AmazonServiceException ase){
                if(ase.getStatusCode()==404) continue;
            }
            }

            s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
          //InputStream objectData = s3object.getObjectContent();
            //displayTextInputStream(s3object.getObjectContent());
           //objectData.close();
//            BufferedReader reader = new BufferedReader(new 
//                    InputStreamReader(s3object.getObjectContent()));
//            while (true) {
//                String line = reader.readLine();
//                if (line == null) break;
//
//                System.out.println("    " + line);
//            }
//            System.out.println();
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		/*
		 * finally{ try { // in.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		getSilentNumbers();
		return numlist;
	}
	
	public static void getSilentNumbers(){
		
		String[] tokens;
		String[] calllist = null;
		BufferedReader in=null;
		String[] Call=null;
		List<CallDetailsBean> calls=new ArrayList<CallDetailsBean>();
		String bucketName     = "cmpe239jjugglers";
		String keyName        = "part-00000";
		AmazonS3 s3client = new AmazonS3Client();
		
        try {
            System.out.println("Downloading a new object to S3 from a file\n");
           // File file = new File(uploadFileName);
            S3Object s3object;
            while(true){
            try{
            s3object=s3client.getObject(new GetObjectRequest(bucketName, keyName));
            break;
            }catch(AmazonServiceException ase){
                if(ase.getStatusCode()==404) continue;
            }
            
            }

            //s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
          //InputStream objectData = s3object.getObjectContent();
            //displayTextInputStream(s3object.getObjectContent());
           //objectData.close();
            BufferedReader reader = new BufferedReader(new 
                    InputStreamReader(s3object.getObjectContent()));
            String str;
            while ((str = reader.readLine()) != null){
            	
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

            s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
            System.out.println();
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
//		try {
//			
//		    
//		    System.out.println("Downloading a new object to S3 from a file\n");
//            File file = new File("part-00000");
//            S3Object object=s3client.getObject(new GetObjectRequest(bucketName, keyName));
//            InputStream objectData = object.getObjectContent();
//            System.out.println(objectData);
//            objectData.close();
//            in = new BufferedReader(new FileReader("part-00000"));
//            String str;
//		    while ((str = in.readLine()) != null){
//		       tokens=str.split(":");
//		       System.out.println(str);
//		       System.out.println(tokens[2]);
//		       calllist=tokens[2].split("%");
//		       //System.out.println(tokens[0]);
//		       for (String call: calllist){
//		    	   Call=call.split(";");
//		    	  System.out.println(Call.length);
//		    	   calls.add(new CallDetailsBean(Call,tokens[1]));
//		       }
		      
//		       numlist.put(tokens[0],calls);
//		    }
//		}
	catch (IOException e) {
			System.out.println("In catch IO Exception... "+ e.getMessage());
		}
//        catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which " +
//            		"means your request made it " +
//                    "to Amazon S3, but was rejected with an error response" +
//                    " for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, which " +
//            		"means the client encountered " +
//                    "an internal error while trying to " +
//                    "communicate with S3, " +
//                    "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        } 
//		
		finally{
			
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
			totalDuration+=calldetails.duration;
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
			if(temp.containsKey(tempcall.otherNumber)){
				count=temp.get(tempcall.otherNumber);
				count=count+1;
			}else{
				temp.put(tempcall.otherNumber,1);
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
			bean.setLatitude(call.latitude);
			bean.setLongtitude(call.longitude);
			bean.setAddress(call.cellLocation);
			beanList.add(bean); 
		}
		return beanList;
	}


	public static SubscriberDetailsBean getSubscriberDetails(String parameter) {

		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		SubscriberDetailsBean bean=new SubscriberDetailsBean();
		try{
			String sql="select SUBSCRIBER_NUMBER, NAME, IMSI, AGENT_NAME, AGENT_CODE, ADDRESS, CIRCLE from datamin.circuitswitched_sdr "+
					"where SUBSCRIBER_NUMBER =? ";
			con=(Connection) ConnectionPool.getConnectionFromPool();
			ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, parameter);
			System.out.println("Query formed:: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next())
			{
				bean.setSubscriberNumber(rs.getString("SUBSCRIBER_NUMBER"));
				bean.setIMSI(rs.getString("IMSI"));
				bean.setAgentName(rs.getString("AGENT_NAME"));
				bean.setAgentCode(rs.getString("AGENT_CODE"));
				bean.setAddress(rs.getString("ADDRESS"));
				bean.setCircle(rs.getString("CIRCLE"));
				bean.setSubscriberName(rs.getString("NAME"));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
	 			}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	
	}
}
