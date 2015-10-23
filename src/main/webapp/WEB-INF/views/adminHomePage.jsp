<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Admin Home Page</title>

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
  <script type="text/javascript">
    $(function () {
        $("input[name='chkUserType']").click(function () {
            if ($("#merchant").is(":checked")) {
                $("#dvMerchantID").show();
                $("#dvPII").hide();
            } else if ($("#singleUser").is(":checked")){
                $("#dvPII").show();
                $("#dvMerchantID").hide();
            }
        });
    });
</script>

<script type="text/javascript">
    $(function () {
        $("input[name='chkUserType2']").click(function () {
            if ($("#merchant2").is(":checked")) {
                $("#dvMerchantID2").show();
                $("#dvPII2").hide();
            } else if ($("#singleUser2").is(":checked")){
                $("#dvPII2").show();
                $("#dvMerchantID2").hide();
            }
        });
    });
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
  						<li><a href="#Tab3" data-toggle="tab">View System Log</a></li>
  						<li><a href="#Tab4" data-toggle="tab">View PII</a></li>
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
    							<h3>View System Log</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab4">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>View PII</h3>
    						</div>
    						
        				</div>
        				
            			<div class="tab-pane active" id="Tab5">
        					<div class="page-header" style="margin-top: 5px;" align="center">
								<h3>Delete User</h3>
    						</div>
    						<form:form class="form-horizontal" action="delete_user_admin" commandName="deleteOp_internal" method="POST">
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
    						<form:form class="form-horizontal" action="modify_user_internal" commandName="modifyOp_internal" method="POST">
  					
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      									<form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Firstname"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="Lastname"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<form:input path="emailId" type="email" class="form-control" id="email" placeholder="Email"/>
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
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" placeholder="zipcode"/>      									
     								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label for="singleUser">
						    				<input type="radio" id="singleUser2" name="chkUserType2" />
						    				Single User  
										</label>
										<label for="chkNo">
						    				<input type="radio" id="merchant2" name="chkUserType2" />
						   					 Merchant
										</label> 
      								</div>
    							</div>
  						</div>
  						
						<div class="form-group" id="dvMerchantID2" style="display: none">
    						<label for="inputContact" class="col-sm-2 control-label">Merchant ID</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input path="uniqId" type="text" class="form-control" id="inputContact" placeholder="Merchant ID"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group" id="dvPII2" style="display: none">
    						<label for="inputContact" class="col-sm-2 control-label">SSN</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input path="uniqId" type="text" class="form-control" id="inputContact" placeholder="SSN"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" >Modify User</button>
     						
    						</div>
  						</div>
					</form:form>
        			
        			
        				</div>
        				
        				<div class="tab-pane" id="Tab7">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Create User</h3>
    						</div>
    						<form:form class="form-horizontal" action="create_user_internal" commandName="createOp_internal" method="POST">
  					
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      									<form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Firstname"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="Lastname"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<form:input path="emailId" type="email" class="form-control" id="email" placeholder="Email"/>
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
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" placeholder="zipcode"/>      									
     								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label for="singleUser">
						    				<input type="radio" id="singleUser" name="chkUserType" />
						    				Single User  
										</label>
										<label for="chkNo">
						    				<input type="radio" id="merchant" name="chkUserType" />
						   					 Merchant
										</label> 
      								</div>
    							</div>
  						</div>
  						
						<div class="form-group" id="dvMerchantID" style="display: none">
    						<label for="inputContact" class="col-sm-2 control-label">Merchant ID</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input path="uniqId" type="text" class="form-control" id="inputContact" placeholder="Merchant ID"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group" id="dvPII" style="display: none">
    						<label for="inputContact" class="col-sm-2 control-label">SSN</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input path="uniqId" type="text" class="form-control" id="inputContact" placeholder="SSN"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" >Create User</button>
     						
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