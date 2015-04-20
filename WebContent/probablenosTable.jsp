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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Criminal Prediction System</title>
	</head>
	<body>
		<% HashMap<String, CallDetailsBean> probableNumbersList = (HashMap<String, CallDetailsBean>)session.getAttribute("ProbableNumbers"); %>
		<table class="table">
		    <thead>
		        <tr>
		            <th>Record</th>
		            <th>Subscriber Number</th>
		            <th>Related IMEI</th>
		            <th>Related IMSI</th>
		            <th>Subscriber Operator</th>
		            <th>Tabular Analytics</th>
		            <th>Location Analytics</th>
		            <th>Graphical Analytics</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<% int i=0;
		    	for(String keys: probableNumbersList.keySet()){
		    		List callList=((List<CallDetailsBean>)probableNumbersList.get(keys));
		    		int count=callList.size();
		    		CallDetailsBean bean=(CallDetailsBean)callList.get(0);
		    	%>
		    	<%if(count>20){ %>
		    	 	<tr class="danger">
		    	 	
		    	 <%}else if (count>10){ %>
		    	 	<tr class="warning">
			    	 	
		    	 <%}else { %>
		    	 	<tr class="success">
			    	 	
		    	 <%} %>
		    	 		<td><%=i=i+1 %></td>
		            	<td><%=keys %></td>
			            <td><%=bean.getImei() %></td>
			            <td><%=bean.getImsi() %></td>
			            <td><%=bean.getSubscriberOperator() %></td>
			            <td><a href="#"><img src='images/table.png'></a></td>
			            <td><a href="#"><img src='images/location.png'></a></td>
			            <td><a href="#"><img src='images/chart.png'></a></td>
			        </tr>
		        <%} %>
		       
		    </tbody>
		</table>
	</body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>