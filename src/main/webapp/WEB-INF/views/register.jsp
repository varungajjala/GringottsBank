<!DOCTYPE html>
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
    
  </head>
  <body>
  	<p></br></p>
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Registration</h3>
    				</div>
    				<form class="form-horizontal">
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<input type="text" class="form-control" id="inputEmail3" placeholder="Username">
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<input type="email" class="form-control" id="inputEmail" placeholder="Email">
      								</div>
    							</div>
  						</div>
  						
  						
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Address</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
      									<input type="text" class="form-control" id="inputAddressStreet" placeholder="Street Address">
      									<input type="text" class="form-control" id="inputAddressApt" placeholder="Apt#">
      									<input type="text" class="form-control" id="inputAddressState" placeholder="State">
      									<input type="text" class="form-control" id="inputAddressZip" placeholder="zip">
      								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<input type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX">
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputUserType" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="dropdown">
									  <button class="btn btn-default dropdown-toggle" type="button" id="inputUserType" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									    Select User Type
									    <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    <li><a href="#">Single User</a></li>
									    <li><a href="#">Merchant</a></li>
									   
									  </ul>
									</div>
    							</div>
  						</div>
  						<div id="merchantID" style="visibility: hidden;">
  							<div class="form-group">
    						<label for="inputMerchantID" class="col-sm-2 control-label">Merchant ID</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<input type="text" class="form-control" id="inputMerchantID" placeholder="Merchant ID">
      								</div>
    							</div>
  							</div>
  						</div>
  						
  						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="button" class="btn btn-primary">Register</button>
     						
    						</div>
  						</div>
					</form>		
  				</div>
			</div>
  		
  		
  		</div>
  		<div class="col-md-2"></div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    
    </script>
    
  </body>
</html>