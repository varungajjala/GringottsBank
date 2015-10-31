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
  	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
  						<li><a href="#Tab4" data-toggle="tab">Approve User Accounts</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Transaction History</a></li>
  						<li><a href="#Tab8" data-toggle="tab">Manage Transactions</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Create Transaction</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Modify Transaction</a></li>
  						<li><a href="#Tab9" data-toggle="tab">Delete Transaction</a></li>
  						
  						
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
    							<form:form action="authorization" commandName="approveOp" method="GET">
    							
    							<c:forEach items="${transactionOp}" var="transactionOp">     
    							<tr>
  								 
  								 <td align="center"><c:out value="${transactionOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${transactionOp.getTransactionAmount()}"/></td>
  								 
  								<td><input type="radio"  value="approve"<%=i %> name="radioValues<%=i %>"/></td>	
  								<td><input type="radio" checked value="reject"<%=i %> name="radioValues<%=i %>"/></td>					 
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
    							<h3>Approve User Accounts</h3>
    							<table align="center" border="1">
    							<tr>
    							
    							<th align="center">First Name</th>
    							<th align="center">Last Name</th>
       							<th align="center">UserName</th>
    							<th align="center">Email ID</th>
    							<th align="center">Contact Number</th>
    							<th align="center">Approve</th>
    							<th align="center">Reject</th>
    							</tr>
    						 	<% i = 0; %>
    							<form:form action="approveUserAccount" commandName="approveUserAccount" method="GET">
    							 
    							<c:forEach items="${displayPiiUsers}" var="displayPiiUsers">  
    							   
    							<tr>
  								 
  								 <td align="center"><c:out value="${displayPiiUsers.getFirstName()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getLastName()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getUsername()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getEmailId()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getContactNo()}"/></td>
  								 
  							
  								<td><input type="radio"  value="approve<%=i %>" name="radioValues<%=i %>"/></td>	
  								<td><input type="radio" checked value="reject<%=i %>" name="radioValues<%=i %>"/></td>					 
  								 </tr>
  								 <% i = i+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=i %> name="size"/>
								<button type="submit">Submit For Approval</button>
								</form:form>
    							</table>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab5">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Transaction History</h3>
    						</div>
    						
        				</div>
        				<div class="tab-pane" id="Tab6">
        					
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Modify Transactions</h3>
    						</div>
    						
    						<form:form class="form-horizontal" action="modifytransaction" commandName="modifyOp" method="POST">
    						
    						<div class="form-group">
	    						<label for="inputTransactionID" class="col-lg-2 control-label">Transaction ID</label>
	   							<div class="col-sm-10">
	   								<div class="col-sm-24">
	     										
	     							<form:input path="id" class="form-control" id="transactionid" placeholder="Enter Transaction ID" />
	     											
	   			 					</div>
	   			 				</div>
	  						</div>
  					
  					
  							<div class="form-group">
	    						<label for="transactionAmount" class="col-lg-2 control-label">Transaction Amount</label>
	   							<div class="col-sm-10">
	   								<div class="col-sm-24">
	     										
	     							<form:input path="transactionAmount" class="form-control" id="inputTransactionAmount" placeholder="Enter Transaction Amount" />
	     											
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
        					
    				
        				<div class="tab-pane" id="Tab8">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>Delete Transaction</h3>
    						</div>
    						
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
    							<% int j = 0; %>
    							<c:if test="${deleteOp != null}">  
    							<form:form action="deleteTransaction" commandName="deleteOp" method="GET">
    							
    							<c:forEach items="${deleteOp}" var="deleteOp">     
    							<tr>
    							 
  								 
  								 <td align="center"><c:out value="${deleteOp.getUniqId()}"/></td>
  								 <td align="center"><c:out value="${deleteOp.getTransactionType()}"/></td>
  								 <td align="center"><c:out value="${deleteOp.getDescription()}"/></td>
  								 <td align="center"><c:out value="${deleteOp.getBalance()}"/></td>
  								 <td align="center"><c:out value="${deleteOp.getDate()}"/></td>
  								 <td align="center"><c:out value="${deleteOp.getTransactionAmount()}"/></td>
  								 
  								<!-- <td><input type="radio"  value="delete"<c:out value="${deleteOp.getId()}"/> name="radioValues<%=j %>"/></td> -->
  								<td><input type="radio" name="radioValues<%=j %>" value="delete<%=j %>"/></td>
  								<td><input type="radio" checked name="radioValues<%=j %>" value="ignore<%=j %>" checked="checked"/></td>						 
  								 </tr>
  								 <% j = j+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=j %> name="size"/>
								<button type="submit">Submit For Deletion</button>
								
								</form:form>
								</c:if>
    							</table>
    	
        				</div>
        				<div class="tab-pane" id="Tab9">
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