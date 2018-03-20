<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.DatabaseConnection" %>
<%@ page import="com.fis.de.HTMLWriter" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.de.User" %>
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

	 <title>Fluginformationssystem (FIS) - Manageransicht - Relationen</title>
	 
	  <script>
		 $(document).ready(function() {
			   $('select').material_select();
		 });
	 </script>
	 
</head>

	<% 
		new Redirection().checkDirection(session, response, "Manager");
		DatabaseConnection dbC = new DatabaseConnection();
		HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());
		ResultSet rs;
		
		if(request != null) {
			if(request.getParameter("submitFlughafenAdd") != null && request.getParameter("flughafenBezeichnung") != null) {
				dbC.connect();
				if(dbC.execute("INSERT INTO flughäfen (Bezeichnung) VALUES (?)", new String[] {request.getParameter("flughafenBezeichnung")})) {
					htmlWriter.writeAlert("Erfolg!", "Der Flughafen wurden erfolgreich hinzugefügt.", "alert-success");
				} else {
					htmlWriter.writeAlert("Warnung!", "Der Flughafen konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger");
				}
				dbC.disconnect();
			}
			if(request.getParameter("submitRelationAdd") != null) {
				if(request.getParameter("startOrt") != null && request.getParameter("landeOrt") != null) {
					dbC.connect();
					if(dbC.execute("INSERT INTO relationen (Startort, Zielort) VALUES (?,?)", new String[] {request.getParameter("startOrt"),request.getParameter("landeOrt")})) {
						htmlWriter.writeAlert("Erfolg!", "Die Relation wurden erfolgreich hinzugefügt.", "alert-success");
					} else {
						htmlWriter.writeAlert("Warnung!", "Die Relation konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger");
					}
					dbC.disconnect();
				}
			}
		}
		
		
	%>

  	<nav>
    	<div class="nav-wrapper">
      		<ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
      		<ul id="nav-mobile" class="left hide-on-med-and-down">
        		<li><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
        		<li><a href="Flugzeuge.jsp"><i class="material-icons left">flight</i> Flugzeuge</a></li>
        		<li class="active"><a href="Relationen.jsp"><i class="material-icons left">date_range</i> Relationen</a></li>
      		</ul>
   		</div>
  	</nav>
  	
  	<div class="row cardPanel" style="padding-top: 10px;">
  		 <div class="col s12">
			 <div class="card">
			    <div class="card-content" style="padding-bottom: 5px !important;">
			    	<form action="<% out.println(request.getRequestURL());%>" method="GET" class="row">
			    		<div class="input-field col s10">
			    			<input id="flughafen" name="flughafenBezeichnung" type="text" class="validate" required>
			    			<label for="flughafen">Flughafen</label>
			    		</div>
			    		<div class="input-field col s2">
							<button class="btn waves-effect waves-light" type="submit" name="submitFlughafenAdd">Flughafen hinzufügen</button>
				        </div>
			    	</form>
		        </div>
	         </div>
		 </div>
  	</div>
  	
  	<div class="row cardPanel">
  		<div class="col s12">
  			<div class="card">
  				<div class="card-content" style="padding-bottom: 0 !important;">
  					<form action="<% out.println(request.getRequestURL());%>" method="post" class="row">
			        	<div class="input-field col s6">
				        	<select id="startOrt" name="startOrt">
				        		<option disabled selected value>Bitte wählen Sie einen Startflughafen aus</option>
				        		<%
				        			dbC.connect();
				        			rs = dbC.executeQuery("SELECT * FROM flughäfen", null);
				        			HashMap<Integer, String> flughäfen = new HashMap<Integer, String>();
				        			while(rs.next()) {
				        				int flughafenID = rs.getInt("ID");
	      								String bezeichnung = rs.getString("Bezeichnung");
	      								flughäfen.put(flughafenID,bezeichnung);
	      								out.println("<option value='" + flughafenID + "'>" + bezeichnung + "</option>");
				        			}
				        			dbC.disconnect();
				        		%>
				        	</select>
				        	<label for="startOrt">Startort</label>
				        </div>
				        <div class="input-field col s6">
				        	<select id="landeOrt" name="landeOrt">
				        		<option disabled selected value>Bitte wählen Sie einen Zielflughafen aus</option>
				        		<%
				        			Iterator iT = flughäfen.entrySet().iterator();
				        			while(iT.hasNext()) {
				        				Map.Entry pair = (Map.Entry)iT.next();
	      								out.println("<option value='" + pair.getKey() + "'>" + pair.getValue() + "</option>");
	      								iT.remove();
				        			}
				        		%>
				        	</select>
				        	<label for="zielOrt">Zielort</label>
			        	</div>
			        	<div class="row">
			        		<div class="input-field col s12">
			        			<button class="btn waves-effect waves-light" type="submit" name="submitRelationAdd">Relation hinzufügen</button>
			        		</div>
			        	</div>        	
			        </form>
  				</div>
  			</div>
  		</div>
  	</div>
  	
  	<div class="row cardPanel">
  		<div class="col s6">
	  		<div class="card">
	  			<div class="card-content">
		  			 <table class="highlight centered">
				        <thead>
				          <tr>
				              <th>ID</th>
				              <th>Flughafen</th>
				          </tr>
				        </thead>
				        <tbody>
	  				<%
	  					dbC.connect();
					    rs = dbC.executeQuery("SELECT * FROM flughäfen", null);
					    while(rs.next()) {
					    	out.println("<tr>");
					    	out.println("<td>" + rs.getInt("ID") +  "</td>");
					    	out.println("<td>" + rs.getString("Bezeichnung") +  "</td>");
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
	  				 <table class="highlight centered">
				        <thead>
				          <tr>
				              <th>ID</th>
				              <th>Starthafen</th>
				              <th>Zielhafen</th>
				          </tr>
				        </thead>
				        <tbody>
	  				<%
	  					dbC.connect();
					    rs = dbC.executeQuery("SELECT relationen.ID, starthafen.Bezeichnung As `Starthafen`, landehafen.Bezeichnung As `Landehafen` FROM relationen INNER JOIN flughäfen As `starthafen` ON relationen.Startort = starthafen.ID INNER JOIN flughäfen As `landehafen` ON relationen.Zielort = landehafen.ID", null);
					    while(rs.next()) {
					    	out.println("<tr>");
					    	out.println("<td>" + rs.getInt("ID") + "</td>");
					    	out.println("<td>" + rs.getString("Starthafen") +  "</td>");
					    	out.println("<td>" + rs.getString("Landehafen") +  "</td>");
					    	out.println("</tr>");
					    }
					    dbC.disconnect();
	  				%>
	  				  </tbody>
			      </table>
	  			</div>
	  		</div>
  		</div>
  	</div>

<body>

</body>
</html>