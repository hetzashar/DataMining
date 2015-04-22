package com.datamining.datacollection;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.datamining.beans.CallDetailsBean;
import com.datamining.beans.CallSummaryBean;
import com.datamining.beans.OtherNumberBean;

public class SilentNumberAnalysis {

	public static HashMap<String, List<CallDetailsBean>> getSilentNumberAnalytics(Date startDateTime, Date endDateTime, String location)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		HashMap<String, List<CallDetailsBean>> map=new HashMap<String, List<CallDetailsBean>>();
		location="%"+location.toLowerCase()+"%";
		try{
			String sql="select * from test.circuitswitched_cdr "+
						"where lower(CELLLOCATION) like ? "+
						"AND SUBSCRIBERNUMBER NOT IN (SELECT DISTINCT SUBSCRIBERNUMBER "+ 
												"FROM test.circuitswitched_cdr "+ 
												"WHERE STARTTIME BETWEEN ? AND ?) ";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, location);
			ps.setTimestamp(2, new Timestamp(startDateTime.getTime()));
			ps.setTimestamp(3, new Timestamp(endDateTime.getTime()));
			System.out.println("Query formed:: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next())
			{
				String subscriberNumber=rs.getString("SUBSCRIBERNUMBER");
				CallDetailsBean bean = new CallDetailsBean();
				bean.setSubscriberNumber(subscriberNumber);
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
				List<CallDetailsBean> beanList;
				if(map.containsKey(subscriberNumber)){
					beanList = map.get(subscriberNumber);
					beanList.add(bean);
					map.put(subscriberNumber, beanList);
				}else{
					beanList=new ArrayList<CallDetailsBean>();
					beanList.add(bean);
					map.put(subscriberNumber, beanList);
				}
				
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
		return map;
	}
	
	public static List<CallDetailsBean> getTabularAnalysis(String number, Date startDateTime, int time)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		List<CallDetailsBean> list=new ArrayList<CallDetailsBean>();
		Date endDateTime = new Date(startDateTime.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDateTime);
		cal.add(Calendar.DATE, -time);
		startDateTime.setTime(cal.getTime().getTime());
		try{
			String sql="select * from test.circuitswitched_cdr "+
						"where SUBSCRIBERNUMBER =? AND STARTTIME BETWEEN ? AND ? ";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setTimestamp(2, new Timestamp(startDateTime.getTime()));
			ps.setTimestamp(3, new Timestamp(endDateTime.getTime()));
			System.out.println("Query formed:: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next())
			{
				CallDetailsBean bean = new CallDetailsBean();
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
		return list;
	}
	
	public static CallSummaryBean getGraphicalAnalysis(String number, Date startDateTime, int time)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		CallSummaryBean bean=new CallSummaryBean();
		Date endDateTime = new Date(startDateTime.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDateTime);
		cal.add(Calendar.DATE, -time);
		startDateTime.setTime(cal.getTime().getTime());
		try{
			String sql="SELECT COUNT(*) COUNT, CALLTYPE, DIRECTION "+
						"FROM test.circuitswitched_cdr " +
						"WHERE SUBSCRIBERNUMBER =? AND STARTTIME BETWEEN ? AND ? "+
						"GROUP BY CALLTYPE, DIRECTION";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setTimestamp(2, new Timestamp(startDateTime.getTime()));
			ps.setTimestamp(3, new Timestamp(endDateTime.getTime()));
			System.out.println("Query formed:: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next())
			{
				bean.setSubscriberNumber(number);
				if(rs.getString("CALLTYPE").equalsIgnoreCase("VC") && rs.getString("DIRECTION").equalsIgnoreCase("OUT")){
					bean.setVcOutTotal(rs.getInt("COUNT"));
				}else if(rs.getString("CALLTYPE").equalsIgnoreCase("VC") && rs.getString("DIRECTION").equalsIgnoreCase("IN")){
					bean.setVcInTotal(rs.getInt("COUNT"));
				}else if(rs.getString("CALLTYPE").equalsIgnoreCase("SMS") && rs.getString("DIRECTION").equalsIgnoreCase("IN")){
					bean.setSmsInTotal(rs.getInt("COUNT"));
				}else{
					bean.setSmsOutTotal(rs.getInt("COUNT"));
				}
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
	
	public static List<OtherNumberBean> getOtherNumberGraphicalAnalysis(String number, Date startDateTime, int time)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		List<OtherNumberBean> beanList=new ArrayList<OtherNumberBean>();
		Date endDateTime = new Date(startDateTime.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDateTime);
		cal.add(Calendar.DATE, -time);
		startDateTime.setTime(cal.getTime().getTime());
		try{
			String sql="SELECT COUNT(*) NOOFCALLS, OTHERNUMBER "
						+"FROM test.circuitswitched_cdr "
 						+"WHERE SUBSCRIBERNUMBER =? AND STARTTIME BETWEEN ? AND ? " 
 						+"GROUP BY OTHERNUMBER";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setTimestamp(2, new Timestamp(startDateTime.getTime()));
			ps.setTimestamp(3, new Timestamp(endDateTime.getTime()));
			System.out.println("Query formed:: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next())
			{
				OtherNumberBean bean=new OtherNumberBean();
				bean.setSubscriberNumber(number);
				bean.setOtherNumber(rs.getString("OTHERNUMBER"));
				bean.setNoOfCalls(rs.getInt("NOOFCALLS"));
				beanList.add(bean);
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
		return beanList;
	}
}
