package com.datamining.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datamining.beans.CallDetailsBean;
import com.datamining.datacollection.SilentNumberAnalysis;
import com.datamining.datacollection.ReadDataFromHadoop;


/**
 * Servlet implementation class TabularAnalytics
 */
public class TabularAnalytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabularAnalytics() {
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
		List<CallDetailsBean> beanList = SilentNumberAnalysis.getTabularAnalysis(subscriberNumber, startDateTime, noOfDays);
		session.setAttribute("callList", beanList);
		
		if(beanList.size()==0){
			out.write("No probable records found");
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("tabularAnalytics.jsp");
			rd.forward(request,response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post of Tabular Analysis Servlet");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
	}

}
