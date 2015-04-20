package com.datamining.datacollection;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.datamining.beans.CallDetailsBean;

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
}
