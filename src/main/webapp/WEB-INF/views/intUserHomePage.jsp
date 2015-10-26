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
  	
  	<div>
  		<form:form class="form-horizontal" action="logout" method="POST">
  			<button type="submit" class="btn btn-danger" style="float: right; margin-right: 50px;">Logout</button>
  		</form:form>
  	</div>
  	
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
            			
            			<div class="tab-pane" id="Tab1">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Profile</h3>
    						</div>
    						<form:form id="profileForm" class="form-horizontal" action="profile" commandName="profile" method="POST">
  						
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<p>
    								${firstName}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<p>
    								${lastName}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<p>
    								${email}
    								</p>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<p>
    								${Username}
    								</p>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Street Address</label>
    							<div class="col-sm-8">
    								<p>
    								${streetAddress}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">City</label>
    							<div class="col-sm-8">
    								<p>
    								${city}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">State</label>
    							<div class="col-sm-8">
    								<p>
    								${state}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Country</label>
    							<div class="col-sm-8">
    								<p>
    								${country}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Zipcode</label>
    							<div class="col-sm-8">
    								<p>
    								${zip}
    								</p>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact Number</label>
    							<div class="col-sm-8">
    								<p>
    								${contactNo}
    								</p>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<p>
    								${userType}
    								</p>
    							</div>
  						</div>
						
  						<div class="form-group" id="dvPII" >
    						<label for="inputContact" class="col-sm-2 control-label">Identification Number(PII)</label>
    							<div class="col-sm-8">
    								<p>
    								${pii}
    								</p>
    							</div>
  						</div>
  				
  						
					</form:form>
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