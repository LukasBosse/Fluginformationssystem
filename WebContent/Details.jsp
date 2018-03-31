<%@ page import="com.fis.controller.FlugController" %>
<%@ page import="com.fis.model.DetailFlug" %>
<%	
	String flugNr = request.getParameter("flugNr").trim();
	FlugController flugController = new FlugController();
	DetailFlug obj = flugController.findFlugByIdWithDetails(flugNr);
	if(obj != null) {
		out.println("<tr>" +
					"<td>" + obj.getFlugnr() + "</td>" +
					"<td>" + obj.getHersteller() + " - " + obj.getType() + "</td>" +
					"<td>" + obj.getStartOrt() + "</td>" +
					"<td>" + obj.getLandeOrt() + "</td>" +
					"<td>" + obj.getStartzeit() + "</td>" +
					"<td>" + obj.getLandezeit() + "</td>" +
					"<td>" + obj.getFlugzeit() + "</td>" +
					"<td>" + obj.getInklusiveMahlzeit() + "</td>" +
					"</tr>");
	} else {
		out.println("<tr><td> - Es konnten keine Informationen zu diesem Flug gefunden werden. - </td></tr>");
	}
%>	 