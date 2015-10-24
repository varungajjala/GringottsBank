<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Home</title>

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery & jQuery UI + theme (required) -->
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-ui.min.js" /> "></script>

	<!-- keyboard widget css & script (required) -->
	<link href="<c:url value="/resources/css/keyboard.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery.keyboard.js" />"></script>

	<!-- initialize keyboard (required) -->
	<script>
		$(function(){
			$('#inputPassword3').keyboard();
		});
	</script>
  </head>
  <body>
  	<p></br></p>
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Gringotts Bank <span class="glyphicon glyphicon-briefcase"></span></h3>
    				</div>
    				<form:form class="form-horizontal" commandName="loginPage" method="POST">
  						<div class="form-group">
    						<label for="inputEmail3" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path = "userId" type="text" class="form-control" id="inputEmail3" placeholder="Username"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
   							<div class="col-sm-8">
   								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
     									<form:input path = "passwd" type="password" class="form-control" id="inputPassword3" placeholder="Password"/>
   			 					</div>
   			 				</div>
  						</div>
  						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
    						<a href="register" role="button" class="btn btn-primary">Register</a>
    						<button type="submit" class="btn btn-success" >Sign-in</button>
    						<h6><a href="forgotPass" role="button" class="btn btn-danger">Forgot Password?</a></h6>
    						</div>
  						</div>
					</form:form>		
  				</div>
			</div>
			
			<div>
			<p>
			${message}
			
			</p>
			</div>
  		
  		
  		</div>
  		<div class="col-md-2"></div>
	</div>

    
  </body>
</html>