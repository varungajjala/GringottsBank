<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>User Home Page</title>

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
  						<li class="active"><a href="#Tab1" data-toggle="tab">Accounts</a></li>
  						<li><a href="#Tab2" data-toggle="tab">Profile</a></li>
  						<li><a href="#Tab3" data-toggle="tab">Credit</a></li>
  						<li><a href="#Tab4" data-toggle="tab">Debit</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Transfers</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Transaction History</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Pay Merchant</a></li>
  						
  						
					</ul>
					
        				</div>
        			<div id="my-tab-content" class="tab-content">	
        				<div class="tab-pane active" id="Tab1">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Accounts</h3>
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
        				<div class="tab-pane" id="Tab2">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Profile</h3>
    						</div>
    						
        				</div>
        				
        				<div class="tab-pane" id="Tab3">
        					<form class="form-horizontal" action="creditmoney" method="get">
  								<div class="page-header" style="margin-top: 5px;" align="center">
    								<h3>Credit Money</h3>
    							</div>
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
        				
        				<div class="tab-pane" id="Tab4">
        					<form class="form-horizontal" action="debitmoney" method="get">
  								<div class="page-header" style="margin-top: 5px;" align="center">
    								<h3>Debit Money</h3>
    							</div>
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
        				<div class="tab-pane" id="Tab5">
        					<form class="form-horizontal" action="debitmoney" method="get">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Transfers</h3>
    						</div>
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
        				<div class="tab-pane" id="Tab6">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Transaction History</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab7">
						<form class="form-horizontal" action="paymerchant" method="get">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Pay Merchant</h3>
   							</div>
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
  					</div>
				</div>
  			</div>
  			<div class="col-md-2"></div>
	
  </body>
</html>