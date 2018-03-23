<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.services.FlughafenDao" %>
<%@ page import="com.fis.services.RelationenDao" %>
<%@ page import="com.fis.model.User" %>
<%@ page import="com.fis.model.Flughäfen" %>
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
		FlughafenDao flughafenDao = new FlughafenDao(response.getWriter());
		RelationenDao relationenDao = new RelationenDao(response.getWriter());
		
		if(request.getParameter("submitFlughafenAdd") != null) flughafenDao.createFlughafen(request.getParameter("flughafenBezeichnung"));
		if(request.getParameter("submitRelationAdd") != null) { relationenDao.createRelation(Integer.parseInt(request.getParameter("startOrt")), Integer.parseInt(request.getParameter("landeOrt"))); }		
		
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
			    		<div class="input-field col s9">
			    			<input id="flughafen" name="flughafenBezeichnung" type="text" class="validate" required>
			    			<label for="flughafen">Flughafen</label>
			    		</div>
			    		<div class="input-field col s3">
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
				        			List<Flughäfen> flughäfenList = flughafenDao.listAllFlughäfen();
				        			for(Flughäfen f : flughäfenList) {
	      								out.println("<option value='" + f.getId() + "'>" + f.getBezeichnung() + "</option>");
				        			}
				        		%>
				        	</select>
				        	<label for="startOrt">Startort</label>
				        </div>
				        <div class="input-field col s6">
				        	<select id="landeOrt" name="landeOrt">
				        		<option disabled selected value>Bitte wählen Sie einen Zielflughafen aus</option>
				        		<%
				        			for(Flughäfen f : flughäfenList) {
	      								out.println("<option value='" + f.getId() + "'>" + f.getBezeichnung() + "</option>");
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
  		<div class="col s6"></div>
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
						int i = 1;
	  					for(Object[] obj : relationenDao.listRelationen()) {
					    	out.println("<tr>");
					    	out.println("<td>" + i + "</td>");
					    	out.println("<td>" + obj[0] +  "</td>");
					    	out.println("<td>" + obj[1] +  "</td>");
					    	out.println("</tr>");
					    	i++;
	  					}
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