<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.DatabaseConnection" %>
<%@ page import="com.fis.de.HTMLWriter" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.de.User" %>
<%@ page import="com.fis.de.Verification" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	 <title>Fluginformationssystem (FIS) - Mitarbeiteransicht</title>
	 
 </head>
 <body>

  <% 
  	
  	new Redirection().checkDirection(session, response, "Mitarbeiter");
  	HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());
  	DatabaseConnection dbC = new DatabaseConnection();
  	ResultSet rs;
  	
  	if(request.getParameter("bestaetigt") != null) {
  		dbC.connect();
  		if(dbC.execute("UPDATE buchung SET bestaetigt = true WHERE ID = ?", new String[] {request.getParameter("bestaetigt")})) {
  			htmlWriter.writeAlert("Erfolg!", "Die Buchung wurde bestätigt.", "alert-success", "right");
  		} else {
  			htmlWriter.writeAlert("Warnung!", "Die Buchung wurde nicht erfolgreich bestätigt.", "alert-danger", "right");  			
  		}
  		dbC.disconnect();
  	}
  	if(request.getParameter("inklMahlzeit") != null) {
  		dbC.connect();
		if(dbC.execute("UPDATE flug SET inklusiveMahlzeit = !inklusiveMahlzeit WHERE flugnr = ?", new String[] {request.getParameter("inklMahlzeit")})) {
  			htmlWriter.writeAlert("Erfolg!", "Dem Flug wurde eine Mahlzeit hinzugefügt.", "alert-success", "right");
  		} else {
  			htmlWriter.writeAlert("Warnung!", "Die Mahlzeit konnte nicht erfolgreich hinzugefügt werden..", "alert-danger", "right");  			
  		}
  		dbC.disconnect();
  	}
  	
  %>

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
		        		dbC.connect();
		        		rs = dbC.executeQuery("SELECT * FROM buchung WHERE bestaetigt = 0 LIMIT 8", null);
		        		int i = 0;
		        		while(rs.next()) {
		        			out.println("<tr>");
		        			out.println("<td>" + rs.getString("name") +"</td>");
		        			out.println("<td>" + rs.getString("flugnr") +"</td>");
		        			out.println("<td>" + rs.getString("tag") +"</td>");
		        			out.println("<td>" + rs.getString("preis") + "</td>");
		        			out.println("<td><form action='" + request.getRequestURL() + "' method='GET'>");
		        			out.println("<input type='checkbox' onChange='this.form.submit();' name='bestaetigt' value='" + rs.getInt("ID") + "' id='bestaetigung" + i + "'><label for='bestaetigung" + i + "'></label>");
		         			out.println("</form></td>");
		        			out.println("</tr>");
		        			i++;
		        		}
		        		dbC.disconnect();
		        		%>
		        	</tbody>    
	        	</table>
  			</div>
  		</div>
  	</div>
  	<div class="col s6">
  		<div class="card">
  			<div class="card-content">
  				 <table class="highlight centered">
		        	<thead>
			          <tr>
			          	  <th>ID</th>
			              <th>Flugnummer</th>
			              <th>Flugzeit</th>
			              <th>Inklusive Mahlzeit</th>
			          </tr>
		        	</thead>		
		        	<tbody>
		        		<%
		        			dbC.connect();
		        			rs = dbC.executeQuery("SELECT flugnr, flugzeit, inklusiveMahlzeit FROM flug", null);
		        			int counter = 1;
		        			while(rs.next()) {
		        				out.println("<tr>");
		        				out.println("<td>" + counter +"</td>");
		        				out.println("<td>" + rs.getString("flugnr") + "</td>");
		        				out.println("<td>" + rs.getDouble("flugzeit") +"</td>");
			        			out.println("<td><form action='" + request.getRequestURL() + "' method='GET'>");
		        				out.println("<input type='checkbox' name='inklMahlzeit' onChange='this.form.submit();' value='" + rs.getString("flugnr") + "' id='" + counter + "'");
		        				if(rs.getBoolean("inklusiveMahlzeit")) out.println("checked");
		        				out.println("><label for='" + counter + "'></label></form></td>");
		        				out.println("</tr>");
		        				counter++;
		        			}
		        			dbC.disconnect();
		        		%>
		        	</tbody>
	        	</table>
  			</div>
		</div>
  	</div>
  </div>

  
  <!-- Compiled and minified JavaScript -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 


</body>
</html>