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
	 
	 <script>
		 $(document).ready(function() {
			   $('select').material_select();
		 });
	 </script>
	 
	 <title>Fluginformationssystem (FIS) - Mitarbeiteransicht - Passagiere</title>
	 
 </head>
 <body>

  <% 	
  	
    new Redirection().checkDirection(session, response, "Mitarbeiter");
  	HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());
  	DatabaseConnection dbC = new DatabaseConnection();
  	ResultSet rs;
  	
  	if(request.getParameter("submitPassagierAdd") != null) {
  		if(request.getParameter("geschlecht") != null && request.getParameter("kundenname") != null && request.getParameter("wohnort") != null && request.getParameter("geburtstag") != null) {
  			dbC.connect();
  			if(dbC.execute("INSERT INTO passagier (name, geschlecht, ort, geburtsdatum) VALUES (?,?,?,?)", new String[] {request.getParameter("kundenname"),request.getParameter("geschlecht"),request.getParameter("wohnort"),request.getParameter("geburtstag")})) {
  				htmlWriter.writeAlert("Erfolg!", "Der Passagier wurde erfolgreich hinzugefügt.", "alert-success", "right");
  			} else {
  				htmlWriter.writeAlert("Warnung!", "Der Passagier konnte nicht hinzugefügt werden.", "alert-danger", "right");  				
  			}
  			dbC.disconnect();
  		}
  	}
  	
  %>
  
   <nav>
    <div class="nav-wrapper">
      <ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
      <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
        <li><a href="Buchungen.jsp"><i class="material-icons left">border_color</i> Buchungen</a></li>
        <li class="active"><a href="Passagiere.jsp"><i class="material-icons left">person</i> Passagiere</a></li>
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
			              <th>Name</th>
			              <th>Geschlecht</th>
			              <th>Wohnort</th>
			              <th>Geburtsdatum</th>
			          </tr>
		        	</thead>		
		        	<tbody>
		        		<%
		        		dbC.connect();
		        		rs = dbC.executeQuery("SELECT * FROM passagier LIMIT 9", null);
		        		while(rs.next()) {
		        			out.println("<tr>");
			        			out.println("<td>" + rs.getString("name") +"</td>");
			        			out.println("<td>");
			        			if(rs.getInt("geschlecht")==0) {
			        				out.println("Männlich");
			        			} else {
			        				out.println("Weiblich");
			        			}
			        			out.println("</td>");
			        			out.println("<td>" + rs.getString("ort") +"</td>");
			        			out.println("<td>" + rs.getString("geburtsdatum") + "</td>");
			         			out.println("</td>");
		        			out.println("</tr>");
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
  				<form action="<% out.println(request.getRequestURL()); %>" method="GET">
  					<div class="row">
	  					<div class="input-field col s12">
	  						<select id="geschlecht" name="geschlecht" class="validate" required>
	  							<option disabled selected value>Bitte wählen Sie ein Geschlecht aus</option>
	  							<option value="0">Herr</option>
	  							<option value="1">Frau</option>
	  						</select>
	  						<label for="geschlecht">Geschlecht</label>
	  					</div>
		  				<div class="input-field col s12">
		  					<input type="text" name="kundenname" id="kundenname" class="validate" required>
		  					<label for="kundenname">Name</label>
		  				</div>
		  				<div class="input-field col s12">
		  					<input type="text" name="wohnort" id="wohnort" class="validate" required>
		  					<label for="wohnort">Wohnort</label>
		  				</div>
		  				<div class="input-field col s12">
		  					<input type="text" class="datepicker validate" name="geburtstag" id="geburtstag" required>
		  					<label for="geburtstag">Geburtsdatum</label>
		  				</div>
		  				<div class="input-field col s12">
		  					<button class="btn waves-effect waves-light" type="submit" name="submitPassagierAdd">Passagier hinzufügen</button>
		  				</div>
  					</div>
  				</form>
  			</div>
  		</div>
  	</div>
  </div>
  	
</body>
</html>