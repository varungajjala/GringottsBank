<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    
<title>One Time Password</title>
</head>
<body>
  	<div class="row">
  		<div class="col-md-12" style="padding-top: 40px">
  			<div class="panel panel-default" style="background: #eee">
  				<div class="panel-body" >
    				<div class="page-header" style="margin-top: 5px;" align="center">
           				<h2>Confirm your One Time Password</h2>
					</div>
				<form:form  action="confirmOtp" commandName="input" class="form-horizontal" method="POST">
  				<div class="col-sm-8">
        			<label class="col-sm-2 control-label">One Time Password</label>
        			<form:input path="password" type="text" class="form-control" id="password" placeholder="password" required="true"/>
        
    			 	<button type="submit" class="btn btn-success" >Confirm</button>
    			</div> 	
				</form:form>
			</div>
		</div>
	</div>
</div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    
    </script>
    </body>
</html>