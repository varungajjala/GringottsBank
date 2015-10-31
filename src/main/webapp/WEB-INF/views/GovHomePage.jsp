<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Government Home Page</title>

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
  						<li class="active"><a href="#Tab1" data-toggle="tab">PII Access</a></li>
  						<!-- <li><a href="#Tab2" data-toggle="tab">User Requests</a></li>
  						<li><a href="#Tab3" data-toggle="tab">View System Log</a></li>
  						<li><a href="#Tab4" data-toggle="tab">View PII</a></li>
  						<li><a href="#Tab5" data-toggle="tab">Delete User</a></li>
  						<li><a href="#Tab6" data-toggle="tab">Modify User</a></li>
  						<li><a href="#Tab7" data-toggle="tab">Create User</a></li> -->
  						
					</ul>
					
					<div id="my-tab-content" class="tab-content">
					<div class="Tab-pane" id="Tab1">
        					<div class="page-header" style="margin-top: 5px;" align="center">
    							<h3>PII Access</h3>
    							</div>
    													
    						<table align="center" border="1">
    							<tr>
    							
    							<th align="center">First Name</th>
    							<th align="center">Last Name</th>
       							<th align="center">UserName</th>
    							<th align="center">Identification Number</th>
    							<th align="center">Approve</th>
    							<th align="center">Reject</th>
    							</tr>
    						 	<% int i = 0; %>
    							<form:form action="piiauthorization" commandName="piiauthorization" method="GET">
    							 
    							<c:forEach items="${displayPiiUsers}" var="displayPiiUsers">  
    							   
    							<tr>
  								 
  								 <td align="center"><c:out value="${displayPiiUsers.getFirstName()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getLastName()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getUsername()}"/></td>
  								 <td align="center"><c:out value="${displayPiiUsers.getIdentificationNo()}"/></td>
  								 
  							
  								<td><input type="radio" value="approve<%=i%>" name="radioValues<%=i%>"/></td>	
  								<td><input type="radio" checked value="reject<%=i%>" name="radioValues<%=i%>"/></td>					 
  								 </tr>
  								 <% i = i+1; %> 
								</c:forEach>
								<input type="hidden" value=<%=i %> name="size"/>
								<button type="submit">Submit For Approval</button>
								</form:form>
    							</table>
    							
    						</div>
    						
        				</div>
            			
            			
        				
        				</div>
        				        				
 
  					
				</div>
  			</div>
  			<div class="col-md-2"></div>
		</div>
  </body>
</html>