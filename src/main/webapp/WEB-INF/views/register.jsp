
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
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<!-- 
<script type="text/javascript">
    $(function () {
        $("input[name='chkUserType']").click(function () {
            if ($("#merchant").is(":checked")) {
               $("#dvMerchantID").show();
                $("#dvPII").hide();
             //  $("#dvPII").children().attr("disabled","disabled");
            } else {
                $("#dvPII").show();
                $("#dvMerchantID").hide();
           // 	$("#dvmerchant").children().attr("disabled","disabled");
            }
        });
    });
</script>
 -->
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
    				<h3>Registration</h3>
    				</div>
    				<form:form class="form-horizontal" commandName="send" method="POST">
  						
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
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="password" class="col-sm-2 control-label">Password</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
      									<form:input path="passwd" type="password" class="form-control" id="inputPassword" placeholder="Enter Password"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputPassword3" class="col-sm-2 control-label">Confirm Password</label>
   							<div class="col-sm-8">
   								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
     									<input type="password" class="form-control" id="inputPassword3" placeholder="Confirm above entered Password">
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
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" value ="null" placeholder="zipcode"/>      									
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
						    				<form:radiobutton path ="utype" id="singleUser" name="chkUserType" value="singleUser" />
						    				Single User  
										</label>
										<label for="chkNo">
						    				<form:radiobutton path = "utype" id="merchant" name="chkUserType" value="merchant"/>
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
      									<form:input type="number" path="identificationNo" value="null" class="form-control" id="inputContact" placeholder="Enter SSN for single user and Merchant ID for merchant"/>
      								</div>
    							</div>
  						</div>
  				<!-- 
  						<div class="form-group" id="dvMerchantID" >
    						<label for="inputContact" class="col-sm-2 control-label">Merchant ID</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input path="identificationNo" type="integer" class="form-control" id="inputContact" placeholder="Merchant ID"/>
      								</div>
    							</div>
  						</div>
  						 -->
  						 
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" >Register</button>
     						
    						</div>
  						</div>
					</form:form>		
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