<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.model.User" %>
<%@ page import="com.fis.services.*" %>
<%@ page import="com.fis.model.*" %>
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
			   $('.timepicker').pickatime({
			    default: 'now', 
			    fromnow: 0,      
			    twelvehour: false,
			    donetext: 'OK',
		        format: "HH:ii:SS",
		        cleartext: 'Clear',
			    canceltext: 'Cancel',
			    autoclose: false,
			    ampmclickable: true,
			    aftershow: function(){}
			  });
		 });
	    $('.timepicker').on('change', function() {
	        let receivedVal = $(this).val();
	        $(this).val(receivedVal + ":00");
	    });
	 </script>

	 <title>Fluginformationssystem (FIS) - Manageransicht</title>
	 
</head>

<body>

<% 

	new Redirection().checkDirection(session, response, "Manager");
	FlugDao flugDao = new FlugDao(response.getWriter());
	FlugzeugDao flugzeugDao = new FlugzeugDao(response.getWriter());
	FlughafenDao flughafenDao = new FlughafenDao(response.getWriter());
	GebuchteFlügeDao flügeDao = new GebuchteFlügeDao(response.getWriter());

	if(request.getParameter("submitFlugAdd") != null) {		
		Flug flug = flugDao.generateFlug(
		request.getParameter("flugNr"),
		request.getParameter("flugzeug"),
		request.getParameter("flugDistanz"),
		request.getParameter("landeZeit"),
		request.getParameter("startOrt"),
		request.getParameter("startZeit"),
		request.getParameter("zielOrt"));					
		flugDao.create(flug);
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
			          					<select id="flugzeuge" name="flugzeug" class="validate" required>
			          					    <option disabled selected value> -- Bitte wählen Sie ein Flugzeug aus -- </option>
			          						<%
							        		  	for(Flugzeuge f : flugzeugDao.listAllFlugzeuge()) {
							        		  		out.println("<option value='" + f.getId() +"'>" + f.getHersteller() + " | " + f.getType() + " | (" + f.getSitze() + ")</option>");    
							        		  	}
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
			          				  	 	List<Flughäfen> flughäfenList = flughafenDao.listAllFlughäfen();
			          						for(Flughäfen f : flughäfenList) {
		          								out.println("<option value='" + f.getId() + "'>" + f.getBezeichnung() + "</option>");
			          				  	 	}
			          				  	 %>
			          				  </select>
							          <label for="startOrt">Startort</label>
							        </div>
							        <div class="input-field col s6">
							          <select id="zielOrt" name="zielOrt" class="validate" required>
							          	 <option disabled selected value>Bitte wählen Sie einen Zielflughafen aus</option>
							          	 <%
							          	 	for(Flughäfen f : flughäfenList) {
		          								out.println("<option value='" + f.getId() + "'>" + f.getBezeichnung() + "</option>");
							          	 	}
							          	 %>
							          </select>
	    							  <label for="zielOrt">Zielort</label>
							        </div>
			          			</div>
			          			<div class="row">
			          				<div class="input-field col s6">
			          					<input id="startZeit" name="startZeit" class="timepicker validate" required>
			          					<label for="startZeit">Startzeit</label>          			
			          				</div>
			          				<div class="input-field col s6">
			          					<input id="landeZeit" name="landeZeit" class="timepicker validate" required>
			          					<label for="landeZeit">Landezeit</label>          			
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
		          	for(GebuchteFlüge gF : flügeDao.listAllFlughäfen()) {
		          		out.println("<tr>");
						out.println("<td>" + gF.getFlugLinie() + "</td>");
						out.println("<td>" + gF.getHersteller() + " - " + gF.getType() + "</td>");
						out.println("<td>" + gF.getStartOrt() + "</td>");
						out.println("<td>" + gF.getZielOrt() + "</td>");
						out.println("<td>" + gF.getAuslastung() + " / " + gF.getKapazität() + "</td>");
						out.println("<td>" + gF.getFlugZeit() + "h</td>");
						out.println("<td>" + gF.getDistanz() + "km</td>");
						out.println("</tr>");
		          	}
		          %>
		        </tbody>
		      </table>
		  	</div>
		  </div>
	  </div>

</body>
</html>