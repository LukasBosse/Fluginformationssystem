<%@ page import="com.fis.controller.FlugController" %>
<%@ page import="com.fis.model.DetailFlug" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
     <meta name="viewport" content="width=device-width, initial-scale=1">

	 <!-- Compiled and minified CSS -->
	 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  	 <link rel="stylesheet" href="assets/css/main.css">


	 <!-- Compiled and minified JavaScript -->
	 <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

	 <title>Fluginformationssystem (FIS) - Mitarbeiteransicht - Details</title>
	 
 </head>
 <body>
 
 	<nav>
	    <div class="nav-wrapper">
	      <ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
	      <ul id="nav-mobile" class="left hide-on-med-and-down">
	        <li class="active"><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
	        <li><a href="Buchungen.jsp"><i class="material-icons left">border_color</i> Buchungen</a></li>
	        <li><a href="Passagiere.jsp"><i class="material-icons left">person</i> Passagiere</a></li>
	      </ul>
	    </div>
  	</nav>
 
 	<div class="row">
 		<div class="col s4"></div>
 		<div class="col s4">
	 		<div class="card">
	  			<div class="card-content">
	  				 <table class="highlight centered">
			        	<thead>
				          <tr>
				              <th>Flugnr</th>
				              <th>Flugzeug</th>
				              <th>Startort</th>
				              <th>Landeort</th>
				              <th>Startzeit</th>
				              <th>Landezeit</th>
				              <th>Flugdauer</th>
				              <th>Distanz</th>
				              <th>Inklusive</th>
				          </tr>
			        	</thead>		
			        	<tbody>
 			<%
 				if(request.getParameter("flugNr") != null) {
	 				FlugController flugController = new FlugController();
	 				DetailFlug obj = flugController.findFlugByIdWithDetails(request.getParameter("flugNr"));
	 				out.println("<tr>");
	 				out.println("</tr>");
 				}
 			%>	
 						</tbody>
					</table>
				</div>
			</div>
 		</div>
 		<div class="col s4"></div>
 	</div>

</body>
</html>