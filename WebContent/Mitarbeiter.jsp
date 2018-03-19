<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.fis.de.Redirection" %>
<%@ page import="com.fis.de.User" %>
<%@ page import="com.fis.de.Verification" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 

 <!-- Compiled and minified CSS -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
  <link rel="stylesheet" href="assets/css/main.css">

<title>Fluginformationssystem (FIS) - Mitarbeiteransicht</title>
</head>
<body>

<% new Redirection().checkDirection(session, response, "Mitarbeiter");%>

<h1>Hallo <% if(session.getAttribute("user") != null) out.println(((User) session.getAttribute("user")).getUsername()); %></h1>

  <div class="row">
    <div class="col s11">
      <ul class="tabs">
        <li class="tab col s4"><a class="active" href="#fluege">Flüge</a></li>
        <li class="tab col s4"><a href="#flugzeuge">Flugzeuge</a></li>
        <li class="tab col s4"><a href="#relationen">Relationen</a></li>
      </ul>
    </div>
    <div class="col s1"><ul><li><a class="waves-effect waves-light btn" style="height: 45px;line-height: -moz-block-height;" href="Logout.jsp">Logout</a></li></ul></div>
     
	    <div id="fluege" class="col s11 m11 customPanel">
		    <div class="card horizontal">
		      <div class="card-stacked">
		        <div class="card-content">
		          <p>I am a very simple card. I am good at containing small bits of information.</p>
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