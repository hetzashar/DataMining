<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A date range picker for Bootstrap</title>
      <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
      <link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<!--       <link rel="stylesheet" type="text/css" media="all" href="css/daterangepicker-bs3.css" /> -->
      <link rel="stylesheet" type="text/css" href="css/stylee.css">
      <link rel="stylesheet" type="text/css" href="css/other.css">
       <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
      <link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
      <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
     href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
    
      <link rel="stylesheet" type="text/css"  href="css/bootstrap-datetimepicker.min.css" />
   
      <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
      
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!--       <script type="text/javascript" src="js/moment.js"></script> -->
<!--       <script type="text/javascript" src="js/daterangepicker.js"></script> -->
<title>Insert title here</title>
</head>
<body>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='home.jsp'><span>Home</span></a></li>
   <li><a href='login.jsp'><span>Login</span></a></li>
   <li><a href='identify.jsp'><span>Sign Up</span></a></li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>
<div class="main">
 <form  role="form" class="form-inline" action="SilentAnalysis" method="POST" id="silentAnalysis">
      <div class="one">
        <div class="register">
          
           <h2>Crime Period</h2>
           
           <div >
            <label padding: 6px 12px 2px 12px;">Start Time </label> 
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

<div >
 <label padding: 6px 12px 2px 12px;">Start Time </label> 
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
          
<!--            <div> -->
<!-- 				<label>Silent Start Date Time : </label>  -->
<!-- 				<input type="datetime-local" name="doblocalStart" id="doblocal"/> -->
<!-- 			</div> -->
<!-- 		<div> -->
<!-- 				<label>Silent Start Date Time : </label>  -->
<!-- 				<input type="datetime-local" name="doblocalEnd" id="doblocal"/> -->
<!-- 			</div> -->

              <br>
               <div>
       <label >Circle : </label> 
       <div style="float: right; width: 380px">
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
				  </div>
  	 </div>
  	 </div>
  	 <br>
  	
  	 <div>
	   <label >Location :    </label>
		<input type="text" class="form-control input-normal" name="location" placeholder="location" required="" id="location" />
	
		</div>		
		<br>		
			<div style="text-align:center">  
    <input type="submit" class="btn btn-success" value="Save" id="submt"  />  
            </div>  

</div>
</div>

</form>
</div>
</body>
</html>