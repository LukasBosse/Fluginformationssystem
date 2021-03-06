<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.dto.User" %>
<%@ page import="com.fis.dto.Flug" %>
<%@ page import="com.fis.dto.Passagier" %>
<%@ page import="com.fis.dto.Buchung" %>
<%@ page import="com.fis.de.Statusverwaltung" %>
<%@ page import="com.fis.controller.BuchungsController" %>
<%@ page import="com.fis.controller.FlugController" %>
<%@ page import="com.fis.controller.PassagierController" %>
<%@ page import="com.fis.de.Verification" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
     <meta name="viewport" content="width=device-width, initial-scale=1">

	 <!-- Compiled and minified CSS -->
	 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
  	 <link rel="stylesheet" href="assets/css/main.css">

	 <!-- Compiled and minified JavaScript -->
	 <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  	 <script src="assets/js/Datepicker.js"></script>
	 <script type="text/javascript" src="assets/js/DetailLoader.js"></script>

	 <title>Fluginformationssystem (FIS) - Mitarbeiteransicht</title>
	 
 </head>
 <body>

  <% 
  	
  	new Redirection().checkDirection(session, response, "Mitarbeiter");
  	BuchungsController buchungsController = new BuchungsController();
  	FlugController flugController = new FlugController();
  	PassagierController passagierController = new PassagierController();
  	
  	if(request.getParameter("bestaetigt") != null) { buchungsController.updateBuchung(response.getWriter(), Integer.parseInt(request.getParameter("bestaetigt"))); }
  	if(request.getParameter("inklMahlzeit") != null) { flugController.updateFlug(response.getWriter(),request.getParameter("inklMahlzeit")); }
  	
  %>

  <nav>
    <div class="nav-wrapper">
      <ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
      <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li class="active"><a href="Mitarbeiter.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
        <li><a href="Buchungen.jsp"><i class="material-icons left">border_color</i> Buchungen</a></li>
        <li><a href="Passagiere.jsp"><i class="material-icons left">person</i> Passagiere</a></li>
      </ul>
    </div>
  </nav>
  
  <div class="row">
  	<div class="col s6">
  		<div class="card">
  			<div class="card-content">
  				 <table class="highlight centered">
		        	<thead>
			          <tr>
			              <th>Kunde</th>
			              <th>Flugnummer</th>
			              <th>Reisedatum</th>
			              <th>Preis (€)</th>
			              <th>Bestätigt</th>
			          </tr>
		        	</thead>		
		        	<tbody>
		        		<%
		        		for(Buchung b : buchungsController.findUnbestätigteBuchung()) {
		        			out.println("<tr>");
		        			out.println("<td>" + b.getName() +"</td>");
		        			out.println("<td>" + b.getFlugnr() +"</td>");
		        			out.println("<td>" + b.formatDate(b.getTag()) +"</td>");
		        			out.println("<td>" + b.getPreis() + "</td>");
		        			out.println("<td><form action='" + request.getRequestURL() + "' method='GET'>");
		        			out.println("<input type='checkbox' onChange='this.form.submit();' name='bestaetigt' value='" + b.getId() + "' id='bestaetigung" + b.getId() + "'><label for='bestaetigung" + b.getId() + "'></label>");
		         			out.println("</form></td>");
		        			out.println("</tr>");
		        		}
		        		%>
		        	</tbody>    
	        	</table>
  			</div>
  		</div>
  	</div>
  	<div class="col s6">
  		<div class="card">
  			<div class="card-content">
  				<div class="row">
  					<form action="Mitarbeiter.jsp" method="GET">
		  				<div class="input-field col s4">
					    	<input id="ersteZeit" name="ersteZeit" class="timepicker validate" required/>
					    	<label for="ersteZeit">Von</label>          			
					    </div>
					    <div class="input-field col s4">
					    	<input id="zweiteZeit" name="zweiteZeit" class="timepicker validate" required/>
					    	<label for="zweiteZeit">Bis</label>          			
					    </div>
		  				<div class="input-field col s2">
				  			<button class="btn waves-effect waves-light" type="submit" name="submitNewCurrentTime">Suchen</button>
						</div>
					</form>	
				</div>
				<div class="row">
	  				<table class="highlight centered col s12">
			        	<thead>
				          <tr>
				              <th>Flugnummer</th>
				              <th>Flugzeit (h)</th>
				              <th>Status</th>
				              <th>Inklusive Mahlzeit</th>
				          </tr>
			        	</thead>		
			        	<tbody>
			        		<%
			        	  		if(request.getParameter("submitNewCurrentTime") != null) { 
			        	  			for(Flug f : flugController.listFlightsByTime(request.getParameter("ersteZeit"), request.getParameter("zweiteZeit"))) {
			        	  				out.println("<tr>");
				        				out.println("<td><a href='#' onclick='loadDetailsByID(this)'>" + f.getFlugnr() + "</a></td>");
				        				out.println("<td>" + f.getFlugzeit() +"</td>");
				        				out.println("<td>" + Statusverwaltung.verifyStatus(f) + "</td>");
					        			out.println("<td><form action='' method='GET'>");
				        				out.println("<input type='checkbox' name='inklMahlzeit' onChange='this.form.submit();' value='" + f.getFlugnr() + "' id='" + f.getFlugnr() + "'");
				        				if(f.getInklusiveMahlzeit()) out.println("checked");
				        				out.println("/><label for='" + f.getFlugnr() + "'></label></form></td>");
				        				out.println("</tr>");
			        	  			}
			        	
			        	  		}
			        		%>
			        	</tbody>
		        	</table>
	        	</div>
  			</div>
		</div>
  	</div>
  </div>
  
  <div class="row">
  		<div class="col s12">
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
				              <th>Aktion</th>
				          </tr>
		        		</thead>		
			        	<tbody id="flugDetails">
			        	</tbody>
	        		</table>
	        	</div>
	    	</div>
    	</div>
  </div>
  

</body>
</html>