<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.DatabaseConnection" %>
<%@ page import="com.fis.de.HTMLWriter" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.de.User" %>
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

	 <title>Fluginformationssystem (FIS) - Manageransicht - Flugzeuge</title>
	 
	 <%
	
	 	new Redirection().checkDirection(session, response, "Manager");
	 	HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());
	 	DatabaseConnection dbC = new DatabaseConnection();
	 	ResultSet rs;
	 	
	  	if(request.getParameter("submitFlugzeugAdd") != null) {
	  		if(request.getParameter("hersteller") != null && request.getParameter("muster") != null && request.getParameter("sitze") != null && request.getParameter("fluggesellschaft") != null) {
	  			dbC.connect();
	  			if(dbC.execute("INSERT INTO flugzeuge (hersteller, type, sitze, fluggesellschaft) VALUES (?,?,?,?)", new String[] {request.getParameter("hersteller"),request.getParameter("muster"),request.getParameter("sitze"),request.getParameter("fluggesellschaft")})) {
	  				htmlWriter.writeAlert("Erfolg!", "Das Flugzeug wurde erfolgreich hinzugefügt.", "alert-success");
				} else {
					htmlWriter.writeAlert("Warnung!", "Das Flugzeug konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger");
				}
	  			dbC.disconnect();
	  		}
	  	}
	  	if(request.getParameter("submitRelationAdd") != null) {
	  		if(request.getParameter("flug") != null && request.getParameter("flugzeug") != null) {
	  			dbC.connect();
	  			if(dbC.execute("UPDATE flugzeuge SET fluglinie = ? WHERE id = ?", new String[] {request.getParameter("flug"),request.getParameter("flugzeug")})) {
	  				htmlWriter.writeAlert("Erfolg!", "Das Flugzeug wurde erfolgreich zugewiesen.", "alert-success");
				} else {
					htmlWriter.writeAlert("Warnung!", "Das Flugzeug konnte nicht zugewiesen werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger");
				}
	  			dbC.disconnect();
	  		}
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
	  								dbC.connect();
	  								rs = dbC.executeQuery("SELECT * FROM fluglinien", null);
	  								while(rs.next()) {
	  									out.println("<option value='" + rs.getString("ID") + "'>" + rs.getString("Bezeichnung") + " - " + rs.getString("Gesellschaft") + "</option>");
	  								}
 	  								dbC.disconnect();
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
	  					dbC.connect();
	  					rs = dbC.executeQuery(" SELECT f.id, f.hersteller, f.type, f.sitze, f.fluglinie, g.Gesellschaft FROM flugzeuge As `f` INNER JOIN fluglinien As `g` ON g.ID = f.fluggesellschaft", null);
	  					HashMap<Integer, String> flugzeugMap = new HashMap<Integer, String>();
	  					while(rs.next()) {
	  						int flugzeugId = rs.getInt("f.id");
	  						String flugzeugHersteller = rs.getString("f.hersteller");
	  						String flugzeugMuster = rs.getString("f.type");
	  						out.println("<tr>");
		  						out.println("<td>" + flugzeugId + "</td>");
		  						out.println("<td>" + flugzeugHersteller + "</td>");
		  						out.println("<td>" + flugzeugMuster + "</td>");
		  						out.println("<td>" + rs.getInt("f.sitze") + "</td>");
		  						if(rs.getString("f.fluglinie") != null) {
		  							out.println("<td>" + rs.getString("f.fluglinie") + "</td>");
		  						} else {
		  							out.println("<td> - </td>");
		  						}
		  						out.println("<td>" + rs.getString("g.Gesellschaft") + "</td>");
	  						out.println("</tr>");
	  						flugzeugMap.put(flugzeugId, flugzeugHersteller + " - " + flugzeugMuster);
	  					}
	  					dbC.disconnect();
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
		  						 Iterator iT = flugzeugMap.entrySet().iterator();
		  						 while(iT.hasNext()) {
		  							 Map.Entry pair = (Map.Entry)iT.next();
		  							 out.println("<option value='" + pair.getKey() + "'>" + pair.getValue() + "</option>");
		  							 iT.remove();
		  						 }
		  						%>
	  						</select>
	  						<label for="flugzeug">Flugzeug</label>
	  					</div>
	  					<div class="input-field col s6">
	  						<select name="flug" id="flug">
	  							<option disabled selected value> -- Bitte wählen Sie einen Flug aus -- </option>
	  							<%
	  							dbC.connect();
	  							rs = dbC.executeQuery("SELECT f.flugnr, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel` FROM `flug` As `f` INNER JOIN flughäfen AS `flS` ON flS.ID = f.start INNER JOIN flughäfen As `flD` ON flD.ID = f.ziel;", null);
	  							while(rs.next()) {
	  								String flugNr = rs.getString("flugnr");
	  								out.println("<option value='" + flugNr  + "'>" + flugNr + " (" + rs.getString("Start") + " &#8594; " + rs.getString("Ziel") + ")</option>");
	  							}
	  							dbC.disconnect();
	  							%>	  						
	  						</select>
	  						<label for="flug">Flug</label>
	  					</div>
  					</div>
  					<div class="row">
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