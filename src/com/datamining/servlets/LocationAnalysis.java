package com.datamining.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datamining.beans.CallDetailsBean;
import com.datamining.beans.LocationBean;
import com.datamining.beans.SubscriberDetailsBean;
import com.datamining.datacollection.SilentNumberAnalysis;

/**
 * Servlet implementation class LocationAnalysis
 */
public class LocationAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get of Tabular Analysis Servlet");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		String subscriberNumber = request.getParameter("param1");
		HttpSession session= request.getSession();
		session.setAttribute("subscriberNumber", subscriberNumber);
		Date startDateTime=(Date)session.getAttribute("crimeStartTime");
		int noOfDays=120;
		List<LocationBean> beanList = SilentNumberAnalysis.getLocationAnalysis(subscriberNumber, startDateTime, noOfDays);
		session.setAttribute("locationBean", beanList);
		
		if(beanList.size()==0){
			out.write("No probable records found");
		}else{
			LocationBean bean=beanList.get(0);
			SubscriberDetailsBean subscriberBean = SilentNumberAnalysis.getSubscriberDetails(subscriberNumber);
			bean.setSubscriber(subscriberBean);
			beanList.set(0, bean);
			session.setAttribute("locationBean", beanList);
			RequestDispatcher rd=request.getRequestDispatcher("locationAnalytics.jsp");
			rd.forward(request,response); 
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
