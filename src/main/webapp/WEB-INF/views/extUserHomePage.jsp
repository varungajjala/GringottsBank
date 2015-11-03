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
   
  	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
  						<li class="active"><a href="#Tab1" data-toggle="tab">Accounts</a></li>
  						<li><a href="#Tab2" data-toggle="tab">Profile</a></li>
  						<li><a href="#Tab3" data-toggle="tab">Credit</a></li>
  						<li><a href="#Tab4" data-toggle="tab">Debit</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Transfers</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Transaction History</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Pay Merchant</a></li>
  						<li><a href="#Tab8" data-toggle="tab">Update Profile</a></li>
  						<li><a href="#Tab9" data-toggle="tab">Authorize Viewing Transactions</a></li>

  						<li><a href="#Tab10" data-toggle="tab">Delete Transaction</a></li>
  						<li><a href="#Tab11" data-toggle="tab">Authorize Transaction</a></li>			
					</ul>
					
        		</div>
        		
        		
        		<div id="my-tab-content" class="tab-content">	
        		
        			<div class="tab-pane" id="Tab9">
        					<form:form class="form-horizontal" action="authorizeViewTransaction" method="POST">
  								<button type="submit">Authorize View Transactions</button>
							</form:form>
        				</div>
        				
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
  						
					</form>	
        			</div>
        				
        				<div class="tab-pane" id="Tab2">
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
    						<label for="inputEmail" class="col-sm-2 control-label">Account No</label>
    							<div class="col-sm-8">
    								<p>
    								${accountno}
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
						
  						
  						
					</form:form>
        				</div>
        				
        				
        				<div class="tab-pane" id="Tab3">
        					<form:form class="form-horizontal" action="credit_money" commandName="creditOp" method="POST">
  								<div class="page-header" style="margin-top: 5px;" align="center">
    								<h3>Credit Money</h3>
    							</div>
  								<div class="form-group">
    								<label for="displayAccountBalance" id="displayBalance" class="col-lg-2 control-label">Account Balance</label>
    								<form:label path="balance" for="displayAmount" class="col-lg2 control-label">${creditOp.getBalance()}</form:label>
  								</div>
  								<div class="form-group">
    								<label for="inputCreditAmount" class="col-lg-2 control-label">Amount</label>
   									<div class="col-lg-10">
   										<div class="col-xs-24">
   			 							<form:input path="transactionAmount" type="number" class="form-control" id="inputcredit" placeholder="Enter Amount" min="0"/>
   			 							</div>
   			 						</div>
  								</div>
  								<div class="form-group">
    								<div class="col-lg-offset-2 col-lg-10">
    									<button type="submit" class="btn btn-primary">Submit</button>
     									<button type="reset" class="btn btn-success">Cancel</button>
    								</div>
  								</div>
							</form:form>
        				</div>
        				
        				<!-- Tab for Debit Operation -->
        				<div class="tab-pane" id="Tab4">
    							
    							<form:form class="form-horizontal" action="debit_money" commandName="debitOp" method="POST">
    							
	        						<div class="page-header" style="margin-top: 5px;" align="center">
	    								<h3>Debit Money</h3>
	    							</div>
	    							
	    							<div class="form-group">
    								<label for="displayAccountBalance" id="displayBalance" class="col-lg-2 control-label">Account Balance</label>
    								<form:label path="balance" for="displayAmount" class="col-lg2 control-label">${debitOp.getBalance()}</form:label>
  									</div>
  					
  								
	  								<div class="form-group">
	    								<label for="inputAmount" class="col-lg-2 control-label">Amount</label>
	   										<div class="col-sm-10">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="transactionAmount" type="number" class="form-control" id="inputAmount" placeholder="Enter Amount" min="0" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<div class="col-lg-offset-2 col-lg-10">
	    									<button type="submit" class="btn btn-primary">Submit</button>
	     										<button type="reset" class="btn btn-success">Cancel</button>
	    								</div>
	    							</div>
    						</form:form>
    						
    						
        				</div>
        				
        					
        				<!-- Tab for transferring money -->
        				
        				<div class="tab-pane" id="Tab5">
    							
    							<form:form class="form-horizontal" action="transfer_money" commandName="transferOp" enctype="multipart/form-data" method="POST">
    							
	        						<div class="page-header" style="margin-top: 5px;" align="center">
	    								<h3>Transfer Money</h3>
	    							</div>
	    							
	    							<div class="form-group">
    								<label for="displayAccountBalance" id="displayBalance" class="col-lg-2 control-label">Account Balance</label>
    								<form:label path="balance" for="displayAmount" class="col-lg2 control-label">${transferOp.getBalance()}</form:label>
  								</div>
  								
  								<div class="form-group">
	    								<label for="inputAccountNumber" class="col-lg-2 control-label">Account Number</label>
	   										<div class="col-sm-10">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="accountno" type="tel" class="form-control" id="inputAccountNumber" placeholder="Enter Account Number" pattern="^\d{9}$"/>
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
  					
  								
	  								<div class="form-group">
	    								<label for="inputAmount" class="col-lg-2 control-label">Amount</label>
	   										<div class="col-sm-10">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="transactionAmount" type="number" class="form-control" id="inputAmount" placeholder="Enter Amount" min="0" />
	     										
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<label for="uploadFile" class="col-lg-2 control-label">Upload certificate</label>
	   										<div class="col-sm-8">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="mpFile" name="mpFile" type="file" class="file" multiple="flase" required="true" id="cert_file" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<label for="uploadFile" class="col-lg-2 control-label">Upload Private Key File</label>
	   										<div class="col-sm-8">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="pkFile" name="pkFile" type="file" class="file" multiple="flase" required="true" id="private_file" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<div class="col-lg-offset-2 col-lg-10">
	    									<button type="submit" class="btn btn-primary">Submit</button>
	     										<button type="reset" class="btn btn-success">Cancel</button>
	    								</div>
	    							</div>
    						</form:form>
  								
    						
        				</div>
        				
        				 
        			
        				<div class="tab-pane" id="Tab6">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							
    							
								<h3>Transaction History</h3>
								
								<form action="download" method="get"><button type="submit">Download</button></form>
    							<table align="center" border="1">
    							<tr>
    							
    				
    							<th align="center">Transaction Type</th>
       							<th align="center">Description</th>
    							<th align="center">Balance</th>
    							<th align="center">Date</th>
    							<th align="center">Transaction Amount</th>
    							<th align="center">Transaction Status</th>
    							</tr>
  
  								<c:if test="${transactionOp != null}">  						
      							<c:forEach items="${transactionOp}" var="transactionOp">     
    							<tr>
  								
  								 <td align="center"><c:out value="${transactionOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getTransactionAmount()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getStatus()}"/></td>
  								 </tr>
								</c:forEach>
								</c:if>
								
    							</table>
    							
    						</div>
    						
        				</div>
        				
        				
        				
        				<!-- Tab for pay merchant -->
        				
        				<div class="tab-pane" id="Tab7">
    							
    							<form:form class="form-horizontal" action="pay_merchant" commandName="paymerchantOp" enctype="multipart/form-data" method="POST">
    							
	        						<div class="page-header" style="margin-top: 5px;" align="center">
	    								<h3>Pay Merchant</h3>
	    							</div>
	    							<div class="form-group">
    								<label for="displayAccountBalance" id="displayBalance" class="col-lg-2 control-label">Account Balance</label>
    								<form:label path="balance" for="displayAmount" class="col-lg2 control-label">${debitOp.getBalance()}</form:label>
  								</div>
  					
  									<div class="form-group">
	    								<label for="inputAccountNumber" class="col-lg-2 control-label">Account Number</label>
	   										<div class="col-sm-10">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="accountno" type="tel" class="form-control" id="inputAccountNumber" placeholder="Enter Account Number of Merchant" pattern="^\d{9}$"/>
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								<div class="form-group">
	    								<label for="inputAmount" class="col-lg-2 control-label">Amount</label>
	   										<div class="col-sm-10">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="transactionAmount" type="number" class="form-control" id="inputAmount" placeholder="Enter Amount" min="0" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<label for="uploadFile" class="col-lg-2 control-label">Upload certificate</label>
	   										<div class="col-sm-8">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="mpFile" name="mpFile" type="file" class="file" multiple="flase" required="true" id="cert_file" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<label for="uploadFile" class="col-lg-2 control-label">Upload Private Key File</label>
	   										<div class="col-sm-8">
	   											<div class="col-sm-24">
	     										
	     										<form:input path="pkFile" name="pkFile" type="file" class="file" multiple="flase" required="true" id="private_file" />
	     											
	   			 								</div>
	   			 							</div>
	  								</div>
	  								
	  								<div class="form-group">
	    								<div class="col-lg-offset-2 col-lg-10">
	    									<button type="submit" class="btn btn-primary">Submit</button>
	     										<button type="reset" class="btn btn-success">Cancel</button>
	    								</div>
	    							</div>
	    							</form:form>
	    				</div>		
	    				
	    				<!-- Tab for update profile -->
	    				
	    				<div class="tab-pane" id="Tab8">
    							
    						<form:form class="form-horizontal" action="upate_profile" commandName="UpdateProfile" method="POST">
    							
	        					<div class="form-group">
    								<label for="username" class="col-sm-2 control-label">First Name</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      										<form:input path="firstName" type="text" class="form-control" id="lastName" value="${firstName}" required="true"/>
      									</div>
    								</div>
  								</div>
  								
  								<div class="form-group">
    								<label for="username" class="col-sm-2 control-label">Last Name</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		      									<form:input path="lastName" type="text" class="form-control" id="firstName" value="${lastName}" required="true"/>
		      								</div>
		    							</div>
  								</div>
  								
		  						<div class="form-group">
		    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon">@</span>
		      									<form:input path="emailId" type="email" class="form-control" id="email" value="${email}" required="true"/>
		      								</div>
		    							</div>
		  						</div>
  						
  								<!-- 
		  						<div class="form-group">
		    						<label for="username" class="col-sm-2 control-label">Username</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username" required="true"/>
		      								</div>
		    							</div>
		  						</div>
		  						 
		  						<div class="form-group">
		    						<label for="password" class="col-sm-2 control-label">Password</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
		      									<form:input path="passwd" type="password" class="form-control" id="inputPassword" placeholder="Enter Password" required="true"/>
		      								</div>
		    							</div>
		  						</div>
  								-->
  						
		  						<div class="form-group">
		    						<label for="inputAddress" class="col-sm-2 control-label">Address</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
		        								<form:input path="address" type="text" class="form-control" id="inputAddressStreet" value="${streetAddress}"/>
		    									<form:input path="city" type="text" class="form-control" id="inputAddressCity" value="${city}"/>
		      									<form:input path="state" type="text" class="form-control" id="inputAddressState" value="${state}"/>
		      									<form:input path="country" type="text" class="form-control" id="inputAddressCountry" value="${country}"/>
		       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" value="${zip}" required="true" pattern="^\d{5}$" min="1000" max="99999"/>            									
		     								</div>
		    							</div>	
		  						</div>
		  						
		  						<div class="form-group">
		    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
		      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" value="${contactNo}" required="true" pattern="^\d{10}$"/>
		      								</div>
		    							</div>
		  						</div>
								<!-- 
		  						<div class="form-group" id="dvPII" >
		    						<label for="inputContact" class="col-sm-2 control-label">Identification Number(PII)</label>
		    							<div class="col-sm-8">
		    								<div class="input-group">
		  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
		      								    <form:input path="identificationNo" type="tel" class="form-control" id="inputContact" placeholder="Enter SSN for single user and Merchant ID for merchant" required="true" pattern="^\d{8}$"/>
		      								
		      								</div>
		    							</div>
		  						</div>
		  						 -->
  						 	<br>
  						 
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" >Update</button>
    						</div>
  						</div>
	    			</form:form>
	    		</div>		
	    		
	    		<div class="tab-pane" id="Tab10">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Delete Transaction</h3>
    							
    							<table  border="1">
    							<tr>
    							
    							<th align="center">Unique ID</th>
    							<th align="center">Transaction Type</th>
       							<th align="center">Description</th>
    							<th align="center">Balance</th>
    							<th align="center">Date</th>
    							<th align="center">Transaction Amount</th>
    							<th align="center">Delete</th>
    							<th align="center">Keep</th>
    							</tr>
    							<% int i = 0; %>
    							<c:if test="${userOp != null}">  
    							<form:form action="deleteauthorization" commandName="userOp" method="GET">
    							
    							<c:forEach items="${userOp}" var="userOp">     
    							<tr>
    							 
  								 
  								 <td align="center"><c:out value="${userOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${userOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${userOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${userOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${userOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${userOp.getTransactionAmount()}"/></td>
  								 
  								
  								<td><input type="radio" name="radioValues<%=i %>" value="delete<%=i %>"/></td>
  								<td><input type="radio" checked name="radioValues<%=i %>" value="ignore<%=i %>" checked="checked"/></td>						 
  								 </tr>
  								 <% i = i+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=i %> name="size"/>
								<button type="submit">Submit For Deletion</button>
								
								</form:form>
								</c:if>
    							</table>
    							
    						</div>
    						</div>
        				
        				
        				<div class="tab-pane" id="Tab11">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Authorize Transaction</h3>
    							
    							<table align="center" border="1">
    							<tr>
    							
    							<th align="center">Unique ID</th>
    							<th align="center">Transaction Type</th>
       							<th align="center">Description</th>
    							<th align="center">Balance</th>
    							<th align="center">Date</th>
    							<th align="center">Transaction Amount</th>
    							<th align="center">Status</th>
    							<th align="center">Approve</th>
    							<th align="center">Reject</th>
    							</tr>
    							<%int j = 0; %>
    							<c:if test="${authorizeOp != null}">  
    							<form:form action="authorize" commandName="authorizeOp" method="GET">
    							
    							<c:forEach items="${authorizeOp}" var="authorizeOp">     
    							<tr>
  								 
  								 <td align="center"><c:out value="${authorizeOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getTransactionAmount()}"/></td>
  								 <td align="center"><c:out value="${authorizeOp.getInternalStatus()}"/></td>
  								<td><input type="radio"  value="approve"<%=j %> name="radioValues<%=j %>"/></td>	
  								<td><input type="radio" checked value="reject"<%=j %> name="radioValues<%=j %>"/></td>					 
  								 </tr>
  								 <% j = j+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=j %> name="size"/>
								<button type="submit">Submit For Approval</button>
								
								</form:form>
								</c:if>
    							</table>
    							
    						</div>
    						
        				</div>		
    			</div>
    		</div>
    		
    		<div>
    		<p>
    		${message}
    		</p>
    		</div>
    						
    	</div>
	</div>
	
	
  </body>
</html>