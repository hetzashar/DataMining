<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="en">
<head>
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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
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

<style>
	.demo select {
		border: 0 !important;  /*Removes border*/
		-webkit-appearance: none;  /*Removes default chrome and safari style*/
		-moz-appearance: none; /* Removes Default Firefox style*/
		background: #0088cc url(img/select-arrow.png) no-repeat 90% center;
		width: 100px; /*Width of select dropdown to give space for arrow image*/
		text-indent: 0.01px; /* Removes default arrow from firefox*/
		text-overflow: "";  /*Removes default arrow from firefox*/ /*My custom style for fonts*/
		color: #FFF;
		border-radius: 15px;
		padding: 5px;
		box-shadow: inset 0 0 5px rgba(000,000,000, 0.5);
	}
	.demo select.balck {
		background-color: #000;
	}
	.demo select.option3 {
		border-radius: 10px 0;
	}
</style>
<title>CRIMINAL PREDICTION</title>
</head>
<body>
    <!-- Navigation -->
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
<div class="containerr">
<section id="content">
 <form  role="form"  action="SilentAnalysis" method="POST" id="silentAnalysis">
           <h2>Silent Number Analysis</h2>
	<div class="one">
        <div class="register">
           <div>
            <label>Start Time </label> 
 				 <div id="datetimepicker2" class="input-append">
    				<input  type="datetime-local" name="doblocalStart" id="doblocal"></input>
 				 </div>
 			 </div>
		 <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
   		 </script> 
    	<script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
   		 </script>
		<script type="text/javascript">
  $(function() {
    $('#datetimepicker2').datetimepicker({
      language: 'en',
      pick12HourFormat: true
    });
  });
</script>

<div>
 <label>Start Time </label> 
  <div id="datetimepicker1" class="input-append">
    <input  type="datetime-local" name="doblocalEnd" id="doblocal"></input>
  </div>
</div>
<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
      language: 'en',
      pick12HourFormat: true
    });
  });
</script>
<br>
<br>
<form class="demo">
<label >Circle </label> 
 
	<select>
	 <span class="selected">Mumbai</span> 
					<span class="caret"></span></button>
		<option>Mumbai</option>
		<option>bangalore </option>
		<option>Hyderabad</option>
		<option>Chennai</option>
		<option>Gujarat</option>
		<option>Delhi</option>
		<option>Kolkata</option>
		<option>Rajasthan</option>
		<option>Goa</option>
		<option>Trivandrum</option>
		<option>Chandigarh</option>
		<option>Srinagar</option>
		<option>Lucknow</option>
		<option>Patna</option>
		<option>Jaipur</option>
		<option>Gurgaon</option>
	</select>
	
</form>
  	 			<br>
  	 			<br>
  	 			<div>  	  	   
		<input type="text"  name="location" placeholder="location" required="" id="location" />
			</div>
		<div style="text-align:center">  
    <input type="submit" class="btn btn-success" value="Submit" id="submt"  />  
            </div>  
</div>
</div>
</form>
    </section> 
   </div>
    <!-- /.container -->
    <!-- jQuery -->
    <script src="js/jquery.js"></script>
	<script src="bootstrap/js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/dropdown.js"></script>
<script src="js/jquery.js"></script>
	
</body>

</html>
