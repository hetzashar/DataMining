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
		<script src="js/highcharts.js"></script>
		<script src="js/highcharts-3d.js"></script>
		<script src="js/modules/exporting.js"></script>
	
		<div id="container"></div>
		<div id="sliders">
			<table>
				<tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span></td></tr>
			    <tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span></td></tr>
			</table>
		</div>
		<div id="otherNumberContainer"></div>
		<div id="slidersOtherNumber">
			<table>
				<tr><td>Alpha Angle</td><td><input id="ROth0" type="range" min="0" max="45" value="15"/> <span id="ROth0-value" class="value"></span></td></tr>
			    <tr><td>Beta Angle</td><td><input id="ROth1" type="range" min="0" max="45" value="15"/> <span id="ROth1-value" class="value"></span></td></tr>
			</table>
		</div>
	</body>
</html>
