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
			    var mapOptions = {
			        mapTypeId: 'roadmap'
			    };
			                    
			    // Display a map on the page
			    map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
			    map.setTilt(45);
			  
			var markers = [];
			var infoWindowContent = [];
				// Multiple Markers
			<% SubscriberDetailsBean subscriber = null;
			List<LocationBean> locationBeanList=(ArrayList<LocationBean>) session.getAttribute("locationBean");
				    if (locationBeanList!=null){
				    	for(int i=0; i<locationBeanList.size(); i++){
				    		LocationBean bean=locationBeanList.get(i);
			%>
				markers[<%=i%>] = ['<%=bean.getAddress()%>', <%=bean.getLatitude()%>, <%=bean.getLongtitude()%>];
							
			<%		}
				     subscriber = locationBeanList.get(0).getSubscriber();
		    	}
				if(subscriber==null){
		    %>
		    infoWindowContent = [ '<div class="info_content">'
									+ '<h3>No Subscriber Records found for the number</h3>'
									+ '</div>' ]
			// Info Window Content
			<%}else{%>	
				
			infoWindowContent = [ '<div class="info_content">'
							+ '<h3><%=subscriber.getSubscriberNumber()%> Subscriber Details</h3>'
							+ '<p><b>Name: </b><%=subscriber.getSubscriberName()%></p><br/>'
							+ '<p><b>Address: </b><%=subscriber.getAddress()%></p><br/>'
							+ '<p><b>Circle: </b><%=subscriber.getCircle()%></p><br/>'
							+ '<p><b>Related IMSI: </b><%=subscriber.getIMSI()%></p><br/>'
							+ '<p><b>Agent Name: </b><%=subscriber.getAgentName()%></p><br/>'
							+ '<p><b>Agent Code: </b><%=subscriber.getAgentCode()%></p><br/>'
							+ '</div>' ];

				<%}%>
				// Display multiple markers on a map
				var infoWindow = new google.maps.InfoWindow(), marker, i;

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
							infoWindow.setContent(infoWindowContent[0][0]);
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
	<div id="map_wrapper">
		<div id="map_canvas" class="mapping"></div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>