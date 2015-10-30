
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Register</title>

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
    
  </head>
  <body>
  	<p></br></p>
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Forgot Password?</h3>
    				</div>
    				<form:form class="form-horizontal" action = "newPass" commandName="newPassBean" method="POST">
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Enter New Password</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="passwd" type="text" class="form-control" id="inputUsername" placeholder="Enter new password"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success">Submit</button>
     						
    						</div>
  						</div>
					</form:form>		
  				</div>
			</div>
  		
  		
  		</div>
  		<div class="col-md-2"></div>
	</div>

    
  </body>
</html>