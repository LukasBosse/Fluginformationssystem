<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="com.fis.de.DatabaseConnection"%>
<%@ page import="com.fis.de.HTMLWriter"%>
<%@ page import="com.fis.de.Redirection"%>
<%@ page import="com.fis.model.User" %>
<%@ page import="com.fis.de.Verification"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Compiled and minified CSS -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/main.css">


<!-- Compiled and minified JavaScript -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<script>
		 $(document).ready(function() {
			   $('select').material_select();
		 });
	 </script>

<title>Fluginformationssystem (FIS) - Mitarbeiteransicht -
	Buchungen</title>

</head>
<body>

	<% 	
  	new Redirection().checkDirection(session, response, "Mitarbeiter");
  	HTMLWriter htmlWriter = new HTMLWriter(response.getWriter());
  	DatabaseConnection dbC = new DatabaseConnection();
  	ResultSet rs;	  	
  %>

	<nav>
	<div class="nav-wrapper">
		<ul class="right">
			<li><a href="Logout.jsp" class="logoutBtn"><i
					class="material-icons">exit_to_app</i></a></li>
		</ul>
		<ul id="nav-mobile" class="left hide-on-med-and-down">
			<li><a href="Manager.jsp"><i class="material-icons left">flight_takeoff</i>
					Flüge</a></li>
			<li class="active"><a href="Buchungen.jsp"><i
					class="material-icons left">border_color</i> Buchungen</a></li>
			<li><a href="Passagiere.jsp"><i class="material-icons left">person</i>
					Passagiere</a></li>
		</ul>
	</div>
	</nav>

	<div class="row">
		<div class="col s6">
			<div class="card">
				<div class="card-content">
					<div class="row">
						<form action="#" method="GET">
							<div class="input-field col s9">
								<select id="flugnr" name="flugnr">
									<option disabled selected value>-- Bitte wählen Sie eine Fluglinie aus --</option>
									<%
			  						dbC.connect();
			  						rs = dbC.executeQuery("SELECT flugnr FROM flug", null);
			  						while(rs.next()) {
			  							out.println("<option value='" + rs.getString("flugnr") + "'>" + rs.getString("flugnr") + "</option>");
			  						}
			  						dbC.disconnect();
			  					%>
								</select> <label for="flugnr">Flugnummer</label>
							</div>
							<div class="input-field col s3">
								<button class="btn waves-effect waves-light" type="submit"
									name="loadFlugDataByFlugNr">Suchen</button>
							</div>
						</form>
					</div>
					<%
						         	if(request.getParameter("loadFlugDataByFlugNr") != null && request.getParameter("flugnr") != null) {
						        	  	dbC.connect();
						        	 	rs = dbC.executeQuery("SELECT * FROM buchung WHERE flugnr = ?", new String[] {request.getParameter("flugnr")});
						        	 	int counter = 1;
						        	 	out.println("<div class='row'>" +
					          					"<div class='col s12'>" +
				  						"<table class='highlight centered'>" +
										        "<thead>" +
										          "<tr>" +
										              "<th>ID</th>" +
										              "<th>Flugnummer</th>" +
										              "<th>Name</th>" +
										              "<th>Ort</th>" +
					        					      "<th>Datum</th>" +
					        					      "<th>Bestätigt</th>" +
										          "</tr>" +
										        "</thead>" +
										        "<tbody id='flugData'>");
						        	 	while(rs.next()) {  	 		
						        	 		out.println("<tr>");
							        	 		out.println("<td>" + counter +"</td>");
							        	 		out.println("<td>" + request.getParameter("flugnr") +"</td>");
							        	 		out.println("<td>" + rs.getString("name") +"</td>");
							        	 		out.println("<td>" + rs.getString("ort") +"</td>");
							        	 		out.println("<td>" + rs.getDate("tag") +"</td>");
							        	 		out.println("<td><input type='checkbox' id='" + counter  +"'");
							        	 		if(rs.getBoolean("bestaetigt")) {
							        	 			out.println("checked");
							        	 		}
							        	 		out.println("><label for='" + counter + "'></label></td>");
						        	 		out.println("</tr>");
						        	 		counter++;
						        	 	}
						        	 	out.println("</tbody></table></div></div>");
						        	 	dbC.disconnect();
						          	}
						        	%>
				</div>
			</div>
		</div>

		<div class="col s6">
			<div class="card">
				<div class="card-content">
					<div class="row">
						<form action="#" method="GET">
							<div class="input-field col s9">
								<select id="user" name="user">
									<option disabled selected value>-- Bitte wählen Sie eine Kundennamen aus --</option>
									<%
			  						dbC.connect();
			  						rs = dbC.executeQuery("SELECT name FROM passagier", null);
			  						while(rs.next()) {
			  							out.println("<option value='" + rs.getString("name") + "'>" + rs.getString("name") + "</option>");
			  						}
			  						dbC.disconnect();
			  					%>
								</select> <label for="user">Kundenname</label>
							</div>
							<div class="input-field col s3">
								<button class="btn waves-effect waves-light" type="submit"
									name="loadFlugDataByUser">Suchen</button>
							</div>
						</form>
					</div>
					<%
						         	if(request.getParameter("loadFlugDataByUser") != null && request.getParameter("user") != null) {
						        	  	dbC.connect();
						        	 	rs = dbC.executeQuery("SELECT * FROM buchung WHERE name = ?", new String[] {request.getParameter("user")});
						        	 	int counter = 1;
						        	 	out.println("<div class='row'>" +
					          					"<div class='col s12'>" +
				  						"<table class='highlight centered'>" +
										        "<thead>" +
										          "<tr>" +
										              "<th>ID</th>" +
										              "<th>Flugnummer</th>" +
										              "<th>Name</th>" +
										              "<th>Ort</th>" +
					        					      "<th>Datum</th>" +
					        					      "<th>Bestätigt</th>" +
										          "</tr>" +
										        "</thead>" +
										        "<tbody id='flugData'>");
						        	 	while(rs.next()) {  	 		
						        	 		out.println("<tr>");
							        	 		out.println("<td>" + counter +"</td>");
							        	 		out.println("<td>" + rs.getString("flugnr") +"</td>");
							        	 		out.println("<td>" + rs.getString("name") +"</td>");
							        	 		out.println("<td>" + rs.getString("ort") +"</td>");
							        	 		out.println("<td>" + rs.getDate("tag") +"</td>");
							        	 		out.println("<td><input type='checkbox' id='" + counter  +"'");
							        	 		if(rs.getBoolean("bestaetigt")) {
							        	 			out.println("checked");
							        	 		}
							        	 		out.println("><label for='" + counter + "'></label></td>");
						        	 		out.println("</tr>");
						        	 		counter++;
						        	 	}
						        	 	out.println("</tbody></table></div></div>");
						        	 	dbC.disconnect();
						          	}
						        	%>
				</div>
			</div>
		</div>

	</div>

</body>
</html>