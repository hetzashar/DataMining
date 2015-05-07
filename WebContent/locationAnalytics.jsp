<%@page import="com.datamining.beans.SubscriberDetailsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datamining.beans.LocationBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criminal Prediction System</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-switch.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href='http://fonts.googleapis.com/css?family=Changa+One' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Cabin:400,500' rel='stylesheet' type='text/css'>
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
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
        
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
			jQuery(function($) {
			    // Asynchronously Load the map API 
			    var script = document.createElement('script');
			    script.src = "http://maps.googleapis.com/maps/api/js?sensor=false&callback=initialize";
			    document.body.appendChild(script);
			});
			function initialize() {
			    var map;
			    var bounds = new google.maps.LatLngBounds();
			    <% SubscriberDetailsBean subscriber = null;
			    List<LocationBean> locationBeanList=(ArrayList<LocationBean>) session.getAttribute("locationBean");
			    float latitude= locationBeanList.get(0).getLatitude();
			    float longtitude = locationBeanList.get(0).getLongtitude();
			    %>
			    var mapOptions = {
			        mapTypeId: 'roadmap',
			        center: new google.maps.LatLng(<%=latitude%>, <%=longtitude%>),
			    };
			                    
			    // Display a map on the page
			    map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
			    map.setTilt(45);
			  
			var markers = [];
			var infoWindowContent = [];
				// Multiple Markers
			<%  subscriber = null;
			 locationBeanList=(ArrayList<LocationBean>) session.getAttribute("locationBean");
				    if (locationBeanList!=null){
				    	for(int i=0; i<locationBeanList.size(); i++){
				    		LocationBean bean=locationBeanList.get(i);
			%>
				markers[<%=i%>] = ['<%=bean.getAddress()%>', <%=bean.getLatitude()%>, <%=bean.getLongtitude()%>];
							
			<%		}
				     subscriber = locationBeanList.get(0).getSubscriber();
		    	}
				if(subscriber==null || subscriber.getSubscriberNumber()==null){
		    %>
		    var contentString = '<div class="info_content">'
									+ '<h3>No Subscriber Records found for the number</h3>'
									+ '</div>';
			// Info Window Content
			<%}else{%>	
			var name='<%=subscriber.getSubscriberName()%>';
			var number='<%=subscriber.getSubscriberNumber()%>';
			var circle='<%=subscriber.getCircle()%>';
			var imsi='<%=subscriber.getIMSI()%>';
			var address='<%=subscriber.getAddress()%>';
			var agentName='<%=subscriber.getAgentName()%>';
			var agentCode='<%=subscriber.getAgentCode()%>';
			
			var contentString = '<div class="info_content">'
				+ '<h3>Subscriber Details: '+number +'</h3>'
				+ '<p><b>Name: </b>'+name+'</p>'
				+ '<p><b>Circle: </b>'+circle+'</p>'
				+ '<p><b>Address: </b>'+address+'</p>'
				+ '<p><b>Related IMSI: </b>'+imsi+'</p>'
				+ '<p><b>Agent Name: </b>'+agentName+'</p>'
				+ '<p><b>Agent Code: </b>'+agentCode+'</p><br/>'	
				+ '</div>';	
			

				<%}%>
				// Display multiple markers on a map
				var infoWindow = new google.maps.InfoWindow({content:contentString}), marker, i;

				// Loop through our array of markers & place each one on the map  
				for (i = 0; i < markers.length; i++) {
					var position = new google.maps.LatLng(markers[i][1],
							markers[i][2]);
					bounds.extend(position);
					marker = new google.maps.Marker({
						position : position,
						map : map,
						title : markers[i][0]
					});

					// Allow each marker to have an info window    
					google.maps.event.addListener(marker, 'click', (function(
							marker, i) {
						return function() {
							infoWindow.open(map, marker);
						}
					})(marker, i));

					// Automatically center the map fitting all markers on the screen
					map.fitBounds(bounds);
				}

				// Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
				var boundsListener = google.maps.event.addListener((map),
						'bounds_changed', function(event) {
							this.setZoom(14);
							google.maps.event.removeListener(boundsListener);
						});

			}
		</script>
<style>
#map_wrapper {
	height: 400px;
}

#map_canvas {
	width: 100%;
	height: 100%;
}
</style>
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
    <br>
    <br>
	<div id="map_wrapper">
		<div id="map_canvas" class="mapping"></div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>