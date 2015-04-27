package com.datamining.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datamining.datacollection.ConnectionPool;
import com.datamining.datacollection.LoginDao;
import com.datamining.datacollection.AuthDao;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateServlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();    
		 Connection conn = null;
		String authid=request.getParameter("authid");    
		
		HttpSession session = request.getSession(false); 
		try{
			   
//			if(session!=null)  
//				session.setAttribute("username", username);  
			
			 try {
					conn=(Connection) ConnectionPool.getConnectionFromPool();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			if(AuthDao.validate(authid)){    
				RequestDispatcher rd=null;
				rd=request.getRequestDispatcher("signup.jsp");
				rd.forward(request,response);    
			}
			else{    
				out.println("<script type=\"text/javascript\">");
				out.print("alert('Invalid ID. Please Try Again');");   
				out.println("</script>");    
				RequestDispatcher rd=request.getRequestDispatcher("identify.jsp");    
				rd.include(request,response);    
			}
			out.close();

}
		catch(Exception e){
			e.printStackTrace();
		}

	
}
	}

