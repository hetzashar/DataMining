package com.datamining.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.datamining.beans.CallDetailsBean;

public class CallDetails {         
		public static List<CallDetailsBean> fetchCallDetails(Date startDate, Date endDate, String SubscriberNum) {
		
		PreparedStatement pst = null;  
		List<CallDetailsBean> list = new ArrayList<CallDetailsBean>();
		ResultSet rs = null;  
		Connection conn=null;
		CallDetailsBean bean=new CallDetailsBean();
		
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			
			System.out.println(SubscriberNum);
			pst = conn.prepareStatement("select * from 239.circuitswitched_cdr where SUBSCRIBERNUMBER =? "
					+ " AND STARTTIME BETWEEN ? AND ?");
			pst.setString(1, SubscriberNum); 
			pst.setTimestamp(2, new Timestamp(startDate.getTime()));
			pst.setTimestamp(3, new Timestamp(endDate.getTime())); 
			
			
			System.out.println(pst.toString());	
			rs = pst.executeQuery(); 
			
			System.out.println("status o/p" +rs);
			while(rs.next())
			{
				bean=new CallDetailsBean();
				String subscriberNumber=rs.getString("SUBSCRIBERNUMBER");
				bean.setSubscriberNumber(subscriberNumber);
				bean.setStartDateTime(rs.getTimestamp("STARTTIME"));
				bean.setCallType(rs.getString("CALLTYPE"));
				bean.setCallDirection(rs.getString("DIRECTION"));
				bean.setDuration(rs.getInt("DURATION"));
				bean.setOtherNumber(rs.getString("OTHERNUMBER"));
				bean.setImei(rs.getString("SUBSCRIBERIMEI"));
				bean.setImsi(rs.getString("SUBSCRIBERIMSI"));
				bean.setCellLocation(rs.getString("CELLLOCATION"));
				bean.setLatitude(rs.getFloat("LATTITUDE"));
				bean.setLongitude(rs.getFloat("LONGITUDE"));
				bean.setOtherNumberServiceProvider(rs.getString("OTHERNUMBERSERVICEPROVIDER"));
				bean.setNationalInternational(rs.getInt("NATIONAL_INTERNATIONALCALL"));
				bean.setCircle(rs.getString("CIRCLE_NAME"));
				bean.setSubscriberOperator(rs.getString("SUBSCRIBER_OPERATOR"));
				list.add(bean);
			}

		} catch (Exception e) {  
			System.out.println(e);  
		} finally {  
			if(conn!=null){
				ConnectionPool.addConnectionBackToPool(conn);
			}
			if (pst != null) {  
				try {  
					pst.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
			if (rs != null) {  
				try {  
					rs.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
		return list;  
	}  
}
