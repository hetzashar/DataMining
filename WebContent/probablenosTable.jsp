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
		<link href='http://fonts.googleapis.com/css?family=Changa+One' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Cabin:400,500' rel='stylesheet' type='text/css'>
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
       <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	     <!-- Bootstrap Core CSS -->
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
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
	<div>
		<% HashMap<String, CallDetailsBean> probableNumbersList = (HashMap<String, CallDetailsBean>)session.getAttribute("ProbableNumbers"); %>
		<center>
		<table class="table table-striped table-bordered table-condensed" style="width:1250px;">
		    <thead>
		        <tr class="info">
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
		            	<td><%=keys%></td>
			            <td><%=bean.getImei() %></td>
			            <td><%=bean.getImsi() %></td>
			            <td><%=bean.getSubscriberOperator() %></td>
			            <td><a href="/TabularAnalytics?param1=<%=keys%>"><img src='images/table.png'></a></td>
			            <td><a href="/LocationAnalysis?param1=<%=keys%>"><img src='images/location.png'></a></td>
			            <td><a href="/GraphAnalysis?param1=<%=keys%>"><img src='images/chart.png'></a></td>
			        </tr>
		        <%} %>
		       
		    </tbody>
		</table>
		</center>
		</div>
		<script src="js/jquery.js"></script>
	<script src="bootstrap/js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/dropdown.js"></script>
	
	</body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>