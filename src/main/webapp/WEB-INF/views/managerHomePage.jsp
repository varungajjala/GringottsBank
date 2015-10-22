
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Manager Home Page</title>

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
  	<nav class="navbar navbar-default">
  	<div class="container-fluid">
  	<ul class="nav navbar-nav">
  	<li><a href="#">Profile</a></li>
  	<li><a href="#">User Requests</a></li>
  	<li><a href="#">Authorize Critical Transactions</a></li>
  	<li><a href="#">View Authorized Transactions</a></li>
  	<li><a href="#">Modify User Accounts</a></li>
  	<li><a href="#">Notifications</a></li>
  	<li><a href="/gringotts/" role="button" class="btn btn-default pull-right">Logout</a></li>
  	</ul>
  	</div>
  	</nav>	
  	<h1 align="center" style="padding-top: 100px">Welcome Manager</h1>
<!-- 
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Account Balance <span class="glyphicon glyphicon-equalizer"> </span></h3>
    			</div>
    			<form class="form-horizontal" action="register" method="get">
  						<div class="form-group">
    						<label for="inputEmail3" class="col-sm-3 control-label">Checking Account :</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label>${checkAccBal}</label>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputPassword3" class="col-sm-3 control-label">Savings Account :</label>
   							<div class="col-sm-8">
   								<div class="input-group">
									<label>${savingAccBal}</label>
   			 					</div>
   			 				</div>
  						</div>
  						
					</form>	
    			</div>
    		</div>
    	</div>
    </div>
  --> 	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
  </body>
</html>