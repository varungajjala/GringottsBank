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
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
  	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    
    </script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

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
  						<!-- <li><a href="#Tab2" data-toggle="tab">User Requests</a></li> -->
  						<li><a href="#Tab3" data-toggle="tab">Authorize Critical Transactions</a></li>
  						<li><a href="#Tab4" data-toggle="tab">View Authorized Transactions</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Delete User</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Modify User</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Create User</a></li>
  						<li><a href="#Tab8" data-toggle="tab">Transaction History</a></li>
  						<li><a href="#Tab9" data-toggle="tab">Display User Login</a></li>
  						
					</ul>
					
					<div id="my-tab-content" class="tab-content">
            			
            			<div class="tab-pane  active" id="Tab1">
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
						
  						
  						
					</form:form>
        				</div>
        				
        				
        				<div class="tab-pane" id="Tab2">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>User Requests</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab3">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Authorize Payments</h3>
    							
    							<table align="center" border="1">
    							<tr>
    							
    							<th align="center">Unique ID</th>
    							<th align="center">Transaction Type</th>
       							<th align="center">Description</th>
    							<th align="center">Balance</th>
    							<th align="center">Date</th>
    							<th align="center">Transaction Amount</th>
    							<th align="center">Approve</th>
    							<th align="center">Reject</th>
    							</tr>
    							<% int i = 0; %>
    							<c:if test="${transactionOp != null}">  
    							<form:form action="authorizeCritical" commandName="approveOp" method="GET">
    							
    							<c:forEach items="${transactionOp}" var="transactionOp">     
    							<tr>
  								 
  								 <td align="center"><c:out value="${transactionOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getTransactionAmount()}"/></td>
  								 
  								<td><input type="radio"  value="approve<%=i %>" name="radioValues<%=i %>"/></td>	
  								<td><input type="radio" checked value="reject<%=i %>" name="radioValues<%=i %>"/></td>					 
  								 </tr>
  								 <% i = i+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=i %> name="size"/>
								<button type="submit">Submit For Approval</button>
								
								</form:form>
								</c:if>
    							</table>
    							
    						</div>
    						</div>
        				<div class="tab-pane" id="Tab4">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>View Authorized Transactions</h3>
    						</div>
    						
        				</div>
        				
            			<div class="tab-pane" id="Tab5">
        					<div class="page-header" style="margin-top: 5px;" align="center">
								<h3>Delete User</h3>
    						</div>
    						<form:form class="form-horizontal" action="delete_user" commandName="deleteOp" method="POST">
  								<div class="form-group">
    								<label for="Uniuqe id" class="col-sm-2 control-label">User Name to Delete</label>
    								<div class="col-sm-8">
    									<div class="input-group">
  											<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      										<form:input path="username" type="text" class="form-control" id="u_id_1" placeholder="User Name" required="true" maxlength="16"/>
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
	    						<label for="username" class="col-sm-2 control-label">Enter Username to Modify</label>
	    							<div class="col-sm-8">
	    								<div class="input-group">
	  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
	      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username" required="true" maxlength="16"/>
	      								</div>
	    							</div>
	  						</div>
  					
  					
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      									<form:input path="firstName" type="text" class="form-control" id="lastName" placeholder="Firstname" required="true" maxlength="20"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="lastName" type="text" class="form-control" id="firstName" placeholder="Lastname" required="true" maxlength="16"/>
      								</div>
    							</div>
  						</div>
  						
  						<!-- 
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<form:input path="emailId" type="email" class="form-control" id="email" placeholder="Email" required="true"/>
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
        								<form:input path="address" type="text" class="form-control" id="inputAddressStreet" placeholder="Street Address" maxlength="32"/>
    									<form:input path="city" type="text" class="form-control" id="inputAddressCity" placeholder="City" maxlength="32"/>
      									<form:input path="state" type="text" class="form-control" id="inputAddressState" placeholder="State" maxlength="32"/>
      									<form:input path="country" type="text" class="form-control" id="inputAddressCountry" placeholder="Country" maxlength="32"/>
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" value = "null" placeholder="zipcode" required="true" pattern="^\d{5}$" min="1000" max="99999"/>      									
     								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX" required="true" pattern="^\d{10}$"/>
      								</div>
    							</div>
  						</div>
  						<!-- 
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label for="singleUser">
						    				<form:radiobutton path ="utype" checked="true" id="singleUser" name="chkUserType" value="singleUser" required="true"/>
						    				Single User  
										</label>
										<label for="chkNo">
						    				<form:radiobutton path = "utype" id="merchant" name="chkUserType" value="merchant" required="true"/>
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
      									<form:input type="password" path="identificationNo" value="null" class="form-control" id="inputContact_create" placeholder="Enter SSN for single user and Merchant ID for merchant" required="true" pattern="^\d{9}$"/>
      								</div>
    							</div>
  						</div>
  						 -->
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
    						<form:form class="form-horizontal" action="create_user" commandName="createOp" method="POST">
  					
  						
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">First Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>      									
      									<form:input path="firstName" type="text" class="form-control" id="lastName" placeholder="Firstname" required="true" maxlength="20"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Last Name</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="lastName" type="text" class="form-control" id="firstName" placeholder="Lastname" required="true" maxlength="16"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon">@</span>
      									<form:input path="emailId" type="email" class="form-control" id="email" placeholder="Email" required="true" maxlength="32"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="col-sm-2 control-label">Username</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
      									<form:input path="username" type="text" class="form-control" id="inputUsername" placeholder="Username" required="true" maxlength="16"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group">
    						<label for="password" class="col-sm-2 control-label">Password</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
      									<form:input path="passwd" type="password" class="form-control" id="inputPassword" placeholder="Enter Password" required="true" minlength="6" maxlength="16" />
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputAddress" class="col-sm-2 control-label">Address</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
        								<form:input path="address" type="text" class="form-control" id="inputAddressStreet" placeholder="Street Address" maxlength="32"/>
    									<form:input path="city" type="text" class="form-control" id="inputAddressCity" placeholder="City" maxlength="32"/>
      									<form:input path="state" type="text" class="form-control" id="inputAddressState" placeholder="State" maxlength="32"/>
      									<form:input path="country" type="text" class="form-control" id="inputAddressCountry" placeholder="Country" maxlength="32"/>
       									<form:input path="zipcode" type="number" class="form-control" id="inputZipcode" value = "null" placeholder="zipcode" required="true" pattern="^\d{5}$" min="1000" max="99999"/>      									
     								</div>
    							</div>
    							
  						</div>
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">Contact No.</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
      									<form:input path="contactNo" type="tel" class="form-control" id="inputContact" placeholder="XXXXXXXXXX" required="true"/>
      								</div>
    							</div>
  						</div>
  						
  						<div class="form-group">
    						<label for="inputContact" class="col-sm-2 control-label">User Type</label>
    							<div class="col-sm-8">
    								<div class="input-group">
										<label for="singleUser">
						    				<form:radiobutton path ="utype" checked="true" id="singleUser" name="chkUserType" value="singleUser" required="true"/>
						    				Single User  
										</label>
										<label for="chkNo">
						    				<form:radiobutton path = "utype" id="merchant" name="chkUserType" value="merchant" required="true"/>
						   					 Merchant
										</label> 
      								</div>
    							</div>
  						</div>
						<div class="form-group" id="dvPII" >
    						<label for="inputContact" class="col-sm-2 control-label">Identification Number(PII - 9 digit)</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input type="password" path="identificationNo" value="null" class="form-control" id="inputContact_create" placeholder="Enter SSN for single user and Merchant ID for merchant" required="true" pattern="^\d{9}$"/>
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
  						
  						<div class="tab-pane" id="Tab8">
  						<form:form class="form-horizontal" action="view_transactions" commandName="username" method="POST">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							
    							
								<h3>Transaction History</h3>
								
								<div class="form-group" id="transhis" >
    						<label for="inputContact" class="col-sm-2 control-label">UserName</label>
    							<div class="col-sm-8">
    								<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-option-vertical"></span></span>
      									<form:input type="text" path="userId"  class="form-control" id="user_name" placeholder="Enter Username" required="true"/>
      								</div>
    							</div>
  						</div>
  						<div class="form-group" >
    						<div class="col-sm-offset-2 col-sm-10">
    						<button type="submit" class="btn btn-success" >Get</button>
     						
    						</div>
  						</div>
								
    							<table align="center" border="1">
    							<tr>
    							
    							<th align="center">Transaction Id</th>
    							<th align="center">Transaction Type</th>
       							<th align="center">Description</th>
    							<th align="center">Balance</th>
    							<th align="center">Date</th>
    							<th align="center">Transaction Amount</th>
    							<th align="center">Transaction Status</th>
    							</tr>
  
  								<c:if test="${usertransactionOp != null}">  						
      							<c:forEach items="${usertransactionOp}" var="usertransactionOp">     
    							<tr>
  									<td align="center"><c:out value="${usertransactionOp.getId()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getTransactionAmount()}"/></td>
  								 <td align="center"><c:out value="${usertransactionOp.getStatus()}"/></td>
  								 </tr>
								</c:forEach>
								</c:if>
								
    							</table>
    							
    						</div>
    						</form:form>
    						
        				</div>
  						
					
        				<div class="tab-pane" id="Tab9">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							
    							
								<h3>All User Logins and Unique Id</h3>
								
								
    							<table align="center" border="1">
    							<tr>
    							
    				
    							<th align="center">User Name</th>
       							<th align="center">Unique Id</th>
    							<th align="center">Role</th>
    							</tr>
  
  								<c:if test="${displayUsersOp != null}">  						
      							<c:forEach items="${displayUsersOp}" var="displayUsersOp">     
    							<tr>
  								
  								 <td align="center"><c:out value="${displayUsersOp.getUserId()}"/></td>
  								 <td align="center"><c:out value="${displayUsersOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${displayUsersOp.getRole()}"/></td>
  								 </tr>
								</c:forEach>
								</c:if>
								
    							</table>
    							
    						</div>
    						
        				</div>
        				
        				
        				
        		
        				
  
  					</div>
				</div>
  			</div>
  			<div>
			<p>
			${message}
			
			</p>
			</div>
  			<div class="col-md-2"></div>
		</div>
	</div>
	
  </body>
</html>