<%@page import="java.util.List"%>
<%@page import="com.datamining.beans.CallDetailsBean"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
		media="screen">
		<link href="bootstrap/css/bootstrap-switch.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/stylee.css">
      <link rel="stylesheet" type="text/css" href="css/other.css">
      <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Criminal Prediction System</title>
	</head>
	<body>
	<div id='cssmenu'>
<ul>
   <li class='active'><a href='home.jsp'><span>Home</span></a></li>
   <li><a href='login.jsp'><span>Login</span></a></li>
   <li><a href='identify.jsp'><span>Sign Up</span></a></li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>
<div class="container">
		<span class="label label-primary">Subscriber Number:: <%=session.getAttribute("subscriberNumber") %></span><br/>
		<% List<CallDetailsBean> numbersList = (List<CallDetailsBean>)session.getAttribute("callList"); %>
		<table class="table">
		    <thead>
		        <tr class="info">
		            <th>Record</th>
		            <th>Number Connected</th>
		            <th>Start Date Time</th>
		            <th>Related IMEI</th>
		            <th>Related IMSI</th>
		            <th>Subscriber Operator</th>
		            <th>Call Type</th>
		            <th>Call Direction</th>
		            <th>Call Location</th>
		            <th>Call Duration</th>
		            <th>Circle</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<% int i=0;
		    	for(CallDetailsBean bean: numbersList){
		    	%>
		    	
		    	 	<tr class="success">
		    	 		<td><%=i=i+1 %></td>
		    	 		<td><%=bean.getOtherNumber() %>
		            	<td><%=bean.getStartDateTime()%></td>
			            <td><%=bean.getImei() %></td>
			            <td><%=bean.getImsi() %></td>
			            <td><%=bean.getSubscriberOperator() %></td>
			            <td><%=bean.getCallType() %></td>
			            <td><%=bean.getCallDirection() %></td>
			            <td><%=bean.getCellLocation() %></td>
			            <td><%=bean.getDuration() %></td>
			            <td><%=bean.getCircle() %></td>
			        </tr>
		        <%} %>
		       
		    </tbody>
		</table>
		</div>
	</body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>