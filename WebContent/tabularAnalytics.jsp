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
		<link rel="stylesheet" type="text/css" href="css/style.css">
       <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		 <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<title>Criminal Prediction System</title>
	</head>
	<body>
	 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<h1> CRIMINAL PREDICTION SYSTEM</h1>
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
				
              <a class="navbar-brand" href="index.html"></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                <li>
                        <a href="main.jsp">Home</a>
                    </li>
                    <li>
                        <a href="home.jsp">Silent Number Analysis</a>
                    </li>
					  <li>
                        <a href="callDetails.jsp">Call Details Analysis</a>
                    </li>
                    <li>
                        <a href="login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
     <br>
    <br>
    <br>
    <br>
<div>
		<% List<CallDetailsBean> numbersList = (List<CallDetailsBean>)session.getAttribute("callList"); %>
		<center>
		<table class="table table-striped table-bordered table-condensed" style="width:1250px;">
		    <thead>
		    <h2><span class="label label-primary">Subscriber Number:<%=session.getAttribute("subscriberNumber") %></span></h2>
		    <br>
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
		</center>
		</div>
	</body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>