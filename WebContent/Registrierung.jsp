<%@ page import="com.fis.de.Verification"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Compiled and minified CSS -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
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

<title>Fluginformationssystem (FIS)</title>
</head>
<body>

	<% 
	if(request.getParameter("register") != null) {
		Verification verification = new Verification(response.getWriter());
		verification.register(response.getWriter(), request.getParameter("username"), request.getParameter("userType"), request.getParameter("passwort"));
	}
%>

	<div class="row" style="margin-top: 100px;">

		<div class="col s4"></div>

		<div class="col s4">

			<div class="card-panel" style="padding-bottom: 5px;">

				<form action="<% out.println(request.getRequestURL());%>"
					method="POST">

					<div class="row">
						<div class="input-field col m11">
							<i class="material-icons prefix">account_circle</i> <input
								id="username" name="username" type="text" class="validate"
								required> <label for="username">Zugangsname</label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col m11">
							<i class="material-icons prefix">account_circle</i> <select
								id="userType" name="userType" required>
								<option disabled selected value>-- Bitte w�hlen Sie
									eine Benutzerart aus --</option>
								<option value="Mitarbeiter">Mitarbeiter</option>
								<option value="Manager">Manager</option>
							</select> <label for="userType">Benutzerart</label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col m11">
							<i class="material-icons prefix">account_circle</i> <input
								id="passwort" name="passwort" type="password" class="validate"
								required> <label for="passwort">Passwort</label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col">
							<button class="btn waves-effect waves-light" type="submit"
								name="register">Absenden</button>
						</div>
				</form>
				<div class="input-field col">
					<a href="MainLogin.xhtml"><button type="button"
							class="btn waves-effect waves-light">Zum Login</button></a>
				</div>
			</div>


		</div>

	</div>

	<div class="col s4"></div>


	</div>

</body>
</html>