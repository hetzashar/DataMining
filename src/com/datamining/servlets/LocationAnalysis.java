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
		System.out.println("Inside doGet");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		System.out.println("Param1:: "+request.getParameter("param1"));
		HttpSession session= request.getSession();
		Date startDateTime=(Date)session.getAttribute("crimeStartTime");
		int noOfDays=7;
		List<CallDetailsBean> beanList = SilentNumberAnalysis.getTabularAnalysis(request.getParameter("param1"), startDateTime, noOfDays);
		session.setAttribute("callList", beanList);
		
		if(beanList.size()==0){
			out.write("No probable records found");
		}else{
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
