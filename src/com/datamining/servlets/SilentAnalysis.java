package com.datamining.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
 * Servlet implementation class SilentAnalysisServlet
 */
public class SilentAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SilentAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post of Silent Number Analysis Servlet");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		String startTime=request.getParameter("doblocalStart");
		String endTime=request.getParameter("doblocalEnd");
		String location=request.getParameter("location");
		
		int year=Integer.parseInt(startTime.substring(0, 4));
		int month=Integer.parseInt(startTime.substring(5, 7));
		int day=Integer.parseInt(startTime.substring(8, 10));
		int hourOfDay=Integer.parseInt(startTime.substring(11, 13));
		int minute=Integer.parseInt(startTime.substring(14));
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(year, month-1, day, hourOfDay, minute);
		
		
		Date startDateTime=null;
		startDateTime=calStart.getTime();
		
		int yearEnd=Integer.parseInt(endTime.substring(0, 4));
		int monthEnd=Integer.parseInt(endTime.substring(5, 7));
		int dayEnd=Integer.parseInt(endTime.substring(8, 10));
		int hourOfDayEnd=Integer.parseInt(endTime.substring(11, 13));
		int minuteEnd=Integer.parseInt(endTime.substring(14));
		
		Calendar calEnd = Calendar.getInstance();
		calStart.set(yearEnd, monthEnd-1, dayEnd, hourOfDayEnd, minuteEnd);
		
		Date endDateTime=null;
		endDateTime=calEnd.getTime();
		session.setAttribute("crimeStartTime",new Date(startDateTime.getTime()));
		HashMap<String, List<CallDetailsBean>> map = SilentNumberAnalysis.getSilentNumberAnalytics(startDateTime, endDateTime, location);
		System.out.println(map.size());
		if(map.size()==0){
			out.write("No probable records found");
		}else{
			session.setAttribute("ProbableNumbers", map);
			RequestDispatcher rd=request.getRequestDispatcher("probablenosTable.jsp");
			rd.forward(request,response); 
		}
		
	}

}
