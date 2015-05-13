package com.datamining.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.datamining.datacollection.CallDetails;
import com.datamining.datacollection.ReadDataFromHadoop;
import com.datamining.beans.SimilarityBean;
import com.datamining.datacollection.Similar;

/**
 * Servlet implementation class SimilarNo
 */
@WebServlet("/SimilarNo")
public class SimilarNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimilarNo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Post of Similar Number Analytics");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		String subscriberNumber = request.getParameter("param1");
		HttpSession session = request.getSession();
		
		
		//String Number=request.getParameter("NUMBER");
		String subscribernumber=request.getParameter("subscribernumber");
		
		List<SimilarityBean> beanList = Similar.fetchSimilarNo(subscriberNumber);
		session.setAttribute("numlist", beanList);
		session.setAttribute("subscriberNumber",subscriberNumber);
		if(beanList.size()==0){
			out.write("No probable records found");
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("similarNo.jsp");
			rd.forward(request,response); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post of Similar Number Analytics");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		String subscriberNumber = request.getParameter("param1");
		HttpSession session = request.getSession();
		
		
		//String Number=request.getParameter("NUMBER");
		String subscribernumber=request.getParameter("subscribernumber");
		
		List<SimilarityBean> beanList = Similar.fetchSimilarNo(subscriberNumber);
		session.setAttribute("numlist", beanList);
		session.setAttribute("subscriberNumber",subscriberNumber);
		if(beanList.size()==0){
			out.write("No probable records found");
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("similarNo.jsp");
			rd.forward(request,response); 
		}
		
		
		
	}

		
	

}
