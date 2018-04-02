<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.controller.FlugzeugController" %>
<%@ page import="com.fis.controller.FluggesellschaftenController" %>
<%@ page import="com.fis.controller.FlugController" %>
<%@ page import="com.fis.dto.*" %>
<%@ page import="com.fis.de.Verification" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 	 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
     <meta name="viewport" content="width=device-width, initial-scale=1">

	 <!-- Compiled and minified CSS -->
	 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
  	 <link rel="stylesheet" href="assets/css/main.css">


	 <!-- Compiled and minified JavaScript -->
	 <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  
	 <script>
		 $(document).ready(function() {
			   $('select').material_select();
		 });
	 </script>

	 <title>Fluginformationssystem (FIS) - Manageransicht - Flugzeuge</title>
	 
	 <%
	
	 	new Redirection().checkDirection(session, response, "Manager");
	 	FlugzeugController flugzeugController = new FlugzeugController();
	 	FluggesellschaftenController fluggesellschaftenController = new FluggesellschaftenController();
	 	FlugController flugController = new FlugController();
	 	
	  	if(request.getParameter("submitFlugzeugAdd") != null) {
	  		flugzeugController.create(response.getWriter(), new Flugzeuge(request.getParameter("hersteller"),request.getParameter("muster"),Integer.parseInt(request.getParameter("sitze")),Integer.parseInt(request.getParameter("fluggesellschaft"))));
	  	}
	  	if(request.getParameter("submitRelationAdd") != null) {
	  		flugzeugController.updateFluglinie(response.getWriter(), Integer.parseInt(request.getParameter("flugzeug")), request.getParameter("flug"));
	  	}
	 	
	 %>
	 
</head>

<body>

 <nav>
    <div class="nav-wrapper">
      <ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
      <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
        <li class="active"><a href="Flugzeuge.jsp"><i class="material-icons left">flight</i> Flugzeuge</a></li>
        <li><a href="Relationen.jsp"><i class="material-icons left">date_range</i> Relationen</a></li>
      </ul>
    </div>
  </nav>
  
  <div class="row">
  	<div class="col s6">
  		<div class="card">
  			<div class="card-content" style="padding-bottom: 5px !important;">
	  			<form action="#" method="GET">
	  				<div class="row">
		  				<div class="input-field col s6">
		  					<input type="text" id="hersteller" name="hersteller" class="validate" required>
		  					<label for="hersteller">Hersteller</label>
		  				</div>
		  				<div class="input-field col s6">
		  					<input type="text" id="muster" name="muster" class="validate" required>
		  					<label for="muster">Muster</label>
		  				</div>
	  				</div>
	  				<div class="row">
	  					<div class="input-field col s6">
	  						<input type="text" id="sitze" name="sitze" class="validate" required>
	  						<label for="sitze">Sitzplätze</label>
	  					</div>
	  					<div class="input-field col s6">
	  						<select id="fluggesellschaft" name="fluggesellschaft" class="validate" required>
	  							<option disabled selected value> -- Bitte wählen Sie ein Fluggesellschaft aus -- </option>	  						
	  							<%
	  							for(Fluggesellschaften f : fluggesellschaftenController.listAllFlugzeuge()) {
	  								out.println("<option value='" + f.getId() + "'>" + f.getBezeichnung() + " - " + f.getGesellschaft() + "</option>");
	  							}
	  							%>
	  						</select>
	  						<label for="fluggesellschaft"></label>
	  					</div>
	  				</div>
	  				<div class="row">
	  					<div class="input-field col s12">
	  						<button class="btn waves-effect waves-light" type="submit" name="submitFlugzeugAdd">Flugzeug hinzufügen</button>
	  					</div>
	  				</div>
	  			</form>
  			</div>
  		</div>
  	</div>
  	<div class="col s6">
	  	<div class="card">
	  			<div class="card-content" style="padding-bottom: 5px !important;">
		  			<table class="highlight centered">
						        <thead>
						          <tr>
						              <th>ID</th>
						              <th>Hersteller</th>
						              <th>Muster</th>
	        					      <th>Sitze</th>
	        					      <th>Fluglinie</th>
	        					      <th>Fluggesellschaft</th> 
						          </tr>
						        </thead>
						        <tbody>
	  				<%
	  					List<Object[]> objs = flugzeugController.findFlugzeugWithDetails();
	  					for(int i = 0; i < objs.size(); i++) {
	  						Object[] obj = objs.get(i);
	  						out.println("<tr>");
	  						out.println("<td>" + (i+1) + "</td>");
	  						out.println("<td>" + obj[1] + "</td>");
	  						out.println("<td>" + obj[2] + "</td>");
	  						out.println("<td>" + obj[3] + "</td>");
	  						if(obj[4] != null) {
	  							out.println("<td>" + obj[4] + "</td>");
	  						} else {
	  							out.println("<td> - </td>");
	  						}
	  						out.println("<td>" + obj[5] + "</td>");
  							out.println("</tr>");
	  					}
		  			%>
	  					</tbody>
					</table>
	  			</div>
		</div>
	</div>
  </div>
  
  <div class="row">
  	<div class="col s6"></div>
  	<div class="col s6">
  		<div class="card">
  			<div class="card-content">
  				<form action="#" method="GET">
  					<div class="row">
	  					<div class="input-field col s6">
	  						<select name="flugzeug" id="flugzeug">
	  							<option disabled selected value> -- Bitte wählen Sie ein Flugzeug aus -- </option>	  						
		  						<%
		  						for(Object[] obj : objs) {
		  							 out.println("<option value='" + obj[0] + "'>" + obj[1] + " - " + obj[2] + "</option>");
		  						}
		  						%>
	  						</select>
	  						<label for="flugzeug">Flugzeug</label>
	  					</div>
	  					<div class="input-field col s6">
	  						<select name="flug" id="flug">
	  							<option disabled selected value> -- Bitte wählen Sie einen Flug aus -- </option>
	  							<%
	  							List<Object[]> fluglinienObj = flugController.listAllFluglinien();
	  							for(Object[] obj : fluglinienObj) {
	  								out.println("<option value='" + obj[0]  + "'>" + obj[0] + " (" + obj[1] + " &#8594; " + obj[2] + ")</option>");
	  							}
	  							%>	  						
	  						</select>
	  						<label for="flug">Flug</label>
	  					</div>
	  					<div class="input-field col s12">
  							<button class="btn waves-effect waves-light" type="submit" name="submitRelationAdd">Flug zuweisen</button>
  						</div>
  					</div>
  				</form>
  			</div>
  		</div>
  	</div>
  </div>

</body>
</html>