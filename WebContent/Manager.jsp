<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.DatabaseConnection" %>
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
	 <link rel="stylesheet" href="main.css">

	 <title>Fluginformationssystem (FIS) - Manageransicht</title>
</head>

<body>

<% 

	if(session.getAttribute("userId") == null) {
		response.sendRedirect("Main.jsp");
	}
	if(session.getAttribute("userType") != null) {
		if(session.getAttribute("userType") != "Manager") {
			response.sendRedirect("Main.jsp");
		}
	}
	if(request != null) {
		if(request.getParameter("submitFlugAdd") != null) {
			if(request.getParameter("flugNr") != null) {
				if(request.getParameter("startOrt") != null) {
					if(request.getParameter("zielOrt") != null) {
						if(request.getParameter("flugZeit") != null) {
							if(request.getParameter("flugDistanz") != null) {
								DatabaseConnection databaseConnection = new DatabaseConnection();
								databaseConnection.connect();
								String[] param = new String[] {
										request.getParameter("flugNr").toString(),
										request.getParameter("startOrt").toString(),
										request.getParameter("zielOrt").toString(),
										request.getParameter("flugZeit").toString(),									
										request.getParameter("flugDistanz").toString()
								};
								if(databaseConnection.execute("INSERT INTO flug (flugnr, start, ziel, flugzeit, km) VALUES (?,?,?,?,?)", param)) {
									response.getWriter().println("<div class='alert alert-success' role='alert'" +
											"<strong>Erfolg!</strong> Ihr Flug wurde erfolgreich hinzugefügt." +
											  "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
											    "<span aria-hidden='true'>&times;</span>" +
											  "</button>" +
											"</div>");
								} else {
									response.getWriter().println("<div class='alert alert-danger' role='alert'" +
											"<strong>Warnung!</strong> Ihr Flug wurde leider <u>nicht</u> erfolgreich hinzugefügt. Bitte überprüfen Sie Ihre Eingaben!" +
											  "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
											    "<span aria-hidden='true'>&times;</span>" +
											  "</button>" +
											"</div>");
								}								
							}	
						}	
					}	
				}
			}
		}
	}
	
%>

<h1 style="padding-left: 10px;">Hallo <% if(session.getAttribute("username") != null) out.println(session.getAttribute("username").toString()); %></h1>

  <div class="row">
    <div class="col s11">
      <ul class="tabs">
        <li class="tab col s4"><a class="active" href="#fluege">Flüge</a></li>
        <li class="tab col s4"><a href="#flugzeuge">Flugzeuge</a></li>
        <li class="tab col s4"><a href="#relationen">Relationen</a></li>
      </ul>	
    </div>
    <div class="col s1"><ul><li><a id="logoutButton" class="waves-effect waves-light btn" href="Logout.jsp">Logout</a></li></ul></div>
     
	    <div id="fluege" class="col s11 m11 customPanel">
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
    						      <input id="startOrt" type="text" name="startOrt" class="validate" required>
						          <label for="startOrt">Startort</label>
						        </div>
		          			</div>
		          			<div class="row">
		          				<div class="input-field col s12">
		          				  <input id="zielOrt" type="text" name="zielOrt" class="validate" required>
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
	  
	  <div id="flugzeuge" class="col s11 m11 customPanel">
		    <div class="card horizontal">
		      <div class="card-stacked">
		        <div class="card-content">
		          <p>I am a very simple card. I am good at containing small bits of information.</p>
		        </div>
		      </div>
		    </div>
	  </div>
	  
	    <div id="relationen" class="col s11 m11 customPanel">
		    <div class="card horizontal">
		      <div class="card-stacked">
		        <div class="card-content">
		          <p>I am a very simple card. I am good at containing small bits of information.</p>
		        </div>
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