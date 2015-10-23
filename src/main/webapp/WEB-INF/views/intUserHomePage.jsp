<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Internal User Home Page</title>

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
  	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    
    </script>
  
  	<p><br/></p>
  	
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
  					<ul class="nav nav-tabs" data-tabs="tabs">
  						<li class="active"><a href="#Tab1" data-toggle="tab">Profile</a></li>
  						<li><a href="#Tab2" data-toggle="tab">Manage Users</a></li>
  						<li><a href="#Tab3" data-toggle="tab">Authorize Payments</a></li>
  						<li><a href="#Tab4" data-toggle="tab">View Critical Requests</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Transaction History</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Manage Transactions</a></li>
  						
  						
					</ul>
					
					<div id="my-tab-content" class="tab-content">
            			
            			<div class="tab-pane active" id="Tab1">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Profile</h3>
    						</div>
    						
        				</div>
        				
        				<div class="tab-pane" id="Tab2">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Manage User</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab3">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Authorize Payments</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab4">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>View Critical Requests</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab5">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Transaction History</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab6">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Manage Transactions</h3>
    						</div>
    	
        			</div>	
        				</div>
				</div>
  			</div>
  			<div class="col-md-2"></div>
		</div>
	</div>
	
  </body>
</html>