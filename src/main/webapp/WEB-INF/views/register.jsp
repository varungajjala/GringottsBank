
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse" %>
<html lang="en">
  <head>
  
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Register</title>
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	
    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
       <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    
    </script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
 
  </head>
  <body>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Registration</h3>
    				</div>
    				<form:form id="loginForm" class="form-horizontal" commandName="send" method="POST">
  						
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      									<form:input path="firstName" type="text" class="form-control" id="lastName" placeholder="Firstname" required="true"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="lastName" type="text" class="form-control" id="firstName" placeholder="Lastname" required="true"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<form:input path="emailId" type="email" class="form-control" id="email" placeholder="Email" required="true"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username" required="true"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="password" class="col-sm-2 control-label">Password</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
      									<form:input path="passwd" type="password" class="form-control" id="inputPassword" placeholder="Enter Password" required="true"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Address</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
        								<form:input path="address" type="text" class="form-control" id="inputAddressStreet" placeholder="Street Address"/>
    									<form:input path="city" type="text" class="form-control" id="inputAddressCity" placeholder="City"/>
      									<form:input path="state" type="text" class="form-control" id="inputAddressState" placeholder="State"/>
      									<form:input path="country" type="text" class="form-control" id="inputAddressCountry" placeholder="Country"/>
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" value ="null" placeholder="zipcode" required="true" pattern="^\d{5}$" min="1000" max="99999"/>            									
     								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX" required="true" pattern="^\d{10}$"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label for="singleUser">
						    				<form:radiobutton path ="utype" id="singleUser" name="chkUserType" value="singleUser" required="true" />
						    				Single User  
										</label>
										<label for="chkNo">
						    				<form:radiobutton path = "utype" id="merchant" name="chkUserType" value="merchant" required="true"/>
						   					 Merchant
										</label> 
      								</div>
    							</div>
  						</div>
  						
						
  						<div class="form-group" id="dvPII" >
    						<label for="inputContact" class="col-sm-2 control-label">Identification Number(PII)</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      								    <form:input path="identificationNo" type="tel" class="form-control" id="inputContact" placeholder="Enter SSN for single user and Merchant ID for merchant" required="true" pattern="^\d{8}$"/>
      								
      								</div>
    							</div>
  						</div>
  				
  						 
  						 <div class="form-group" id="captcha">
  						 <label for="captcha" class="col-sm-2 control-label">Are you a human?</label>
					        
					        
					        
					       <div class="g-recaptcha" data-sitekey="6Lc_0f4SAAAAAF9ZA_d7Dxi9qRbPMMNW-tLSvhe6"></div>
					        
  						 </div>
  						 <br>
  						 <br>
  						 
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" onsubmit="return myFunction()" >Register</button>
     						<a href="home" role="button" class="btn btn-success" >Back to Sign In</a>
    						</div>
  						</div>
  						
					</form:form>		
  				</div>
			</div>
  		
  			<div>
  			<P>Upon successful registration save the files.</P>
  			</div>
  		
  		</div>
  		<div class="col-md-2"></div>
	</div>
	
 
    
  </body>
</html>