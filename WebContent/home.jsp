<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="bootstrap/css/bootstrap-switch.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$('body').on('click','.option li',function(){
			var i = $(this).parents('.select').attr('id');
			var v = $(this).children().text();
			var o = $(this).attr('id');
			$('#'+i+' .selected').attr('id',o);
			$('#'+i+' .selected').text(v);
		});
		
	</script>
	<style>
		body {margin:20px;}

		.select button {width:100%; text-align:left;}
		.select .caret {position:absolute; right:10px; margin-top:10px;}
		.select:last-child>.btn {border-top-left-radius:5px; border-bottom-left-radius:5px;}
		.selected {padding-right:10px;}
		.option {width:100%;}
	</style>
	<title>Criminal Prediction System</title>
</head>
<body>
	<form action="SilentAnalysis" method="POST" id="silentAnalysis">
		<table>
			<tr>
				<td>Silent Start Date Time: </td>
				<td><input type="datetime-local" name="doblocalStart" id="doblocal"/></td>
			</tr>
			<tr>
				<td>Silent Start Date Time: </td>
				<td><input type="datetime-local" name="doblocalEnd" id="doblocal"/></td>
			</tr>
			<tr>
				<td>Circle: </td>
				<td>
				<div class="input-group-btn select" id="select">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					<span class="selected">Mumbai</span> 
					<span class="caret"></span></button>
				
				 <ul class="dropdown-menu option" role="menu">
				    <li id="1"><a href="">Mumbai</a></li>
				    <li id="2"><a href="#">Banglore</a></li>
				    <li id="3"><a href="#">Hyderabad</a></li>
				    <li id="4"><a href="#">Chennai</a></li>
				    <li id="5"><a href="#">Gujarat</a></li>
				    <li id="6"><a href="#">Delhi</a></li>
				    <li id="7"><a href="#">Kolkata</a></li>
				    <li id="8"><a href="#">Rajasthan</a></li>
				    <li id="9"><a href="#">Goa</a></li>
				    <li id="10"><a href="#">Trivandrum</a></li>
				    <li id="11"><a href="#">Chandigarh</a></li>
				    <li id="12"><a href="#">Srinagar</a></li>
				    <li id="13"><a href="#">Lucknow</a></li>
				    <li id="14"><a href="#">Patna</a></li>
				    <li id="15"><a href="#">Jaipur</a></li>
				    <li id="16"><a href="#">Gurgaon</a></li>
				  </ul>
				  </div></td>
			</tr>
			<tr>
				<td>Location: </td>
				<td><input type="text" name="location"/></td>
			</tr>
		</table>
		<input type="submit" value="Save" id="submt"
						class="btn btn-success"/>
	</form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-switch.js"></script>
</html>