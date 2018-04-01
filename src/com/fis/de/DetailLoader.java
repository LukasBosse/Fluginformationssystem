/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.de;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fis.controller.FlugController;
import com.fis.dto.DetailFlug;

/**
 * Servlet implementation class DetailLoader
 */
@WebServlet("/DetailLoader")
public class DetailLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailLoader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("flugNr");
		if(id == null || "".equals(id)) return;
		FlugController flugController = new FlugController();
		DetailFlug obj = flugController.findFlugByIdWithDetails(id);
		if(obj != null) {
			response.getWriter().write("<tr>" +
						"<td>" + obj.getFlugnr() + "</td>" +
						"<td>" + obj.getHersteller() + " - " + obj.getType() + "</td>" +
						"<td>" + obj.getStartOrt() + "</td>" +
						"<td>" + obj.getLandeOrt() + "</td>" +
						"<td>" + obj.getStartzeit() + "</td>" +
						"<td>" + obj.getLandezeit() + "</td>" +
						"<td>" + obj.getFlugzeit() + " h</td>" +
						"<td>" + obj.getKm() + " km</td>" +
						"<td><a href='Buchungen.jsp?flugNr=" + obj.getFlugnr() + "' class='btn modal-trigger'>Buchen</a></td>" + 
						"</tr>");
		} else {
			response.getWriter().write("<tr><td> - Es konnten keine Informationen zu diesem Flug gefunden werden. - </td></tr>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
