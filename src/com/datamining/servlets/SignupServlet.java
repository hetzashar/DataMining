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
import com.datamining.datacollection.UserDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
	
        String username = request.getParameter("username");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(username);
        HttpSession session = request.getSession(false);
		PreparedStatement ps=null;
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
//        HttpSession session = request.getSession();
        try{
        	
        	try {
			conn=(Connection) ConnectionPool.getConnectionFromPool();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
		//ps=con.prepareStatement(sql);
      
        	if(UserDao.validates(username)){    
				RequestDispatcher rd=null;
				rd=request.getRequestDispatcher("signup.jsp");
				out.print("<p style=\"color:red\">Invalid ID</p>");  
				rd.forward(request,response);    
			}
			else{    
				  
				//RequestDispatcher rd=request.getRequestDispatcher("login.jsp");    
				//rd.include(request,response);    

        //loading drivers for mysql
       
        	PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into userlog(username, fname, lname, email, password) values(?,?,?,?,?)");
             
        	 pst.setString(1,username);  
             pst.setString(2,fname);        
             pst.setString(3,lname);
             pst.setString(4,email);
             pst.setString(5,password);
             
             int i = pst.executeUpdate();
//             conn.commit(); 
             String msg=" ";
                  		
             if(i!=0){  
               msg="Record has been inserted";
               out.println("<font size='6' color=blue>" + msg + "</font>");  
               response.sendRedirect("login.jsp");

             }  
             else{  
               msg="failed to insert the data";
               out.println("<font size='6' color=blue>" + msg + "</font>");
              }  
             pst.close();
             }
            
           }  
       
           catch (Exception e){  
             out.println(e);  
           }  
                 
                
	}

}
