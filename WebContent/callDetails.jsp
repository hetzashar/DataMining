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
                        <a href="home.jsp">Silent Numvber Analysis</a>
                    </li>
					  <li>
                        <a href="callDetails.jsp">Call Setails Analysis</a>
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
           <h2>Crime Period</h2>
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
          
<div>
     <label>Mobile Number </label>
	<input type="text" name="username" placeholder="Mobile number" required="required" id="username" />
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

</body>

</html>
