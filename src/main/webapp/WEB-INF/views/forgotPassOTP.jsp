<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    
<title>One Time Password</title>
</head>
<body>
			
			<form:form class="form-horizontal" action = "forgotPassOTP" commandName="confirmOTP" method="POST">
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Enter OTP</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="password" type="text" class="form-control" id="inputUsername" placeholder="Please enter OTP"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success">Submit Request</button>
     						
    						</div>
  						</div>
					</form:form>
    </body>
</html>