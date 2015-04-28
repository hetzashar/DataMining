<%@page import="com.datamining.beans.OtherNumberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datamining.beans.CallSummaryBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href='http://fonts.googleapis.com/css?family=Changa+One' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Cabin:400,500' rel='stylesheet' type='text/css'>
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/style.css">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	     <!-- Bootstrap Core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		
		<title>Criminal Prediction System</title>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<style type="text/css">
#container, #sliders {
	min-width: 310px; 
	max-width: 800px;
	margin: 0 auto;
}
#container {
	height: 400px; 
}
#otherNumberContainer, #slidersOtherNumber {
	min-width: 310px; 
	max-width: 800px;
	margin: 0 auto;
}
#otherNumberContainer {
	height: 400px; 
}
		</style>
		<script type="text/javascript">
			$(function () {
			    // Set up the chart
			    var dataos=new Array();
			    var otherNumber=new Array();
			    var otherNumberCallCount=new Array();
			    
			    <%
			    	CallSummaryBean bean = (CallSummaryBean)session.getAttribute("summaryBean");
			    	if(bean != null){
			    		int i=0;
			    		%>
			    			dataos[<%=i++%>]=<%=bean.getVcInTotal()%>;
			    			dataos[<%=i++%>]=<%=bean.getVcOutTotal()%>;
			    			dataos[<%=i++%>]=<%=bean.getSmsInTotal()%>;
			    			dataos[<%=i++%>]=<%=bean.getSmsOutTotal()%>;
			    		<%
			    	}
			    	List<OtherNumberBean> otherNumberBean = (ArrayList<OtherNumberBean>)session.getAttribute("otherNumberBean");
			    	if(otherNumberBean!=null){
			    		for(int j=0;j<otherNumberBean.size();j++){
			    			OtherNumberBean otherBean=(OtherNumberBean)otherNumberBean.get(j);%>
			    			
			    			otherNumber[<%=j%>]=<%=otherBean.getOtherNumber()%>;
			    			otherNumberCallCount[<%=j%>]=<%=otherBean.getNoOfCalls()%>;
			    			
			    		<%}
			    	}
			    %>
			    var chart = new Highcharts.Chart({
			        chart: {
			            renderTo: 'container',
			            type: 'column',
			            margin: 75,
			            options3d: {
			                enabled: true,
			                alpha: 15,
			                beta: 15,
			                depth: 50,
			                viewDistance: 25
			            }
			        },
			        title: {
			            text: 'Call Summary'
			        },
			        subtitle: {
			            text: 'Summary of Number: <%=session.getAttribute("subscriberNumber")%>'
			        },
			        xAxis: {
			        	categories: ['VC-IN', 'VC-OUT', 'SMS-IN', 'SMS-OUT']
			        	},
		        	yAxis: {
		        		title: {
		        			text: 'Count'
		        		}
		        	},
			        plotOptions: {
			            column: {
			                depth: 25
			            }
			        },
			        series: [{
			            data: dataos
			        }]
			    });
			
			    var otherNumberChart = new Highcharts.Chart({
			        chart: {
			            renderTo: 'otherNumberContainer',
			            type: 'column',
			            margin: 75,
			            options3d: {
			                enabled: true,
			                alpha: 15,
			                beta: 15,
			                depth: 50,
			                viewDistance: 25
			            }
			        },
			        title: {
			            text: 'Other Number Calls'
			        },
			        subtitle: {
			            text: 'Calls to Other Numbers from: <%=session.getAttribute("subscriberNumber")%>'
			        },
			        xAxis: {
			        	categories: otherNumber
			        	},
		        	yAxis: {
		        		title: {
		        			text: 'Count'
		        		}
		        	},
			        plotOptions: {
			            column: {
			                depth: 25
			            }
			        },
			        series: [{
			            data: otherNumberCallCount
			        }]
			    });
			    
			    function showValues() {
			        $('#R0-value').html(chart.options.chart.options3d.alpha);
			        $('#R1-value').html(chart.options.chart.options3d.beta);
			    }
				
			    function showValuesOth() {
			    	$('#ROth0-value').html(otherNumberChart.options.chart.options3d.alpha);
		        	$('#ROth1-value').html(otherNumberChart.options.chart.options3d.beta);
			    }
			    // Activate the sliders
			    $('#R0').on('change', function () {
			        chart.options.chart.options3d.alpha = this.value;
			        showValues();
			        chart.redraw(false);
			    });
			    $('#R1').on('change', function () {
			        chart.options.chart.options3d.beta = this.value;
			        showValues();
			        chart.redraw(false);
			    });
			    $('#ROth0').on('change', function () {
			    	otherNumberChart.options.chart.options3d.alpha = this.value;
			        showValuesOth();
			        otherNumberChart.redraw(false);
			    });
			    $('#ROth1').on('change', function () {
			    	otherNumberChart.options.chart.options3d.beta = this.value;
			        showValuesOth();
			        otherNumberChart.redraw(false);
			    });
			
			    showValues();
			});
		</script>
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
		<script src="js/highcharts.js"></script>
		<script src="js/highcharts-3d.js"></script>
		<script src="js/modules/exporting.js"></script>
		
	
		<div id="container"></div>
		<br>
		<div id="sliders">
			<table>
				<tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span></td></tr>
			    <tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span></td></tr>
			</table>
		</div>
		
		<div id="otherNumberContainer"></div>
		<br>
		<div id="slidersOtherNumber">
			<table>
				<tr><td>Alpha Angle</td><td><input id="ROth0" type="range" min="0" max="45" value="15"/> <span id="ROth0-value" class="value"></span></td></tr>
			    <tr><td>Beta Angle</td><td><input id="ROth1" type="range" min="0" max="45" value="15"/> <span id="ROth1-value" class="value"></span></td></tr>
			</table>
		</div>
	</body>
</html>
