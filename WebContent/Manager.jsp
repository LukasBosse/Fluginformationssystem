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

	 <title>Fluginformationssystem (FIS) - Manageransicht</title>
	 
</head>

<body>

<% 

	DatabaseConnection dbC = new DatabaseConnection();
	ResultSet rs;

	new Redirection().checkDirection(session, response, "Manager");
	HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());	

	if(request != null) {
		if(request.getParameter("submitFlugAdd") != null) {
			if(request.getParameter("flugNr") != null && request.getParameter("flugzeug") != null) {
				if(request.getParameter("startOrt") != null) {
					if(request.getParameter("zielOrt") != null) {
						if(request.getParameter("flugZeit") != null) {
							if(request.getParameter("flugDistanz") != null) {			
								dbC.connect();
								String[] param = new String[] {
										request.getParameter("flugNr").toString(),
										request.getParameter("flugzeug").toString(),
										request.getParameter("startOrt").toString(),
										request.getParameter("zielOrt").toString(),
										request.getParameter("flugZeit").toString(),									
										request.getParameter("flugDistanz").toString()
								};
								if(dbC.execute("INSERT INTO flug (flugnr, flugzeug, start, ziel, flugzeit, km) VALUES (?,?,?,?,?,?)", param)) {
									htmlWriter.writeAlert("Erfolg!", "Ihr Flug wurde erfolgreich hinzugefügt.", "alert-success", "right");
								} else {
									htmlWriter.writeAlert("Warnung!", "Ihr Flug wurde leider <u>nicht</u> erfolgreich hinzugefügt. Bitte überprüfen Sie Ihre Eingaben!", "alert-danger", "right");
								}
								dbC.disconnect();
							}	
						}	
					}	
				}
			}
		}
	}
		
%>

  <nav>
    <div class="nav-wrapper">
      <ul class="right"><li><a href="Logout.jsp" class="logoutBtn"><i class="material-icons">exit_to_app</i></a></li></ul>
      <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li class="active"><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i> Flüge</a></li>
        <li><a href="Flugzeuge.jsp"><i class="material-icons left">flight</i> Flugzeuge</a></li>
        <li><a href="Relationen.jsp"><i class="material-icons left">date_range</i> Relationen</a></li>
      </ul>
    </div>
  </nav>
  
  <div class="row">
	  <div id="fluege" class="col s6">
			    <div class="card horizontal">
			      <div class="card-stacked">
			        <div class="card-content">
			        	<div class="row">
			          		<form method="GET" class="col s12" action="<% out.println(request.getRequestURL());%>">
			          			<div class="row">
			          				<div class="input-field col s12">
	    						      <input id="flugNr" type="text" name="flugNr" class="validate" required>
							          <label for="flugNr">Flugnummer</label>
							        </div>
			          			</div>
			          			<div class="row">
			          				<div class="input-field col s12">
			          					<select id="flugzeuge" name="flugzeug" required>
			          					    <option disabled selected value> -- Bitte wählen Sie ein Flugzeug aus -- </option>
			          						<%
			          							dbC.connect();
							        		  	rs = dbC.executeQuery("SELECT * FROM flugzeuge", null);
							        		  	while(rs.next()) {
							        		  		out.println("<option value='" + rs.getInt("ID") +"'>" + rs.getString("hersteller") + " | " + rs.getString("type") + " | (" + rs.getInt("sitze") + ")</option>");    
							        		  	}
							        		  	dbC.disconnect();
			          						%>
			          					</select>
			          					<label for="flugzeuge">Flugzeuge</label>
			          				</div>
			          			</div>
			          			<div class="row">
			          				<div class="input-field col s6">
			          				  <select id="startOrt" name="startOrt" class="validate" required>
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
							          <select id="zielOrt" name="zielOrt" class="validate" required>
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
			          			</div>
			          			<div class="row">
			          				<div class="input-field col s12">		          			
	    						      <input id="flugZeit" type="text" name="flugZeit" class="validate" required>
							          <label for="flugZeit">Flugdauer <small><i>(in Stunden)</i></small></label>
							        </div>
			          			</div>
			          			<div class="row">
			          				<div class="input-field col s12">
	    						      <input id="flugDistanz" type="text" name="flugDistanz" class="validate" required>
							          <label for="flugDistanz">Distanz <small><i>(in Kilometer)</i></small></label>
							        </div>
			          			</div>
			         			<div class="row">
			          				<div class="input-field col s12">
									  <button class="btn waves-effect waves-light" type="submit" name="submitFlugAdd">Flug hinzufügen</button>
							        </div>
			          			</div>
			          		</form>
			          	</div>
			        </div>
			      </div>
			    </div>
		  </div>
		  <div class="col s6">
		  	<div class="card">
		      <table class="highlight centered">
		        <thead>
		          <tr>
		              <th>Flugnummer</th>
		              <th>Flugzeug</th>
		              <th>Startort</th>
		              <th>Zielort</th>
		              <th>Auslastung</th>
		              <th>Flugdauer</th>
		              <th>Distanz</th>
		          </tr>
		        </thead>		
		        <tbody>
		          <%
		          
		          	dbC.connect();
					rs = dbC.executeQuery("SELECT fZ.fluglinie, fZ.hersteller, fZ.type, fH.Bezeichnung As `Startort`, fHZ.Bezeichnung As `Zielort`, f.flugzeit, f.km , COUNT(b.flugnr) As `Auslastung`, fZ.sitze As `Kapazität` FROM `flugzeuge` As `fZ` INNER JOIN flug As `f` ON fZ.fluglinie = f.flugnr INNER JOIN flughäfen As `fH` ON fH.ID = f.start INNER JOIN flughäfen As `fHZ` ON fHZ.ID = f.ziel INNER JOIN buchung As `b` ON b.flugnr = fZ.fluglinie GROUP BY fZ.fluglinie ", null);	
					while(rs.next()) {
						out.println("<tr>");
						out.println("<td>" + rs.getString("fz.fluglinie") + "</td>");
						out.println("<td>" + rs.getString("fZ.hersteller") + " - " + rs.getString("fZ.type") + "</td>");
						out.println("<td>" + rs.getString("Startort") + "</td>");
						out.println("<td>" + rs.getString("Zielort") + "</td>");
						out.println("<td>" + rs.getInt("Auslastung") + " / " + rs.getInt("Kapazität") + "</td>");
						out.println("<td>" + rs.getString("f.flugzeit") + "h</td>");
						out.println("<td>" + rs.getString("f.km") + "km</td>");
						out.println("</tr>");
					}
					dbC.disconnect(); 
		   
		          %>
		        </tbody>
		      </table>
		  	</div>
		  </div>
	  </div>

</body>
</html>