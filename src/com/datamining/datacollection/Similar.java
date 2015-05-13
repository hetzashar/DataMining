package com.datamining.datacollection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datamining.beans.SimilarityBean;

public class Similar {

      
		public static List<SimilarityBean> fetchSimilarNo(String Number) {
		
		PreparedStatement pst = null;  
		List<SimilarityBean> list = new ArrayList<SimilarityBean>();
		ResultSet rs = null;  
		Connection conn=null;
		SimilarityBean bean=new SimilarityBean();
		
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			
			System.out.println(Number);
			pst = conn.prepareStatement("select * from test.similarity where NUMBER =? "
					);
			pst.setString(1, Number); 
			
			
			
			System.out.println(pst.toString());	
			rs = pst.executeQuery(); 
			
			System.out.println("status o/p" +rs);
			while(rs.next())
			{
				bean=new SimilarityBean();
				String Numbers=rs.getString("NUMBER");
				bean.setNumber(Numbers);
				String Number1=rs.getString("NUMBER1");
				bean.setNumber1(Number1);
				String Similarity1=rs.getString("similarity1");
				bean.setSimilarity1(Similarity1);
				String Number2=rs.getString("NUMBER2");
				bean.setNumber2(Number2);
				String Similarity2=rs.getString("similarity2");
				bean.setSimilarity2(Similarity2);
				String Number3=rs.getString("NUMBER3");
				bean.setNumber3(Number3);
				String Similarity3=rs.getString("similarity3");
				bean.setSimilarity3(Similarity3);
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
