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
  						<li><a href="#Tab1" data-toggle="tab">Profile</a></li>
  						<li><a href="#Tab2" data-toggle="tab">User Requests</a></li>
  						<li><a href="#Tab3" data-toggle="tab">Authorize Critical Transactions</a></li>
  						<li><a href="#Tab4" data-toggle="tab">View Authorized Transactions</a></li>
  						<li class="active"><a href="#Tab5" data-toggle="tab">Delete User</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Modify User</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Create User</a></li>
  						
					</ul>
					
					<div id="my-tab-content" class="tab-content">
            			
            			<div class="tab-pane" id="Tab1">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Profile</h3>
    						</div>
    						
        				</div>
        				
        				<div class="tab-pane" id="Tab2">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>User Requests</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab3">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Authorize Critical Transactions</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab4">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>View Authorized Transactions</h3>
    						</div>
    						
        				</div>
        				
            			<div class="tab-pane active" id="Tab5">
        					<div class="page-header" style="margin-top: 5px;" align="center">
								<h3>Delete User</h3>
    						</div>
    						<form:form class="form-horizontal" action="delete_user" commandName="deleteOp" method="POST">
  								<div class="form-group">
    								<label for="Uniuqe id" class="col-sm-2 control-label">Unique ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      										<form:input path="uniqId" type="text" class="form-control" id="u_id_1" placeholder="Unique ID"/>
      									</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="Employe ID" class="col-sm-2 control-label">Employe ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="balance" type="text" class="form-control" id="e_id_1" placeholder="Employe ID"/>
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="Account Number" class="col-sm-2 control-label">Account Number</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="accountno" type="number" class="form-control" id="acc_1" placeholder="Account Number" />
      										</div>
    								</div>
  								</div>
  						
  								<div class="form-group">
    								<label for="inputUserType" class="col-sm-2 control-label">User Type</label>
    								<div class="col-sm-8">
    									<div class="dropdown">
									  		<button class="btn btn-default dropdown-toggle" type="button" id="inputUserType_1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									    		Select User Type
									    		<span class="caret"></span>
									  		</button>
									  		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    		<li><a href="#">Internal User</a></li>
									    		<li><a href="#">External User</a></li>
									  		</ul>
										</div>
    								</div>
  								</div>
  						  		
  								<div class="form-group">
    								<div class="col-sm-offset-2 col-sm-10">
    								<button type="submit" class="btn btn-success">Delete User</button>
     						
    								</div>
  								</div>
							</form:form>
        				</div>
        				
        				<div class="tab-pane" id="Tab6">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Modify User</h3>
    						</div>
    						<form:form class="form-horizontal" action="modify_user" commandName="modifyOp" method="POST">
  								<div class="form-group">
    								<label for="First Name" class="col-sm-2 control-label">First Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="firstName" type="text" class="form-control" id="f_name" placeholder="First Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="Last Name" class="col-sm-2 control-label">Last Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="lastName" type="text" class="form-control" id="l_name" placeholder="Last Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="contactNo" class="col-sm-2 control-label">Contact Number</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="contactNo" type="text" class="form-control" id="contact_No" placeholder="Contact Number" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="emailId" class="col-sm-2 control-label">Email ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="emailId" type="text" class="form-control" id="email_id" placeholder="Email ID" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="username" class="col-sm-2 control-label">User Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="username" type="text" class="form-control" id="u_name" placeholder="User Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="uniqId" class="col-sm-2 control-label">Unique ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="uniqId" type="text" class="form-control" id="u_ID_2" placeholder="Unique ID" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="address" class="col-sm-2 control-label">Address</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="address" type="text" class="form-control" id="address" placeholder="Address" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="city" class="col-sm-2 control-label">City</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="city" type="text" class="form-control" id="city" placeholder="city" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="zipcode" class="col-sm-2 control-label">Zipcode</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="zipcode" type="number" class="form-control" id="zipcode" placeholder="Zipcode" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="state" class="col-sm-2 control-label">State</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="state" type="text" class="form-control" id="state" placeholder="state" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="country" class="col-sm-2 control-label">Country</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="country" type="text" class="form-control" id="country" placeholder="country" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<div class="col-sm-offset-2 col-sm-10">
    								<button type="submit" class="btn btn-success">Modify User</button>
    								</div>
  								</div>
							</form:form>
        				</div>
        				
        				<div class="tab-pane" id="Tab7">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Create User</h3>
    						</div>
    						<form:form class="form-horizontal" action="create_user" commandName="createOp" method="POST">
  								<div class="form-group">
    								<label for="First Name" class="col-sm-2 control-label">First Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="firstName" type="text" class="form-control" id="f_name_1" placeholder="First Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="Last Name" class="col-sm-2 control-label">Last Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="lastName" type="text" class="form-control" id="l_name_1" placeholder="Last Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="contactNo" class="col-sm-2 control-label">Contact Number</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="contactNo" type="text" class="form-control" id="contact_No_1" placeholder="Contact Number" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="emailId" class="col-sm-2 control-label">Email ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="emailId" type="text" class="form-control" id="email_id_1" placeholder="Email ID" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="username" class="col-sm-2 control-label">User Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="username" type="text" class="form-control" id="u_name_1" placeholder="User Name" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="uniqId" class="col-sm-2 control-label">Unique ID</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="uniqId" type="text" class="form-control" id="u_ID_3" placeholder="Unique ID" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="address" class="col-sm-2 control-label">Address</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="address" type="text" class="form-control" id="address_1" placeholder="Address" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="city" class="col-sm-2 control-label">City</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="city" type="text" class="form-control" id="city_1" placeholder="city" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="zipcode" class="col-sm-2 control-label">Zipcode</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="zipcode" type="number" class="form-control" id="zipcode_1" placeholder="Zipcode" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="state" class="col-sm-2 control-label">State</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="state" type="text" class="form-control" id="state_1" placeholder="state" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="country" class="col-sm-2 control-label">Country</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      											<form:input path="country" type="text" class="form-control" id="country_1" placeholder="country" />
      										</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<div class="col-sm-offset-2 col-sm-10">
    								<button type="submit" class="btn btn-success">Create User</button>
    								</div>
  								</div>
							</form:form>
        				</div>
        				
        				
        				
        				
        				<!-- Sujata's work -->
        				
  <!--       				
        				<div class="tab-pane" id="Tab4">
        					<form class="form-horizontal" action="debitmoney" method="get">
  								<div class="form-group">
    								<label for="displayAccountBalance" class="col-lg-2 control-label">Account Balance</label>
    								<label for="displayAmount" class="col-lg2 control-label"></label>
    									<div class="col-lg-10">
    										<div class="col-xs-24">
      											<input type="text" class="form-control" for="displayBalance" placeholder="Balance goes here" readonly="readonly" class="col-lg-2" id="balance"></label>
      										</div>
    									</div>
  								</div>
  								<div class="form-group">
    								<label for="inputAccNo" class="col-lg-2 control-label">Recipient Account Number</label>
   										<div class="col-lg-10">
   											<div class="col-xs-24">
     											<input type="text" class="form-control" id="inputAccNum" placeholder="Enter Account Number">
   			 								</div>
   			 							</div>
  								</div>
  								<div class="form-group">
    								<label for="inputAmount" class="col-lg-2 control-label">Amount</label>
   										<div class="col-lg-10">
   											<div class="col-xs-24">
     											<input type="text" class="form-control" id="inputAmount" placeholder="Enter Amount">
   			 								</div>
   			 							</div>
  								</div>
  								<div class="form-group">
    								<div class="col-lg-offset-2 col-lg-10">
    									<a href="performTransaction" role="button" class="btn btn-primary">Submit</a>
     										<button type="reset" class="btn btn-success">Cancel</button>
    								</div>
  								</div>
							</form>
        				</div>
 -->       				
  <!--       				
        				<div class="tab-pane" id="Tab5">
        					<form class="form-horizontal" action="debitmoney" method="get">
  								<div class="form-group">
    								<label for="displayAccountBalance" class="col-lg-2 control-label">Account Balance</label>
    								<label for="displayAmount" class="col-lg2 control-label"></label>
    								<div class="col-lg-10">
    									<div class="col-xs-24">
      										<input type="text" class="form-control" for="displayBalance" placeholder="Balance goes here" readonly="readonly" class="col-lg-2" id="balance"></label>
      									</div>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputAmount" class="col-lg-2 control-label">Amount</label>
   									<div class="col-lg-10">
   										<div class="col-xs-24">
     										<input type="text" class="form-control" id="inputAmount" placeholder="Enter Amount">
   			 							</div>
   			 						</div>
  								</div>
  								<div class="form-group">
    								<div class="col-lg-offset-2 col-lg-10">
    									<a href="performTransaction" role="button" class="btn btn-primary">Submit</a>
     										<button type="reset" class="btn btn-success">Cancel</button>
    								</div>
  								</div>
							</form>
        				</div>
        				
        				-->	
  					</div>
				</div>
  			</div>
  			<div class="col-md-2"></div>
		</div>
	</div>
	
  </body>
</html>